# 완전탐색 문제 속 주요 Method 정리

**Brute Force Algorithm** : 모든 경우의 수를 전부 ckw아서 답을 찾는 알고리즘

* 자원만 받쳐준다면 항상 100%의 정확도를 보장
* 완전 탐색 기법의 종류
  * Brute Force : for과 if를 이용하여 처음부터 끝까지 탐색
  * 재귀함수 : 특정 함수 내에서 자기 자신을 다시 호출하여 문제 해결
  * 순열 : 서로 다른 n개의 원소에서 r개의 중복을 허용하지 않고 순서대로 늘어 놓은 수
  * 비트 마스크 : 이진수 표현을 자료구조로 쓰는 기법 (AND, OR, XOR, SHIFT, NOT)
  * BFS(너비우선탐색), DFS(깊이우선탐색)





<br>

<br>

### PracticeTest - 모의고사

재귀함수를 적용하여 풀었으나 BF 방법으로도 가능하다.

```java
import java.util.Collections;
  
public static <T extends Object & Comparable> T max(Collection coll);
public static <T extends Object & Comparable> T min(Collection coll);
```

* **Collections** 클래스의 정적 메소드
* 파라미터 리스트의 최대 / 최소 값을 찾아서 반환
* max() method 사용 시 리스트가 비어있을 경우 *NoSuchElementException* 에러 발생

* * 


<br>

<br>

### FindDecimal - 소수 찾기

|           | 조합 | 중복조합 | 순열 | 중복순열 |
| --------- | ---- | -------- | ---- | -------- |
| 순서 구분 | X    | X        | O    | O        |
| 중복 허용 | X    | O        | X    | O        |

* **순열**은 순서를 구분하기 때문에 [ 0, 1, 2]와 [ 1, 2 0]을 다른 경우로 본다.
  * **순서있게 배열**
  * 즉 다음 뽑을 원소를 찾을 때 처음부터(0번부터) 찾는다.
* **조합**을 순서를 구분하지 않기 때문에 [ 0, 1, 2]와 [ 1, 2 0]를 같은 경우로 본다.
  * **순서와 상관 없이 뽑는 거에만 집중**
  * 즉 다음 원소를 뽑을 때 본인 다음 번호부터 찾는다.

* 순열과 조합은 원소가 사용되었는지 확인하지만, **중복순열/조합**은 원소 사용 여부를 확인하지 않고 뽑는다.
  * 자기 자신을 포함 하냐, 하지 않느냐의 차이

<br>

#### 조합, 중복조합, 순열, 중복순열 예시 코드

```java
public static String[] BOX = { "A", "B", "C", "D" };

	public static void main(String[] args) {
		int n = BOX.length;
		int r = 2;

		boolean[] visited = new boolean[n];
		Arrays.fill(visited, false);

		System.out.println("[조합]");
		combination(new String[r], n, r, 0, 0);

		System.out.println("\n[중복조합]");
		repeatedCombination(new String[r], n, r, 0, 0);

		System.out.println("\n[순열]");
		permutation(new String[r], n, r, 0, visited);

		System.out.println("\n[중복순열]");
		repeatedPermutation(new String[r], n, r, 0);

	}

	/**
	 * 조합
	 * 
	 * @param comb    조합의 결과를 담을 배열
	 * @param n       총 뽑힐 개수(배열 안에서 뽑는거기 때문에 뽑을 값이 담긴 배열의 길이로 지정하지만 경우에 따라 바꿀 수 있음)
	 * @param r       총 뽑을 개수
	 * @param current 현재까지 뽑은 개수
	 * @param start   그 다음 반복문 시작 값
	 */
	public static void combination(String[] comb, int n, int r, int current, int start) {
		if (r == current) {
			System.out.println(Arrays.toString(comb));
			return;
		}
		for (int i = start; i < n; i++) {
			comb[current] = BOX[i];
			combination(comb, n, r, current + 1, start + 1);
		}
	}

	/**
	 * 중복조합
	 * 
	 * @param comb    조합의 결과를 담을 배열
	 * @param n       총 뽑힐 개수(배열 안에서 뽑는거기 때문에 뽑을 값이 담긴 배열의 길이로 지정하지만 경우에 따라 바꿀 수 있음)
	 * @param r       총 뽑을 개수
	 * @param current 현재까지 뽑은 개수
	 * @param start   그 다음 반복문 시작 값
	 */
	public static void repeatedCombination(String[] comb, int n, int r, int current, int start) {
		if (r == current) {
			System.out.println(Arrays.toString(comb));
			return;
		}
		for (int i = start; i < n; i++) {
			comb[current] = BOX[i];
			combination(comb, n, r, current + 1, i); // 나왔던 값이 또 나와도 되기 때문에 start 값 증가 X
		}
	}

	/**
	 * 순열
	 * 
	 * @param perm    순열의 결과를 답을 배열
	 * @param n       총 뽑힐 개수(배열 안에서 뽑는거기 때문에 뽑을 값이 담긴 배열의 길이로 지정하지만 경우에 따라 바꿀 수 있음)
	 * @param r       총 뽑을 개수
	 * @param current 현재까지 뽑은 개수
	 * @param visited 해당 위치의 값을 뽑았는지 아닌지(방문 여부)를 확인하기 위한 배열
	 */
	public static void permutation(String[] perm, int n, int r, int current, boolean[] visited) {
		if (r == current) {
			System.out.println(Arrays.toString(perm));
			return;
		}
		for (int i = 0; i < n; i++) { // 뽑는 순서가 상관 없기 때문에 처음부터 뽑음
			if (!visited[i]) { // 방문 여부 판단
				visited[i] = true;
				perm[current] = BOX[i];
				permutation(perm, n, r, current + 1, visited);
				visited[i] = false;
			}
		}
	}

	/**
	 * 중복순열
	 * 
	 * @param perm    순열의 결과를 답을 배열
	 * @param n       총 뽑힐 개수(배열 안에서 뽑는거기 때문에 뽑을 값이 담긴 배열의 길이로 지정하지만 경우에 따라 바꿀 수 있음)
	 * @param r       총 뽑을 개수
	 * @param current 현재까지 뽑은 개수
	 */
	public static void repeatedPermutation(String[] perm, int n, int r, int current) {
		if (r == current) {
			System.out.println(Arrays.toString(perm));
			return;
		}
		for (int i = 0; i < n; i++) {
			perm[current] = BOX[i];
			repeatedPermutation(perm, n, r, current + 1);
		}
	}
```


