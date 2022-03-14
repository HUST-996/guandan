import javax.swing.*;

public class WrongThread extends Thread{
    public void run(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
