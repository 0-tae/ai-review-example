import java.util.*;
import java.io.*;

public class Main{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s=new StringTokenizer(br.readLine()," ");

        int N =Integer.parseInt(s.nextToken());
        int K =Integer.parseInt(s.nextToken());
        int[][] dp = new int[K+1][N+1];

        int d = 1000000000;
        for(int k=1;k<=K;k++){
            dp[k][0]=1;
            for(int n=1;n<=N;n++) {
                dp[k][n] = ((dp[k][n-1] % d)+(dp[k-1][n]%d))%d;
            }
        }

        System.out.println(dp[K][N]);
    }
}