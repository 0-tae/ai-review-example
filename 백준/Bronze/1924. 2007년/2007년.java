import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s=new StringTokenizer(br.readLine()," ");

        String[] weeks = {"MON", "TUE", "WED", "THU", "FRI", "SAT","SUN"};
        int[] days = {0,
                31,28,31,30,31,30,
                31,31,30,31,30,31};

        int m = Integer.parseInt(s.nextToken());
        int d = Integer.parseInt(s.nextToken());

        int day = d - 1;

        if(m>1){
            for(int i = m - 1 ; i>0;i--){
                day += days[i];
            }
        }

        System.out.println(weeks[day % weeks.length]);
    }
}