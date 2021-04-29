import java.util.ArrayList;

class State {
    protected ChessPlayer [] players;
    protected Map map;
    protected ArrayList<Pallet> pallets;

    public State() {
        this.players = new ChessPlayer[2];
        this.map = new Map();
        this.pallets = new ArrayList<>();
    }

    public State(Map map) {
        this.players = new ChessPlayer[2];
        this.map = map;
        this.pallets = new ArrayList<>();
    }

    public Pallet getClosestPallet(Point pacmanPoint) {
        /* Return the closest pallet to the pacman */
        double shortestDistance = Double.POSITIVE_INFINITY;
        Pallet closestPallet = null;

        double distance;
        for (Pallet pallet: pallets) {
            distance = pallet.point.manhattanDistance(pacmanPoint);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                closestPallet = pallet;
            }
        }
        return closestPallet;
    }
}