package com.liuxiaozhu.binarytree;

/**
 * Author：Created by liuxiaozhu on 2018/3/9.
 * Email: chenhuixueba@163.com
 * 创建AVL树（平衡二叉树）
 */

public class AVLTree<E> {
    Node<E> root;
    /**
     * RH:只有右子节点
     * LH:只有左子节点
     * EH:有左右子节点
     */
    //左面减右面为负
    private static final int RH = -1;
    private static final int LH = 1;
    private static final int EH = 0;
    private int size;

    /**
     * 中序遍历
     * @param root
     */
    public void midOrderDisplay(Node root) {
        if (root == null) {
            return;
        } else {
            midOrderDisplay(root.left);
            System.out.println("midOrder: " + root.element);
            midOrderDisplay(root.right);
        }
    }

    /**
     * 插入元素
     * @param element
     * @return
     */
    public boolean insertElement(E element) {
        Node<E> t = root;
        if (t == null) {
            root = new Node<>(element, null);
            size = 1;
            root.balance = 0;
            return true;
        } else {
            //插入节点
            int cmp = 0;
            Node<E> parent;
            Comparable<? super E> e = (Comparable<? super E>) element;
            do {
                parent = t;
                cmp = e.compareTo(t.element);
                if (cmp < 0) {
                    t = t.left;
                } else if (cmp > 0) {
                    t = t.right;
                } else {
                    return false;
                }
            } while (t != null);
            Node<E> child = new Node<>(element, parent);
            if (cmp < 0) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            //插入节点 结束
            //检查平衡因子，回溯查找
            while (parent != null) {
                //比较当前节点与父节点的大小
                cmp = e.compareTo(parent.element);
                //根据插入的位置来调整parent
                if (cmp > 0) {
                    parent.balance--;
                } else {
                    parent.balance++;
                }
                if (parent.balance == 0) {
                    break;
                }

                if (Math.abs(parent.balance) == 2) {
                    fixAfterInsertion(parent);
                    break;
                } else {
                    parent = parent.parent;
                }
            }
            size++;
            return true;
        }
    }

    /**
     * @param parent
     */
    private void fixAfterInsertion(Node<E> parent) {
        if (parent.balance == 2) {
            leftBalance(parent);
        }
        if (parent.balance == -2) {
            rightBalance(parent);
        }
    }

    /**
     * 做平衡操作
     *
     * @param t
     */
    public void leftBalance(Node<E> t) {
        Node<E> tl = t.left;
        switch (tl.balance) {
            case LH:
                rightRoate(t);
                tl.balance = EH;
                t.balance = EH;
                break;
            case RH:
                Node<E> tlr = tl.right;
                leftRoate(t.left);
                rightRoate(t);
                switch (tlr.balance) {
                    case LH:
                        t.balance = RH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                    case RH:
                        t.balance = EH;
                        tl.balance = LH;
                        tlr.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tl.balance = EH;
                        tlr.balance = EH;
                        break;
                }
                break;
        }
    }

    /**
     * 右平衡操作
     *
     * @param t
     */
    public void rightBalance(Node<E> t) {
        Node<E> tr = t.right;
        switch (tr.balance) {
            case RH:
                //根节点的右子树的右子树上插入
                leftRoate(t);
                t.balance = EH;
                tr.balance = EH;
                break;
            case LH:
                //根节点的右子树的左子树上插入
                Node<E> trl = tr.left;
                rightRoate(t.right);
                leftRoate(t);
                switch (trl.balance) {
                    case RH:
                        t.balance = LH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                    case LH:
                        t.balance = EH;
                        tr.balance = RH;
                        trl.balance = EH;
                        break;
                    case EH:
                        t.balance = EH;
                        tr.balance = EH;
                        trl.balance = EH;
                        break;
                }
                break;
            case EH:
                break;
        }
    }

    /**
     * 右旋算法
     *
     * @param node
     */
    public void rightRoate(Node<E> node) {
        if (node != null) {
            Node<E> nodel = node.left;
            node.left = node.right;
            //
            if (nodel.right != null) {
                nodel.right.parent = node;
            }
            //
            nodel.parent = node.parent;
            if (node.parent == null) {
                root = nodel;
            } else {
                if (node.parent.left == node) {
                    node.parent.left = nodel;
                } else if (node.parent.right == node) {
                    node.parent.right = nodel;
                }
            }
            nodel.right = node;
            node.parent = nodel;
        }
    }

    /**
     * 左旋算法
     *
     * @param node
     */
    public void leftRoate(Node<E> node) {
        if (node != null) {
            //node的右子树节点
            Node<E> nodeR = node.right;
            //1.把node右子树的左节点指定给node的右子树
            node.right = nodeR.left;
            if (nodeR.left != null) {
                nodeR.left.parent = node;
            }
            //2.把node制定给node右子树的左节点
            nodeR.parent = node.parent;
            //3.将node右子树的parent指定
            if (node.parent == null) {
                root = nodeR;
            } else {
                if (node.parent.left == node) {
                    node.parent.left = nodeR;
                } else if (node.parent.right == node) {
                    node.parent.right = nodeR;
                }
            }
            nodeR.left = node;
            node.parent = nodeR;
        }
    }

    public class Node<E> {
        E element;
        //平衡因子（左子树深度减右子树深度）
        int balance = 0;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }
}
