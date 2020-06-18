package 网易秋招笔试题;

import java.util.Scanner;

/**
 * # 题二：吃葡萄
 * ### 【题目描述】
 * 有三种葡萄，每种分别有a,b,c颗。有三个人，第一个人只吃第1,2种葡萄，第二个人只吃第2,3种葡萄，第三个人只吃第1,3种葡萄。
 * 适当安排三个人使得吃完所有的葡萄,并且三个人中吃的最多的那个人吃得尽量少。
 *
 * ### 【输入描述】
 * 第一行数字T，表示数据组数。
 *
 * 接下来T行，每行三个数a,b,c
 *
 * 1≤a,b,c≤10^18,1≤T≤10
 *
 * ### 【输出描述】
 * 对于每组数据，输出一行一个数字表示三个人中吃的最多的那个人吃的数量。
 *
 * ### 【示例】
 * ##### 示例1：
 * **输入：**
 *
 * > 2
 * 1 2 3
 * 1 2 6
 *
 *
 * **输出：**
 *
 * > 2
 * 3
 *
 *
 * ##### 示例2：
 * **输入：**
 *
 * > 1
 * 12 13 11
 *
 * **输出：**
 *
 * > 12
 *
 * ### 【解决思路及要点】
 * - 如果两种较少葡萄的和比葡萄最多的一半要多，那么可以实现平分。【此时三人平分能够使得吃的最多的那个人吃得尽量少】
 * - 如果两种较少葡萄的和比葡萄最多的一半要少，那么结果是最多葡萄的一半。
 * - 注意要使用向上取整。（一颗葡萄也要单独吃一次）
 * - 也可以看成是三个人分别站在三角形的顶点，假设三角形两个短边是a,b，长边是c。则，若两短边之和大于等于长边的一半，可实现总数平分；反之，则结果为长边的一半。
 */
public class Solution2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        while(T-->0){
            long a,b,c;
            a=sc.nextLong();
            b=sc.nextLong();
            c=sc.nextLong();
            System.out.println(solve(a,b,c));
        }
    }
    public static long solve(long a,long b,long c){
        long maxx=Math.max(Math.max(a,b),c);
        long minn=Math.min(Math.min(a,b),c);
        long mid=a+b+c-maxx-minn;
        if(minn+mid>=maxx/2){
            return up(a+b+c,3);
        }else{
            return up(maxx,2);
        }
    }
    //除法向上取整
    private static long up(long a,long b){
        return a%b>0?a/b+1:a/b;
    }
}
