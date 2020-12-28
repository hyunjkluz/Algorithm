# 완전탐색 문제 속 주요 Method 정리


<br>


### 15561 - 링크와 스타트

##### HashSet 집합 합집합, 교집합, 차집합 구하기

```java
HashSet<Integer> set1 = new HashSet<Integer>();
HashSet<Integer> set2 = new HashSet<Integer>();

// 합집합
set1.addAll(set2);

// 교집합
set1.retainAll(set2);

// 차집합
set1.removeAll(set2);

// 집합 포함 여부 : 부분 집합
// true or false 가 반환됨
set1.containsAll(set2);
```

<br>

##### 간단 조합 - 완전 탐색

```java
static int N; // 뽑아야 할 개수
static boolean[] visit = new boolean[N]; // 뽑았는지 안뽑았는지 확인하는 배열

public static void pick(int depth, int index) {
  if (depth == N) {
    // 다 뽑았을 때
    return;
  }
  
  visit[depth] = true;
  pick(depth + 1, index);
  visit[depth] = false;
  pick(depth + 1, index);
}
```


