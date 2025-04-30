package lambda;

import java.util.*;

public class Example5 {
  public static void main(String[] ar) {
    List<Integer> list = Arrays.asList(11,22,33,44,55,66,77);

    // 짝수만 추출하자   조건: a%2==0
    list.stream().filter(a->a%2==0).forEach(a->System.out.println(a));

    // 짝수만 추출해서 결과 ArrayList 를 만들어라
    List<Integer> result1 = new ArrayList();
    for (Integer a:list) {
      if (a % 2 == 0)
        result1.add(a);
    }

    List<Integer> result2 = list.stream().filter(a->a%2==0).toList();

    // list 에서 짝수만 골라서 제곱한 다음 출력
    list.stream().filter(a->a%2==0).map(a->a*a).forEach(a->System.out.println(a));

    // list 에서 70이상을 골라서 제곱한 다음 출력
    list.stream().filter(a->a>=70).map(a->a*a).forEach(a->System.out.println(a));
  }
}
