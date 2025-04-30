package lambda;

// 자바는 람다식을 사용하면 자바도 JS 와 동급의 생산성을 확보할 수 있음
// 람다식을 사용하려면 반드시 인터페이스가 있어야 함
    // 자바가 람다식으로 사용할 인터페이스들을 미리 만들어 놓았다
    //  ㄴ Consumer : 입력과 출력 모두 없다
    //  ㄴ Supplier : 입력은 없고 출력은 있다
    //  ㄴ Function : 입력도 있고 출력도 있다
    //  ㄴ Predicate : 입력은 있고 출력은 boolean

import java.util.*;
import java.util.function.*;

public class Example4 {
  public static void main(String[] ar) {
    Consumer consumer1 = a->System.out.println(a);

    // 람다식에서 함수를 하나만 호출하는 경우 파라미터를 생략하고 사용하는 표현식
    Consumer consumer2 = System.out::println;

    // 자바 Stream : List 를 전개해서 람다식을 적용하는 문법
    List<Integer> list = Arrays.asList(11,22,33,44,55,66,77);
    list.stream().forEach(a->System.out.println(a));
  }
}
