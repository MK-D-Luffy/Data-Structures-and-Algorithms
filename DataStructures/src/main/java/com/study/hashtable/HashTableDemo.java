package com.study.hashtable;

import java.util.Scanner;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/26 10:54
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(8);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("输入add:添加元素");
            System.out.println("输入list:遍历元素");
            System.out.println("输入find:查找元素");
            System.out.println("输入del:删除元素");
            System.out.println("输入exit:退出程序");
            String s = scanner.next();
            switch (s) {
                case "add":
                    System.out.print("输入雇员的id: ");
                    int id = scanner.nextInt();
                    System.out.print("输入雇员的name: ");
                    String name = scanner.next();
                    hashTable.add(new Emp(id, name));
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.print("输入查找雇员的id: ");
                    int searchId = scanner.nextInt();
                    Emp emp = hashTable.findEmpById(searchId);
                    System.out.println("emp = " + emp);
                    break;
                case "del":
                    System.out.print("输入删除雇员的id: ");
                    int delId = scanner.nextInt();
                    hashTable.delEmpById(delId);
                    System.out.println("删除成功");
                    break;
                case "exit":
                    return;
                default:
                    break;
            }
        }
    }
}

// 管理多条链表
class HashTable {
    int size;
    EmpLinkedList[] empLinkedLists;

    public HashTable(int size) {
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    public void add(Emp emp) {
        if (emp == null) {
            System.out.println("雇员信息有误,请重新输入");
            return;
        }
        // 根据员工id,确认员工需要添加到哪条链表中
        int empLinkedListNo = hashCode(emp.id);
        // 将emp添加到对应的链表中
        empLinkedLists[empLinkedListNo].add(emp);
    }

    // 遍历所有的链表
    public void list() {
        for (int i = 0; i < empLinkedLists.length; i++) {
            empLinkedLists[i].list(i);
        }
    }

    // 根据输入的id,查找雇员
    public Emp findEmpById(int id) {
        int empLinkedListNo = hashCode(id);
        return empLinkedLists[empLinkedListNo].findEmpById(id);
    }

    // 根据输入的id,删除雇员
    public void delEmpById(int id) {
        int empLinkedListNo = hashCode(id);
        empLinkedLists[empLinkedListNo].deleteEmpById(id);
    }

    // 编写散列函数,使用一个简单取模法
    public int hashCode(int id) {
        return (id + 1) % size;
    }
}

// 创建链表
class EmpLinkedList {
    private Emp head;

    public void add(Emp emp) {
        // 如果是添加第一个雇员
        if (head == null) {
            head = emp;
        } else {
            // 如果不是,则找到最后一个节点后再添加
            Emp p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = emp;
        }
    }

    // 遍历链表中的所有元素
    public void list(int no) {
        if (head == null) {
            System.out.printf("第%d号链表为空\n", no + 1);
            return;
        }
        System.out.printf("第%d号链表的信息为: ", no + 1);
        Emp p = head;
        while (p != null) {
            System.out.printf("=>id=%d,name=%s\t", p.id, p.name);
            p = p.next;
        }
        System.out.println();
    }

    // 根据id在链表中查找
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp p = head;
        // 遍历整个链表同时查找,如果遍历完或找到就直接返回
        while (p != null && p.id != id) {
            p = p.next;
        }
        return p;
    }

    // 根据id在链表中删除
    public void deleteEmpById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return;
        } else if (head.id == id) { // 首元节点不为空,并且首元节点就是要删除的节点
            head = head.next;
            return;
        }

        Emp p = head;
        while (p.next != null && p.next.id != id) {
            p = p.next;
        }
        if (p.next != null) {
            p.next = p.next.next;
        }
    }

}

// 表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}