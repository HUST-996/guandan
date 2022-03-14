import javax.swing.*;

public class PokerLabel extends JLabel {
    private Pai pai;
    private int id;
    private String name;
    private int num;
    private boolean isOut;
    private boolean isUp;
    private boolean isSelected;
    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public void setUp(boolean up) {
        isUp = up;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public boolean isOut() {
        return isOut;
    }

    public boolean isUp() {
        return isUp;
    }

    public PokerLabel(int id, int num, String name, boolean isSelected){
        this.id = id;
        this.num = num;
        this.name = name;
        this.isSelected = isSelected;
        this.setSize(56,74);
    }
    public PokerLabel(int id,int num,String name,boolean isOut,boolean isUp){
        this.id = id;
        this.num = num;
        this.name = name;
        this.isOut = isOut;
        this.isUp = isUp;
        this.setSize(56,74);
    }

    public PokerLabel(Pai pai){
        this.pai = pai;
        this.setSize(56,74);
    }

    public PokerLabel(){
        this.setSize(56,74);
    }

    public void setPai(Pai pai) {
        this.pai = pai;
    }

    public Pai getPai() {
        return pai;
    }
    public void turnUp(){
        this.setIcon(new ImageIcon(pai.getImageIcon()));
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
