package com.study.sparsearray;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/16 15:53
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建一个原始的二维数组 11*11
        // 0:表示没有棋子 1:表示黑子 2:表示蓝子
        int[][] chessArray1 = new int[11][11];
        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;

        // 输出原始的二维数组
        System.out.println("原始的二维数组为:");
        for (int[] ints : chessArray1) {
            for (int data : ints) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 二维数组转成稀疏数组
        // 1.先遍历二维数组得到sum的值
        int sum = 0;
        for (int[] ints : chessArray1) {
            for (int data : ints) {
                if (data != 0) {
                    sum++;
                }
            }
        }

        // 2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 3.遍历二维数组将非0的数放入稀疏数组中
        int count = 1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArray1[i][j] != 0) {
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArray1[i][j];
                    count++;
                }
            }
        }

        // 输出为稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组是:");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }


        // 稀疏数组转二维数组
        // 1.先读取稀疏数组的第一行根据第一行穿件二维数组
        int[][] chessArray2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        // 2.读取稀疏数组后几行的数据(从第二行开始),并赋值给原始的二维数组即可
        for (int i = 1; i < sparseArr.length; i++) {
            chessArray2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] ints : chessArray2) {
            for (int data : ints) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
