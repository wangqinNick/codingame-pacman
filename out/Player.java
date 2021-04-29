import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.ArrayList;

class Agent {
    protected Scanner in;
    protected State state;

    public Agent() {
        in = new Scanner(System.in);
        state = new State();
    }

    public void readMap() {
        /* Read the map */
        int width = in.nextInt();
        int height = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        ArrayList<String> stringMap = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            String row = in.nextLine();
            stringMap.add(row);
        }

        state.map.read(width, height, stringMap);
        /* End read the map */
    }

    public void readTurnInfo() {
        /* Read player */
        int myPlayerScore = in.nextInt();
        int opponentScore = in.nextInt();
        ArrayList<Pacman> myPacManArrayList = new ArrayList<>();
        ArrayList<Pacman> opponentPacManArrayList = new ArrayList<>();

        int visiblePacCount = in.nextInt(); // all your pacs and enemy pacs in sight
        for (int i = 0; i < visiblePacCount; i++) {
            int pacId = in.nextInt(); // pac number (unique within a team)
            boolean mine = in.nextInt() != 0; // true if this pac is yours

            int x = in.nextInt(); // position in the grid
            int y = in.nextInt(); // position in the grid
            String typeId = in.next(); // unused in wood leagues
            int speedTurnsLeft = in.nextInt(); // unused in wood leagues
            int abilityCoolDown = in.nextInt(); // unused in wood leagues
            // int x, int y, int pacId, Point point, String typeId, int speedTurnsLeft, int abilityCoolDown
            Pacman pacman = new Pacman(x, y, pacId, typeId, speedTurnsLeft, abilityCoolDown);

            if (mine) myPacManArrayList.add(pacman);
            else  opponentPacManArrayList.add(pacman);
        }
        ChessPlayer myPlayer = new ChessPlayer(myPlayerScore, myPacManArrayList);
        ChessPlayer opponent = new ChessPlayer(opponentScore, opponentPacManArrayList);

        state.players[0] = myPlayer;
        state.players[1] = opponent;
        /* End read player */

        /* Start read pallets */
        int visiblePelletCount = in.nextInt(); // all pellets in sight
        for (int i = 0; i < visiblePelletCount; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            int value = in.nextInt(); // amount of points this pellet is worth
            state.map.updatePallets(x, y, value);
        }
        /* End read pallets */
    }

    public void think() {
        System.out.println("MOVE 0 1 10");
    }
}
enum PointType {
    WALL(-1),
    EMPTY(0),
    PALLET(1);

    protected int value;

    PointType(int value) {
        this.value = value;
    }
}

class Map {
    protected int width;
    protected int height;
    protected int[][] map;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new int[height][width];
    }

    public Map() {
    }

    public void read(int width, int height, ArrayList<String> stringMap) {
        map = new int[height][width];
        for (int i = 0; i < stringMap.size(); i++) {
            String row = stringMap.get(i);
            for (int j = 0; j < row.length(); j++) {
                char c = row.charAt(j);
                switch (c) {
                    case ConstantFiled.WALL:
                        map[i][j] = ConstantFiled.WALL_VALUE;
                        break;
                    case ConstantFiled.EMPTY:
                        map[i][j] = ConstantFiled.EMPTY_VALUE;
                        break;
                    default:
                        assert false: "Invalid map input";
                }
            }
        }
    }

    public void updatePallets(int x, int y, int value) {
        map[y][x] = value;
    }
}
class Log {
    public static void log(String msg) {
        System.err.println(msg);
    }
}
final class ConstantFiled {
    protected static final char WALL = '#';
    protected static final char EMPTY = ' ';
    protected static final int WALL_VALUE = -99999;
    protected static final int EMPTY_VALUE = 0;
}

class Player {
    protected Agent agent;

    public static void main(String args[]) {
        Agent agent = new Agent();
        agent.readMap();
        // game loop
        while (true) {
            agent.readTurnInfo();
            agent.think();
        }
    }
}
class Pacman {
    protected int x;
    protected int y;
    protected int pacId;
    protected String typeId;
    protected int speedTurnsLeft;
    protected int abilityCoolDown;

    public Pacman(int x, int y, int pacId, String typeId, int speedTurnsLeft, int abilityCoolDown) {
        this.x = x;
        this.y = y;
        this.pacId = pacId;
        this.typeId = typeId;
        this.speedTurnsLeft = speedTurnsLeft;
        this.abilityCoolDown = abilityCoolDown;
    }
}
class Point {
    protected int x;
    protected int y;
    protected int value;

    public Point(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}

class ChessPlayer {
    protected int score;
    protected ArrayList<Pacman> pacmanArrayList;

    public ChessPlayer(int score, ArrayList<Pacman> pacmanArrayList) {
        this.score = score;
        this.pacmanArrayList = pacmanArrayList;
    }
}
class State {
    protected ChessPlayer [] players;
    protected Map map;

    public State() {
        this.players = new ChessPlayer[2];
        this.map = new Map();
    }
}
