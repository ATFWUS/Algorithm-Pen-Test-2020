package 字节秋招笔试题;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 5、优惠券
 *
 * 【题目描述】
 * 你有n种无门槛优惠券，每种优惠券有一个面值ai。当一件商品的售价≥ai时，你可以出示这种优惠券直接抵扣。抵扣后，优惠券不会被回收，可以继续使用。现在，你想要买m件商品，每件商品的售价是bi，请问你最少需要花费多少钱？
 *
 * 输入描述：
 * 第一行两个正整数n,m(1≤n,m≤106)
 * 第二行n个正整数ai (0≤ai≤106)，代表n种无门槛优惠券的面值 （不保证排序）
 * 第三行m个正整数bi (0≤bi ≤106)，代表m件商品的价格 （不保证排序）
 *
 * 输出描述：
 * 输出合理使用优惠券后，购买上述m件商品最少需要的花费。
 *
 * 示例1：
 *
 * 输入：
 * 3 4
 * 50 100 200
 * 99 199 200 300
 *
 * 输出：
 * 248
 *
 * 解决思路：
 * 对a数组进行排序后二分搜索，找到最解决的优惠券使用
 */
public class Solution5 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[] a=new int[n];
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        Arrays.sort(a);
        long ans=0;
        while(m-->0){
            int price=sc.nextInt();
            int index=Arrays.binarySearch(a,price);
            //注意二分搜索的返回值
            if(index<0){
                index=-index-2;
            }
            ans+=price-a[index];
        }
        System.out.println(ans);
    }
}
