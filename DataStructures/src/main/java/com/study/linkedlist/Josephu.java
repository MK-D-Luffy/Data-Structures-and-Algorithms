package com.study.linkedlist;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/18 10:35
 */
public class Josephu {
    public static void main(String[] args) {
        CyclicLinkedList cyclicLinkedList = new CyclicLinkedList();
        cyclicLinkedList.start(30, 9, 15);
    }
}
class CyclicLinkedList {
    private final Node HEAD = new Node(1);

    private static class Node {
        public int num;
        public Node next;

        public Node(int num) {
            this.num = num;
        }
    }

    private void initNodes(int total) {
        Node p = HEAD;
        int i = 2;
        while (i <= total) {
            p.next = new Node(i);
            p = p.next;
            i++;
        }
        // 头尾连接，形成单循环链表
        p.next = HEAD;
    }

    // total:总共多少人
    // deadNum:数到几自杀
    public void start(int total, int deadNum, int leaveNum) {
        initNodes(total);
        Node p = HEAD;
        int count = 2;
        while (getLength() != leaveNum) {
            while (count != deadNum) {
                p = p.next;
                count++;
            }
            // 将数到该数字的人杀死
            p.next = p.next.next;
            p = p.next;
            count = 2;
        }
        getAll();
    }

    public int getLength() {
        Node p = HEAD;
        Node stay = null;
        int count = 0;
        while (p != stay) {
            if (stay == null) {
                stay = p;
            }
            p = p.next;
            count++;
        }
        return count;
    }

    public void getAll() {
        Node p = HEAD;
        Node stay = null;
        while (p != stay) {
            if (stay == null) {
                stay = p;
            }
            System.out.println("存活的编号: " + p.num);
            p = p.next;
        }
    }
}
