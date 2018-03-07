package com.liuxiaozhu.binarytree;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Author：Created by liuxiaozhu on 2018/3/6.
 * Email: chenhuixueba@163.com
 * 二叉树
 * 栈排序不是太理解
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
     * 递归实现前序遍历
     *
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
     * 递归实现中序遍历
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
     * 递归实现后序遍历
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


    /**
     * 栈实现前序遍历
     *
     * 利用栈实现循环先序遍历二叉树
     *
     * 维护一个栈，将根节点入栈，然后只要栈不为空，出栈并访问，
     * 接着依次将访问节点的右节点、左节点入栈。
     * 这种方式应该是对先序遍历的一种特殊实现（看上去简单明了），
     * 但是不具备很好的扩展性，在中序和后序方式中不适用
     * @param root
     */
    public void proOrderTraverseStack(Node root) {
        if (root == null) {
            return;
        } else {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (!stack.empty()) {
                Node cur = stack.pop();
                Log.e("Stack前序遍历", "节点" + cur.data);
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
    }

    /**
     * 栈实现中序遍历
     *
     *  利用栈模拟递归过程实现循环先序遍历二叉树
     *  这种方式具备扩展性，它模拟递归的过程，
     *  将左子树点不断的压入栈，直到null，然后处理栈顶节点的右子树
     * @param root
     */
    public void midOrderTraverseStack(Node root) {
        if (root == null) {
            return;
        } else {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while (root != null || !stack.empty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                Log.e("Stack中序遍历", "节点" + root.data);
                root = root.right;
            }
        }
    }

    /**
     * 栈实现后序遍历
     *
     * 后序遍历不同于先序和中序，它是要先处理完左右子树，然后再处理根(回溯)，
     * 所以需要一个记录哪些节点已经被访问的结构(可以在树结构里面加一个标记)，
     * 这里可以用map实现
     * @param root
     */
    public void postOrderTraverseStack(Node root) {
        if(root==null)return;
        Stack<Node> s=new Stack<>();
        Map<Node,Boolean> map=new HashMap<>();
        s.push(root);
        while(!s.isEmpty()){
            Node temp=s.peek();
            if(temp.left!=null&&!map.containsKey(temp.left)){
                temp=temp.left;
                while(temp!=null){
                    if(map.containsKey(temp))break;
                    else s.push(temp);
                    temp=temp.left;
                }
                continue;
            }
            if(temp.right!=null&&!map.containsKey(temp.right)){
                s.push(temp.right);
                continue;
            }
            Node t=s.pop();
            map.put(t,true);
            Log.e("Stack后序遍历", "节点" + t.data);
        }
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
