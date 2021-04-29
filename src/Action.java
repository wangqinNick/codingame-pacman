public class Action {
    protected ActionType actionType;
    protected int pacmanIndex;
    protected Point destination;

    public void move(int pacmanIndex, Point destination) {
        actionType = ActionType.MOVE;
        this.pacmanIndex = pacmanIndex;
        this.destination = destination;
    }

    public void print() {
        
    }
}
