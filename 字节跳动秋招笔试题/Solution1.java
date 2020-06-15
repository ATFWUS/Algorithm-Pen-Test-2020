package 字节秋招笔试题;

import java.util.*;

/**
 * 1、模型文件去重
 *
 * 【题目描述】
 *
 * 抖音上不同的用户类型我们有不同的用户模型文件。
 * 我们有一个模型配置文件，里面有很多的不同的用户类型和他们对应的模型文件。我们需要找出每个模型对应的是哪些用户类型。
 *
 * 给定一行输入，格式是a b
 * a表示这个用户的用户类型，b表示这个用户对应的模型文件。
 * 请你输出每个模型文件对应的用户类型。
 * 注意1：每个模型文件可能对应多个用户类型，用户类型之间用空格作为切分。
 * 注意2: 如果有多个用户类型输出，用户类型之间的排序按照字母表排序。
 * 注意3: 如果有多个模型输出，模型输出的顺序按照模型文件在输入数据中顺序，即从上到下。
 * 输入描述
 * 输入第1行: 用户类型 N（表示有多少个 用户类型）
 * 接下来的N行：用户类型 模型文件
 *
 * 输出描述
 * 模型文件用户类型1 用户类型2
 *
 * 示例1
 * 输入
 * 1
 * abc 1.txt
 *
 * 输出
 *
 * 1.txt abc
 *
 * 解决思路：
 * 模型用List存储
 * 用哈希表将模型与用户的Set集合对应起来
 * Set起到去重的作用
 */
public class Solution1 {
    public static void main(String[] args) {
        List<String> model=new ArrayList<>();
        Map<String,Set<String>> table=new HashMap<>();
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        String a=new String();
        String b=new String();
        while(n-->0){
            a=sc.next();
            b=sc.next();
            if(!table.containsKey(b)){
                model.add(b);
                table.put(b, new HashSet<String>());
            }
            table.get(b).add(a);
        }
        for(String m:model){
            System.out.print(m);
            for(String consumer:table.get(b)){
                System.out.print(" "+consumer);
            }
            System.out.print("\n");
        }
    }
}
