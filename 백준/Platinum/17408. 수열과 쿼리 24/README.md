# [Platinum IV] 수열과 쿼리 24 - 17408 

[문제 링크](https://www.acmicpc.net/problem/17408) 

### 성능 요약

메모리: 142256 KB, 시간: 896 ms

### 분류

자료 구조, 세그먼트 트리

### 제출 일자

2024년 5월 12일 16:05:29

### 문제 설명

<p>길이가 N인 수열 A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub>이 주어진다. 이때, 다음 쿼리를 수행하는 프로그램을 작성하시오</p>

<ul>
	<li><code>1 i v</code>: A<sub>i</sub>를 v로 바꾼다. (1 ≤ i ≤ N, 1 ≤ v ≤ 10<sup>9</sup>)</li>
	<li><code>2 l r</code>: l ≤ i < j ≤ r을 만족하는 모든 A<sub>i</sub> + A<sub>j</sub> 중에서 최댓값을 출력한다. (1 ≤ l < r ≤ N)</li>
</ul>

<p>수열의 인덱스는 1부터 시작한다.</p>

### 입력 

 <p>첫째 줄에 수열의 크기 N이 주어진다. (2 ≤ N ≤ 100,000)</p>

<p>둘째 줄에는 A<sub>1</sub>, A<sub>2</sub>, ..., A<sub>N</sub>이 주어진다. (1 ≤ A<sub>i</sub> ≤ 10<sup>9</sup>)</p>

<p>셋째 줄에는 쿼리의 개수 M이 주어진다. (2 ≤ M ≤ 100,000)</p>

<p>넷째 줄부터 M개의 줄에는 쿼리가 한 줄에 하나씩 주어진다.</p>

### 출력 

 <p>2번 쿼리에 대해서 정답을 한 줄에 하나씩 순서대로 출력한다.</p>

## AI 코드 리뷰

<template>

### 코드 리뷰
이 코드는 주어진 수열에 대해 두 가지 쿼리를 처리하기 위해 세그먼트 트리를 사용합니다. 주요 기능은 수열의 특정 인덱스를 업데이트하고, 특정 범위 내에서 두 값의 합의 최대를 찾는 것입니다.

```java
// input & data init
int N= Integer.parseInt(br.readLine());
StringTokenizer s = new StringTokenizer(br.readLine()," ");
int M= Integer.parseInt(br.readLine());

src = new int[N+1];
segTree = new Item[N*4+1];

for(int i=1;i<N+1;i++){
    src[i]=Integer.parseInt(s.nextToken());
}

// logic
segment(1,1,N);
```
위 코드는 입력을 처리하고 세그먼트 트리를 초기화합니다. `src` 배열은 수열을 저장하고, `segTree`는 세그먼트 트리의 노드를 저장합니다. `segment` 메서드는 세그먼트 트리를 구축합니다.

```java
for(int i=0;i<M;i++){
    s =new StringTokenizer(br.readLine()," ");
    int cmd = Integer.parseInt(s.nextToken());
    int value1 = Integer.parseInt(s.nextToken());
    int value2 = Integer.parseInt(s.nextToken());

    if(cmd==1){
        update(1,1,N,value1,value2);
    }else{
        Item result = read(1,1,N,value1,value2);
        writer.append(result.maxValue).append("\n");
    }
}
```
이 코드는 쿼리를 처리합니다. 첫 번째 쿼리는 `update` 메서드를 호출하여 특정 인덱스를 업데이트하고, 두 번째 쿼리는 `read` 메서드를 호출하여 주어진 범위에서 두 값의 합의 최대를 계산합니다.

```java
public static Item segment(int nodeIdx, int start, int end){
    if(start==end){
        return segTree[nodeIdx] = new Item(src[start],0);
    }

    int mid = (start+end)/2;
    return segTree[nodeIdx] =
            Item.maxChildren(segment(nodeIdx*2,start,mid),segment(nodeIdx*2+1,mid+1,end));
}
```
`segment` 메서드는 세그먼트 트리를 재귀적으로 생성합니다. 리프 노드는 수열의 원소를 저장하고, 내부 노드는 자식 노드의 최대 값을 기반으로 값을 저장합니다.

```java
public static Item read(int nodeIdx, int start, int end, int targetStart, int targetEnd){
    if(targetEnd < start || targetStart > end){
        return new Item(0,0);
    }else if(start >=targetStart && end <= targetEnd) {
        return segTree[nodeIdx];
    }
    int mid = (start+end)/2;
    return Item.maxChildren(read(nodeIdx*2,start,mid,targetStart,targetEnd),
            read(nodeIdx*2+1,mid+1,end,targetStart,targetEnd));
}
```
`read` 메서드는 쿼리 범위에 따라 세그먼트 트리에서 값을 읽어오는 기능을 합니다. 범위가 서로 겹치지 않으면 기본값을 반환하고, 범위가 완전히 겹치면 해당 노드를 반환합니다.

```java
public static Item update(int nodeIdx, int start, int end, int updatedIdx, int updateValue){
    if (!(updatedIdx < start || updatedIdx > end)) {
        if(start==end){
            return segTree[nodeIdx] = new Item(updateValue,0);
        }

        int mid = (start+end)/2;
        return segTree[nodeIdx] = Item.maxChildren(update(nodeIdx*2,start,mid,updatedIdx,updateValue),
                update(nodeIdx*2+1,mid+1,end,updatedIdx,updateValue));
    }

    return segTree[nodeIdx];
}
```
`update` 메서드는 세그먼트 트리의 특정 인덱스를 업데이트합니다. 업데이트 후, 부모 노드는 자식 노드의 최대 값으로 갱신됩니다.

### 시간/공간 복잡도 분석
- **시간 복잡도**: 
  - 세그먼트 트리 구축: O(N)
  - 업데이트 쿼리: O(log N)
  - 범위 쿼리: O(log N)
  전체 쿼리 처리: O(M log N) (M은 쿼리의 수)
  
- **공간 복잡도**: 
  - 세그먼트 트리 배열: O(4N) (최대 4N의 노드 필요)
  - 원본 배열: O(N)
  전체 공간 복잡도: O(N)

### 코드 최적화 가능성 및 개선 제안
- 코드의 가독성은 전반적으로 괜찮으나, `Item` 클래스의 생성자에서 `maxValue`를 계산하는 방식은 불필요한 계산을 포함하고 있습니다. `maxValue`는 `leftMax`와 `rightMax`의 합으로만 계산될 수 있습니다.
- `maxChildren` 메서드는 중복된 계산이 있을 수 있습니다. 이를 최적화하여 각 자식의 최대 값을 직접 비교할 수 있습니다.
- `BufferedReader`와 `StringTokenizer` 대신 `Scanner`를 사용하면 코드가 더 짧아질 수 있지만, 성능은 저하될 수 있습니다. I/O 성능이 중요한 경우 현재 방식을 유지하는 것이 좋습니다.
- 메서드에 주석을 추가하여 각 기능에 대한 설명을 덧붙이면 유지보수성이 향상됩니다.
- 메서드의 파라미터 수가 많을 경우, 객체로 묶어서 전달하는 것도 가독성을 높이는 방법입니다. 

이러한 개선을 통해 코드의 성능과 가독성을 모두 향상시킬 수 있습니다.
</template>

