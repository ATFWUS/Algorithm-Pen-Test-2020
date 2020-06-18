package 网易秋招笔试题;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


/**
 * # 题五：乘积
 * ### 【题目描述】
 * 小易给定你一个长度为n的正整数序列Ai，你每次可以使用1的代价将某个数加一或者减一，你希望用最少的代价使得所有数的乘积等于B，求最小代价（操作结束后每个数也必须是正整数）。
 *
 * ### 【输入描述】
 * 第一行数字n, B,表示序列长度和目标乘积。
 *
 * 接下来一行n个正整数表示初始序列。
 *
 * 1≤n≤10^3 ,  1≤B≤ 10^5，1≤Ai≤ 10^5.
 *
 * ### 【输出描述】
 * 一行一个数字表示答案
 *
 * ### 【示例】
 * ##### 示例1：
 * **输入：**
 *
 * > 5 12
 * 1 3 9 2 6
 *
 * **输出：**
 *
 * > 10
 *
 * **说明：**
 * 把3变为1需要2的代价，把9变为1需要8的代价，总代价为10。
 *
 *
 * ##### 示例2：
 * **输入：**
 *
 * > 3 15
 * 3 8 7
 *
 *
 * **输出：**
 *
 * > 9
 *
 * **说明：**
 *
 * 把8变为5需要3的代价，把7变为1需要6的代价，总代价为9。
 *
 * ### 【解决思路及要点】
 * - dp解决，dp[i][j]表示表示让前i个数字乘积为j的最小代价。
 * - 需要大量用到数的因子，故生成一个从1-B的所有数的因子的集合。
 * - 具体转移方程和其它细节在注释中。
 */
public class Solution5 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int B=sc.nextInt();
        int[] a=new int[n+1];
        for(int i=1;i<=n;i++){
            a[i]=sc.nextInt();
        }
        System.out.println(solve(a,B));
    }
    public static int solve(int[] a,int B){
        int n=a.length-1;
        //该集合是1-B的所有数的因子的集合
        List<List<Integer>> v=new ArrayList<>();
        //初始化集合
        for(int i=0;i<=B;i++){
            v.add(new ArrayList<>());
        }
        //得到1-B所有数的因子的集合
        for(int i=1;i<=B;i++){
            for(int j=i;j<=B;j+=i){
                v.get(j).add(i);
            }
        }
        //m为B的因子的个数
        int m=v.get(B).size();
        int[] num=new int[B+1];
        //从前往后记录B的因子的下标【等于可以从一个因子得到相应的下标，也可以用哈希表实现】
        for(int i=0;i<m;i++){
            num[v.get(B).get(i)]=i;
        }
        //dp数组
        int[][] dp=new int[n+1][m];
        //初始化dp[0]为最大值
        for(int i=1;i<m;i++){
            dp[0][i]= (int) 1e9;
        }
        for(int i=1;i<=n;i++){
            for(int j=0;j<m;j++){
                //初始化dp[i][j]为最大值
                dp[i][j]= (int) 1e9;
                //k遍历B的第j个因子的所有因子
                for(int k=0;k<v.get(v.get(B).get(j)).size();k++){
                    //dp[i-1][num[v.get(v.get(B).get(j)).get(k)]]是前i-1个数乘积为B的第j个因子的第k个因子的最小代价
                    //Math.abs(a[i]-v.get(B).get(j)/v.get(v.get(B).get(j)).get(k))是 第i个数-B的第j个因子/B的第j个因子的第k个因子 的绝对值
                    //这两个之和就是转换的最小代价
                    dp[i][j]=Math.min(dp[i][j],dp[i-1][num[v.get(v.get(B).get(j)).get(k)]]
                            +Math.abs(a[i]-v.get(B).get(j)/v.get(v.get(B).get(j)).get(k)));
                }
            }
        }
        //最终答案是前n个数字乘积为B的最小代价
        return dp[n][m-1];
    }
}
