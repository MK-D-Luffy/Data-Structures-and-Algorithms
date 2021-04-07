package com.study.binarySortTree;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/4/5 14:07
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int j : arr) {
            binarySortTree.add(new Node(j));
        }
        System.out.println("删除前:");
        binarySortTree.infixOrder();
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(12);
        binarySortTree.delNode(5);
        binarySortTree.delNode(2);
        binarySortTree.delNode(9);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        System.out.println("删除后:");
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    private Node root;

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

    // 查找要删除的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return this.root.search(value);
        }
    }

    // 查找要删除的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return this.root.searchParent(value);
        }
    }

    // 返回并删除右子树的最小值
    public int deleteRightTreeMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        int temp = node.value;
        delNode(temp);
        return temp;
    }

    // 删除节点
    public void delNode(int value) {
        // 如果树为空,直接返回
        if (root == null) {
            return;
        }
        // 先找到要删除的结点targetNode
        Node targetNode = search(value);
        // 如果没有找到要删除的节点,就直接返回
        if (targetNode == null) {
            return;
        }
        // 先处理要删除的只有一个节点的情况
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        // 找到targetNode的父结点parent
        Node parent = searchParent(value);

        // 如果要删除的节点为叶子节点
        if (targetNode.left == null && targetNode.right == null) {
            // 确定targetNode是parent的左子结点还是右子结点
            if (parent.left != null && parent.left.value == value) { // 左子节点
                parent.left = null;
            } else { // 右子节点
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) { // 删除有两颗子树的节点
            targetNode.value = deleteRightTreeMin(targetNode.right);
        } else { // 删除只有一颗子树的节点
            // 如果要删除的节点有的是左子节点
            if (targetNode.left != null) {
                if (parent != null) {
                    if (parent.left != null && parent.left.value == value) {
                        parent.left = targetNode.left;
                    } else if (parent.right != null && parent.right.value == value) {
                        parent.right = targetNode.left;
                    }
                } else { // ★★★
                    root = targetNode.left;
                }
            } else { // 如果要删除的节点有的是右子节点
                if (parent != null) {
                    if (parent.left != null && parent.left.value == value) {
                        parent.left = targetNode.right;
                    } else if (parent.right != null && parent.right.value == value) {
                        parent.right = targetNode.right;
                    }
                } else { // ★★★
                    root = targetNode.right;
                }
            }
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

    // 查找要删除的节点
    public Node search(int value) {
        // 如果找到该节点,直接返回
        if (this.value == value) {
            return this;
        } else if (value < this.value) { // 如果查找到的值小于当前节点,就向左子树递归
            return this.left.search(value);
        } else { // 如果查找到的值大于等于当前节点,就向右子树递归
            return this.right.search(value);
        }
    }

    // 查找要删除的父节点
    public Node searchParent(int value) {
        // 如果当前节点就是要删除节点的父节点,就直接返回
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (this.left != null && value < this.value) {
                // 向左子树递归
                return this.left.searchParent(value);
            } else if (this.right != null && this.value <= value) {
                // 向右子树递归
                return this.right.searchParent(value);
            } else {
                // 没有找到父节点
                return null;
            }
        }
    }
}