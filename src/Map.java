import java.util.ArrayList;

public class Map {
    protected int width;
    protected int height;
    protected int[][] map;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new int[height][width];
    }

    public Map() {
    }

    public void read(int width, int height, ArrayList<String> stringMap) {
        map = new int[height][width];
        for (int i = 0; i < stringMap.size(); i++) {
            String row = stringMap.get(i);
            for (int j = 0; j < row.length(); j++) {
                char c = row.charAt(j);
                switch (c) {
                    case ConstantFiled.WALL:
                        map[i][j] = ConstantFiled.WALL_VALUE;
                        break;
                    case ConstantFiled.EMPTY:
                        map[i][j] = ConstantFiled.EMPTY_VALUE;
                        break;
                    default:
                        assert false: "Invalid map input";
                }
            }
        }
    }

    public void updatePallets(int x, int y, int value) {
        map[y][x] = value;
    }
}
