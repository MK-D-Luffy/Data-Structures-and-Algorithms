package com.study.avl;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/4/5 17:08
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        // 添加节点
        for (int value : arr) {
            avlTree.add(new Node(value));
        }
        // 遍历
        avlTree.infixOrder();

        System.out.println("在平衡处理后~");
        System.out.println("树的高度是=" + avlTree.getRoot().height());
        System.out.println("树的左子树的高度是=" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度是=" + avlTree.getRoot().rightHeight());
        System.out.println("树的根节点是=" + avlTree.getRoot());
    }
}

// 创建AVL树
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    // 添加节点的方法
    public void add(Node node) {
        // 如果root为空,直接将node节点挂载到root上
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("当前二叉排序树为空,无法遍历");
        } else {
            root.infixOrder();
        }
    }
}

// 创建Node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 左旋转方法
    public void leftRotate() {
        // 创建新的节点,以当前根节点的值
        Node newNode = new Node(this.value);
        // 把新的节点的左子树设置成当前节点的左子树
        newNode.left = this.left;
        // 把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = this.right.left;
        // 把当前节点的值替换为右子节点的值
        this.value = this.right.value;
        // 把当前节点的右子树设置成当前节点右子树的右子树
        this.right = this.right.right;
        // 把当前节点的左子树(左子节点)设置成新的节点
        this.left = newNode;
    }

    public void rightRotate() {
        // 创建新的节点,以当前根节点的值
        Node newNode = new Node(this.value);
        // 把新的节点的右子树设置成当前节点的右子树
        newNode.right = this.right;
        // 把新的节点的左子树设置成当前节点的左子树的右节点
        newNode.left = this.left.right;
        // 把当前节点的值替换为左子节点的值
        this.value = this.left.value;
        // 把当前节点的左子树设置成当前节点左子树的左子树
        this.left = this.left.left;
        // 把当前节点的右子树(右子节点)设置成新的节点
        this.right = newNode;
    }

    // 返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        } else {
            return left.height();
        }
    }

    // 返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        } else {
            return right.height();
        }
    }

    // 返回以该节点为根节点的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(),
                right == null ? 0 : right.height()) + 1;
    }


    // 添加节点的方法
    // 递归的形式添加节点,注意需要满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        // 添加的节点小于当前节点的值
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                // 递归向左子树添加
                this.left.add(node);
            }
        }
        // 添加的节点值大于当前节点的值
        if (node.value >= this.value) {
            if (this.right == null) {
                this.right = node;
            } else {
                // 向右递归
                this.right.add(node);
            }
        }

        // ★★★★★
        if (rightHeight() - leftHeight() > 1) { // 左旋转
            // 如果当前节点右子树的左子树的高度大于右子树的右子树的高度时
            if (right != null && right.leftHeight() > right.rightHeight()) {
                // 则先对当前节点的右子树进行左旋转
                right.rightRotate();
                // 再对当前节点进行左旋转
                leftRotate();
            } else {
                // 直接对当前节点进行左旋转
                leftRotate();
            }
        } else if (leftHeight() - rightHeight() > 1) { // 右旋转
            // 如果当前节点左子树的右子树的高度大于左子树的左子树的高度时
            if (left != null && left.rightHeight() > left.leftHeight()) {
                // 则先对当前节点的左子树进行左旋转
                left.leftRotate();
                // 再对当前节点进行右旋转
                rightRotate();
            } else {
                // 直接对当前节点进行右旋转
                rightRotate();
            }
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}