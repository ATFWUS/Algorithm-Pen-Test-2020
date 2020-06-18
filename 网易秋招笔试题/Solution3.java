package 网易秋招笔试题;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * # 题三：圆环切割
 * ### 【题目描述】
 * 小易有n个数字排成一个环，你能否将它们分成连续的两个部分(即在环上必须连续)，使得两部分的和相等？
 * ### 【输入描述】
 * 第一行数据组数T，对于每组数据
 * 第一行数字n，表示数字个数
 *
 * 接下来一行n个数，按顺序给出环上的数字。
 *
 * 2≤n≤100000,1≤Ai≤10^9
 *
 * ### 【输出描述】
 * 对于每组数据，一行输出YES/NO
 *
 * ### 【示例】
 * ##### 示例1：
 * **输入：**
 *
 * > 1
 * 6
 * 1 2 3 4 5 6
 *
 * **输出：**
 *
 * > NO
 *
 * ##### 示例2：
 * **输入：**
 *
 * > 1
 * 4
 * 4 4 5 3
 *
 * **输出：**
 *
 * > YES
 *
 *
 *
 * ### 【解决思路及要点】
 * - 维护一个前缀和数组，将每个前缀和与圆环和的一半做差，如果存在一个前缀和和这个差相等，那么就可以分割。【具体的原理自己在脑海中构造个圆环想一下】这里利用的是圆环起点可变的特性。
 */
public class Solution3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        while(T-->0){
            int n=sc.nextInt();
            int[] pre=new int[n];
            for(int i=0;i<n;i++){
                int x=sc.nextInt();
                pre[i]=(i>0)?pre[i-1]+x:x;
            }
            if(solve(pre)){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
    public static boolean solve(int[] pre){
        int n=pre.length;
        //奇数不可能
        if((pre[n-1]&1)>0){
            return false;
        }
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<n;i++){
            //得到pre[i]与圆环和一半的差值
            int s=pre[i]-pre[n-1]/2;
            //若刚好有一个前缀和与这个差值相等，说明可以从前缀中减去，从而构成两部分相等
            if(set.contains(s)){
                return true;
            }
            //将这个前缀和存入set集合
            set.add(pre[i]);
        }
        return false;
    }
}
