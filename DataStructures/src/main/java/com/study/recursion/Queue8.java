package com.study.recursion;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/20 16:20
 */
public class Queue8 {

    // 定义一共有多少个皇后
    int max = 8;
    // 定义数组array,保存皇后放置的位置
    // 一维数组的下标表示第几行(第几个皇后),数组存放的值是对应的第几列
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d个结果\n", count);
        System.out.printf("一共判断冲突的次数%d次\n", judgeCount);
    }

    // 放置第0到n个皇后
    // 特别注意(★★★): check方法的每一次递归中都有for循环,如果冲突就更改到下一列,以此实现回溯.
    public void check(int n) {
        if (n == max) {
            print();
            return;
        }
        // 遍历n列
        for (int i = 0; i < max; i++) {
            // 将当前第n个皇后放到第i列上
            array[n] = i;
            // 如果放置了当前皇后后不冲突,
            if (judge(n)) {
                // 接着放第n+1个.
                check(n + 1);
            }
            // 如果冲突再次循环将当前皇后放的位置改变到下一列
        }
    }

    // 判断已经放置的n个皇后是否发生冲突
    public boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // 说明:
            // 1.array[n]==array[i]: 表示判断第n个皇后和第n-1个皇后是否在同一列
            // 2.Math.abs(n - i) == Math.abs(array[n] - array[i]):
            //   表示判断第n个皇后和第i个皇后是否在同一斜线
            if ((array[n] == array[i]) ||
                    (Math.abs(n - i) == Math.abs(array[n] - array[i]))) {
                return false;
            }
        }
        return true;
    }

    public void print() {
        count++;
        for (int i : array) {
            System.out.printf("%d  ", i);
        }
        System.out.println();
    }
}
