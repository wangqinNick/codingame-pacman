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

    public Pacman(Pacman pacman) {
        this.point = new Point(pacman.point.x, pacman.point.y);
        this.pacId = pacman.pacId;
        this.typeId = pacman.typeId;
        this.speedTurnsLeft = pacman.speedTurnsLeft;
        this.abilityCoolDown = pacman.abilityCoolDown;
    }
}