# 그래프 문제 속 주요 Method 정리

<br>



### Main_1766


#### 위상 정렬(Topological Sorting)

* 그래프 순서 정렬
    * 모든 정점을 일렬로 나열하되, 정점 x에서 정점 y로 가는 간선이 있으면 x는 반드시 y보다 앞에 위치한다.
    * 일반적으로 임의의 유향 그래프에 대해 **복수의 위상순서가 존재**한다.
* 조건 : **방향성이 있으나 사이클은 없는 그래프 DAG(Directed Qcyclic Graph)** 여야 함
    * A -> B : O
    * A -> B, B -> A : X
* 구현 방법
    * DFS
    * indegree 배열
* 시간 복잡도 : O(V + E)
    * 정점의 개수 + 간선의 개수만큼 소요됨



##### indegree 배열

| 변수                      | 용도                                         |
| ------------------------- | -------------------------------------------- |
| List<List<Integer>> array | 그래프의 관계를 표현하는 2차원 인접 리스트   |
| int[] indegree            | 해당 노드는 가리키고 있는 간선의 개수를 담음 |
| Queue<Integer> visit      | indegree가 0이 된 노드들을 담기 위한 배열    |

* **indegree가 0인 노드** 부터 방문 
  * = 자신을 가르키는 노드(간선)이 없어졌기 때문에 
  * = 선행 노드 이미 방문 완료했다는 의미



```java
int N = 10;	// 노드의 개수
int M = 7; // 관계의 개수

// 해당 노드를 가르키는 간선의 개수 담음
int[] indegree = new int[N + 1];

// 그래프의 관계를 나타냄
ArrayList<ArrayList<Integer>> nodes = new ArrayList<ArrayList<Integer>>();

// 방문 순서
Queue<Integer> visit = new LinkedList<Integer>();

// 그래프 초기화
for (int i = 0; i < N + 1; i++) {
	nodes.add(new ArrayList<Integer>());
}

for (int i = 0; i < M; i++) {
	int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

	nodes.get(info[0]).add(info[1]);
	indegree[info[1]] += 1;
}


// 선행 노드가 없는 노드부터 차례대로 방문하려 담음
for (int i = 1; i < N + 1; i++) {
	if (indegree[i] == 0) {
		ques.add(i);
	}
}

while (!ques.isEmpty()) {
	int before = ques.poll();

	for (int after : nodes.get(before)) {
    // 간선의 개수 감소시켜줌
		indegree[after]--;

		if (indegree[after] == 0) {
			ques.add(after);
		}
	}
}
```

<br>

##### DFS 

| 변수                      | 용도                                                         |
| ------------------------- | ------------------------------------------------------------ |
| List<List<Integer>> array | 그래프의 관계를 표현하는 2차원 인접 리스트                   |
| boolean[] visited         | 노드 방문 체크 여부                                          |
| ArrayList<Integer> sorted | 위상 정렬 노드 저장<br />알고리즘 끝나면 연결 리스트에 노드들이 위상정렬된 순서로 들어있음 |

```java
public void topologicalSort() {
  for (int i = 0; i < array.length; i++) {
    // 방문하지 않은 노드에 대한 DFS
    if (!visited[i]) {
      dfs(i);
    }
  }
}

public void DFS(int start) {
  // 방문 여부 체크
  visited[start] = true;
  
  // 현재 노드와 인접해있는 노드 탐색하여 DFS
  for (int end : array.get(start)) {
    if (!visited[end]) {
      DFS(end);
    }
  }
  
  // 연결 리스트의 맨 앞에 노드 삽입
  // 한 노드에서 재귀가 다 끝나야지만 추가 가능
  sorted.add(start);
}
```



<br>


### Main_1916 - 최소비용 구하기

#### 그래프 최단 경로 구하기

* 조건
  * 가중치가 있는 방향 그래프
  * 무방향 그래프가 주어진다면, 각 간선에 대해 양쪽으로 방향이 있는 방향 그래프로 생각
* 두 정점 사이의 최단경로
  * 두 정점 사이의 경로들 중 간선의 가중치 합이 최소인 경로
  * 간선 가중치의 합이 음수인 싸이클이 있으면 안됨

