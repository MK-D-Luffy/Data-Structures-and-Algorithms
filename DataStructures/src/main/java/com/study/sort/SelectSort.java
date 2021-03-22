package com.study.sort;

import java.util.Random;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/22 14:40
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, -3, 50};

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt(800000);
        }
        // 测试时间
        System.out.println("start");
        long start = System.currentTimeMillis();
        // 选择排序
        selectSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序共消耗了 " + (end - start) / 1000 + " s"); // 1s
        System.out.println("end");

    }

    public static void selectSort(int[] arr) {
        // 选择排序时间复杂度也是O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[minIndex];
            // 获取到数组中最小的值并与第i个进行交换
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            // 交换,如果最小值就是当前位置,则不进行交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
