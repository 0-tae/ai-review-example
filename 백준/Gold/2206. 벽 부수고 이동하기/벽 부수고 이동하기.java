import java.util.*;
import java.io.*;

public class Main{
    static int[][] board;
    static int BREAK_LIMIT = 1;
    static int N;
    static int M;
    static final int NORMAL_TRAVEL = -BREAK_LIMIT;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s = new StringTokenizer(br.readLine());

        N =Integer.parseInt(s.nextToken());
        M =Integer.parseInt(s.nextToken());

        board = new int[N][M];
        for(int i=0;i<N;i++){
            String line = br.readLine();
            for(int j=0;j<M;j++){
                board[i][j]=line.charAt(j)-'0';
            }
        }

        System.out.println(search(new Loc(0,0,0,0)));
    }

    public static int search(Loc start){
        Queue<Loc> q = new LinkedList<>();
        q.offer(start);
        board[start.i][start.j] = NORMAL_TRAVEL;
        int[] di = {-1,1,0,0};
        int[] dj = {0,0,-1,1};

        while(!q.isEmpty()){
            Loc cLoc = q.poll();

            if(cLoc.i == N-1 && cLoc.j == M-1){
                return cLoc.depth + 1;
            }

            for(int x=0;x<di.length;x++){
                int nextI = di[x]+cLoc.i;
                int nextJ = dj[x]+cLoc.j;

                if(nextI>-1 && nextI<N && nextJ > -1 && nextJ<M && board[nextI][nextJ] != -1){
                    Loc nextLoc = new Loc(nextI,nextJ,cLoc.depth+1,cLoc.breaked);
                    if(board[nextI][nextJ] == 1 && cLoc.breaked < BREAK_LIMIT){
                        nextLoc.breaked++;
                        q.offer(nextLoc);
                        board[nextI][nextJ] = cLoc.getBreakTravel();
                    }else if(board[nextI][nextJ] == 0 || board[nextI][nextJ] < cLoc.getBreakTravel()){
                        q.offer(nextLoc);
                        board[nextI][nextJ] = cLoc.getBreakTravel();
                    }
                }


            }
        }

        return -1;
    }
}

class Loc{
    int i;
    int j;
    int depth;
    int breaked;

    Loc(int i,int j,int d,int b){
        this.i=i;
        this.j=j;
        depth=d;
        breaked = b;
    }

    public int getBreakTravel(){
        return -(this.breaked+1);
    }
}