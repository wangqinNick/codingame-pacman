class Pacman {
    protected Point point;
    protected int pacId;  // id for the pacman, the id for the player's arraylist
    protected String typeId;
    protected int speedTurnsLeft;
    protected int abilityCoolDown;

    public Pacman(Point point, int pacId, String typeId, int speedTurnsLeft, int abilityCoolDown) {
        this.point = point;
        this.pacId = pacId;
        this.typeId = typeId;
        this.speedTurnsLeft = speedTurnsLeft;
        this.abilityCoolDown = abilityCoolDown;
    }
}