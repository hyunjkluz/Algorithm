# Hash 문제 속 주요 Method 정리



### CompletePlayer - 완주하지 못한 선수

```java
java.util.HashMap
  
public V getOrDefault(Object key,V defaultValue)
```

* 찾고자하는 key 값이 map에 있으면 해당 key의 vlaue 반환, 없으면 defaultValue 반환



```java
public V putIfAbsent(K key, V value)
```

* 찾고자하는 key 값이 없다면 입력 된 key와 value 를 입력, 해당 key가 존재하면 입력 되었던 값 반환




<br/>
<br/>



### PhoneBook

```java
java.io.*

public boolean startsWith(String prefix, int toffset)
```

1. prefix : 비교할 대상

2. toffset : 문자 비교시 어느 위치부터 비교 시작할 것인지

* 문자열 비교시 비교할 대상이 해당 문자열보다 길 경우 자동으로 'false' 반환
* toffset 사용 X : 문자 맨 처음부터 비교
* toffset 사용 O : 지정한 순서부터 문자열 비교





<br/><br/>
### BestAlbum - 베스트 앨범

#### Stream

* Java 8부터 추가된 반복자 (이전까지는 Iterator 사용)

* 컬렉션의 저장 요소를 하나씩 참보하여 람다식으로 처리할 수 있도록 해줌

* 내부 반복자를 사용하여 병렬처리가 쉽다

  * **외부 반복자** : 개발자가 직접 코드로 반복적으로 컬렉션의 요소를 가져오는 코드 패턴

    ```java
    for(int i = 0; i < length; i++) {
      int n = list.get(i);	// 외부 반복자
    }
    ```

  * **내부 반복자** : 컬렉션 내부에서 요소들을 반복 + 개발자는 요소당 처리해야할 로직만 제공하는 코드 패턴

    * 개별 요소 처리 로직에만 집중할 수 있음
    * 병렬 처리 작업 수행 가능
      * **병렬 처리** : 한 가지 작업을 여러 sub로 나눔 -> 나누어진 sub 작업은 분리된 스레드에서 병렬적으로 처리 -> sub 작업들의 결과를 최종적으로 병합

* 중간 처리와 최종 처리 가능

  * 중간 처리 : 매핑, 필터링, 정렬 ...
  * 최종 처리 : 반복, 계산(카운트, 평균 ,총합), 변환 ...

* Stream 종류

  * Stream, IntStream, LongStream, DoubleStream

<br>

##### Get Stream

```java
// 1) Connection 객체에서 Stream 얻기
List<Song> songList = Arrays.asList( ... );
Stream<Song> listStream = songList.stream();

// 2) Array에서 Stream 얻기
String[] array = { ... };
Stream<String> arrayStream = Arrays.stream(array);

// 3) 숫자 범위에서  Stream 얻기
IntStream intStream = IntStream.range(0, 100);	//rangeClosed(s, e) : s<= n <= e
```

* 이 외에 추가적인 방법들 존재





##### 주요 메소드

* [전체 메소드 확인](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
* 중간 처리 메소드
  * Return Type은 Stream 종류 중 하나

| Return Type | Method                                        | Desc                                                    |
| ----------- | --------------------------------------------- | ------------------------------------------------------- |
|             | distinct()                                    | 중복제거<br>equals() 메소드에서 true == 동일 객체       |
|             | filter(x -> ...)                              | 조건 필터링<br>매개변수의 함수에서 true 반환시 필터링   |
|             | map(x -> ...)<br>mapToInt(x -> ...)           | 요소를 대체하는 요소로 구성된 새로운 스트림을 리턴한다. |
|             | sorted()<br />sorted((a, b)-> a.compareTo(b)) | 객체를 Comarable 구현 방법에 따라 정렬                  |



* 최종 처리 메소드
| Return Type | Method                                             | Desc                                            |
| ----------- | -------------------------------------------------- | ----------------------------------------------- |
| long        | count()                                            | 요소 개수 집계                                  |
| Optional<T> | max(), max(compatator)<br />min(), min(compatator) | 최대, 최소 요소 집계                            |
| R           | collect(Collector<T, A, R> collector)              | 매핑된 요소들로 구성된 새로운 컬렉션 생성<br /> |
|             | forEach(...)                                       | 각 요소를 조회                                  |

* Collector<T, A, R> collector : Collection.toList() , toSet(), toMap() ...
  * 