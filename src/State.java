public class State {
    protected ChessPlayer [] players;
    protected Map map;

    public State() {
        this.players = new ChessPlayer[2];
        this.map = new Map();
    }
}
