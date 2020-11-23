# Queue 문제 속 주요 개념 정리





### Main_1766


#### 위상 정렬

* 그래프 순서 정렬
* 조건 : **방향성이 있으나 사이클은 없는 그래프 DAG(Directed Qcyclic Graph)** 여야 함
    * A -> B : O
    * A -> B, B -> A : X
* 구현 방법
    * DSF
    * indegree 배열



##### indegree 배열

| 변수                      | 용도                                         |
| ------------------------- | -------------------------------------------- |
| List<List<Integer>> array | 그래프의 관계를 표현하는 2차원 인접 리스트   |
| int[] indegree            | 해당 노드는 가리키고 있는 간선의 개수를 담음 |
| Queue<Integer> visit      | indegree가 0이 된 노드들을 담기 위한 배열    |

* indegree가 0인 노드부터 방문 
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


// 선행 노드가 없는 노드부처 차례대로 방문하려 담음
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


