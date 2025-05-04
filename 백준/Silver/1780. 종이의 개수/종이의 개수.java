import java.util.*;
import java.io.*;

public class Main{

    static int[][] board;
    static int N;
    static int a;
    static int b;
    static int c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N =Integer.parseInt(br.readLine());
        board = new int[N][N];

        for(int i =0;i<N;i++){
            StringTokenizer s=new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                board[i][j] = Integer.parseInt(s.nextToken());
            }
        }


        divide(0,0,N);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    public static void divide(int startI,int startJ, int n){
        int prev = board[startI][startJ];
        for (int i=startI;i<startI+n;i++){
            for (int j=startJ;j<startJ+n;j++) {
                    if (prev != board[i][j]) {
                        divide(startI, startJ, n/3);
                        divide(startI, startJ+n/3, n/3);
                        divide(startI, startJ+n*2/3, n/3);
                        divide(startI+n/3, startJ, n/3);
                        divide(startI+n*2/3, startJ, n/3);
                        divide(startI+n/3, startJ+n/3, n/3);
                        divide(startI+n*2/3, startJ+n*2/3, n/3);
                        divide(startI+n*2/3, startJ+n/3, n/3);
                        divide(startI+n/3, startJ+n*2/3, n/3);
                        return;
                    }
                    prev = board[i][j];
            }
        }


        if(prev==-1){
            a++;
        }else if(prev==0){
            b++;
        }else{
            c++;
        }
    }
}