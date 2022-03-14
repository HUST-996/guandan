import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageFilter;

public class MyPanel extends JPanel {
    public MyPanel(){
        this.setLayout(null);
    }
    @Override
    protected void paintComponent(Graphics g){
        Image image = new ImageIcon("src/res/game_bg.png").getImage();

        g.drawImage(image,0,0,this.getWidth(),this.getHeight(),null);
    }
}
