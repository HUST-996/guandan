import javax.swing.*;

public class NormalLabels extends JLabel {
    private String name;
    private String Pic;

    public void setPic(String pic) {
        Pic = pic;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return Pic;
    }

    @Override
    public String getName() {
        return name;
    }
    public NormalLabels(){
        this.setSize(104,58);
    }

    public NormalLabels(String name,String Pic){
        this.name = name;
        this.Pic = Pic;
        this.setSize(104,58);
    }
}
