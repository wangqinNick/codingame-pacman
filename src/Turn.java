import java.util.ArrayList;

public class Turn {
    protected ArrayList<Action> actions;

    public Turn() {
        this.actions = new ArrayList<>();
    }

    public void clear() {
        actions.clear();
    }

    public void add(Action action) {
        actions.add(action);
    }

    public void print(State state) {
        int num_actions = actions.size();
        for (int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            if (action == null) {

            } else {
                action.print(state, i == num_actions-1);
            }
        }
    }
}
