package com.study.tree;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/28 15:48
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();

    }
}

class ArrBinaryTree {
    // 存储数据节点的数组
    private final int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    // 完成顺序存储二叉树的前序遍历
    private void preOrder(int index) {
        // 如果数组为空,或者arr.length==0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树的前序遍历");
            return;
        }

        // 输出当前这个元素
        System.out.println(arr[index]);

        // 向左递归遍历
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }

        // 向右递归遍历
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }
}