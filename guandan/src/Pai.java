import javax.swing.*;
import java.awt.*;
import java.util.Comparator;

public class Pai {
    private String name;
    private int ability;
    private String imageIcon;
    public static Comparator<Pai>paiComparator = new Comparator<Pai>() {
        @Override
        public int compare(Pai o1, Pai o2) {
            return Integer.compare(o1.ability, o2.ability);
        }
    };
    public Pai(){}


    public Pai(String name,int ability){
        this.ability = ability;
        this.name = name;
    }

    public Pai(String name,int ability,String imageIcon){
        this.imageIcon = imageIcon;
        this.ability = ability;
        this.name = name;
    }
    public int getAbility() {
        return ability;
    }

    public String getName(){
        return name;
    }

    public void setAbility(int ability) {
        this.ability = ability;
    }

    public String getImageIcon() {
        return imageIcon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageIcon(String imageIcon) {
        this.imageIcon = imageIcon;
    }
}
