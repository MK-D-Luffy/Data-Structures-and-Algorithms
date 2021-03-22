package com.study.recursion;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/20 15:20
 */
public class Maze {
    public static void main(String[] args) {
        // 先创建一个二维数组,模拟迷宫
        // 地图
        int[][] map = new int[8][7];
        // 使用1表示墙
        // 初始化上下左右均为墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        // 打印地图
        System.out.println("地图的情况: ");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf("%d\t", map[i][j]);
            }
            System.out.println();
        }

        // 使用递归回溯给小球找路
        setWay(map, 1, 1);

        // 输出新的地图
        System.out.println("小球走过并标识过的地图的情况");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.printf("%d\t", map[i][j]);
            }
            System.out.println();
        }

    }

    // 使用递归回溯给小球找路
    // 说明:
    // 1.map表示地图
    // 2.i,j表示从地图哪个位置出发
    // 3.如果小球能找到map[6][5]位置,则说明通路找到.
    // 4.约定: map[i][j] 为 0:表示该点没有走过、1:表示墙、
    //                     2:表示通路可以走、3:表示该点已经走过,但走不通
    // 5.设置找路的策略为: 下->右->上->左
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {//map[i][j]为1,2,3时
                return false;
            }
        }
    }
}
