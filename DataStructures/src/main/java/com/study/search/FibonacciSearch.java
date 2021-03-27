package com.study.search;

import java.util.Arrays;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/26 8:39
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int resIndex = fibSearch(arr, 1234);
        System.out.println("resIndex = " + resIndex);

    }

    // 非递归法编写斐波那契查找算法
    public static int fibSearch(int[] arr, int findVal) {
        int lo = 0;
        int hi = arr.length - 1;
        int mid;
        int[] f = fib();
        int k = 0;  // 存放斐波那契数组的下标
        // 解析：
        // 首先要明确: 如果一个有序表的元素个数为n,并且n正好是(某个斐波那契数 - 1),
        // 即n=F[k]-1时,才能用斐波那契查找法。 如果有序表的元素个n不等于(某个斐波那契数 - 1).
        // 即n≠F[k]-1,这时必须要将有序表的元素扩展到大于n的那个斐波那契数 - 1才行.
        while (hi > f[k] - 1) {
            k++;
        }

        // 使用斐波那契数组的长度构建一个新数组
        int[] temp = Arrays.copyOf(arr, f[k] - 1);
        // 将数组后面填充的部分赋值为最大值,使数组仍然有序
        for (int i = hi + 1; i < f[k] - 1; i++) {
            temp[i] = arr[hi];
        }

        while (lo <= hi) {
            // 将mid置为黄金分割点的值
            mid = lo + f[k - 1] - 1;

            if (findVal < temp[mid]) {
                // 向左查找
                hi = mid - 1;
                k = k - 1;
            } else if (findVal > temp[mid]) {
                // 向右查找
                lo = mid + 1;
                k = k - 2;
            } else {
                // 因为数组补充了多个值,要查找的是原数组的最后一个值可能会越界
                // 因为temp是扩充过的,所以mid会大于hi
                return Math.min(mid, hi);
            }
        }
        return -1;
    }

    // 非递归方法得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}