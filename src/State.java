import java.util.ArrayList;

class State {
    protected ChessPlayer [] players;
    protected Map map;
    protected ArrayList<Pallet> pallets;
    protected Graph graph;

    public State() {
        this.players = new ChessPlayer[2];
        this.map = new Map();
        this.pallets = new ArrayList<>();
        this.graph = new Graph();
    }

    public State(Map map, Graph graph) {
        this.players = new ChessPlayer[2];
        this.map = map;
        this.pallets = new ArrayList<>();
        this.graph = new Graph(graph);
    }

    public int generateKey(Point point){
        return point.x + point.y * map.width;
    }

    private void initGraph() {
        Log.log("Start Graph Init");
        graph = new Graph();
        for (int i = 0; i < map.height; i++) {  // y
            for (int j = 0; j < map.width; j++) {  // x
                Point point = new Point(j, i);
                int key = generateKey(point);
                graph.add(point, key);
            }
        }
        Log.log("Graph Inited");
    }

    private void createEdges() {
        int edges = 0;
        Log.log("Start Graph Fullfill");
        for (int i = 0; i < map.height; i++) {  // y row
            for (int j = 0; j < map.width; j++) {  // x col
                if (map.data[i][j] == ConstantFiled.WALL_VALUE) continue; // ignore wall
                int key1 = generateKey(new Point(j, i));
                for (int k = 0; k < 4; k++) {  // Up, Down, Left, Right
                    int row_ = i + ConstantFiled.DIR_ROW[k];
                    int col_ = j + ConstantFiled.DIR_COL[k];
                    if (row_ < 0 || col_ < 0 || row_ >= map.height || col_ >= map.width) continue;  // Out of bound
                    if (map.data[row_][col_] == ConstantFiled.WALL_VALUE) continue;  // ignore wall
                    int key2 = generateKey(new Point(col_, row_));
                    graph.addEdge(key1, key2);
                    edges += 1;
                }
            }
        }

        Log.log(String.format("Graph Fullfilled, total %d edges", edges));
    }

    public void createGraph() {
        initGraph();
        createEdges();
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