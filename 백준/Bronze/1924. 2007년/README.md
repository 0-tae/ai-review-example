# [Bronze I] 2007년 - 1924 

[문제 링크](https://www.acmicpc.net/problem/1924) 

### 성능 요약

메모리: 14328 KB, 시간: 100 ms

### 분류

구현, 수학

### 제출 일자

2025년 2월 5일 13:24:17

### 문제 설명

<p>오늘은 2007년 1월 1일 월요일이다. 그렇다면 2007년 x월 y일은 무슨 요일일까? 이를 알아내는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 빈 칸을 사이에 두고 x(1 ≤ x ≤ 12)와 y(1 ≤ y ≤ 31)이 주어진다. 참고로 2007년에는 1, 3, 5, 7, 8, 10, 12월은 31일까지, 4, 6, 9, 11월은 30일까지, 2월은 28일까지 있다.</p>

### 출력 

 <p>첫째 줄에 x월 y일이 무슨 요일인지에 따라 SUN, MON, TUE, WED, THU, FRI, SAT중 하나를 출력한다.</p>

### AI 코드 리뷰

<template>

### Code Review
The provided Java code is designed to determine the day of the week for a given date in the year 2007. The program reads the month and day from standard input and calculates the corresponding weekday based on the known fact that January 1, 2007, is a Monday.

```java
import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer s=new StringTokenizer(br.readLine()," ");
```
**Explanation 1: Input Handling**
This block imports necessary libraries and initializes the `Main` class. It reads a line of input, which is expected to contain a month (`x`) and a day (`y`). The `BufferedReader` and `StringTokenizer` are used for efficient reading and parsing of the input.

```java
        String[] weeks = {"MON", "TUE", "WED", "THU", "FRI", "SAT","SUN"};
        int[] days = {0,
                31,28,31,30,31,30,
                31,31,30,31,30,31};
```
**Explanation 2: Data Structures**
This block initializes an array of strings `weeks` that represents the days of the week, starting from Monday. It also initializes an integer array `days` that holds the number of days in each month of 2007. The first element is set to `0` to allow for 1-based indexing for months.

```java
        int m = Integer.parseInt(s.nextToken());
        int d = Integer.parseInt(s.nextToken());

        int day = d - 1;
```
**Explanation 3: Input Parsing and Day Calculation**
Here, the month (`m`) and day (`d`) are parsed from the input. The variable `day` is initialized to `d - 1` to account for the zero-based index in the subsequent calculations.

```java
        if(m>1){
            for(int i = m - 1 ; i>0;i--){
                day += days[i];
            }
        }
```
**Explanation 4: Day Accumulation**
This block checks if the month is greater than January. If it is, it iterates from the month before `m` down to January, accumulating the total number of days that have passed in the year up to the end of the previous month.

```java
        System.out.println(weeks[day % weeks.length]);
    }
}
```
**Explanation 5: Output**
Finally, the program calculates the day of the week by taking the total number of days (`day`) and using the modulo operator with the length of the `weeks` array. It then prints the corresponding weekday.

### Time/Space Complexity Analysis
- **Time Complexity**: The dominant operation in this code is the loop that sums up the days from previous months, which can execute a maximum of 11 iterations in the case of December. Therefore, the overall time complexity is O(1).
- **Space Complexity**: The space complexity is also O(1), as the amount of space used does not depend on the input size but is fixed with the arrays for weeks and days.

### Code Optimization Possibilities and Improvement Suggestions
1. **Readability**: The code is fairly readable, but comments would improve clarity, especially regarding the purpose of the arrays and the logic behind day accumulation.
2. **Error Handling**: There is no error handling for invalid input (e.g., month out of range, or day exceeding the maximum for the given month). Implementing input validation could enhance robustness.
3. **Array Usage**: The `days` array could be defined as a `List<Integer>` for flexibility, although this would slightly complicate access.
4. **Simplification of Logic**: The calculation of the total number of days could potentially be done in a single line using a formula instead of a loop, but given the constraints of the problem (only 12 months), the current approach is sufficient.

These improvements would make the code more robust and easier to maintain while still maintaining its efficiency.

</template>

