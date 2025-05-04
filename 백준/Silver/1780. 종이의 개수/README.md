# [Silver II] 종이의 개수 - 1780 

[문제 링크](https://www.acmicpc.net/problem/1780) 

### 성능 요약

메모리: 313572 KB, 시간: 980 ms

### 분류

분할 정복, 재귀

### 제출 일자

2025년 2월 6일 10:46:18

### 문제 설명

<p>N×N크기의 행렬로 표현되는 종이가 있다. 종이의 각 칸에는 -1, 0, 1 중 하나가 저장되어 있다. 우리는 이 행렬을 다음과 같은 규칙에 따라 적절한 크기로 자르려고 한다.</p>

<ol>
	<li>만약 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.</li>
	<li>(1)이 아닌 경우에는 종이를 같은 크기의 종이 9개로 자르고, 각각의 잘린 종이에 대해서 (1)의 과정을 반복한다.</li>
</ol>

<p>이와 같이 종이를 잘랐을 때, -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수를 구해내는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 N(1 ≤ N ≤ 3<sup>7</sup>, N은 3<sup>k</sup> 꼴)이 주어진다. 다음 N개의 줄에는 N개의 정수로 행렬이 주어진다.</p>

### 출력 

 <p>첫째 줄에 -1로만 채워진 종이의 개수를, 둘째 줄에 0으로만 채워진 종이의 개수를, 셋째 줄에 1로만 채워진 종이의 개수를 출력한다.</p>

### AI 코드 리뷰

<template>

### 코드 리뷰
```java
import java.util.*;
import java.io.*;

public class Main {
    static int[][] board;
    static int N;
    static int a;
    static int b;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer s = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(s.nextToken());
            }
        }

        divide(0, 0, N);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    public static void divide(int startI, int startJ, int n) {
        int prev = board[startI][startJ];
        for (int i = startI; i < startI + n; i++) {
            for (int j = startJ; j < startJ + n; j++) {
                if (prev != board[i][j]) {
                    divide(startI, startJ, n / 3);
                    divide(startI, startJ + n / 3, n / 3);
                    divide(startI, startJ + n * 2 / 3, n / 3);
                    divide(startI + n / 3, startJ, n / 3);
                    divide(startI + n * 2 / 3, startJ, n / 3);
                    divide(startI + n / 3, startJ + n / 3, n / 3);
                    divide(startI + n * 2 / 3, startJ + n * 2 / 3, n / 3);
                    divide(startI + n * 2 / 3, startJ + n / 3, n / 3);
                    divide(startI + n / 3, startJ + n * 2 / 3, n / 3);
                    return;
                }
                prev = board[i][j];
            }
        }

        if (prev == -1) {
            a++;
        } else if (prev == 0) {
            b++;
        } else {
            c++;
        }
    }
}
```

이 코드는 N×N 크기의 행렬을 입력받아, -1, 0, 1로만 채워진 종이의 개수를 세는 프로그램입니다. `divide` 메서드는 재귀적으로 종이를 9개의 하위 종이로 나누며, 각 하위 종이가 모두 같은 숫자로 이루어져 있는지를 검사합니다. 만약 모두 같은 숫자로 이루어져 있으면 해당 숫자에 따라 카운트를 증가시키고, 그렇지 않으면 다시 나누는 과정을 반복합니다.

1. **입력받기**: `main` 메서드에서 N을 입력받고, N×N 크기의 `board` 배열을 초기화합니다. 이후 사용자로부터 종이의 각 칸에 대한 값을 입력받아 저장합니다.

2. **분할**: `divide` 메서드는 현재 영역에서 같은 숫자인지를 확인하고, 다르면 9개의 하위 영역으로 분할합니다. 각 하위 영역에 대해 재귀 호출을 진행합니다. 만약 같은 숫자이면, 해당 숫자에 따라 카운트를 증가시킵니다.

### 알고리즘의 시간/공간 복잡도 분석
- **시간 복잡도**: 이 알고리즘은 종이를 분할하여 재귀적으로 호출하기 때문에, 최악의 경우 각 호출에서 모든 숫자를 검사하게 됩니다. N의 크기가 3의 거듭제곱일 때, 최대 호출 수는 O(log₃(N))입니다. 각 호출에서 N^2 크기의 영역을 검사하기 때문에, 전체 시간 복잡도는 O(N^2)입니다.

- **공간 복잡도**: 공간 복잡도는 주로 재귀 호출에 의해 결정됩니다. 최대 깊이인 log₃(N)만큼의 스택 메모리가 필요하므로 O(log₃(N))이지만, 배열 `board`에 N² 크기의 메모리가 소요되므로 최종적으로 O(N²)입니다.

### 코드 최적화 가능성과 개선 방안
- **최적화 가능성**: 현재 코드에서는 모든 호출이 독립적으로 이루어지므로, 메모이제이션 기법을 활용하여 이미 계산된 영역에 대한 결과를 저장하고 재사용하는 방법을 고려할 수 있습니다.

- **가독성 및 유지보수성**: 코드의 구조는 명확하고, 각 기능이 분리되어 있어 가독성이 좋습니다. 그러나 `divide` 함수 내에서 9개의 영역을 처리하는 부분이 반복적이므로, 반복적인 코드를 함수로 분리하여 코드 중복을 줄일 수 있습니다. 예를 들어, 9개의 하위 영역을 처리하는 별도의 메서드를 만들면 더 깔끔해질 것입니다.

- **에러 처리**: 현재 코드는 입력에 대한 유효성 검사를 수행하지 않으므로 잘못된 입력에 대해 예외 처리를 추가하면 안정성을 높일 수 있습니다.

</template>

