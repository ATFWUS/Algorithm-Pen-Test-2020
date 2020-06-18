package 网易秋招笔试题;

import java.util.Scanner;

/**
 * # 题四：跳柱子
 * ### 【题目描述】
 * 小易有n根柱子，第i根柱子的高度为hi。一开始小易站在第一根柱子上。小易能从第i根柱子跳到第j根柱子，当且仅当hj≤hi且1≤j−i≤k。其中k为指定的一个数字。
 * 另外小易拥有一次释放超能力的机会。这个超能力能让小易从柱子i跳到任意满足1≤j−i≤k的柱子j而无视柱子高度的限制。
 * 现在小易想知道，小易是否能到达第n根柱子。
 * ### 【输入描述】
 * 第一行数据组数T
 *
 * 对于每组数据，第一行数字n,k接下来一行n个数字表示hi。
 *
 * 1≤n≤1000,1≤hi≤10^9,1≤T≤10,1≤k≤n
 *
 * ### 【输出描述】
 * 对于每组数据，输出YES或NO
 *
 * ### 【示例】
 * ##### 示例1：
 * **输入：**
 *
 * > 1
 * 5 3
 * 6 2 4 3 8
 *
 * **输出：**
 *
 * > YES
 *
 * ##### 示例2：
 * **输入：**
 *
 * > 1
 * 5 2
 * 1 8 2 3 4
 *
 * **输出：**
 *
 * > NO
 *
 *
 * ### 【解决思路及要点】
 * - 明显可以使用dp。
 * - dp[i][0]表示不使用超能力的情况下是否能到达柱子。
 * - dp[i][1]表示使用超能力的情况下是否能到达柱子。
 * - 对每个柱子，枚举前面的k个柱子，并更新dp即可。
 * - 具体细节见代码。
 */
public class Solution4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        while(T-->0){
            int n=sc.nextInt();
            int k=sc.nextInt();
            int[] height=new int[n];
            for(int i=0;i<n;i++){
                height[i]=sc.nextInt();
            }
            if(solve(height,k)){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
    public static boolean solve(int[] height,int k){
        int n=height.length;
        boolean[][] dp=new boolean[n][2];
        //初始条件
        dp[0][0]=dp[0][1]=true;
        for(int i=0;i<n;i++){
            for(int j=i-1;j>=0 && j>=i-k;j--){
                //如果第j根柱子不使用超能力能达到
                if(dp[j][0]){
                    //那么使用也是肯定可以的
                    dp[j][1]=true;
                    //如果距离和高度都满足条件，那么第i根柱子在不使用超能力的情况下也是可以达到的
                    if(height[j]>=height[i]){
                        dp[i][0]=true;
                    }
                }
                //如果第j根柱子使用超能力能达到
                if(dp[j][1]){
                    //那么如果满足条件下，第i根柱子在已经使用了超能力的情况下，能够达到
                    if(height[j]>=height[i]){
                        dp[i][1]=true;
                    }
                }
            }
        }
        //最终的答案是，在允许使用超能力的情况下，能否到达最后一根柱子
        return dp[n-1][1];
    }
}
