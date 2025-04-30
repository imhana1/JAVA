package lambda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// JS 는 btn.addEventListener(이벤트이름, 함수)

class MyFrame2 extends JFrame {
  private JPanel panel = new JPanel();
  private JButton btn = new JButton("빨강색으로");
  private JButton blueBtn = new JButton("파란색으로");

  public MyFrame2() {
    // JS 라면 btn.addEventListener('click', 이벤트 처리 함수);
    // 자바는 이벤트 처리 함수인 ActionPerformed 가 ActionListener 인터페이스 내부에 약속되어 있다
    // 자바의 모든 함수는 클래스나 인터페이스 소속이다 그래서 객체부터 생성해야함
    // 개발자가 자기 마음대로 ActionPerformed 를 만드는 것이 아니라 인터페이스부터 사용해야함
    //      마음대로 만들게 되면 자바는 실행을 해 줄 수가 없음
    //      자바가 볼 때 함수는 반드시 정확한 객체의 메소드여야 함
    btn.addActionListener(new ActionListener() {
      // 인터페이스의 객체를 어떻게 만들지? 추상 메소드를 생성하면서 객체를 생성하면 됨
      // 익명 객체 : 추상 메소드를 생성하면서 객체를 바로 생성하는 것
      @Override
      public void actionPerformed(ActionEvent e) {
        panel.setBackground(Color.red);
      }
    });

    // blueBtn.addActionListener() 에서 파라미터는 ActionListener 객체여야 한다는 걸 자바가 안다
    // 화살표 함수 형식으로 메소드만 정의하면 자바가 객체를 생성하자는 문법을 만들어낸다
    // 자바에서 람다를 사용하면 객체를 자바가 알아서 생성함
        // 인터페이스가 있기에 가능함 그래서 코드가 간결해지기에 람다 사용을 선호함
    blueBtn.addActionListener((ActionEvent e) -> panel.setBackground(Color.blue));

    add(panel);
    add(blueBtn,BorderLayout.NORTH);
    add(btn, BorderLayout.SOUTH);
    setSize(400, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

  }

}
public class Example2 {
  public static void main(String[] ar) {
    new MyFrame2();
  }
}
