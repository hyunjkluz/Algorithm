# 문자열 문제 속 주요 Method 정리

<br>

### 9935 - 문자열 폭발

부분 문자열 제거 방법

1. Java `String` 클래스

- `replace()` Method

  ```java
  string.replace(char oldChar, char newChar)
  ```

  - oldChar : 교체하고 싶은 문자열
  - newChar : 이를 대체할 문자열

- `replaceAll()` Method

  ```java
  string.replaceAll(String regex, String replace)
  ```

  - regex : 교체하고 싶은 문자열 정규 표현식
  - replace : 이를 대체할 문자열

2. Java `StringBuffer` 클래스

- `replace()` Method

  ```java
  StringBuffer.replace(int start, int end, String str)
  ```

  - start : 대체할 문자열 구간 시작점
  - end : 대체할 문자열 구간 끝 점
    - start <= 범위 < end
  - str : 지정할 범위 내용 대체하는 문자열

<br>

<br>

### 1786 - 찾기

#### KMP 알고리즘

- 문자열 안에서 부분 문자열을 찾을 때 사용하는 알고리즘
- 부분 문자열 매칭 중 실패하였을 때
  - 바로 다음 칸으로 이동하여 비교를 다시 시작하는 것이 아니라(브루트포스)
  - **여러칸을 건너 뛰어서 다시 비교 시작**
  - 매칭이 실패하더라도 실패한 idx 이전에 매칭될 수 있는 부분 문자열이 있으면 거기서부터 다시 찾기 시작
  - 틀린 위치 부터 부분 문자열 다시 비교 시작
- 시간 복잡도 : O(N+M)
- [참고하기 좋은 더 자세한 설명](https://gusdnd852.tistory.com/172)

##### LPS Table (Logest Prefix & Suffix)

- **문자열 자기 자신을 제외한** 동일한 접두사(Prefix) 와 접미사(Suffix) 쌍의 최대 길이

<br>
<br>

### 16920 - 확장 게임

#### Character Class

- Character -> Integer

  ```java
  int n = Character.getNumericValue(charNum);
  ```

- Integer -> Character

  ```java
  char n = Character.forDigit(value, radix);
  // value : 캐릭터형으로 바꿀 숫자
  // radix : 기수 (10진수 = 10, 16진수 = 16, ...)
  // value나 radix가 유효하지 않은 경우 null 반환
  ```
