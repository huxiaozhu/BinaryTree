package com.liuxiaozhu.binarytree;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Author：Created by liuxiaozhu on 2018/3/7.
 * Email: chenhuixueba@163.com
 * 二叉排序树
 * <p>
 * 特点：左子节点值比根节点小，
 * 右子节点值比根节点大的一种二叉树
 */

public class SearchBinaryTree {

    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    /**
     * 添加元素
     *
     * @param data
     * @return
     */
    public TreeNode put(int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        } else {
            TreeNode node = root;
            TreeNode parent = null;
            while (node != null) {
                parent = node;
                if (data < node.data) {
                    node = node.leftNode;
                } else if (data > node.data) {
                    node = node.rightNode;
                } else return node;
            }
            TreeNode newNode = new TreeNode(data);
            if (data < parent.data) {
                parent.leftNode = newNode;
            } else if (data > parent.data) {
                parent.rightNode = newNode;
            }
            newNode.parent = parent;
            return newNode;
        }
    }

    /**
     * 查找元素
     *
     * @param data
     * @return
     */
    public TreeNode get(int data) {
        if (root == null) {
            return null;
        }
        TreeNode node = root;
        while (node != null) {
            if (data == node.data) {
                return node;
            } else {
                if (data < node.data) {
                    node = node.leftNode;
                } else {
                    node = node.rightNode;
                }
            }
        }
        return null;
    }

    /**
     * 删除元素
     *
     * @param node
     * @return
     */
    public TreeNode remove(TreeNode node) {

        if (node == null) {
            throw new NullPointerException();
        } else {
            //删除元素
            TreeNode parent = node.parent;

            if (node.leftNode == null && node.rightNode == null) {
                //1.没有子节点
                if (parent == null) {
                    root = node;
                } else if (parent.rightNode == node) {
                    parent.rightNode = null;
                } else if (parent.leftNode == node) {
                    parent.leftNode = null;
                }
                node.parent = null;
            } else if (node.leftNode != null && node.rightNode == null) {
                //2.只有左孩子
                if (parent == null) {
                    node.leftNode.parent = null;
                    root = node.leftNode;
                } else {
                    if (parent.leftNode == node) {
                        parent.leftNode = node.leftNode;
                    } else if (parent.rightNode == node) {
                        parent.rightNode = node.leftNode;
                    }
                }
                node.parent = null;
            } else if (node.leftNode == null && node.rightNode != null) {
                //3.只有右孩子
                if (parent == null) {
                    node.rightNode.parent = null;
                    root = node.rightNode;
                } else {
                    if (parent.leftNode == node) {
                        parent.leftNode = node.rightNode;
                    } else {
                        parent.rightNode = node.rightNode;
                    }
                }
                node.parent = null;
            } else {
                //4.有两个孩子
                if (node.rightNode.leftNode == null) {
                    node.rightNode.leftNode = node.leftNode;
                    if (parent == null) {
                        root = node.rightNode;
                    } else {
                        if (parent.leftNode == node) {
                            parent.leftNode = node.rightNode;
                        } else {
                            parent.rightNode = node.rightNode;
                        }
                    }
                    node.rightNode.parent = parent;
                    node.parent = null;
                } else {
                    //先找被删除节点右子树的最小值
                    TreeNode leftNode = getMinLeftTreeNode(node.rightNode);

                    leftNode.leftNode = node.leftNode;
                    leftNode.leftNode.parent = leftNode;

                    TreeNode leftNodeP = leftNode.parent;
                    leftNodeP.leftNode = leftNode.rightNode;
                    if (leftNode.rightNode != null) {
                        leftNode.rightNode.parent = leftNode.parent;
                    }

                    leftNode.rightNode = node.rightNode;
                    leftNode.rightNode.parent = leftNode;

                    if (parent == null) {
                        root = leftNode;
                    } else {
                        if (parent.leftNode == node) {
                            parent.leftNode = leftNode;
                        } else {
                            parent.rightNode = leftNode;
                        }
                    }
                    leftNode.parent = parent;
                }
            }
        }
        return null;
    }

    /**
     * 查找节点又子树的最小值
     * @param node
     * @return
     */
    private TreeNode getMinLeftTreeNode(TreeNode node) {
        TreeNode cruRoot = null;
        if (node == null) {
            return null;
        } else {
            cruRoot = node;
            while (cruRoot.leftNode != null) {
                cruRoot = cruRoot.leftNode;
            }
        }
        return cruRoot;
    }

    /**
     * 遍历
     *
     * @param root
     */
    public void proOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        Log.e("前序遍历", "节点" + root.data);
        proOrderTraverse(root.leftNode);
        proOrderTraverse(root.rightNode);
    }

    public class TreeNode {
        int data;
        TreeNode leftNode;
        TreeNode rightNode;
        TreeNode parent;

        public TreeNode(int data) {
            this.data = data;
            this.leftNode = null;
            this.rightNode = null;
            this.parent = null;
        }

    }
}
