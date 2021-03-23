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
        System.out.println("排序共消耗了 " + (end - start) + " ms"); // 8w:32ms,800w:1s
        System.out.println("end");

    }

    public static void quickSort(int[] arr, int left, int right) {
        // 分区
        int temp;
        int l = left; // 左下标
        int r = right; // 右下标
        // 中轴值
        int pivot = arr[(left + right) / 2];
        while (l < r) {
            // 找到左边比大于等于pivot的数
            while (arr[l] < pivot) {
                l++;
            }
            // 找到右边比小于等于pivot的数
            while (arr[r] > pivot) {
                r--;
            }
            // 如果 l>=r 说明两边已经遍历完了,没有找到符合条件的数,说明已经有序,则退出while循环.
            if (l >= r) {
                break;
            }
            // 如果没退出,则进行交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完后满足 arr[l] == pivot 说明r的右边已经都是符合条件的了,
            // 此时[l,r)之间的数还没有遍历到,所以将r左移,继续循环,直到遍历完,退出程序.
            if (arr[l] == pivot) {
                r--;
            }
            // 如果交换完后满足 arr[r] == pivot 说明l的左边已经都是符合条件的了,
            // 此时(l,r]之间的数还没有遍历到,所以将l右移,继续循环,直到遍历完,退出程序.
            if (arr[r] == pivot) {
                l++;
            }
        }

        if (l == r) {
            l++;
            r--;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 向右递归
        if (l < right) {
            quickSort(arr, l, right);
        }
    }

}
