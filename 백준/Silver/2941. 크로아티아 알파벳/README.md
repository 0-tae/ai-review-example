# [Silver V] 크로아티아 알파벳 - 2941 

[문제 링크](https://www.acmicpc.net/problem/2941) 

### 성능 요약

메모리: 14216 KB, 시간: 104 ms

### 분류

구현, 문자열

### 제출 일자

2025년 5월 5일 00:04:15

### 문제 설명

<p>예전에는 운영체제에서 크로아티아 알파벳을 입력할 수가 없었다. 따라서, 다음과 같이 크로아티아 알파벳을 변경해서 입력했다.</p>

<table class="table table-bordered table-center-20 th-center td-center">
	<thead>
		<tr>
			<th>크로아티아 알파벳</th>
			<th>변경</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>č</td>
			<td>c=</td>
		</tr>
		<tr>
			<td>ć</td>
			<td>c-</td>
		</tr>
		<tr>
			<td>dž</td>
			<td>dz=</td>
		</tr>
		<tr>
			<td>đ</td>
			<td>d-</td>
		</tr>
		<tr>
			<td>lj</td>
			<td>lj</td>
		</tr>
		<tr>
			<td>nj</td>
			<td>nj</td>
		</tr>
		<tr>
			<td>š</td>
			<td>s=</td>
		</tr>
		<tr>
			<td>ž</td>
			<td>z=</td>
		</tr>
	</tbody>
</table>

<p>예를 들어, ljes=njak은 크로아티아 알파벳 6개(lj, e, š, nj, a, k)로 이루어져 있다. 단어가 주어졌을 때, 몇 개의 크로아티아 알파벳으로 이루어져 있는지 출력한다.</p>

<p>dž는 무조건 하나의 알파벳으로 쓰이고, d와 ž가 분리된 것으로 보지 않는다. lj와 nj도 마찬가지이다. 위 목록에 없는 알파벳은 한 글자씩 센다.</p>

### 입력 

 <p>첫째 줄에 최대 100글자의 단어가 주어진다. 알파벳 소문자와 '-', '='로만 이루어져 있다.</p>

<p>단어는 크로아티아 알파벳으로 이루어져 있다. 문제 설명의 표에 나와있는 알파벳은 변경된 형태로 입력된다.</p>

### 출력 

 <p>입력으로 주어진 단어가 몇 개의 크로아티아 알파벳으로 이루어져 있는지 출력한다.</p>

### AI 코드 리뷰

```markdown
### 코드 리뷰
```java
import java.util.*;
import java.io.*;

public class Main {
    static int ptr = 0;  // 포인터 초기화
    public static void main(String[] args) throws IOException {
        HashSet<String> dict = initDictionary();  // 사전 초기화

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] context = br.readLine().toCharArray();  // 입력된 문자열을 문자 배열로 변환
        int ans = 0;  // 크로아티아 알파벳 수 카운트

        while (ptr < context.length) {  // 문자열 끝까지 반복
            String case1 = getLetter(ptr, context) + getLetter(ptr + 1, context);  // 현재 문자와 다음 문자 결합
            String case2 = case1 + getLetter(ptr + 2, context);  // 현재 문자, 다음 문자, 그 다음 문자 결합

            // case2가 사전에 존재하면 3칸 이동
            if (dict.contains(case2)) {
                ptr += 3;
            } 
            // case1이 사전에 존재하면 2칸 이동
            else if (dict.contains(case1)) {
                ptr += 2;
            } 
            // 그 외에는 1칸 이동
            else {
                ptr++;
            }

            // 첫 문자가 알파벳 소문자인지 확인
            if (!(case1.charAt(0) >= 'a' && case1.charAt(0) <= 'z')) continue; 

            ans++;  // 크로아티아 알파벳 카운트 증가
        }

        System.out.println(ans);  // 결과 출력
    }

    public static String getLetter(int ptr, char[] context) {
        return ptr < context.length ? context[ptr] + "" : "";  // 유효한 포인터일 경우 해당 문자 반환
    }

    public static HashSet initDictionary() {
        HashSet<String> dict = new HashSet<>();  // 크로아티아 알파벳 사전 초기화

        dict.add("c-");
        dict.add("dz=");
        dict.add("d-");
        dict.add("lj");
        dict.add("nj");
        dict.add("s=");
        dict.add("z=");

        return dict;  // 사전 반환
    }
}
```

이 코드는 크로아티아 알파벳의 개수를 세기 위한 알고리즘으로, 주어진 문자열을 스캔하면서 각 문자의 조합에 대해 사전(Dictionary)에 확인하여 크로아티아 알파벳으로 정의된 조합을 찾아내고 카운트합니다. 

- **포인터 초기화 및 입력 처리**: `ptr`이라는 정수 변수를 사용하여 현재 문자의 인덱스를 추적하며, `BufferedReader`를 통해 입력된 문자열을 읽고 이를 문자 배열로 변환합니다.
  
- **문자 조합 생성 및 사전 확인**: `case1`은 현재 문자와 다음 문자를 결합하고, `case2`는 그 다음 문자를 추가로 결합합니다. 이 두 경우를 사전에 존재하는지 확인하여, 각각 3칸 또는 2칸 이동합니다.

- **알파벳 카운트**: 유효한 크로아티아 알파벳이 발견될 때마다 카운트(`ans`)를 증가시키고, 마지막에 결과를 출력합니다.

### 알고리즘의 시간/공간 복잡도 분석
- **시간 복잡도**: O(n) - 문자열을 한 번 스캔하면서 포인터(`ptr`)를 이동시키므로, 입력 문자열의 길이에 비례하는 시간 복잡도를 가집니다.
- **공간 복잡도**: O(1) - 입력 문자열을 제외한 추가적인 메모리 사용은 사전(`dict`)을 제외하면 상수 공간만 사용합니다. 사전의 크기는 고정되어 있으므로 이를 고려하지 않을 경우 공간 복잡도는 O(1)로 평가할 수 있습니다.

### 코드 최적화 가능성과 개선 방안
- **가독성**: 현재 코드에서 가독성을 높이기 위해 변수 및 메서드 명을 더욱 명확하게 하는 것이 좋습니다. 예를 들어, `getLetter`를 `getCharacterAt`와 같이 명명하면 의도가 더 명확해질 수 있습니다.
  
- **중복 코드 제거**: `getLetter` 메서드는 문자열의 유효성을 확인하는 로직이 포함되어 있는데, 이 로직을 메인 루프에서 따로 처리할 수 있도록 개선하면 중복 코드를 줄일 수 있습니다.

- **사전 초기화**: `initDictionary`에서 `HashSet` 생성과 초기화를 한 줄로 합치는 것도 코드의 간결성을 높일 수 있습니다.

이러한 개선 방안을 통해 코드의 유지보수성과 가독성을 높일 수 있으며, 추가적인 최적화가 가능할 것입니다.
```

