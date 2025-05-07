import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(s.nextToken());
        int M = Integer.parseInt(s.nextToken());
        HashMap<String,ArrayList<String>> map = new HashMap<>();
        HashMap<String,String> groupMap = new HashMap<>();
        
        for(int i=0;i<N;i++){
            String group = br.readLine();
            int memSize = Integer.parseInt(br.readLine());
            map.put(group,new ArrayList<>());
            
            ArrayList<String> members = map.get(group);
            
            for(int m = 0;m<memSize;m++){
                String name = br.readLine();
                members.add(name);
                groupMap.put(name,group);
            }
        }
        
        for(int i=0;i<M;i++){
            String query = br.readLine();
            int type = Integer.parseInt(br.readLine());
            if(type == 0){
                ArrayList<String> members = map.get(query);
                Collections.sort(members);
                
                members.forEach(member->System.out.println(member));
            }else{
                System.out.println(groupMap.get(query));
            }
        }
        
    }
}