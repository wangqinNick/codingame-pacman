import java.util.Objects;

class Pallet {
    protected int value;
    protected Point point;

    public Pallet(int value, Point point) {
        this.value = value;
        this.point = point;
    }

    public Pallet(Pallet pallet) {
        this.value = pallet.value;  // 1 or 10
        this.point = new Point(pallet.point.x, pallet.point.y);
    }

    public boolean equals(Pallet pallet) {
        return this.point.equals(pallet.point);
    }
}