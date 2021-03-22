package com.study.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/22 19:12
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt(800000);
        }
        // 测试时间
        System.out.println("start");
        long start = System.currentTimeMillis();
        // 希尔排序
//        shellSort(arr); // 交换法 5s
        shellSort2(arr); // 移位法 14ms
        long end = System.currentTimeMillis();
        System.out.println("排序共消耗了 " + (end - start) + " ms");
        System.out.println("end");

    }

    // 希尔排序-移位法
    public static void shellSort2(int[] arr) {
        int insertVal;
        int j;
        // 通过增量分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素开始,逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                // 对应组有序表中的最后一个数的后gap个数
                j = i;
                // 当前要插入的元素
                insertVal = arr[j];
                if (arr[j - gap] > insertVal) {
                    // 找到插入的位置j
                    while (j - gap >= 0 && arr[j - gap] > insertVal) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    // 实现插入
                    arr[j] = insertVal;
                }

            }

        }
    }

    // 希尔排序-交换法
    public static void shellSort(int[] arr) {
        int temp;

        // 分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 插入排序
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果前面的元素大于后面的元素则交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }
}

