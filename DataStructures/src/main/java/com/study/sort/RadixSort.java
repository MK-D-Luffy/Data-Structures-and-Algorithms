package com.study.sort;

import java.util.Random;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/23 19:37
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53, 3, 542, 748, 14, 214};

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt(800000);
        }
        // 测试时间
        System.out.println("start");
        long start = System.currentTimeMillis();
        // 基数排序
        radixSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序共消耗了 " + (end - start) + " ms"); // 8w:42ms ,800w:400ms
        System.out.println("end");

    }

    // 基数排序
    public static void radixSort(int[] arr) {

        // 找到最大值的位数
        int max = arr[0]; // 假设第一位就是最大值
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 得到最大值的位数
        int maxLength = (max + "").length();

        // 定义一个二维数组,表示10个桶,每个桶就是一个一维数组
        // 说明
        // 1.二维数组包含10个一维数组
        // 2.为了防止放入数的时候,下标越界,将每个桶的大小定为arr.length
        // 3.基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        // 为了记录每个桶实际存放了多少个数据,我们定义一个一维数组来记录各个桶每一轮实际放入的数据个数
        int[] bucketElementCounts = new int[10];
        for (int k = 0; k < maxLength; k++) {
            for (int i = 0; i < arr.length; i++) {
                // 取出每个元素对应位的值(个,十,百,千位)
                int num = arr[i] / (int) Math.pow(10, k) % 10;
                // 将当前这个值,放到对应的桶中,桶的编号为该值,桶中的位置由bucketElementCounts控制
                bucket[num][bucketElementCounts[num]] = arr[i];
                bucketElementCounts[num]++;
            }

            // 用于将桶中的数据重新放回数组中所使用的索引
            int index = 0;
            // 遍历每一个桶,如果桶中有数据,就将其依次放回原数组中
            for (int i = 0; i < bucketElementCounts.length; i++) {
                if (bucketElementCounts[i] != 0) {
                    for (int j = 0; j < bucketElementCounts[i]; j++) {
                        arr[index] = bucket[i][j];
                        index++;
                    }
                    // 将对应第i个bucket中的bucketElementCounts的值重新置0
                    bucketElementCounts[i] = 0;
                }
            }
        }
    }
}
