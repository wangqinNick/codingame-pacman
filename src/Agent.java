import java.util.ArrayList;
import java.util.Scanner;

public class Agent {
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