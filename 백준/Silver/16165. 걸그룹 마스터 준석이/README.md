# [Silver III] 걸그룹 마스터 준석이 - 16165 

[문제 링크](https://www.acmicpc.net/problem/16165) 

### 성능 요약

메모리: 14208 KB, 시간: 100 ms

### 분류

자료 구조, 해시를 사용한 집합과 맵, 집합과 맵

### 제출 일자

2025년 5월 4일 23:46:13

### 문제 설명

<p>정우는 소문난 걸그룹 덕후이다. 정우의 친구 준석이도 걸그룹을 좋아하지만 이름을 잘 외우지 못한다는 문제가 있었다. 정우는 친구를 위해 걸그룹 개인과 팀의 이름을 검색하여 외우게 하는 퀴즈 프로그램을 만들고자 한다.</p>

### 입력 

 <p>첫 번째 줄에는 총 입력 받을 걸그룹의 수 N(0 < N < 100)과 맞혀야 할 문제의 수 M(0 < M < 100)을 입력받는다.</p>

<p>두 번째 줄부터는 각 걸그룹마다 팀의 이름, 걸그룹의 인원 수, 멤버의 이름을 한 줄씩 차례대로 입력받는다. 팀과 멤버의 이름은 최대 100글자이며, 모든 글자는 알파벳 소문자이다. 하나의 걸그룹이나 서로 다른 두 걸그룹에 이름이 같은 두 멤버가 있는 경우는 없다.</p>

<p>그 다음 줄부터는 M개의 퀴즈를 입력받는다. 각각의 퀴즈는 두 줄로 이루어져 있으며, 팀의 이름이나 멤버의 이름이 첫 줄에 주어지고 퀴즈의 종류를 나타내는 0 또는 1이 두 번째 줄에 주어진다. 퀴즈의 종류가 0일 경우 팀의 이름이 주어지며, 1일 경우 멤버의 이름이 주어진다.</p>

### 출력 

 <p>첫 번째 줄부터 차례대로 퀴즈에 대한 답을 출력한다. 퀴즈의 종류가 0일 경우 해당 팀에 속한 멤버의 이름을 사전순으로 한 줄에 한 명씩 출력한다. 퀴즈의 종류가 1일 경우 해당 멤버가 속한 팀의 이름을 출력한다.</p>

## AI 코드 리뷰

<template>

### 코드 리뷰
```java
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
```

이 코드는 주어진 걸그룹의 이름과 해당 멤버 정보를 입력받아, 사용자가 입력하는 쿼리에 따라 멤버 목록이나 그룹 이름을 출력하는 프로그램입니다. 코드는 크게 두 부분으로 나눌 수 있습니다:

1. **데이터 입력 및 저장**:
   ```java
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
   ```
   이 코드 블록에서는 N개의 걸그룹과 각 그룹의 멤버 정보를 입력받아 두 개의 해시맵에 저장합니다. 하나는 그룹 이름을 키로 하고 멤버 리스트를 값으로 하는 `map`이며, 다른 하나는 멤버 이름을 키로 하고 그룹 이름을 값으로 하는 `groupMap`입니다.

2. **쿼리 처리 및 결과 출력**:
   ```java
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
   ```
   이 블록에서는 M개의 쿼리를 처리합니다. 쿼리의 유형에 따라 멤버 목록을 정렬하여 출력하거나, 멤버의 속한 그룹 이름을 출력합니다.

### 시간/공간 복잡도 분석
- **시간 복잡도**:
  - 입력 단계에서 그룹과 멤버를 저장하는 데 O(N + K) 시간이 소요됩니다. 여기서 K는 모든 멤버의 총 수입니다.
  - 쿼리 처리 단계에서, 멤버 목록을 정렬하는 데 O(K log K) 시간, 멤버의 그룹을 찾는 데 O(1) 시간이 소요됩니다.
  - 따라서 전체 시간 복잡도는 O(N + K + M * K log K)입니다.

- **공간 복잡도**:
  - 두 개의 해시맵을 사용하므로 O(N + K) 공간을 사용합니다. 각 그룹과 멤버 정보를 저장하기 위해 추가적인 공간이 필요합니다.

### 코드 최적화 가능성 및 개선 제안
- 코드의 전반적인 구조는 명확하고 효율적입니다. 그러나 다음과 같은 개선 사항이 있습니다.
  1. **정렬 최적화**: 쿼리에서 멤버 목록을 요청할 때마다 정렬하는 것은 비효율적입니다. 멤버 목록을 저장할 때 정렬하여 저장하면, 쿼리 처리 시 정렬을 생략할 수 있습니다. 또는 정렬된 멤버 목록을 미리 준비해두고, 필요한 경우에만 정렬하는 방법도 고려할 수 있습니다.
  2. **입력 처리 최적화**: `BufferedReader`를 사용하고 있지만, 여전히 `StringTokenizer`를 통해 문자열을 분리하고 있습니다. `split()` 메서드를 사용하면 좀 더 직관적으로 코드를 작성할 수 있습니다.
  3. **가독성 향상**: 변수명 및 코드 블록의 주석을 추가하여 코드의 가독성을 높일 수 있습니다. 예를 들어, `members` 리스트 대신 `memberList`와 같은 명확한 이름을 사용하면 좋습니다.
  4. **예외 처리**: 입력 값이 잘못될 경우의 예외 처리 로직을 추가하면 코드의 안정성을 높일 수 있습니다.

이러한 개선 사항을 통해 코드의 성능과 가독성을 더욱 향상시킬 수 있습니다. 
</template>

