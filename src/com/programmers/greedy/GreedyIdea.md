

# 탐욕법(Greedy) 문제 속 주요 Idea 정리

**Greedy** : 문제 해결 과정 중, 그 순간순간마다 최적이라고 생각하는 방법을 결정하여 최종 해답에 도달한다.
* 최선의 선택이 최선의 해결책이 되진 않지만 .. 이 해결법은 **계산 속도가 매우 빠르다.**
* DP(Dynamic Progarmming)과 서로 보완하는 개념



<br>

<br>

### GymSuit - 체육복

```java
List<Integer> lists = new ArrayList<Integer>();
Integer findNum = 1;

Integer number = lists.stream()
  .filter(n -> findNum.equals(n))
  .findAny()
  .orElse(null);
```

* Java 8 Steam API를 이용하여 리스트 내 객체를 찾는 방법
* filter() : 어떤 기준에 부합하는 값을 찾을것인지
* findAny() : filter의 기준에 맞는 첫번째로 매치되는 객체를 리턴
* orElse() : 기준에 맞는 값이 없을 경우 리턴되는 값

<br>

```java
// Java 8
Interface Collection<E>
  
public boolean removeIf(Predicate<? super E> filter);

// 사용 예시 : 짝수인 값 리스트에서 제거
lists.removeIf(n -> (n % 2 == 0));
```

* return default boolean : 필터 조건에 맞는 객체가 지워졌을 시 true 반환
* 만약 collection에서 값이 지워지지 않았을 때, *UnsupportedOperationException* 발생

<br>

##### ConcurrentModificationException 

이 문제를 풀면서 '런타임 에러'로 나왔단 예외이다.

이 예외는 List를 for문에 직접 넣고 돌리여 remove를 호출했을 때 발생한다.

```java
for (Integer i : lists) {
  lists.remove(i);
}
```

반복문을 돌리면서 remove를 호출하면 remove 호출로 인해 기존 idx값이 달라지게된다. size가 계속해서 바뀌기(줄어들기)때문이다.

해결방법

* 삭제해야할 값을 담을 리스트를 만들어 반복문이 끝난 후 한꺼번에 제거(해당 문제에 적용한 방법)

  ```java
  public boolean removeAll(Collection<?> c)
  ```

  * 인자로 받은 Collection이 포함하고 있는 객체를 리스트에서 삭제

* for ( ; ; ) 사용

* 반복자(Interator)사용

  ```java
  Iterator<Integer> iter = lists.iterator();
  while (iter.hasNext()) {
      Integer s = iter.next();
      iter.remove();
  }
  ```

  
  
  <br>

### BigNumber - 큰 수 만들기

#### String vs StringBuilder vs StringBuffer

##### String

* Immutable 객체(변하지 않음)이기 때문에 문자열 재할당, 문자열 더해서 새로운 문자열 만들기 등 문자열을 변경하면 변경된 문자열을 위해 새로운 문자열을 만들어낸다. 
  * String은 내부에서 값을 *final char[]* 형태로 저장해두기 때문이다.
* **즉, 새로운 값을 할당할 때마다 String이 새로 생성된다.**
* String + String은 메모리 할당/해제를 발생시킴 -> 연산이 많아지면 성능 저하가 올 수 있음 !
  * 반복문에서 Sting을 계속 더하면, 각 String의 주소 값이 Stack에 쌓이고, class는 GC(Garbage Collector)가 호출되기 전 까지 heap에 지속적으로 쌓이게됨 !
* 하지만 멀티 스레드 환경에서 값 동기화를 지원(Thread-safe)하기 때문에, 내부 데이터를 자유롭게 공유 가능하다.

##### StringBuilder

* 변경 가능한 문자열 클래스(**동일 객체 내에서 문자열 변경 가능**)
* 값은 memory에 **append**하는 방식으로 클래스를 직접생성하지 않는다.
* 즉, 문자열을 더할 때 새로운 객체 생성이 아닌 **기존의 객체에 더하는 방식**이다.
* 최대 용량을 초과하면 *ArgumentOutOfRangeException* 또는 *OutOfMemoryException* 발생된다.
* 변경가능한 문자열이지만 멀티 스레드 환경에서 값 동기화(synchronization)를 지원하지 않는다.
  * 단일 스레드 환경에서 성능 뛰어남
* 문자열의 추가,수정,삭제가 빈번하게 발생할 경우 사용한다.

##### StringBuffer

* StringBuilder와 대부분 비슷하나, StringBuilder와 다르게  멀티 스레드 환경에서 값 동기화를 지원한다.