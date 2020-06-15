package 字节秋招笔试题;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 2、穿越沙漠的补给次数
 *
 * 【题目描述】
 *
 * 旅行者穿越沙漠的过程中需要不断地消耗携带的饮用水，到达终点前会经过几个绿洲，每个绿洲均设有水分补给站可以为旅行者提供水分补给并收取一定的费用。
 * 沿途共有n个补给站，每个补给站收取的费用都一样，但是提供的水量不尽相同。起点到终点的距离为D公里，postion[i]表示第i个补给站距离起点的距离，单位为公里，supply[i]表示第i 个补给站可以提供的水量，单位为升。
 * 假设旅行者在起点时携带了W升的水，每行走1公里需要消耗1升的水量，身上可携带的水量没有上限，且携带的水量多少不会对体能消耗产生影响，鉴于每次进补给站花费的钱都是一样多，期望用最少的补给次数到达终点，请帮忙计算最少的补给次数。
 *
 * 输入描述：
 * 第一行输入整数D和W, D表示起点到终点的距离，W表示初始携带的水量
 * 第二行输入数组postion，长度为N，分别表示N个补给站分别距离起点的距离
 * 第三行输入数组supply，长度为N, 分别表示N个补给站分别可以供给的水量
 * 数据范围：1 <= D, W<=10^8, 0<=N<=1000, 0<position[i],supply[i]<D
 *
 * 输出描述：
 * 输出一个整数表示最少的补给次数，若无法到达终点则返回-1
 *
 * 示例1:
 *
 * 输入:
 *
 * 10 4
 * 1 4 7
 * 6 3 5
 *
 * 输出:
 * 1
 *
 * 说明:
 * 每行输入用空格隔开。起点到终点共10公里，初始时携带4升水，途径3个补给站。共需补给一次：只需在第1个补给站补给一次获得6升水，即可走完全程
 *
 *
 * 解决思路：
 * 贪心思想
 * 先假设用完身上所有的水，能走到一个位置，然后再去考虑去这个位置之前的水站加水，为了保证最少的加水次数，应该到能加到最多水的水站加水
 * 加完水后，需要标记，防止以后重复从这取水
 */
public class Solution2 {
    public static int minCount(int D,int W,int[] position,int[] supply){
        int ans=0;
        int n=position.length;
        //标记数组，true代表已经取过水了
        boolean[] flag=new boolean[n];
        //当前所在位置
        int currPos=0;
        while(currPos<D){
            //每次都把水喝完
            currPos+=W;
            W=0;
            //如果走完，直接返回
            if(currPos>=D){
                return ans;
            }
            //记录能获得最多水的下标
            int maxWater=-1;
            for(int i=0;i<n;i++){
                //还未达到相应的补给站
                if(position[i]>currPos){
                    break;
                }
                //如果如果还没从该水站取水，就看在这里取水能否得到最大的水量
                if(!flag[i]&&supply[i]>W){
                    W=supply[i];
                    maxWater=i;
                }
            }
            //此时无水，并且没有可以取水的水站，无法到达
            if(maxWater==-1){
                return -1;
            }
            flag[maxWater]=true;
            ans++;
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int D=sc.nextInt();
        int W=sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        String[] s1 = s.split(" ");
        int n=s1.length;
        int[] position=new int[n];
        int[] supply=new int[n];
        for (int i = 0; i < n; i++) {
            position[i]=Integer.valueOf(s1[i]);
        }
        s = sc.nextLine();
        String[] s2 = s.split(" ");
        for (int i = 0; i < n; i++) {
            supply[i]=Integer.valueOf(s2[i]);
        }

        System.out.println(minCount(D,W,position,supply));
    }
}
