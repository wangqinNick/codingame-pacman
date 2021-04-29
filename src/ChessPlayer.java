import java.util.ArrayList;

public class ChessPlayer {
    protected int score;
    protected ArrayList<Pacman> pacmanArrayList;

    public ChessPlayer(int score, ArrayList<Pacman> pacmanArrayList) {
        this.score = score;
        this.pacmanArrayList = pacmanArrayList;
    }
}
