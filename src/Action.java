public class Action {
    protected ActionType actionType;
    protected int pacmanIndex;
    protected Point destination;

    public void move(int pacmanIndex, Point destination) {
        actionType = ActionType.MOVE;
        this.pacmanIndex = pacmanIndex;
        this.destination = destination;
    }

    public void print(State state, boolean isEnd) {
        Pacman pacman = state.players[0].pacmanArrayList.get(pacmanIndex);
        switch (actionType) {
            case MOVE:
                System.out.printf("MOVE %d %d %d|", pacman.pacId, destination.x, destination.y);
            default:
                break;
        }
        if (isEnd) System.out.println();
    }
}
