import java.util.*;

class Graph {
    HashMap<Integer, Node> nodeHashMap;

    public Graph() {
        this.nodeHashMap = new HashMap<>();
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

    public LinkedList<Node> getEdge(int key) {
        Node node = nodeHashMap.get(key);
        return node.neighbours;
    }

    public LinkedList<Node> getNeighbours(int key) {
        return nodeHashMap.get(key).neighbours;
    }

    public LinkedList<Point> breadthFirstSearch(int keyStart, int keyEnd) {
        Queue<Node> frontier = new LinkedList<>();
        Set<Integer> explored = new HashSet<>();
        LinkedList<Point> solution = new LinkedList<>();

        Node startNode = nodeHashMap.get(keyStart);
        frontier.add(startNode);
        while (!frontier.isEmpty()) {
            Node node = frontier.poll();  // 返回第一个元素，并在队列中删除
            if (node.value == keyEnd) {  // Found the end
                solution.add(node.point);
                while (node.parent != null) {
                    node = node.parent;
                    solution.add(node.point);
                }
                return solution;
            }
            if (!explored.contains(node.value)) {  // not explored yet
                explored.add(node.value);
                LinkedList<Node> neighbours = node.neighbours;
                for (Node childNode: neighbours) {
                    childNode.parent = node;
                    frontier.add(childNode);
                }
            }
        }
        return null;
    }
}
