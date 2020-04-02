package com.ll.leetcode;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaoli
 * date: 2018/7/12
 * time: 15:11
 */
public class UniqueBSTII {

    /**
     * Input: 3
     * Output:
     * [
     *   [1,null,3,2],
     *   [3,2,null,1],
     *   [3,1,null,null,2],
     *   [2,1,3],
     *   [1,null,2,null,3]
     * ]
     * Explanation:
     * The above output corresponds to the 5 unique BST's shown below:
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if(n<1){
            return new ArrayList<TreeNode>();
        }
        //int[][]num=new int[n+1][n+1];
        TreeNodeList[][]trees=new TreeNodeList[n+1][n+1];
        for(int i=1;i<n+1;i++){
            //num[i][i]=1;
            TreeNodeList treeNodeList=new TreeNodeList();
            treeNodeList.getTreeNodes().add(new TreeNode(i));
            trees[i][i]=treeNodeList;
        }
        for(int l=2;l<n+1;l++){
            for(int i=1;i+l<=n+1;i++){
                int k=0;
                TreeNodeList treeNodeList=new TreeNodeList();
                for(int j=i;j<i+l;j++){
                    if(j==i){
                        TreeNodeList treeNodeList1=trees[j+1][i+l-1];
                        for (TreeNode treeNode : treeNodeList1.getTreeNodes()) {
                            TreeNode treeNode1=new TreeNode(j);
                            treeNode1.right=treeNode;
                            treeNodeList.getTreeNodes().add(treeNode1);
                        }
                        //k+=num[j+1][i+l-1];
                    }else if(j==i+l-1){
                        TreeNodeList treeNodeList1=trees[i][j-1];
                        for (TreeNode treeNode : treeNodeList1.getTreeNodes()) {
                            TreeNode treeNode1=new TreeNode(j);
                            treeNode1.left=treeNode;
                            treeNodeList.getTreeNodes().add(treeNode1);
                        }
                        //k+=num[i][j-1];
                    }else {
                        TreeNodeList leftNodeList=trees[i][j-1];
                        TreeNodeList rightNodeList=trees[j+1][i+l-1];
                        for (TreeNode treeNode : leftNodeList.getTreeNodes()) {
                            for (TreeNode node : rightNodeList.getTreeNodes()) {
                                TreeNode treeNode1=new TreeNode(j);
                                treeNode1.left=treeNode;
                                treeNode1.right=node;
                                treeNodeList.getTreeNodes().add(treeNode1);
                            }
                        }
                        //k+=num[i][j-1]*num[j+1][i+l-1];
                    }
                }
                trees[i][i+l-1]=treeNodeList;
                //num[i][i+l-1]=k;
            }
        }
        return trees[1][n].getTreeNodes();
    }

    public class TreeNodeList{
        List<TreeNode>treeNodes;

        public List<TreeNode> getTreeNodes() {
            return treeNodes;
        }

        public void setTreeNodes(List<TreeNode> treeNodes) {
            this.treeNodes = treeNodes;
        }

        public TreeNodeList() {
            treeNodes=new ArrayList<>();
        }
    }
    /**
     * Definition for a binary tree node.*/
      public class TreeNode { int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

}
