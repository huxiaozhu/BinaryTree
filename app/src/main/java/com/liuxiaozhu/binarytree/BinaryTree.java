package com.liuxiaozhu.binarytree;

import android.util.Log;

/**
 * Author：Created by liuxiaozhu on 2018/3/6.
 * Email: chenhuixueba@163.com
 * 二叉树
 */

public class BinaryTree {

    Node<String> root;

    /**
     * 创建二叉树
     *           A
     *      B        F
     *   C    D
     * E
     */
    public void createTree() {
        root = new Node<>("A", null, null);
        Node<String> b = new Node<>("B", null, null);
        Node<String> c = new Node<>("C", null, null);
        Node<String> d = new Node<>("D", null, null);
        Node<String> e = new Node<>("E", null, null);
        Node<String> f = new Node<>("F", null, null);
        root.left = b;
        b.left = c;
        b.right = d;
        c.right = e;
        root.right = f;
    }

    /**
     * 前序遍历
     * @param root
     */
    public void proOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        Log.e("前序遍历", "节点" + root.data);
        proOrderTraverse(root.left);
        proOrderTraverse(root.right);
    }

    /**
     * 前序遍历
     *
     * @param root
     */
    public void midOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        midOrderTraverse(root.left);
        Log.e("中序遍历", "节点" + root.data);
        midOrderTraverse(root.right);
    }

    /**
     * 后序遍历
     *
     * @param root
     */
    public void postOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        Log.e("后序遍历", "节点" + root.data);
    }

    private class Node<T> {
        T data;
        Node left;
        Node right;

        public Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
