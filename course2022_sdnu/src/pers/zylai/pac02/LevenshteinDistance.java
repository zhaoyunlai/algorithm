package pers.zylai.pac02;

import java.util.Arrays;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/13/19:01
 * @Description:
 * Levenshtein Distance编辑距离算法
 */
public class LevenshteinDistance {

    //存距离矩阵
    private static int[][] DP;

    //记录编辑操作矩阵
    private static char[][] P;

    //初始化
    public static void initDP(String a,String b){
        int m = a.length();
        int n = b.length();

        DP = new int[m+1][n+1];
        P = new char[m+1][n+1];
        //初始化填充0
        for (char[] chars : P) {
            Arrays.fill(chars, '0');
        }

        //第一行赋值，注意维度是m+1 * n+1
        for (int i = 1; i <= n; i++) {
            DP[0][i] = i;
            P[0][i] = 'U';
        }
        //第一列赋值
        for (int i = 1; i <= m; i++) {
            DP[i][0] = i;
            P[i][0] = 'L';
        }
        
    }

    //获取编辑距离
    public static int getLSEditDist(String a,String b){
        int m = a.length();
        int n = b.length();
        int temp;
        for (int i = 1; i <= m; i++) {
            for(int j = 1;j <= n; j++){
                temp = a.charAt(i-1) == b.charAt(j-1) ? 0 : 1;
                //从三个地方获取最小值
                DP[i][j] = Math.min(DP[i-1][j-1]+temp,Math.min(DP[i-1][j]+1,DP[i][j-1]+1));

                //判断从哪里取得值，记录
                if(DP[i][j] == DP[i-1][j]+1){
                    P[i][j] = 'U';
                }else if(DP[i][j] == DP[i][j-1]+1){
                    P[i][j] = 'L';
                }else if(temp == 1){
                    P[i][j] = '1';
                }
            }
        }
        return DP[m][n];
    }

    //借助P数组回推最优解
    public static void getLSEdits(String a,String b){
        int i = a.length();
        int j = b.length();
        StringBuilder ax = new StringBuilder();
        StringBuilder bx = new StringBuilder();
        while(i  > 0 && j > 0){
            //直接从左上角
            if(P[i][j] == '0' || P[i][j] == '1'){
                ax.insert(0, a.charAt(i-1));
                bx.insert(0,b.charAt(j-1));
                i--;
                j--;
            }else if(P[i][j] == 'U'){
                ax.insert(0,a.charAt(i-1));
                bx.insert(0,'-');
                i--;
            }else{
                ax.insert(0,'-');
                bx.insert(0,b.charAt(j-1));
                j--;
            }
        }
        //回推最优解的结果
        System.out.println("回推最优解的结果如下：");
        //System.out.println(a+"\t"+ax.toString());
        System.out.println(ax.toString());
        //System.out.println(b+"\t"+bx.toString());
        System.out.println(bx.toString());
    }

    public static void printDP(){
        System.out.println("******DP Arrays********");
        for (int[] ints : DP) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static void printP(){
        System.out.println("******P Array********");
        for (char[] chars : P) {
            System.out.println(Arrays.toString(chars));
        }
    }

    public static void LSEdit(String a,String b){
        initDP(a,b);
        getLSEditDist(a,b);
        printDP();
        printP();
        getLSEdits(a,b);
    }

    public static void main(String[] args) {

        String[][] abs ={
                { "SUNNY", "SNOWY" },
                { "efficiency", "fiercely" }
        };

        for (String[] ab : abs) {
            LSEdit(ab[0],ab[1]);
        }

        //StringBuilder str1 = new StringBuilder();
        //StringBuilder str2 = new StringBuilder();
        //for (int i = 0; i < 120; i++){
        //    char temp = (char) ('a' + (int)(Math.random()*26));
        //    str1.append(temp);
        //}
        //
        //for (int i = 0; i < 130; i++) {
        //    char temp = (char) ('a' + (int)(Math.random()*26));
        //    str2.append(temp);
        //}
        //
        //LSEdit(str1.toString(),str2.toString());`
    }
}
