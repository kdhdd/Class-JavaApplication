package week4.타이머이벤트처리;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Myclass implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        System.out.println("beep");
    }
}

public class CallbackTest {
    public static void main(String[] args) {

        ActionListener listener = new Myclass();
        Timer t = new Timer(1000, listener);
        t.start();
        // 프로그램이 끝나지 않도록 시간을 더 충분히 줍니다.
        try {
            Thread.sleep(10000);    // 10초 대기
        } catch (Exception e) {

        }
    }
}
