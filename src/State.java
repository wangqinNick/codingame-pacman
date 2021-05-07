import java.util.ArrayList;
import java.util.LinkedList;

class State {
    protected ChessPlayer [] players;
    protected Map map;
    protected ArrayList<Pallet> pallets;
    protected Graph graph;
    protected int remainScore;

    public State() {
        this.players = new ChessPlayer[2];
        this.map = new Map();
        this.pallets = new ArrayList<>();
        this.graph = new Graph();
        this.remainScore = 0;
    }

    public State(Map map, Graph graph) {
        this.players = new ChessPlayer[2];
        this.map = map;
        this.pallets = new ArrayList<>();
        this.graph = new Graph(graph);
        this.remainScore = 0;
    }

    // Constructs a new state independent to the old state
    public State(State state) {
        this.players = new ChessPlayer[2];
        this.players[0] = new ChessPlayer(state.players[0]);
        this.players[1] = new ChessPlayer(state.players[1]);
        this.map = new Map(state.map);
        this.pallets = new ArrayList<>();
        for (Pallet pallet: state.pallets) {
            this.pallets.add(new Pallet(pallet));
        }
        this.graph = new Graph(state.graph);  // Todo: deepcopy graph (its hashmap)
        this.remainScore = 0;
    }

    public void countRemainingScore() {
        this.remainScore = 0;
        for (Pallet pallet: pallets) {
            this.remainScore += pallet.value;
        }
    }

    public int generateKey(Point point){
        return point.x + point.y * map.width;
    }

    private void initGraph() {
        graph = new Graph();
        for (int i = 0; i < map.height; i++) {  // y
            for (int j = 0; j < map.width; j++) {  // x
                Point point = new Point(j, i);
                int key = generateKey(point);
                graph.add(point, key);
            }
        }
    }

    private void createEdges() {
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
                }
            }
        }

        // Add Left / Right edges
        int leftKey;
        int rightKey;

        Point leftPoint;
        Point rightPoint;

        for (int i = 0; i < map.height; i++) {
            leftPoint = new Point(0, i);
            rightPoint = new Point(map.width-1, i);

            if (!map.isWall(leftPoint) && !map.isWall(rightPoint)) {
                leftKey = generateKey(leftPoint);
                rightKey = generateKey(rightPoint);
                graph.addEdge(leftKey, rightKey);
            }
        }
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

    public ArrayList<Point> getPacmanNextLegalPoints(int playerId, int pacmanId) {
        /*
        playerId: 0 for me, 1 for opponent
         */
        try {
            Point currentPoint = players[playerId].pacmanArrayList.get(pacmanId).point;

            int currentKey = generateKey(currentPoint);
            LinkedList<Node> neighbourNodes = graph.nodeHashMap.get(currentKey).neighbours;
            ArrayList<Point> neighbourPoints = new ArrayList<>();
            for (Node node : neighbourNodes) {
                neighbourPoints.add(node.point);
            }
            return neighbourPoints;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    // Update the state (newState) according to the player's Pacman's new point
    public State getNextState(State state, int playerId, int pacmanId, Point newPoint) {
        State newState = new State(state);
        // Update player's score
        int valueChange = newState.map.getValue(newPoint);
        if (valueChange != ConstantFiled.WALL_VALUE && valueChange != ConstantFiled.EMPTY_VALUE) {
            newState.players[playerId].score += valueChange;
            // Update the map's pallet
            newState.map.eatPellet(newPoint);
            // Update the palletsList
            newState.pallets.removeIf(pallet -> pallet.point.equals(newPoint));
        }
        // Update player's pacman info
        // Position
        newState.players[playerId].pacmanArrayList.get(pacmanId).point = newPoint;
        return newState;
    }

    public boolean isWin(int playerId) {  // playerId: 0~1
        ChessPlayer myPlayer = players[playerId];
        ChessPlayer opponent = players[1-playerId];
        return myPlayer.score > remainScore + opponent.score;
    }

    public boolean isLose(int playerId) {  // playerId: 0~1
        ChessPlayer myPlayer = players[playerId];
        ChessPlayer opponent = players[1-playerId];
        return myPlayer.score + remainScore < opponent.score;
    }
}