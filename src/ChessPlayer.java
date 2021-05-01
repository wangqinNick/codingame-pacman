import java.util.ArrayList;

public class ChessPlayer {
    protected int score;
    protected ArrayList<Pacman> pacmanArrayList;

    public ChessPlayer(int score, ArrayList<Pacman> pacmanArrayList) {
        this.score = score;
        this.pacmanArrayList = pacmanArrayList;
    }

    public ChessPlayer(ChessPlayer player) {
        this.score = player.score;
        this.pacmanArrayList = new ArrayList<>();
        for (Pacman pacman: player.pacmanArrayList) {
            this.pacmanArrayList.add(new Pacman(pacman));
        }
    }
}
