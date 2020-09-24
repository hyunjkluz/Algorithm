

# Heap 문제 속 주요 Idea 정리

### Heap

* 완전 이진 트리의 일종
  * 트리 구조이기 때문에 삽입/삭제는 매우 빠름 : O(logN)
* 완전히 정렬되지 않은 상태
* 최대힙과 최소힙으로 나누어짐
  * 최대힙 : 부모 노드가 가장 큰 것
  * 최소힙 : 부모 노드가 가장 작은 것



#### Java Heap

* 자바의 Heap 영역에는 주로 긴 생명주기를 가지는 데이터들이 저장됨
  * 모든 App Memory - Stack 안 Data = Heap
* 모든 Object type(Integer, String, ArrayList, ... 등)은 Heap 영역에 생성됨
* 스래드 생성 개수에 상관 없이 단 하나의 Heap 영역만 존재함
* **Heap 영역에 있는 객체를 가리키는 레퍼런스 변수가 Stack에 올라감**

<br>

<br>

### DoublePriority - 이중우선순위큐

#### HashSet

* **중복 값을 허용하지 않는 Set** 인터페이스를 구현한 컬렉션

  * 중복된 값을 저장하려할 때 *false* 반환

* 객체 생성 시 따로 인스턴스를 지정해주지 않는다면, 저장할 때 인스턴스가 다르면 서로 다른 객체로 인식한다.

  ```java
  HashSet hashSet = new Hashset();
  
  hashSet.add("1");
  hashSet.add(1);		// return true
  ```

  * 1을 표현하는 것이 <u>String 인스턴스</u>와 <u>Integer 인스턴스</u>이기 때문에 서로 다른 객체로 취급한다. -> 둘 가 저장 가능
  * 각 인스턴스들을 다른 것으로 인식시키기 위해서는 **equals()**와 **hashCode()** 메소드를 재정의해줘야 한다.
    * [equals()와 hashCode()와의 관계](#equals() 와 hashCode()의 관계)
    * [재정의 시 주의해야할 점](#재정의 시 주의해야할 점)

* 저장 순서를 유지하지 않음

  * 저장 순서와 가져오는 순서가 다름
  * 저장 순와 가져오는 순서가 같아야할 때(저장 순서 중요할 때) **LinkedHashSet** 사용

<br>

##### equals() 와 hashCode()의 관계

* equals() 
  
* 인수로 전달 된 다른 객체가 현재 인스턴스와 **"동일"** 한지 여부를 나타내는 **java.lang.Object** 에서 제공하는 메소드
  
  * Custome Class의 객체를 비교할 때 equals()에 객체의 동일성 여부를 판단할 수 있는 조건을 넣어 재정의해야한다.
  
  ```java
  // 예시
  @Override
  public boolean equals(Object obj) {
      if (obj == null) return false;
      if (!(obj instanceof Student))
          return false;
      if (obj == this)
          return true;
      return this.getId() == ((Student) obj).getId();
  }
  ```
  
  

* hashCode()
  * 객체 메모리 주소의 정수 표현(해시코드)을 반환하는 **java.lang.Object** 에서제공하는 메서드
  * 각 인스턴스에 대해 고유 한 임의의 정수를 반환



_"**equals (Object)** 메서드 에 따라 두 객체가 같으면 두 객체 각각에 대해 **hashcode ()** 메서드 를 호출 하면 동일한 정수 결과가 생성되어야한다."_



##### 재정의 시 주의해야할 점

* HashSet 안에있는 요소를 검색 할 때 먼저 요소의 해시 코드를 생성하고, 이 해시 코드에 해당하는 객체를 찾는다.
* equals()를 이용한 비교에 의해서 **true**를 얻는 두 객체는 **hashCode()를 호출한 결과도 같아야한다.**
* equals()를 이용한 비교에서 **false**를 얻는 두 객체는 hashCode() 호출 시 **다른 int값을 반환하는 것이 좋다.**
  * 서로 다른 객체에서 해시코드 중복 발생 시, 해싱 사용하는 컬렉션에서 검색 속도가 떨어진다 : _HashMap, HashTable_...
* 두 객체가 같으면 해시 코드가 같아야한다.
  * 두 개체가 동일한 해시 코드를 가지고 있다고해서 두 개체가 같다는 의미 X
* equals()만 재정의 하면 해싱 데이터 구조 비교시 정확도가 떨어진다 :  _HashSet, HashMap, HashTable_... 등

<br>

<br>

#### TreeSet

* **이진 검색 트리(binary search tree)** 형태의 자료구조
  * 하나의 부모 노드에 최대 두개의 자식 노드가 올 수 있음
    * 왼쪽 노드의 값 < 부모 노드 < 오른쪽 노드의 값 
  * 정렬, (범위)검색에 높은 성능을 보여줌
    * 순차적으로 저장하지 않기 때문에 삽입, 삭제는 시간이 많이 걸림
    * 대소문자가 섞여있으면 의도하지 않은 검색 결과를 얻을 수 있으니, 가능하면 _대문자/소문자로 통일_시키는 것이 좋다.
* **중복 값을 허용하지 않는 Set** 인터페이스를 구현한 컬렉션
* 저장 순서를 유지하지 않음
* 객체간의 비교 시 **Comparable** 구현 / **Comparator** 제공 필요
  * 제공하지 않았을 때 TressSet에 객체 저장 시 예외 발생



* 주요 메소드

| Return Type      | Method                                                       | Desc                                                         |
| ---------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| SortedSet        | headSet(Object toElement)                                    | 지정된 객체보다 작은 값의 객체들을 반환                      |
| NavigableSet     | headSet(Object toElement, boolean inclusive)                 | 지정된 객체보다 작은 값의 객체들을 반환 <br>inclusive가 true이면 같은 값의 객체도 포함 |
| SortedSet        | subSet(Object fromElement, Object toElement)                 | 범위검색( fromElement 와 toElement 사이)의 결과를 반환 <br>( toElement 는 범위에 포함되지 X) |
| NavigableSet< E> | subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) | 범위검색( fromElement 와 toElement 사이)의 결과를 반환 <br>(fromInclusive가 true면 시작값이 포함되고, toInclusive가 true면 끝 값이 포함된다.) |
| Object           | pollFirst()                                                  | Tree의 첫번째 요소 반환                                      |
| Object           | pollLast()                                                   | Tree의 마지막 요소 반환                                      |














