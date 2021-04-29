public class Pacman {
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
