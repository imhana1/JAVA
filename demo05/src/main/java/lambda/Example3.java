package lambda;

interface Calc {
  int doCalc(int x, int y);

}



public class Example3 {
  public static void main(String[] ar) {
    // 익명 객체를 이용한 덧셈 객체 생성
    Calc add = new Calc() {
      @Override
      public int doCalc(int x, int y) {
        return x + y;
      }
    };

    // 람다 식으로 뺄셈 객체를 생성하자
    // 원래는 Clac add= new Clac() { @Override public int doClac 까지 생략하지만
    // 람다로 짰을 때 한 줄일 때는 return 도 생략 가능
    Calc sub = (x, y) -> x - y;

  }
}
