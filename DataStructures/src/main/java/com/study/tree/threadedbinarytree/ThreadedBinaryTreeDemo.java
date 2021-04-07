package com.study.tree.threadedbinarytree;

import lombok.Data;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/31 21:43
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        // 手动创建二叉树(后面递归实现)
        HeroNode root = new HeroNode(1, "jack");
        HeroNode node2 = new HeroNode(3, "tom");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //  测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);

        // 中序线索化
        threadedBinaryTree.infixThreaded();

        // 测试:以10号节点为例
//        HeroNode leftNode = node5.getLeft();
//        HeroNode rightNode = node5.getRight();
//        System.out.println("10号节点的前驱节点是: " + leftNode);
//        System.out.println("10号节点的后继节点是: " + rightNode);

        System.out.println("遍历线索化二叉树的结果: ");
        threadedBinaryTree.threadedLists();
    }
}

class ThreadedBinaryTree {
    private HeroNode root;

    // 为了实现线索化,需要创建要给指向当前节点的前驱节点的指针.
    // 在递归进行线索化时,pre总是保留前一个节点.
    private HeroNode pre;


    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 重载
    public void infixThreaded() {
        infixThreaded(root);
    }

    // 对二叉树进行中序线索化的方法
    private void infixThreaded(HeroNode node) {
        if (node == null) {
            return;
        }

        // (一)遍历左子树
        infixThreaded(node.getLeft());
        // (二)线索化节点
        // 线索化前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }

        // 线索化后继节点(线索化后继节点需要使用下一个节点,用下一个节点的前驱节点完成操作)
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        // 将pre节点后移,为上一个入栈递归所使用 ★★★
        pre = node;

        // (三)遍历右子树
        infixThreaded(node.getRight());
    }

    // 遍历线索化二叉树
    public void threadedLists() {
        HeroNode node = root;
        while (node != null) {
            // 循环找到leftType=1的节点
            // 因为当leftType=1时,说明该节点是按照线索处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println("node = " + node);

            // 如果当前节点指向的是后继节点,就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println("node = " + node);
            }

            // 替换这个遍历的节点
            node = node.getRight();
        }
    }
}

@Data
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    // 说明:
    // 如果 XXtype == 0 表示指向的是子树,如果为 1 则表示 前驱或后继节点
    private int leftType;
    private int rightType;

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
}