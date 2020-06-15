package 字节秋招笔试题;

import java.util.Scanner;

/**
 * 4、简单变换
 *
 * 【题目描述】
 *
 * 春节在家的凯凯真的是太无聊了，他准备和他家的猫玩一个游戏。
 * 凯凯在黑板上写下了两个长度相等的数列a[1...n], b[1...n]。
 * 现在他想让他的猫判断数列a能否通过一个操作变换成数列b。
 * 这个操作是：在数列a中选择一个区间l-r，对这个区间所有的数字加上一个k。
 * 其中1<=l<=r<=n, k>=0。
 * 你可以帮帮可怜的小猫做出这个判断么？
 *
 * 输入描述：
 * 首先输入一个数字t，表示有t组数据
 * 每组数据的第一行为一个数字n 表示数列的长度
 * 接下来两行每行有n个数字，分别为数组a和数组b
 *
 * 输出描述：
 * 对于每组数据输出YES 或者 NO
 * 表示数列a能否通过对应的操作变换成数列b。
 *
 * 备注：
 * t<=10
 * n<=100000
 *
 * 示例1：
 * 输入：
 * 2
 * 6
 * 3 7 1 4 1 2
 * 3 7 3 6 3 2
 * 5
 * 1 1 1 1 1
 * 1 2 1 3 1
 *
 * 输出：
 * YES
 * NO
 *
 * 说明：
 * 对于第一个样例可以对区间[1,4,1] 的每个数字加上2，即可把数列a转换成数列b
 * 对于第二个样例没法做相应的操作
 *
 * 解决思路：
 *
 *差分数组，设置左右指针进行讨论
 */
public class Solution4 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0){
            int n=sc.nextInt();
            int[] a=new int[n];
            int[] b=new int[n];
            for(int i=0;i<n;i++){
                a[i]=sc.nextInt();
            }
            for(int i=0;i<n;i++){
                b[i]=sc.nextInt();
            }
            //差分数组
            for (int i = 0; i < n; ++i){
                a[i] = b[i] - a[i];
            }
            //左右指针
            int left=-1,right=-1;
            int k=0;
            //标记是否成功转换
            boolean flag=true;
            for(int i=0;i<n;i++){
                //a[i]属于相等的部分
                if(a[i]!=0){
                    //更新左指针
                    if(left==-1){
                        left=i;
                    }
                    //更新右指针
                    if (right == -1 || right == i - 1){
                        right=i;
                    }else if(right!=-1 && right!=i-1){
                        //此时若右指针不在初始位置并且不在i-1的位置，说明需要转换的地方不连续，无法进行转换操作
                        System.out.println("NO");
                        flag=false;
                        break;
                    }
                }else if(a[i]<0){
                    //此时说明数组b需要加,对a操作不可能转换
                    System.out.println("NO");
                    flag=false;
                    break;
                }else if(a[i]>0){
                    //a数组要加,如果k不是初始值，也不和a[i]相等，也无法转换
                    if(k!=0 && k!=a[i]){
                        System.out.println("NO");
                        flag=false;
                        break;
                    }
                    k=a[i];
                }
            }
            if(flag){
                System.out.println("YES");
            }
        }
    }
}
