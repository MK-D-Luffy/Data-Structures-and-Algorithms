package com.study.search;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/25 22:35
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
//        int[] arr = new int[100];
//        for (int i = 0; i < 100; i++) {
//            arr[i] = i + 1;
//        }

        int resIndex = insertValueSearch(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndex = " + resIndex);

    }

    // 待优化:查找到多个相同的值时全部返回
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("插值查找~");

        // 判断findVal是否在数组的范围内是必须要的,
        // 因为后面的mid值的计算跟findVal有关,如果findVal超出范围,过大或过小,可能导致下标越界
        if (left > right || findVal < arr[left] || findVal > arr[right]) {
            return -1;
        }

        // 自适应
        int mid = left + (findVal - arr[left]) / (arr[right] - arr[left]) * (right - left);

        if (findVal < arr[mid]) {
            // 向左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else if (findVal > arr[mid]) {
            // 向右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }

    }
}
