# Queue/Stack 문제 속 주요 Method 정리





## Queue 개념

먼저 들어간 데이터를 먼저 꺼내는 FIFO(First In Forst Out) 구조이다.

최근 사용 문서, 인쇄 작업 대기목록, 스레드/네트워크 스케줄링, 메세지 관리, 버퍼(buffer)등의 기능을 구현할 때 활용한다.



Queue 인터페이스를 상속받는 하위 인터페이스

1. Deque
2. BlockingDeque
3. BlockingQueue
4. TransferQueue

**Deque** : 양방향으로 데이터 삽입/삭제 가능



#### 객체 생성

```java
import java.util.Queue;
import java.util.LinkedList;

Queue<T> queue = new LinkedList<T>();
```



#### 주요 메소드

| Return Type | Method          | Desc                                                         |
| ----------- | --------------- | ------------------------------------------------------------ |
| boolean     | add(Object o)   | 해당 객체를 Queue에 추가<br />성공하면 true, 저장 공간 부족하면 *IllegalStateException* 발생 |
| boolean     | offer(Object o) | 해당 객체를 Queue에 추가<br />성공하면 true, 실패하면 false  |
| Object      | peek()          | 삭제 없이 맨 앞의 요소 읽음<br />Queue가 비어있으면 null 반환 |
| Object      | element         | 삭제 없이 맨 앞의 요소 읽음<br />Queue가 비어있으면 *NoSuchElementException* 발생 |
| Object      | poll()          | Queue 에서 객체 꺼내서 반환<br />Queue가 비어있으면 null 반환 |
| Object      | remove()        | Queue 에서 객체 꺼내서 반환<br />Queue가 비어있으면 *NoSuchElementExceoption* 발생 |
| boolean     | isEmpty()       | Queue가 비어있으면 false, 비어있지 않으면 true               |







### Printer - 프린터

```java
import java.util.LinkedList; 
  
public boolean removeFirstOccurrence(Object o)
```

* 리스트 첫 부분 부터 끝까지 매개변수로 들어온 값과 일치하는 첫번째 값을 삭제



#### PriorityQueue 우선순위 큐

들어간 순서에 상관없이 일정한 규칙에 따라 우선순위를 선정하고 <u>우선순위가 가장 높은 데이터가 가장 먼저 나옴.</u>

저장공간으로 배열을 사용하며, 각 요소를 힙(heap)이라는 자료구조의 형태로 저장한다.



##### 우선순위 변경 - 기본 자료형

```java
// 오름차순 우선순위
PriorityQueue<Integer> queue = new new PriorityQueue<>();
// 내림차순 우선순위
PriorityQueue<Integer> queue = new new PriorityQueue<>(Collections.reverseOrder());
```



##### 우선순위 변경 - class

 Comparator 클래스나 Comparable 인터페이스를 이용

```java
class Sample implements Comparable<Sample> {
  public Integer priority;
  public String name;
  
  @Override
	public int compareTo(Sample target) { 
    /*
     * 우선순위에 따가 더 큰 값을 return하면 됨
     * EX) 오름차순 : 값이 크면 1, 같으면 0, 값이 작으면 -1
     */
		return this.priority <= target.priority ? 1 : -1;
	}
}
```







