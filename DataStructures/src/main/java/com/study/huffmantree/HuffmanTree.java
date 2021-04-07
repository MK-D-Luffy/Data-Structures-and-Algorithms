package com.study.huffmantree;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/4/3 16:22
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    // 构建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        // 当列表中只剩最后一个节点时结束
        while (nodes.size() > 1) {
            // 排序
            Collections.sort(nodes);

            // 取出权值最小的两个结点(二叉树)
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            // 构建一颗新的二叉树
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            // 将刚才取出使用过的两个结点从列表中移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // 并将新创建的二叉树添加到到列表中
            nodes.add(parent);
        }

        // 返回赫夫曼树的root结点
        return nodes.get(0);
    }

    // 前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树,无法遍历");
        }
    }
}

@Data
class Node implements Comparable<Node> {
    private int value;  // 结点权值
    private Node left;  // 指向左子结点
    private Node right; // 指向右子节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 实现Comparable接口,重写比较方法
    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.value - o.value;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}