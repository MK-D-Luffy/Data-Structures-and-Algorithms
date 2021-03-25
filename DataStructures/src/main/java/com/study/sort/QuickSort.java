package com.study.sort;

import java.util.Random;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/22 21:01
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt(800000);
        }
        // 测试时间
        System.out.println("start");
        long start = System.currentTimeMillis();
        // 快速排序
        quickSort(arr, 0, arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("排序共消耗了 " + (end - start) + " ms"); // 8w:16ms,800w:720ms
        System.out.println("end");

    }

    // 快速排序
    public static void quickSort(int[] arr, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(arr, lo, hi);    // 切分
        quickSort(arr, lo, j - 1);      // 将左半部分排序
        quickSort(arr, j + 1, hi);      // 将右半部分排序

    }

    // 快速排序的切分
    public static int partition(int[] arr, int lo, int hi) {
        // 将数组切分伟a[lo...i-1],a[i],a[i+1...hi]
        int i = lo, j = hi + 1; // 左右扫描指针
        int v = arr[lo];        // 切分元素
        while (true) {
            // 先将i后移再比较,是因为基准值设在了第一个
            while (arr[++i] < v) {
                // 从左向右扫描,扫描到数组最后一个,说明数组已经全部有序,则退出
                if (i == hi) {
                    break;
                }
            }
            // 先将j前移再比较,是因为j在初始化的时候是最后一个的后一个
            while (arr[--j] > v) {
                // 从右向左扫描,扫描到数组第一个,说明数组已经全部有序,则退出
                if (lo == j) {
                    break;
                }
            }
            // 左右扫描后(left,i)和(j,right)已经有序,
            // 只要再将已经有序中间位置与基准位置交换就行了.
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
        }
        // 循环结束说明:
        // 在最后一次循环中j会前移,i会后移,
        // j前移后还是小于基准值的,而i后移会变成大于基准值的一部分
        // 所以选j不选i
        swap(arr, lo, j);

        return j;
    }

    // 通过下标交换数组的元素
    public static void swap(int[] arr, int i, int j) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
