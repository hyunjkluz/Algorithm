# 문자열 문제 속 주요 Method 정리


<br>


### 9935 - 문자열 폭발

부분 문자열 제거 방법

1. Java  `String` 클래스


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

  * start : 대체할 문자열 구간 시작점
  * end : 대체할 문자열 구간 끝 점
    * start <= 범위 < end
  * str : 지정할 범위 내용 대체하는 문자열


