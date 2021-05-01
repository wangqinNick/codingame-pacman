import java.util.LinkedList;

public class Node {
    protected LinkedList<Node> neighbours;
    protected Point point;
    protected int value;
    protected Node parent;

    public Node(Point point, int value) {
        this.neighbours = new LinkedList<>();
        this.point = point;
        this.value = value;
        parent = null;
    }

    public void addNeighbour(Node node) {
        if (neighbours.contains(node)) return;  // avoid duplicated neighbour adding
        this.neighbours.add(node);
    }
}
