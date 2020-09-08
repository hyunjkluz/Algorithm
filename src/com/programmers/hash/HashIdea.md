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