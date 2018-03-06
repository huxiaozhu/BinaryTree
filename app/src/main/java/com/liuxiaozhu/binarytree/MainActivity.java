package com.liuxiaozhu.binarytree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 二叉树的遍历
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BinaryTree tree = new BinaryTree();
        tree.createTree();
        //前序
        tree.proOrderTraverse(tree.root);
        //中序
        tree.midOrderTraverse(tree.root);
        //后序
        tree.postOrderTraverse(tree.root);
    }
}
