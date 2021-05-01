public class Point {
    protected int x;
    protected int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point point) {
        return this.x == point.x && this.y == point.y;
    }

    public int manhattanDistance (Point p) {
        return Math.abs(x - p.x) + Math.abs(y - p.y);
    }
}
