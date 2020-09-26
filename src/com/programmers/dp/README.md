

# DP(동적계획법) 문제 속 주요 Idea 정리

### Dynamic Programming

* **overlapping하는 sub-problem들을 풀어서** 원래 문제를 푸는 방식
  * 모든 작은 문제들은 한번만 풀어야 한다. 큰 문제를 풀 때 작은 문제의 답을 적어놓은 곳에서 가져와서 계산한다.
  * <u>작은 문제의 반복 + 같은 문제는 구할 때 마다 정답이 같다</u>
* 주어진 문제에 대한 순환식을 정의한다
  * 순환식
    * **memoization** : *top-down* 방식, 실제 필요한 sub-problem만을 푼다
      * top-down : 대부분 재귀함수로 구현. 큰 문제의 답을 구할 때 작은 문가 아직 풀리지 않았다면 그때야 작은 문제 해결
    * **dp** : *bottom-up* 방식, recursion에 수반되는 overhead가 없음
      * bottom-up : 작은 문제부터 차근차근 해결
* 일반적으로 최적화 문제(optimisation problem) 혹은 counting 문제에 적용됨



<br>

#### DP vs Divide and Conquer(분할 정복법)

* **분할정복법**의 분할된 sub-problem은 disjoint하다.
    * disjoint : 서로소(공통 원소가 없는 두 집합)
    * 즉, 작은 문제가 중복이 일어나지 않는다.
    * 단지 작은 문제로 나누어 푸는것일 뿐

* **동적계획법**의 두 sub-problem은 작은 문제들이 반복된다.
  * 작은 문제들의 답이 같다.






