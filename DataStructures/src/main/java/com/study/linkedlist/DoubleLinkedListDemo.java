package com.study.linkedlist;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/18 9:44
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addTail("123");
        doubleLinkedList.addTail("456");
        doubleLinkedList.addTail("789");
        doubleLinkedList.deleteNode(0);
        doubleLinkedList.getAll();
    }
}

class DoubleLinkedList {
    private final Node HEAD = new Node();

    public static class Node {
        public String key;
        public Node pre;
        public Node next;

        public Node() {
        }

        public Node(String key) {
            this.key = key;
        }
    }

    public void getAll() {
        Node p = HEAD.next;
        Integer count = 0;
        while (p != null) {
            System.out.println(count + "\t" + p.key + " ==> " + p.next);
            p = p.next;
            count++;
        }
        if (count == 0) {
            System.out.println("Nothing");
        }
    }

    public void addTail(String key) {
        Node p = HEAD;
        Node newNode = new Node(key);
        while (p.next != null) {
            p = p.next;
        }
        newNode.next = null;
        newNode.pre = p;
        p.next = newNode;
    }

    public void deleteNode(Integer i) {
        // 返回头节点
        Node p = HEAD.next;
        // 找到要删除节点的上一个节点
        while (i != 0) {
            p = p.next;
            i--;
        }
        // 通过将上一个节点的next值赋值为后一个节点，使中间节点消失，实现删除节点
        // p.next = p.next.next;(单向链表)
        p.pre.next = p.next;
    }
}