# DFS(깊이 우선 탐색) 문제 속 주요 Idea 정리

### 그래프(Graph)

* 형상이나 사물을 vertex(정점)과 edge(간선)으로 표현한 것
* Graph G = (V, E)
  * V : 정점 집합
  * E : 간선 집함
* 두 정점이 간선으로 연결되어 있으면 인접(adjacent)하다고 한다
  * 간선은 두 정점dml 관계를 나타낸다.

<br><br>

### 깊이 우선 탐색(DFS : Depth-Fisrt Search)

* 그래프를 순회하는 방법 중 하나로써, 루트 노드(또는 다른 임의의 노드)부터 시작하여 다른 branch로 넘어가기 전 해당 branch를 완벽하게 탐색하는 방법
  * 트리를 순회하는데 있어서 **깊이(deep)**를 우선으로 순회하는 방법
* 갈 수 있을때 까지 가다가 더이상 갈 수 없으면 직전의 갈림길(가장 가까운 갈림길)로 돌아가서 다른 방향으로 다시 탐색 진행
* 트리 순회 방법
  * preorder :  
* BFS(넓이 우선 탐색)보다 간단하지만, 단순 검색에서는 BFS보다 느리다.
* DFS는 **순환 알고리즘 형태**를 가지고 있다 : 어떤 노드를 방문했는지의 여부를 꼭 검사해야한다. 
  * 구현방법 1) 재귀함수 : 임의의 node 탐색 종료 후, 그 node와 인접한 node가 있으면 재귀호출
  * 구현방법 2) 명시적인 스택 : 스택에 방문한 정점 push / pop

```
DFS(v) 
{
	for each v in V
		visited[v] = NO;
	for each v in V
		if(visited[v] = NO) then aDFS(v);
}

aDFS(v)
{
  visited[v] = YES;
  for each x in L(v)		// L(v) : 정점 v의 인접 리스트
    if(visited=[x] = NO) then aDFS(u);
}
```

* 수행 시간(V : 정점의 수, E : 간선의 수)
  * 인접 리스트로 표현된 그래프: O(|V|+|E|)
  * 인접 행렬로 표현된 그래프: O(N^2)







<br>

<br>