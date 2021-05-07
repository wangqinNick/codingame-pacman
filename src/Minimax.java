import java.util.ArrayList;

public class Minimax {
    public double evaluateState(State state) {
        if (state.isWin(ConstantFiled.MY_ID)) return Double.POSITIVE_INFINITY;
        if (state.isLose(ConstantFiled.MY_ID)) return Double.NEGATIVE_INFINITY;
        return 0;  // Todo: evaluate the state
    }

    public boolean isTerminate(State state) {
        return state.isWin(ConstantFiled.MY_ID) || state.isLose(ConstantFiled.MY_ID);
    }

    public double min_value(State state, int depth, int pacId) {  // for opponent
        ChessPlayer myPlayer = state.players[ConstantFiled.MY_ID];
        ChessPlayer opponent = state.players[ConstantFiled.OPPONENT_ID];
        ArrayList<Integer> myPacIdList = new ArrayList<>();


        for (Pacman pacman: myPlayer.pacmanArrayList) {
            myPacIdList.add(pacman.pacId);
        }

        if (isTerminate(state)) return evaluateState(state);
        double v = Double.POSITIVE_INFINITY;
        ArrayList<Point> nextPoints = state.getPacmanNextLegalPoints(ConstantFiled.OPPONENT_ID, pacId);
        for (Point point: nextPoints) {
            if (pacId == opponent.pacmanArrayList.size()-1) {  // Last one
                v = Math.min(v, max_value(state.getNextState(state, ConstantFiled.OPPONENT_ID, pacId, point), depth+1, myPacIdList.get(0)));
            } else {
                v = Math.min(v, min_value(state.getNextState(state, ConstantFiled.OPPONENT_ID, pacId, point), depth, pacId+1));
            }
        }
        return v;
    }

    public double max_value(State state, int depth, int pacId) {
        ChessPlayer myPlayer = state.players[ConstantFiled.MY_ID];
        ChessPlayer opponent = state.players[ConstantFiled.OPPONENT_ID];
        ArrayList<Integer> hisPacIdList = new ArrayList<>();
        if (depth >= ConstantFiled.MAX_DEPTH) {
            return evaluateState(state);
        }

        for (Pacman pacman: opponent.pacmanArrayList) {
            hisPacIdList.add(pacman.pacId);
        }

        if (isTerminate(state)) return evaluateState(state);
        double v = Double.NEGATIVE_INFINITY;
        ArrayList<Point> nextPoints = state.getPacmanNextLegalPoints(ConstantFiled.MY_ID, pacId);
        for (Point point: nextPoints) {
            if (pacId == myPlayer.pacmanArrayList.size()-1) {  // Last one
                v = Math.max(v, min_value(state.getNextState(state, ConstantFiled.MY_ID, pacId, point), depth, hisPacIdList.get(0)));
            } else {
                v = Math.max(v, max_value(state.getNextState(state, ConstantFiled.MY_ID, pacId, point), depth, pacId+1));
            }
        }
        return v;

    }
}
