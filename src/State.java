import java.util.ArrayList;

public class State {
    protected ChessPlayer [] players;
    protected Map map;
    protected ArrayList<Pallet> pallets;

    protected Graph graph;

    public State() {
        players = new ChessPlayer[2];
        map = new Map();
        pallets = new ArrayList<>();
        graph = new Graph();
    }

    public State(Map map, Graph graph) {
        players = new ChessPlayer[2];
        this.map = map;
        pallets = new ArrayList<>();
        this.graph = graph;
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

    public ArrayList<Pallet> getSuperPallets() {
        ArrayList<Pallet> superPallets = new ArrayList<>();
        for (Pallet pallet: pallets) {
            if (pallet.value != 10) continue;
            superPallets.add(pallet);
        }
        return superPallets;
    }
}
