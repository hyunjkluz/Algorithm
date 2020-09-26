# Sort 문제 속 주요 Method 정리



### KthNumber - K번째 수

```java
import java.util.Arrays;
  
public static <T> T[] copyOf(T[] original, int newLength)
```

* 원본 배열의 인덱스 0번부터 newLength 길이까지 배열 복사하여 리턴
* int, double, string 등 다양한 자료형 배열을 잘라서 복사할 수 있음



```java
import java.util.Arrays;

public static <T> T[]	copyOfRange(T[] original, int from, int to)
```

* 원본 배열의 인덱스 *from* 부터 *to(복사할 끝 인덱스 + 1)* 까지 값을 복사하여 리턴
* copyOf Method와 동일하게 다양한 자료형 배열을 다룰 수 있고 **값에 의한 복사**이기 때문에 복사된 배열에서 값을 바꿔도 <u>원본 배열의 값이 바뀌지 않음</u>





```java
import java.util.Arrays;

public static void sort(Object[] a);
```

* 배열을 오름차순 정렬(Comparable Interface의 compareTo Method를 기준으로 정렬)

* 내림차순 방법

  * **Comparable** 인터페이스의 **compareTo()** 메서드를 원하는 조건으로 오버라이드 : 내가 원하는 조건으로 정렬할 수 있음

  * 익명 인터페이스 **java.util.Comparator**를 구현한 Class내 **compare()** 메서드를 원하는 정렬조건으로 오버라이드 : 내가 원하는 조건으로 정렬할 수 있음

    ```java
    // 방법 1) 익명 인터페이스 구현 예시
    class CustomComparator implements Comparator<Integer> { 
      @Override 
      public int compare(Integer o1, Integer o2) { 
        return o2.compareTo(o1); 
      } 
    }
    // 방법 2) 생성과 동시에 메소드 오버라이드 - 
    Arrays.sort(stringArr,new Comparator<Integer>() { 
      @Override 
      public int compare(Integer o1, Integer o2) { 
        return o2.compareTo(o1); 
      } 
    });
    ```

  * FunctionalInterface **Comparator**, java.util.**Collections** 클래스의 **reverseOrder() 메서드**를 사용

    ```java
    Arrays.sort(Array,Comparator.reverseOrder()); 
    Arrays.sort(Array,Collections.reverseOrder()); 
    ```

    





### MaxNumber - 가장 큰 수

```java
Interface Comparable<T>

public int compareTo(String s);
public int compareTo(NumberSubClass i);
```

* **기준값A.compareTo(비교값B)** : 두개의 문자열을 비교 후 결과에 대한 int형 값 반환

  | 비교   | return                                         |
  | ------ | ---------------------------------------------- |
  | A == B | 0                                              |
  | A > B  | 문자 비교일 때 : 양수<br />숫자 비교일 때 : 1  |
  | A < B  | 문자 비교일 때 : 음수<br />숫자 비교일 때 : -1 |

* 문자열 맨 첫 자리부터 차례대로 비교

* 문자열 비교 시 각 문자열의 아스키코드값을 비교 : 각각의 아스키코드 값의 차이를 반환

* 문자열일 경우 대소문자를 구분하여 비교한다

  * 대소문자 무시 비교

    ```java
    public int compareToIgnoreCase(String s);
    ```

    