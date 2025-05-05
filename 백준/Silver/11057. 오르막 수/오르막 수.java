import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N =Integer.parseInt(br.readLine());
        int[][] dp = new int[N+1][10];
        int D=10007;

        for(int i = 1;i<N+1;i++){
            dp[i][0] = 1;
            for(int k = 1;k<10;k++) {
                dp[i][k] = ((dp[i-1][k] % D) + (dp[i][k-1] % D)) % D;
            }
        }

        System.out.println(Arrays.stream(dp[N]).sum() % D);
    }
}