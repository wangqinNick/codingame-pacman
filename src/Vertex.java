import java.util.LinkedList;
import java.util.Objects;

class Vertex {
    protected Point point;
    private LinkedList<Vertex> adjacents;

    public Vertex(Point point) {
        this.adjacents = new LinkedList<>();
        this.point = point;
    }

    public void addAdjacent(Vertex adjacentVertex) {
        adjacents.add(adjacentVertex);
    }

    public LinkedList<Vertex> getAdjacents() {
        return this.adjacents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(adjacents, vertex.adjacents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adjacents);
    }
}