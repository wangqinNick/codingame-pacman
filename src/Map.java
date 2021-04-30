import java.util.ArrayList;

class Map {
    protected int width;
    protected int height;
    protected int[][] data;

    public Map() {
    }

    public void read(int width, int height, ArrayList<String> stringMap) {
        this.width = width;
        this.height = height;
        data = new int[height][width];
        for (int i = 0; i < stringMap.size(); i++) {
            String row = stringMap.get(i);
            for (int j = 0; j < row.length(); j++) {
                char c = row.charAt(j);
                switch (c) {
                    case ConstantFiled.WALL:
                        data[i][j] = ConstantFiled.WALL_VALUE;
                        break;
                    case ConstantFiled.EMPTY:
                        data[i][j] = ConstantFiled.EMPTY_VALUE;
                        break;
                    default:
                        assert false: "Invalid map input";
                }
            }
        }
    }

    public void updatePallets(int x, int y, int value) {
        data[y][x] = value;
    }
}