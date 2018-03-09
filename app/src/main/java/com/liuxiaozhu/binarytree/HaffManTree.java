package com.liuxiaozhu.binarytree;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Author：Created by liuxiaozhu on 2018/3/9.
 * Email: chenhuixueba@163.com
 * HaffMan树
 */

public class HaffManTree {
    Node root;

    /**
     * 创建HaffMan树
     * @param list
     * @return
     */
    public Node creatHaffManTree(ArrayList<Node> list) {
        if (list.size() < 1) throw new NullPointerException();
        while (list.size() > 1) {
            //将list按权从大到小排列
            Collections.sort(list);
            Node left = list.get(list.size() - 1);
            Node right = list.get(list.size() - 2);
            Node<String> parent = new Node<>(left.weight + right.weight, "p");//跟节点
            parent.left = left;
            left.parent = parent;
            parent.right = right;
            right.parent = parent;
            list.remove(left);
            list.remove(right);
            list.add(parent);
        }
        root = list.get(0);
        return list.get(0);
    }

    /**
     * 利用队列逐层遍历HaffMan树
     */
    public void showHaffMan() {
        LinkedList<Node> list = new LinkedList<>();
        if (root == null) return;
        list.offer(root);//将元素添加到队列
        while (!list.isEmpty()) {
            Node node = list.pop();//将第一个元素移除
            Log.e("逐层遍历二叉树", "元素值" + node.data);
            if (node.left != null) {
                list.offer(node.left);
            }
            if (node.right != null) {
                list.offer(node.right);
            }
        }
    }

    /**
     * 利用栈来实现权重的查询
     */
    public void getWedgit(Node node) {
        Stack<String> stack = new Stack<>();
        Node newNode = node;
        while (newNode != null && newNode.parent != null) {
            if (newNode.parent.left == newNode) {
                stack.push("0");
            } else if (newNode.parent.right == newNode) {
                stack.push("1");
            }
            newNode = newNode.parent;
        }
        while (!stack.empty()) {
            Log.e("权重", "权重值"+stack.pop());
        }
    }


    static class Node<T> implements Comparable<Node<T>> {
        Node left;
        Node right;
        Node parent;
        //权值
        int weight;
        //数据
        T data;

        public Node(int weight, T data) {
            this.weight = weight;
            this.data = data;
        }

        @Override
        public int compareTo(@NonNull Node<T> o) {
            if (this.weight > o.weight) {
                return -1;
            } else if (this.weight < o.weight) {
                return 1;
            }
            return 0;
        }
    }


}
