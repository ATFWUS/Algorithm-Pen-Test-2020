package 字节秋招笔试题;

import java.util.*;

/**
 * 3、走迷宫
 *
 * 【题目描述】
 *
 * 给定一个迷宫，找到最快从起点到达终点的路径所需要的步数。
 * 假设迷宫如下，假定左上角坐标为(0,0)，右下角坐标为 (3,2)
 * 1 0 -1 1
 * -2 0 -1 -3
 * 2 2 0 0
 * -2是迷宫的起点，坐标为(0,1)
 * -3是迷宫的终点，坐标为(3,1)
 * -1代表障碍物，不能行走
 * 1和2代表传送门，传送门由正整数标示，只会成对出现。站在传送门上，能仅用一步就传送到相同数字的另一个传送门的位置：1只能传送到1，2只能传送到2。站在传送门上也可以选择不传送。
 * 从起点到终点有若干种走法，举例如下：
 * (0,1)->(1,1)->(1,2)->(2,2)->(3,2)->(3,1)，共花费5步
 * 或者
 * (0,1)->(0,0)-传送>(3,0)->(3,1)，共花费3步
 * 经检验3步是所需的最少步数，最后结果返回3
 *
 * 输入描述
 * 每一行输入都是用空格隔开的整数
 * 第一行给出迷宫地图的长和宽，均为正整数
 * 之后每一行的每一个数字，都代表迷宫的一格
 * -2表示起点，-3表示终点，-1表示不可通过的障碍物，0表示可通过的道路，大于0的正整数代表传送门，并且保证成对出现，在传送门上，可以仅用一步传送到另一个相同数字的传送门的位置。
 *
 * 输出描述
 * 输出最少要多少步能够从起点走到终点。
 * 输出-1如果没有任何办法从起点走到终点。
 *
 * 备注
 * 迷宫大小<=200*200
 *
 * 示例1：
 * 输入：
 * 4 3
 * 1 0 -1 1
 * -2 0 -1 -3
 * 2 2 0 0
 *
 * 输出：
 * 3
 *
 * 说明：
 * (0,1)->(0,0)-传送>(3,0)->(3,1) ，共花费3步
 *
 * 解决思路：
 * BFS
 * 大体思想：每一个点封装成类，传送门封装成类，当前的状态也封装成类，第一次到终点的步数就是最少的
 */
public class Solution3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //获取长宽
        String[] dimension = sc.nextLine().split(" ");
        int width = Integer.valueOf(dimension[0]);
        int height = Integer.valueOf(dimension[1]);
        //迷宫
        Square[][] map = new Square[width][height];
        //一个y对应底图的一列
        int y = 0;
        //起始点和终点
        Square start = null;
        Square end = null;
        //传送门对应的哈希表
        Map<Integer, WarpGate> warpGate = new HashMap<>();
        //获取地图，同时初始化相关的类
        while (y<height) {
            String line = sc.nextLine();
            String[] squares = line.split(" ");
            for (int x = 0; x < width; x++) {
                map[x][y] = new Square(x, y, Integer.valueOf(squares[x]));
                if (map[x][y].type == Square.SQUARE_START) {
                    start = map[x][y];
                } else if (map[x][y].type == Square.SQUARE_END) {
                    end = map[x][y];
                } else if (map[x][y].isWarpGate()) {
                    //建立传送门
                    int gateNumber = map[x][y].type;
                    WarpGate wg = warpGate.get(gateNumber);
                    if (wg == null) {
                        wg = new WarpGate();
                        warpGate.put(gateNumber, wg);
                    }
                    wg.buildWarpGate(map[x][y]);
                }
            }
            y++;
        }
        if (start == null || end == null) {
            throw new RuntimeException("未发现起始点或者终点！");
        }
        //迷宫创建成功，开始运算
        System.out.println(solve(start, end, map, warpGate));
    }
    //走迷宫核心代码
    public static int solve(Square start, Square end, Square[][] map, Map<Integer, WarpGate> warpGate) {
        //标志是否访问过
        Set<State> visited = new HashSet<>();
        //BFS队列
        Set<State> queue = new LinkedHashSet<>();
        //放入起始点
        queue.add(new State(start, 0));

            while(!queue.isEmpty()) {
                Iterator<State> currentIt = queue.iterator();
                //获取当前当前走的点
                State current = currentIt.next();
                currentIt.remove();
                //走到终点
                if (current.square.equals(end)) {
                    return current.step;
                }
                //更新标志
                visited.add(current);
                //寻找下一个要走的点
                current.nextStates(map, queue, visited, warpGate);
        }
        return -1;
    }
    //迷宫中的一个点
    public static class Square {
        public final int x;
        public final int y;
        public final int type;//这个点的类型

        //题目中可能的类型
        public static final int SQUARE_START = -2;
        public static final int SQUARE_END = -3;
        public static final int SQUARE_PATH = 0;
        public static final int SQUARE_BLOCK = -1;

        public Square(int x,int y,int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }

        //提供专门判断是否是传送门的方法
        public boolean isWarpGate() {
            return type > 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    //传送门类
    public static class WarpGate{
        public Square s1;
        public Square s2;

        //获取传送到的点，不是s1或者s2就返回null
        public Square getOtherSide(Square s) {
            if (s.equals(s1)) {
                return s2;
            } else if (s.equals(s2)) {
                return s1;
            } else {
                return null;
            }
        }

        //建立一个传送门
        public void buildWarpGate(Square s) {
            if (s1 == null) {
                s1 = s;
            } else if (s2 == null) {
                s2 = s;
            } else {
                throw new RuntimeException("建立传送门异常！");
            }
        }
    }
    //一个点的状态
    public static class State{
        public final Square square;
        public int step;

        public State(Square square, int step) {
            this.square = square;
            this.step = step;
        }

        //更新到下一个未访问过的点
        public void nextStates(Square[][] map, Set<State> toVisit,
                                        Set<State> visited, Map<Integer, WarpGate> warpGate){
            //左边的点存在，尝试加入队列
            if (square.x > 0) {
                Square left = map[square.x - 1][square.y];
                addUnvisitedStateToList(left, toVisit, visited);
            }
            //右边的点存在，尝试加入队列
            if (square.x < map.length - 1) {
                Square right = map[square.x + 1][square.y];
                addUnvisitedStateToList(right, toVisit, visited);
            }
            //下边的点存在，尝试加入队列
            if (square.y > 0) {
                Square up = map[square.x][square.y - 1];
                addUnvisitedStateToList(up, toVisit, visited);
            }
            //上边的点存在，尝试加入队列
            if (square.y < map[0].length - 1) {
                Square down = map[square.x][square.y + 1];
                addUnvisitedStateToList(down, toVisit, visited);
            }
            //尝试走传送门
            WarpGate gate = warpGate.get(square.type);
            if (gate != null) {
                addUnvisitedStateToList(gate.getOtherSide(square), toVisit, visited);
            }
        }
        private void addUnvisitedStateToList(Square s, Set<State> toVisit, Set<State> visited) {
            State resultState = new State(s, this.step + 1);
            //满足条件的点加入队列
            if (s.type != Square.SQUARE_BLOCK && !visited.contains(resultState) && !toVisit.contains(resultState)) {
                    toVisit.add(resultState);
            }
        }
    }
}