* 종류
  * 단일 시작점 / 단일 끝점 최단 경로
  * 단일 끝점 최단경로
    * 끝 노드만 정해져있고, 다른 모든 노드에서 시작하여 최단경로 구함
  * 단일 시작점 최단경로
    * 시작 노드만 정해져있고, 다른 모든 노드로 끝나는 최단 경로 구함
    * **다익스트라 알고리즘**
      * ***음의 가중치를 허용하지 않음*** : 가중치가 음수가 되면 올바른 값 도출 불가능
    * **벨만-포드 알고리즘**
      * ***음의 가중치 허용***
    * 사이클이 없는 그래프의 최단 경로
  * 모든 쌍 최단 경로
    * 모든 노드 쌍 사이의 최단경로를 모두 구한다
    * **플로이드-워샬 알고리즘**

<br>

#### 다익스트라 알고리즘(Dijkstra Algorithm)

* 그래프 내의 **특정 정점에서 갈 수 있는 모든 정점들까지의 최단 경로** 구하는 알고리즘
  * 최단경로를 여러개의 최단거리로 나타낼 수 있음
  * 하나의 최단 거리를 구할 때, 그 이전까지 구했던 최단 거리 정보를 그대로 사용한다.
    * 완화/이완(Relaxation) : 현재 경로 값보다 더 작은 경로 값을 가지는 경로가 존재한다면 값 갱신
* 그래프가 큰 경우에도 사용 가능함
* 간선의 **가중치는 모두 양수** 여야 함
* 시작점이 주어짐
* 구현 방법
  * 우선순위 큐
    * 시간 복잡도 : O((V+E)logV)
      * 모든 간선(E)을 검사하며 정점을 우선수위 큐에 넣고 뺀다.
        * 모든 간선 검사 : O(E)
        * 모든 간섬 검사 시 우선순위 큐에 노드 삽입/삭제하는 경우 : O(ElgE)
  * Array 이용
    * 시간 복잡도 : O(V^2)
      * V는 정점위 개수



###### 구현 방법

1. 출발 노드 설정
   * 초기에 최단 경로 구하지 않은 상태 = 무한대로 표시
2. 출발 노드 기준으로 각 노드의 최소 비용 저장
   * 특정 정점 / 출발 노드와 특정 정점 사이의 가중치 비교하여 더 작은 겂으로 업데이
3. 방문하지 않은 정점 중 가장 비용이 적게 드는 정점 선택
4. 해당 정점을 거쳐 특정 점점으로 가는 경우 고려하여 최소 비용 갱신
   * 2번 과정 수행
5. 3 - 4번 과정 반복



##### 우선순위 큐

| 변수                   | 용도                                 |
| ---------------------- | ------------------------------------ |
| PriorityQueue<Node> pq | 방문한 간선을 가중치 최솟값으로 정렬 |
| int[] dist             | 최단 거리 저장 집합                  |



```java
int N; // 정점의 개수

// 정점에 대한 추정 거리를 담을 배열 
int[] dist = new int[N];

// 최대값으로 초기화
Arrays.fill(dist, Integer.MAX_VALUE);
// 시작 정점인 자기 자신에 대한 거리는 0
dist[start] = 0;

// 간선의 가중치 최소값 정렬
PriorityQueue<Node> pq = new PriorityQueue<>();
// 시작 정점 넣음 : start -> start, weight : 0
pq.add(new Node(start, weight));

while(!pq.isEmpty()) {
  Node node = pq.poll();
  
  for (Node next : graph.get(node.end)) {
    // 기준 정점으로부터 인접 정점
		int nextEnd = next.end;
    // 인접 정점에 대한 새로운 가중치
		int newWeight = dist[current.end] + next.weight;
    
    // 현재 경로의 값보다 더 작은 경로 값 발견하면 갱신 : Relaxation
    if (newWeight < dist[nextEnd]) {
      dist[nextEnd] = newWeight;
      // 다음 정점으로 추가
      pq.add(new Node(nextEnd, dist[nextEnd]));
    }
  }
}
```







