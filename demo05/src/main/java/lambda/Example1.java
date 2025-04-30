package lambda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// 프로그램의 사각형 틀(최소화, 최대화 버튼......) 을 제공하는 JFrame을 상속
// JPanel = 회색의 패널 공간 (색을 지정할 수 있음)
class MyFrame1 extends JFrame implements ActionListener {
  // 버튼을 클릭하면 JPanel 의 색을 빨강으로 바꾸자 (이벤트 처리)
  // 버튼을 클릭하면 함수를 실행하는 자바스크립트 (JS 에서 함수는 변수와 대등함. first class citizen)
  // document.getElementById(아이디).onclick=function() { 색 변경하는 코드 } 와 동일한 작업
  // 원래는 document.getElementById(아이디).addEventListener("click", function() { 배경색 바꾸는 코드; });

  // 같은 코드를 자바로 작성하려면...

  private JPanel panel = new JPanel();
  private JButton btn = new JButton("빨강색으로");

  public MyFrame1() {
    btn.addActionListener(this);
    // panel, btn 배치
    add(panel);
    add(btn, BorderLayout.SOUTH);
    setSize(400, 400);
    // 종료버튼 활성화
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true); // 보이게 변경

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    panel.setBackground(Color.red);
  }
}
public class Example1 {
  public static void main(String[] ar) {
    new MyFrame1();
  }
}
