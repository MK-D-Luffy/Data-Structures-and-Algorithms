package com.study.sort;

import java.util.Random;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/22 16:23
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt(800000);
        }
        // 测试时间
        System.out.println("start");
        long start = System.currentTimeMillis();
        // 选择排序
        insertSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序共消耗了 " + (end - start) + " ms"); // 523ms
        System.out.println("end");

    }

    // 插入排序
    public static void insertSort(int[] arr) {

        int insertVal;
        int insertIndex;

        // 将数组分为两个表,有序表和无序表
        // 遍历无序表,将其与有序表进行比较,确定其插入的位置
        for (int i = 1; i < arr.length; i++) {
            // 需要进行插入的数(无序表中的数)
            insertVal = arr[i];
            // 有序表的最后一个数
            insertIndex = i - 1;

            // 给insertVal找到插入的位置
            // 说明:
            // 1. insertIndex >= 0 保证在给 insertVal 找插入位置时,不越界
            // 2. insertVal < arr[insertIndex] 待插入的数,还没有找到插入位置
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            // 插入
            // 当退出while循环时,说明插入位置已经找到, insertIndex+1
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

        }
    }
}
