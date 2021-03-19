package com.study.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/3/19 16:18
 */
public class PolandNotation {
    public static void main(String[] args) {
        // --------------实现中缀表达式转后缀表达式------------------
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);
        List<String> suffixExpressionList = parseSuffixExpressionList(list);
        int res = calculate(suffixExpressionList);
        System.out.println("计算结果是 = " + res);

        // -----------------实现逆波兰表达式-----------------------
        // 先定义一个逆波兰表达式
        // (3+4)×5-6 => 3 4 + 5 × 6 –
       /* String suffixExpression = "3 4 + 5 * 6 -";
        List<String> list = getString(suffixExpression);
        int res = calculate(list);
        System.out.println(suffixExpression+" 的计算结果是 = " + res);*/
    }

    // 将得到的中缀表达式的List => 后缀表达式的List
    public static List<String> parseSuffixExpressionList(List<String> list) {
        // 定义两个栈
        Stack<String> s1 = new Stack<>();// 符号栈
        List<String> s2 = new ArrayList<>();// 储存中间结果的栈(List)

        for (String item : list) {
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if ("(".equals(item)) {
                s1.push(item);
            } else if (")".equals(item)) {
                // 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                // 此时将这一对括号丢弃.
                s1.pop();
            } else {
                // ★★★
                // 当item优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并压入到s2中,
                // 再次转到(4.1)与s1中新的栈顶运算符相比较;
                // 否则直接将item压入栈.
                while (s1.size() != 0 && priority(item, s1.peek()) <= 0) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        // 将s1中剩余的运算符依次弹出并压入s2
        while (!s1.isEmpty()) {
            s2.add(s1.pop());
        }
        return s2;
    }

    // 运算符优先级的比较
    public static int priority(String oper1, String oper2) {
        int p1 = 0;
        int p2 = 0;
        if ("*".equals(oper1) || "/".equals(oper1)) {
            p1 = 2;
        } else if ("+".equals(oper1) || "-".equals(oper1)) {
            p1 = 1;
        }
        if ("*".equals(oper2) || "/".equals(oper2)) {
            p2 = 2;
        } else if ("+".equals(oper2) || "-".equals(oper2)) {
            p2 = 1;
        }

        return Integer.compare(p1, p2);
    }


    // 将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s) {
        // 定义一个List,存放中缀表达式对应的内容
        List<String> list = new ArrayList<>();
        int i = 0;
        StringBuilder str;// 对多位数的拼接
        char ch;
        while (i < s.length()) {
            // 如果是个非数字,需要加入到list中去
            if ((ch = s.charAt(i)) < 48 || (ch = s.charAt(i)) > 57) {
                list.add(ch + "");
                i++;
            } else {// 如果是一个数,需要考虑多位数的情况
                str = new StringBuilder();
                while (i < s.length() && (ch = s.charAt(i)) >= 48 && (ch = s.charAt(i)) <= 57) {
                    str.append(ch);
                    i++;
                }
                list.add(str.toString());
            }
        }
        return list;
    }

    // 将逆波兰表达式的数据和运算符依次放入ArrayList中
    public static List<String> getString(String suffixExpression) {
        String[] strings = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(strings));
    }

    /**
     * 逆波兰表达式的运算步骤:
     * 3 4 + 5 × 6 -
     * 1) 从左至右扫描，将3和4压入堆栈；
     * 2) 遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     * 3) 将5入栈；
     * 4) 接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
     * 5) 将6入栈；
     * 6) 最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (item) {
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    default:
                        throw new RuntimeException("运算符错误");
                }
                stack.push(res + "");
            }
        }
        // 最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}
