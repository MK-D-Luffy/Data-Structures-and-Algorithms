package com.study.linkedlist;

import java.util.Stack;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/17 16:16
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.initList();
        singleLinkedList.addHead("456");
        singleLinkedList.addHead("123");
        singleLinkedList.addTail("789");
        singleLinkedList.searchKey("789");
        singleLinkedList.addKey(5, "444");
        singleLinkedList.getAll();

        int length = singleLinkedList.getLength();
        System.out.println("有效节点的个数 = " + length);

        SingleLinkedList.Node node = singleLinkedList.findNode(1);
        System.out.println("node = " + node);

        singleLinkedList.reverse();
        singleLinkedList.getAll();

        singleLinkedList.reversePrint();
    }
}

class SingleLinkedList {
    private final Node HEAD = new Node();

    public static class Node {
        public String key;
        public Node next;

        public Node() {
        }

        public Node(String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key='" + key + '\'' +
                    ", next=" + next +
                    '}';
        }
    }

    // 获取到单链表的节点的个数
    public int getLength() {
        Node p = HEAD;
        int length = 0;
        while (p.next != null) {
            p = p.next;
            length++;
        }
        return length;
    }

    // 查找单链表中的倒数第k个结点 【新浪面试题】
    // 1.编写一个方法,接受一个index
    // 2.index表示倒数第index个节点
    // 3.先把链表从头到尾遍历一遍,得到链表总的长度 getLength()
    // 4.得到总长度size后,从第一个开始遍历,遍历到size-index个即可
    public Node findNode(int index) {
        Node p = HEAD.next;
        int size = getLength();

        // 如果链表为空,index校验不通过就返回null
        if (p == null || index <= 0 || index > size) {
            return null;
        }

        for (int i = 0; i < size - index; i++) {
            p = p.next;
        }
        return p;
    }

    // 单链表的反转【腾讯面试题,有点难度】
    public void reverse() {
        Node r;
        Node q = HEAD.next;
        // 如果链表不为空,或有大于一个元素时,可以对链表进行反转.
        if (!(q != null && q.next != null)) {
            return;
        }
        Node p = HEAD.next.next;

        // 将首元节点变成尾节点rev
        q.next = null;
        while (p != null) {
            // 先将p.next保存,为后面的后移做准备
            r = p.next;
            // 将p.next赋值为q实现反向连接
            p.next = q;
            // q后移
            q = p;
            // p后移
            p = r;
        }
        HEAD.next = q;
    }

    // 从尾到头打印单链表 【百度，方式1：反向遍历(不建议). 方式2：Stack栈(使用)】
    public void reversePrint() {
        Node p = HEAD.next;
        // 空链表,不打印
        if (HEAD.next == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        // 将链表中所有的节点压入栈中
        while (p != null) {
            stack.push(p);
            p = p.next;
        }
        // 将栈中的节点进行打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }


    // (1)初始化线性表
    public void initList() {
        HEAD.next = null;
    }

    // (2)输出线性表
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

    // (3)取表中的第i个元素的键值
    public Node getNode(Integer i) {
        Node p = HEAD.next;
        while (i != 0) {
            p = p.next;
            i--;
        }
        return p;
    }

    // (4)从表中删除指定位置的元素
    public void deleteNode(Integer i) {
        // 返回头节点
        Node p = HEAD;
        // 找到要删除节点的上一个节点
        while (i != 0) {
            p = p.next;
            i--;
        }
        // 通过将上一个节点的next值赋值为后一个节点，使中间节点消失，实现删除节点
        p.next = p.next.next;
    }

    // (5)从表中删除指定键值的元素
    public void deleteNode(String key) {
        Node p = HEAD;
        while (!key.equals(p.next.key)) {
            p = p.next;
        }
        // 与上面同理
        p.next = p.next.next;
    }

    // (6)向表的头部添加键值为key的元素
    public void addHead(String key) {
        Node newNode = new Node(key);
        newNode.next = HEAD.next;
        HEAD.next = newNode;
    }

    // (7)向表的尾部添加键值为key的元素
    public void addTail(String key) {
        Node p = HEAD;
        Node newNode = new Node(key);
        while (p.next != null) {
            p = p.next;
        }
        newNode.next = null;
        p.next = newNode;
    }

    // (8)向表中指定的位置pos处添加键值为key的元素
    public void addKey(Integer pos, String key) {
        Node p = HEAD;
        Node newNode = new Node(key);
        while (pos != 0 && p.next != null) {
            p = p.next;
            pos--;
        }
        newNode.next = p.next;
        p.next = newNode;
    }

    // (9)在表中中搜索键值为key的元素，看其是否存在
    public void searchKey(String key) {
        Node p = HEAD.next;
        boolean flag = false;
        while (p != null) {
            if (key.equals(p.key)) {
                flag = true;
                break;
            }
            p = p.next;
        }
        if (flag) {
            System.out.println("key:" + key + " exists");
        } else {
            System.out.println("key:" + key + " doesn't exists");
        }
    }

}