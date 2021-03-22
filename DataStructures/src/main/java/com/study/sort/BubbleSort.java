package com.study.sort;

import java.util.Random;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/22 10:08
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {3, 9, -1, 10, -2};

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt(800000);
        }
        // 测试时间
        System.out.println("start");
        long start = System.currentTimeMillis();
        // 冒泡排序
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序共消耗了 " + (end - start) / 1000 + " s"); // 9s
        System.out.println("end");

    }

    public static void bubbleSort(int[] arr) {
        // 冒泡排序的时间复杂度O(n^2)
        int temp; // 临时变量
        boolean flag = false; // 标识变量,表示是否进行过交换
        // 只需要冒泡数组长度-1趟,
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的大,则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            // 在这趟排序中没有进行一次交换,说明数组已经有序,直接退出.
            if (!flag) {
                break;
            } else {
                // 重置flag,进行下次判断
                flag = false;
            }
        }
    }
}
