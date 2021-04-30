class Player {
    protected Agent agent;

    public static void main(String args[]) {
        Agent agent = new Agent();
        agent.readMap();
        // game loop
        while (true) {
            agent.readTurnInfo();
            agent.think();
            agent.print();
        }
    }
}