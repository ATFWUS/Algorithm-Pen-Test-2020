package 网易秋招笔试题;

import java.util.Scanner;

/**
 * # 题一：最小数位和
 *
 * ### 【题目描述】
 * 定义S(n)，表示n在十进制下的各位数字和。
 *
 * 现在给定一个x,请你求出最小正整数n，满足x≤S(n).
 *
 * ### 【输入描述】
 * 第一行数据组数T，对于每组数据，一行一个数字x。
 *
 * 1≤x≤10^5,1≤T≤10
 *
 * ### 【输出描述】
 * 对于每组数据，一行一个整数表示最小的n。
 *
 * ### 【示例】
 * ##### 示例1：
 * **输入：**
 *
 * > 2
 * 7
 * 9
 *
 *
 * **输出：**
 *
 *
 * > 7
 * >  9
 *
 *
 * ##### 示例2：
 * **输入：**
 *
 * > 2
 * 13
 * 18
 *
 * **输出：**
 *
 * > 49
 * 99
 *
 * ### 【解决思路及要点】
 * - 【贪心】n要尽可能小，说明位数要尽可能少，在十进制中9最大，所以对x除9，就可以得到n中9的个数，n的最高位就是余数。（满足n最高位最小，位数最小）
 * -  可以直接从最高位开始一位一位的输出，先输出余数，再输出x/9个9不需要再整体转换为数字。
 */
public class Solution1 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        while(T-->0){
            int x=sc.nextInt();
            solve(x);
        }
    }
    public static void solve(int x){
        //余数做最高位
        if(x%9>0){
            System.out.print(x%9);
        }
        x-=x%9;
        //x/9就是n中9的个数
        for(int i=1;i<=x/9;i++){
            System.out.print("9");
        }
        System.out.print("\n");
    }
}
