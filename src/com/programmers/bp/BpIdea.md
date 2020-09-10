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