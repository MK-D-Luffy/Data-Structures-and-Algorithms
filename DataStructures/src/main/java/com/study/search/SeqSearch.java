package com.study.search;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/25 21:28
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int index = seqSearch(arr, 11);
        if (index != -1) {
            System.out.println("查找到的下标为" + index + "");
        } else {
            System.out.println("没有找到");
        }
    }

    public static int seqSearch(int[] arr, int value) {
        // 线性查找是逐一对比,发现相同值,就返回下标
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}