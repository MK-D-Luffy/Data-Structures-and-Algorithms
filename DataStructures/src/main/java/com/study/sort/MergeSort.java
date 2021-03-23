package com.study.sort;

import java.util.Random;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/23 10:47
 */
public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};

        int[] arr = new int[8000000];
        int[] temp = new int[arr.length];
        Random random = new Random();
        for (int i = 0; i < 8000000; i++) {
            arr[i] = random.nextInt(800000);
        }
        // 测试时间
        System.out.println("start");
        long start = System.currentTimeMillis();
        // 快速排序
        mergeSort(arr, 0, arr.length - 1, temp);
        long end = System.currentTimeMillis();
        System.out.println("排序共消耗了 " + (end - start) + " ms"); // 8w:12ms,800w:1.15s
        System.out.println("end");

    }

    // 分而治之
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 中间索引
            int mid = (left + right) / 2;
            // 向左递归进行分解
            mergeSort(arr, left, mid, temp);
            // 向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  用于临时中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left;     // 左边有序序列的初始索引
        int j = mid + 1;  // 右边有序序列的初始索引
        int t = 0;        // 指向temp数组的当前索引

        // (1)
        // 先将左右两边分别有序的数据按照规则填充到temp数组中
        // 直到左右两边的有序序列,有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        // (2)
        // 把有剩余数据一边的数据依次全部填充到temp
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        // (3)
        // 将temp填充到原来的arr中(注意:并不是每一次都拷贝所有)
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }

    }
}
