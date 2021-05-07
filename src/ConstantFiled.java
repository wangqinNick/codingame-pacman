final class ConstantFiled {
    protected static final char WALL = '#';
    protected static final char EMPTY = ' ';
    protected static final int WALL_VALUE = -99999;
    protected static final int EMPTY_VALUE = 0;
    protected static final int PACMAN_VALUE = -1;
    protected static final int[] DIR_ROW = {1, -1, 0, 0}; // Up, Down, Left, Right
    protected static final int[] DIR_COL = {0, 0, -1, 1}; // Up, Down, Left, Right

    protected static final int MY_ID = 0;
    protected static final int OPPONENT_ID = 1;
    protected static final int MAX_DEPTH = 3;
}
