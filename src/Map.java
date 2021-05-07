import java.util.ArrayList;

class Map {
    protected int width;
    protected int height;
    protected int[][] data;

    public Map() {
    }

    public Map(Map map) {
        this.width = map.width;
        this.height = map.height;
        this.data = new int[height][width];
        for (int i = 0; i < width; i++) {  // i -> x
            for (int j = 0; j < height; j++) {  // j -> y
                this.data[j][i] = map.data[j][i];
            }
        }
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

    public boolean isPellet(Point point) {
        /*
        Return true if the point is not a wall nor a empty path
         */
        int value = data[point.y][point.x];
        return value != ConstantFiled.WALL_VALUE && value != ConstantFiled.EMPTY_VALUE;
    }

    public int getValue(Point point) {
        return data[point.y][point.x];
    }

    public void eatPellet(Point point) {
        data[point.y][point.x] = ConstantFiled.EMPTY_VALUE;
    }

    public boolean isWall(Point point) {
        return data[point.y][point.x] == ConstantFiled.WALL_VALUE;
    }

    public void updatePacmanLocation(Pacman pacman) {
        data[pacman.point.y][pacman.point.x] = ConstantFiled.PACMAN_VALUE;
    }
}