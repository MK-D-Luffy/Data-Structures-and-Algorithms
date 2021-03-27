package com.study.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/25 21:48
 */
public class BinarySearch {
    public static void main(String[] args) {
        // 二分查找的数组必须是有序的!
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> list = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("list = " + list);
    }

    // 优化:查找到多个相同的值时全部返回
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        System.out.println("二分查找~");

        // 当 left>right 时,说明递归了整个数组,都没有找到.
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;

        if (findVal < arr[mid]) {
            // 向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else if (findVal > arr[mid]) {
            // 向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else {

            // 优化部分
            List<Integer> list = new ArrayList<>();

            // 自mid向左扫描
            int temp = mid - 1;
            while (true) {
                // 退出
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                } else {
                    list.add(temp);
                }
                // temp左移
                temp--;
            }

            // mid为已经二分查找到的下标
            list.add(mid);

            // 自mid向右扫描
            temp = mid + 1;
            while (true) {
                // 退出
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                } else {
                    list.add(temp);
                }
                // temp右移
                temp++;
            }

            return list;
        }
    }

    // 二分查找算法
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        // 当 left>right 时,说明递归了整个数组,都没有找到.
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;

        if (findVal < arr[mid]) {
            // 向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else if (findVal > arr[mid]) {
            // 向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }
    }
}