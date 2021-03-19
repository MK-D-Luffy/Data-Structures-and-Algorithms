package com.study.stack;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/19 14:08
 */
public class Calculator {
    public static void main(String[] args) {
        String expresion = "321+225*6-1250";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';
        StringBuilder longNum = new StringBuilder();

        for (int i = 0; i < expresion.length(); i++) {
            ch = expresion.charAt(i);
            // 判断扫描到的是否是运算符
            if (ArrayStack2.isOper(ch)) {
                // 如果当前符号栈是空
                if (operStack.isEmpty()) {
                    operStack.push(ch);
                } else {// 如果符号栈有操作符,就进行比较
                    // 如果当前的操作符的优先级大于栈中的操作符,就直接入符号栈。
                    if (ArrayStack2.priority(ch) > ArrayStack2.priority(operStack.peek())) {
                        operStack.push(ch);
                    } else {
                        // 否则,就从符号栈中pop出一个符号,再从数栈中pop出两个数,
                        // 进行运算,将得到的结果再存入数栈。
                        num2 = numStack.pop();
                        num1 = numStack.pop();
                        oper = operStack.pop();
                        res = ArrayStack2.cal(num1, num2, oper);
                        operStack.push(ch);
                        numStack.push(res);
                    }
                }
            } else {
                longNum.append(ch);
                int count = 0;

                // 如果已经到了最后一位,直接入栈
                if (i + 1 == expresion.length()) {
                    numStack.push(ch - 48);
                } else {
                    // 否则,确定判断字符最大长度不大于字符串总长度,判断下一位是否为数值,是为添加上
                    // 处理多位数截取的具体实现
                    while (i + count + 1 < expresion.length() &&
                            !ArrayStack2.isOper(expresion.charAt(i + count + 1))) {
                        longNum.append(expresion.charAt(i + count + 1));
                        count++;
                    }
                    numStack.push(Integer.parseInt(longNum.toString()));
                    longNum = new StringBuilder();
                    i = i + count;
                }
            }
        }

        while (!operStack.isEmpty()) {
            num2 = numStack.pop();
            num1 = numStack.pop();
            oper = operStack.pop();
            res = ArrayStack2.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.println(expresion + " = " + numStack.pop());
    }
}

// 定义ArrayStack2表示栈,扩展功能
class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 返回运算符的优先级,优先级是程序员确定的,优先级使用数字表示
    // 定义:数字越大,优先级越高
    public static int priority(int oper) {
        // 假定目前表达式只有+,-,*,/
        if (oper == '*' || oper == '/') {
            return 2;
        } else if (oper == '+' || oper == '-') {
            return 1;
        } else {
            return 0;
        }
    }

    // 判断是否为运算符
    public static boolean isOper(int oper) {
        return oper == '*' || oper == '/' || oper == '+' || oper == '-';
    }

    // 计算结果
    public static int cal(int num1, int num2, int oper) {
        switch (oper) {
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            default:
                throw new RuntimeException("运算符错误,无法运算");
        }
    }

    // 返回当前栈顶的值,但不弹出栈
    public int peek() {
        if (isEmpty()) {
            System.out.println("栈空,没有数据");
        }
        return stack[top];
    }

    // 判断栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 判断栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈已满,无法添加");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空,没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 显示栈的情况[遍历栈],遍历时,需要从栈顶显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空,没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}