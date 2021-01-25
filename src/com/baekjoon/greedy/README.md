# Greedy 문제 속 주요 Idea 정리

<br>



### Main_10775 : 공항

#### Union-Find 자료구조

* disjoint-set(서로소 집합) 구조
  * 서로 다른 집합이 교집합은 없지만, 모든 집합의 합집합은 전체 집합
  * 파티션과 비슷한 개념
* union, find 2가지의 연산만 지원
  * find : 해당 정점의 루트 노드를 찾음
  * union : 서로 다른 루트 노드를 가지고 있는 정점을 같은 루트 노드를 같도록 합침
    * 서로 다른 두 집합을 하나도 합침
    * 둘 중 하나의 루트를 다른 하나의 루트의 자식이 되게 연결
    * 어떤 집합을 루트로 선정할지는 개발자 마음

```java
int N;
// 각 수의 루트 노드를 적어놓은 배열
int[] parent = new int[N];

public void solution() {
  // 배열 초기화
  for (int i = 0; i < N; i++) {
    parentGate[i] = i;
    //parentGate[i] = -1;
  }
  
  // 집합을 엮을 수들의 집합이 있다고 가정
  int[][] numbers = new int[N-2][2];
  
  for (int[] num : numbers) {
    int root1 = find(num[0]);
    int root2 = find(num[1]);
    
    merge(root1, root2);
  }
}

public int find(int n) {
  // 두 값이 같으면 아직 집합에 속해있지 않다는 의미
  if (parent[n] == n) {
    return n;
  }
  // 집합에 속해있으면 루트 노드를 찾아서 반환
  return parent[n] = find(parent[n]);
}

public void merge(int n1, int n2) {
  int n1 = find(n1);
	int n2 = find(n2);

  // 서로 다른 집합을 하나의 루트를 가지도록 합침
	parent[n1] = n2;
}

```




