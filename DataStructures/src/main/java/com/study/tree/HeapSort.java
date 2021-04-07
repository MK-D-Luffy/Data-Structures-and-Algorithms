package com.study.tree;

import java.util.Arrays;
import java.util.Random;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/4/3 9:41
 */
public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {4, 6, 8, 5, 9};
//        heapSort(arr);

        int[] arr = new int[80000];
        Random random = new Random();
        for (int i = 0; i < 80000; i++) {
            arr[i] = random.nextInt(800000);
        }
        // 测试时间
        System.out.println("start");
        long start = System.currentTimeMillis();
        // 堆排序
        heapSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序共消耗了 " + (end - start) + " ms"); // 8w:15ms ,800w:2s
        System.out.println("end");

        System.out.println("arr = " + Arrays.toString(arr));
    }

    // 堆排序
    public static void heapSort(int[] arr) {
        int temp;

        // 将无序序列构建成一共堆,根据升序降序要求选择大顶堆或小顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        // 将堆顶元素与末尾元素交换,将最大元素"沉"到数组末端
        // 重现调整结构,直到最后一个元素
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

    }

    // 将一个数组(二叉树),调整成一个大顶堆

    /**
     * 功能: 完成将以i对应的非叶子节点的树调整成大顶堆
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整,len是在逐渐减少的
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];

        // 开始调整
        // 说明
        // k 初始化为 i节点的左子节点
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            // 使 k 指向 i节点的左右节点中较大的那个
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }

            // 如果当前节点小于它的左右子节点中的较大者就进行交换
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }

        arr[i] = temp;
    }
}
