package com.liuxiaozhu.binarytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * 二叉树的遍历
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        testBinaryTree();
        testSearchBinaryTree();
    }

    /**
     * 测试二叉排序树
     */
    private void testSearchBinaryTree() {
        int[] array = {12, 3, 23, 5, 8, 1, 19};
        SearchBinaryTree tree = new SearchBinaryTree();
        for (int i = 0; i < array.length; i++) {
            tree.put(array[i]);
        }
        tree.proOrderTraverse(tree.getRoot());
        Log.e("aaa", "删除节点-------------------");
        tree.remove(tree.get(23));
        tree.proOrderTraverse(tree.getRoot());
    }

    /**
     * 测试二叉树的遍历
     */
    private void testBinaryTree() {
        BinaryTree tree = new BinaryTree();
        tree.createTree();
        //前序
        tree.proOrderTraverse(tree.root);
        tree.proOrderTraverseStack(tree.root);
        //中序
        tree.midOrderTraverse(tree.root);
        tree.midOrderTraverseStack(tree.root);
        //后序
        tree.postOrderTraverse(tree.root);
        tree.postOrderTraverseStack(tree.root);
    }
}
