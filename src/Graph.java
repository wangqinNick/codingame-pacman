import java.util.*;

class Graph {
    HashMap<Integer, Node> nodeHashMap;

    public Graph() {
        this.nodeHashMap = new HashMap<>();
    }

    public Graph(Graph prevGraph) {
        this.nodeHashMap = new HashMap<>();
        this.nodeHashMap.putAll(prevGraph.nodeHashMap);
    }

    public void clearDependencies() {
        nodeHashMap.forEach((k, v) -> v.parent = null);
    }

    public int size() {
        return nodeHashMap.size();
    }

    public void add(Point point, int key) {
        Node node = new Node(point, key);
        assert !nodeHashMap.containsKey(key): "Attempt to add a duplicated element";
        nodeHashMap.put(key, node);
    }

    public void addEdge(int key1, int key2) {
        Node node1 = nodeHashMap.get(key1);
        Node node2 = nodeHashMap.get(key2);
        node1.addNeighbour(node2);
        node2.addNeighbour(node1);
    }

    public LinkedList<Point> breadthFirstSearch(int keyStart, int keyEnd) {
        clearDependencies();
        Queue<Node> frontier = new LinkedList<>();
        HashSet<Integer> explored = new HashSet<>();
        LinkedList<Point> solution = new LinkedList<>();
        HashMap<Integer, Integer> sonToParent = new HashMap<>();

        Node startNode = nodeHashMap.get(keyStart);
        assert startNode != null: "Cannot find start node";
        frontier.offer(startNode);

        while (!frontier.isEmpty()) {
            Node node = frontier.remove();  // 返回第一个元素，并在队列中删除
            assert node != null: "All searched but sth is wrong";
            if (node.value == keyEnd) {  // Found the end
                for (int son = keyEnd; son!= keyStart; son = sonToParent.get(son)){
                    solution.addFirst(nodeHashMap.get(son).point);
                }
                return solution;

            } else if (!explored.contains(node.value)) {  // not explored yet
                // Log.log(String.format("Expanding Point %d %d, Value: %d", node.point.x, node.point.y, node.value));
                explored.add(node.value);
                for (Node childNode: node.neighbours) {
                    childNode.parent = node;
                    if (!explored.contains(childNode.value)) {
                        sonToParent.put(childNode.value, node.value);
                        frontier.add(childNode);
                    }
                }
            }
        }
        return null;
    }
}
