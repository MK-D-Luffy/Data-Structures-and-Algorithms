package com.study.huffmancode;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @author 一个努力变秃的小白
 * @creat 2021/4/4 10:20
 */
public class HuffmanCode {

    @Test
    public void testZip() {
        String scrFile = "d://1.png";
        String descFile = "d://2.png";
        zipFile(scrFile, descFile);
    }

    //-----------------------------------------------------------------------------
    //---------------------------------压缩文件--------------------------------------
    //-----------------------------------------------------------------------------
    public static void zipFile(String srcFile, String descFile) {
        FileInputStream is = null;
        FileOutputStream os = null;
        ObjectOutputStream oos = null;

        try {
            // 创建文件的输入流
            is = new FileInputStream(srcFile);
            // 创建文件输出流
            os = new FileOutputStream(descFile);
            // 创建一个和文件输出流相关的对象流
            oos = new ObjectOutputStream(os);

            // 创建一个和源文件一样大小的byte[]
            byte[] b = new byte[is.available()];
            // 将文件内容通过 is 输入流读取 b 中
            is.read(b);
            // 获取文件压缩后的字节数组
            byte[] huffmanBytes = huffmanZip(b);

            // 将压缩后的对象信息传给输出流进行输出
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("压缩成功~");
        }
    }


    @Test
    public void testUnZip() {
        String scrFile = "d://2.png";
        String descFile = "d://3.png";
        unZipFile(scrFile, descFile);
    }

    //-----------------------------------------------------------------------------
    //---------------------------------解压文件--------------------------------------
    //-----------------------------------------------------------------------------
    public static void unZipFile(String scrFile, String descFile) {
        FileInputStream is = null;
        ObjectInputStream ois = null;
        FileOutputStream os = null;

        try {
            is = new FileInputStream(scrFile);
            ois = new ObjectInputStream(is);
            os = new FileOutputStream(descFile);

            // 读取压缩后的数组和赫夫曼编码表
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            // 解码文件
            byte[] decode = decode(huffmanCodes, huffmanBytes);
            os.write(decode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (ois != null) {
                    ois.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("解压成功~");
        }
    }


    @Test
    public void testCodeDecode() {
        String content = "i like like like java do you like a java";
        byte[] bytes = content.getBytes();
        // 压缩文件返回字节数组
        byte[] huffmanZipBytes = huffmanZip(bytes);
        System.out.println("huffmanZipBytes = " + Arrays.toString(huffmanZipBytes));

        // 解压文件返回原先传入的字节数组
        byte[] decode = decode(huffmanCodes, huffmanZipBytes);
        System.out.println(new String(decode));
    }

    //-----------------------------------------------------------------------------
    //----------------------------------解压----------------------------------------
    //-----------------------------------------------------------------------------

    /**
     * 将一个byte转成二进制的String
     *
     * @param flag 表示是否为数组的最后一位,如果是(true)则无需补高位,否则(false)正常处理都默认补高位
     * @param b    传入的byte
     * @return 返回的是byte对应的二进制的字符串(注意是按补码返回)
     */
    public static String bitToBitString(boolean flag, byte b) {
        // byte(8位)转化为int(32位)
        int temp = b;
        // 如果传入的是正数,还需要补高位,因为正数对应的就是本身,少于8位
        // 如果是最后一位就不需要补0了
        if (!flag) {
            temp = temp | 256;
        }
        // 返回的是字节对应的二进制补码
        String str = Integer.toBinaryString(temp);
        return !flag ? str.substring(str.length() - 8) : str;
    }

    /**
     * 完成对压缩数据的解码
     *
     * @param huffmanCodes 赫夫曼编码表map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原先字符串对应的数组
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();

        // 将要解码的字节数组转换为二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            // 如果是最后一位就无需补高位
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(bitToBitString(flag, huffmanBytes[i]));
        }

        // 通过编码表获取译码表
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        // 将处理好的译码表与 stringBuilder 进行匹配,获取原来的字节
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 0;
            Byte b;

            while (true) {
                String str = stringBuilder.substring(i, i + count);
                if ((b = map.get(str)) == null) {
                    count++;
                } else {
                    i = i + count;
                    break;
                }
            }
            list.add(b);
        }

        // 将list中的元素放到数组中去
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }


    //-----------------------------------------------------------------------------
    //----------------------------------压缩----------------------------------------
    //-----------------------------------------------------------------------------
    // 封装后的压缩,返回压缩后的字节数组
    public static byte[] huffmanZip(byte[] bytes) {
        // 构建节点数组
        List<Node> nodes = getNodes(bytes);
        // 根据 nodes 创建赫夫曼树
        Node root = createHuffmanTree(nodes);
        // 获取赫夫曼编码
        Map<Byte, String> map = getCodes(root);
        // 压缩成字节数组
        return zip(bytes, map);
    }


    /**
     * 将字符串的byte[]数组,通过生成的赫夫曼编码表,返回一个赫夫曼编码压缩后的byte[]
     *
     * @param bytes        字符串对应的byte[]
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;

        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            // ★★★
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }

        return huffmanCodeBytes;
    }


    // 重载
    private static Map<Byte, String> getCodes(Node root) {
        getCodes(root, "", stringBuilder);
        return huffmanCodes;
    }

    // 生成赫夫曼编码表
    // 思路:
    // 1.将赫夫曼编码表存放在Map<Byte,String>形式
    //   32->101,97->100,100->11000等等
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    // 2.在生成赫夫曼编码表时,需要拼接路径,定义一个StringBuilder,存放某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);

        if (node != null) {
            // 非叶子节点
            if (node.data == null) {
                // 向左递归
                getCodes(node.left, "0", stringBuilder2);
                // 向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                // 叶子节点,说明已经找到了树某一叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }


    // 创建赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {

        // 当列表中只剩最后一个节点时结束
        while (nodes.size() > 1) {
            // 排序
            Collections.sort(nodes);

            // 取出权值最小的两个结点(二叉树)
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            // 构建一颗新的二叉树
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            // 将刚才取出使用过的两个结点从列表中移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // 并将新创建的二叉树添加到到列表中
            nodes.add(parent);
        }

        // 返回赫夫曼树的root结点
        return nodes.get(0);
    }


    // 返回节点表用于构建赫夫曼树
    private static List<Node> getNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();

        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes) {
            map.merge(b, 1, Integer::sum);
        }

        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }
}

class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}