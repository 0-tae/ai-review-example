# [Platinum V] 책 페이지 - 1019 

[문제 링크](https://www.acmicpc.net/problem/1019) 

### 성능 요약

메모리: 14372 KB, 시간: 132 ms

### 분류

수학

### 제출 일자

2024년 4월 13일 18:57:08

### 문제 설명

<p>지민이는 전체 페이지의 수가 N인 책이 하나 있다. 첫 페이지는 1 페이지이고, 마지막 페이지는 N 페이지이다. 각 숫자가 전체 페이지 번호에서 모두 몇 번 나오는지 구해보자.</p>

### 입력 

 <p>첫째 줄에 N이 주어진다. N은 1,000,000,000보다 작거나 같은 자연수이다.</p>

### 출력 

 <p>첫째 줄에 0이 총 몇 번 나오는지, 1이 총 몇 번 나오는지, ..., 9가 총 몇 번 나오는지를 공백으로 구분해 출력한다.</p>

## AI 코드 리뷰

<template>

### 코드 리뷰
```java
import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        String originalNum = br.readLine();

        // data init
        int[] ans = new int[10];
        int currentDegreeIndex = originalNum.length()-1;
        int totalNumLength  = originalNum.length();

        // logic
        for(;currentDegreeIndex>=0;currentDegreeIndex--){
            int[] tempNumsArray = new int[10];
            int overNumber = getOverNumber(originalNum,currentDegreeIndex);
            int degree = getDegree(totalNumLength-currentDegreeIndex);
            int previousNumber = getPreviousNumber(originalNum,currentDegreeIndex);

            // f1
            tempNumsArray[0] = overNumber * degree;

            // f2
            for(int i=1;i<10;i++){
                tempNumsArray[i] = (overNumber+1) * degree;
            }

            // loss
            int currentNum = originalNum.charAt(currentDegreeIndex)-'0';

            tempNumsArray[currentNum] -= (degree - previousNumber - 1); // self num loss

            for(int i=currentNum+1;i<10;i++){
                tempNumsArray[i] -= degree;
            } // next num loss

            IntStream.range(0,ans.length).forEach(i->ans[i]+=tempNumsArray[i]);
        }

        // result
        StringBuilder writer = new StringBuilder();
        IntStream.range(0,ans.length).forEach(i->writer.append(ans[i]).append(" "));
        
        System.out.println(writer);
    }
    public static int getOverNumber(String numString, int untilIndex){
        if(untilIndex == 0){
            return 0;
        }

        return Integer.parseInt(numString.substring(0,untilIndex));
    }

    public static int getPreviousNumber(String numString, int startIndex){
        int untilIndex= numString.length();

        if(startIndex == untilIndex-1){
            return 0;
        }

        return Integer.parseInt(numString.substring(startIndex+1,untilIndex));
    }

    public static int getDegree(int tries){
        int result = 1;

        for(int i=0;i<tries-1;i++){
            result*=10;
        }

        return result;
    }
}
```

이 코드는 주어진 책의 페이지 수 N에서 각 숫자가 몇 번 나타나는지를 계산하는 프로그램입니다. 입력으로 한 줄에 주어진 N을 읽고, 각 자리수를 분석하여 0부터 9까지 각 숫자가 나타나는 횟수를 출력합니다.

1. **입력 처리 및 초기화**:
   - `BufferedReader`를 사용하여 입력을 읽고, `originalNum`에 저장합니다. 
   - `ans` 배열을 초기화하여 각 숫자가 등장하는 횟수를 기록합니다. 
   - `currentDegreeIndex`와 `totalNumLength`를 설정하여 현재 분석 중인 자리수의 인덱스와 숫자의 전체 길이를 저장합니다.

2. **로직**:
   - `for` 루프를 통해 각 자리수에 대해 반복하고, 해당 자리수보다 높은 자리수에서 오는 값(overNumber), 자리수의 값(degree), 현재 자리수보다 낮은 자리수의 값(previousNumber)을 계산합니다.
   - `tempNumsArray`를 사용하여 각 숫자가 나타나는 횟수를 임시로 저장합니다.
   - 현재 자리수의 숫자(currentNum)에 대해서는 `tempNumsArray` 값을 조정하여 자기 자신과 이후 자리수의 숫자에 대한 조정을 수행합니다.
   - 최종적으로 `ans` 배열에 `tempNumsArray`의 값을 합산하여 각 숫자의 총 등장 횟수를 계산합니다.

3. **결과 출력**:
   - `StringBuilder`를 사용하여 결과를 저장하고, 이를 출력합니다.

### 시간/공간 복잡도 분석
- **시간 복잡도**: 이 알고리즘은 N의 자리 수에 비례하여 작동하므로 O(log N)입니다. N이 최대 1,000,000,000일 경우 최대 10자리 수이므로, 최악의 경우 10번의 반복이 발생합니다.
- **공간 복잡도**: `ans` 배열(10개 요소)과 `tempNumsArray` 배열(10개 요소)을 사용하므로 O(1)의 공간 복잡도를 가집니다. 입력 문자열 `originalNum`의 길이에 따라 메모리 사용량이 증가할 수 있지만, 이는 상수 공간으로 간주합니다.

### 코드 최적화 가능성 및 개선 제안
- **코드 최적화 가능성**: 현재 코드 구조는 효율적으로 작성되어 있으며, O(log N) 시간 복잡도로 최적화된 알고리즘입니다. 특히 `getOverNumber`, `getDegree`, `getPreviousNumber`와 같은 메서드들은 각각의 계산을 명확히 하고 있어 가독성도 높습니다. 그러나, `getDegree` 메서드는 `Math.pow(10, tries-1)`을 사용하는 것이 더 직관적일 수 있습니다.
- **개선 제안 사항**:
  - `getDegree` 메서드는 반복문 대신 `Math.pow(10, tries-1)`를 사용하여 간결하게 작성할 수 있습니다.
  - 코드 가독성을 높이기 위해 각 메서드의 주석을 추가하여 기능을 명확히 설명하는 것도 좋습니다.
  - `StringBuilder`의 사용은 좋은 선택이나, 결과를 만드는 `forEach` 대신 `Arrays.toString(ans)`을 사용하여 가독성을 높일 수 있습니다.
- **코드 가독성 및 유지보수성**: 전반적으로 코드 구조가 깔끔하고 가독성이 좋으며, 메서드로 로직을 잘 나누어 유지보수성을 높이고 있습니다. 그러나 코드에 대한 문서화 주석을 추가하면 더욱 이해하기 쉬울 것입니다.

</template>

