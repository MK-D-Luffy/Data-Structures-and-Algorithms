package com.study.tree;

import lombok.Data;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/28 9:20
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 先创建一颗需要的二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setLeft(node4);
        node3.setRight(node5);
        binaryTree.setRoot(root);

        // 测试遍历
//        System.out.println("前序遍历:"); // 1,2,3,4,5
//        binaryTree.preOrder();
//
//        System.out.println("中序遍历:"); // 2,1,4,3,5
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历:"); // 2,4,5,3,1
//        binaryTree.postOrder();

        // 测试查找
        HeroNode resNode;
//        System.out.println("前序查找:");
//        resNode = binaryTree.preOrderSearch(4);
//
//        System.out.println("中序查找:");
//        resNode = binaryTree.infixOrderSearch(4);
//
//        System.out.println("后序查找:");
//        resNode = binaryTree.postOrderSearch(4);
//
//        if (resNode != null) {
//            System.out.printf("找到了,信息为no=%d name=%s\n", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.println("没有找到 no=%d 的英雄");
//        }

        // 测试删除
        System.out.println("删除前,前序遍历~");
        binaryTree.preOrder();
        binaryTree.deleteNode(5);
        System.out.println("删除后,前序遍历~");
        binaryTree.preOrder();
    }
}

class BinaryTree {
    HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空,无法遍历");
        }
    }

    // 前序查找
    public HeroNode preOrderSearch(int no) {
        return root != null ? root.preOrderSearch(no) : null;
    }

    // 中序查找
    public HeroNode infixOrderSearch(int no) {
        return root != null ? root.infixOrderSearch(no) : null;
    }

    // 后序查找
    public HeroNode postOrderSearch(int no) {
        return root != null ? root.postOrderSearch(no) : null;
    }

    // 删除节点
    public void deleteNode(int no) {
        if (root == null) {
            System.out.println("空树,不能删除");
        } else {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.deleteNode(no);
            }
        }

    }
}

@Data
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name=" + name +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);  // 先输出父节点
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);  // 输出父节点
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    // 后序遍历
    public void postOrder() {
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);  // 输出父节点
    }

    // 前序查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("前序查找~~");
        // 如果找到直接返回
        if (this.no == no) {
            return this;
        }

        HeroNode resNode = null;
        // 左节点不为空,左递归查找左子树
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        // 右节点不为空,右递归查找右子树
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }

        return resNode;
    }

    // 中序查找
    public HeroNode infixOrderSearch(int no) {

        HeroNode resNode = null;
        // 左节点不为空,左递归查找左子树
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("中序查找~~");
        // 如果找到直接返回
        if (this.no == no) {
            return this;
        }

        // 右节点不为空,右递归查找右子树
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    // 后序查找
    public HeroNode postOrderSearch(int no) {

        HeroNode resNode = null;
        // 左节点不为空,左递归查找左子树
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        // 右节点不为空,右递归查找右子树
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("后序查找~~");
        // 如果左右子树都没找到,再判断当前节点是不是
        if (this.no == no) {
            resNode = this;
        }
        return resNode;
    }

    public void deleteNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.deleteNode(no);
        }
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }

}