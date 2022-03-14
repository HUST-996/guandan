import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaiJu {
    List<Pai> paiList = new ArrayList<>();
    private int DaJi;
    public void initialPais(){
        for(int i=1;i<=2;i++) {
            paiList.add(new Pai("红桃2", 2,("src/res/heart_15.png")));
            paiList.add(new Pai("红桃3", 3,("src/res/heart_3.png")));
            paiList.add(new Pai("红桃4", 4,("src/res/heart_4.png")));
            paiList.add(new Pai("红桃5", 5,("src/res/heart_5.png")));
            paiList.add(new Pai("红桃6", 6,("src/res/heart_6.png")));
            paiList.add(new Pai("红桃7", 7,("src/res/heart_7.png")));
            paiList.add(new Pai("红桃8", 8,("src/res/heart_8.png")));
            paiList.add(new Pai("红桃9", 9,("src/res/heart_9.png")));
            paiList.add(new Pai("红桃10", 10,("src/res/heart_10.png")));
            paiList.add(new Pai("红桃J", 11,("src/res/heart_11.png")));
            paiList.add(new Pai("红桃Q", 12,("src/res/heart_12.png")));
            paiList.add(new Pai("红桃K", 13,("src/res/heart_13.png")));
            paiList.add(new Pai("红桃A", 14,("src/res/heart_14.png")));
            paiList.add(new Pai("黑桃2", 2,("src/res/spade_15.png")));
            paiList.add(new Pai("黑桃3", 3,("src/res/spade_3.png")));
            paiList.add(new Pai("黑桃4", 4,("src/res/spade_4.png")));
            paiList.add(new Pai("黑桃5", 5,("src/res/spade_5.png")));
            paiList.add(new Pai("黑桃6", 6,("src/res/spade_6.png")));
            paiList.add(new Pai("黑桃7", 7,("src/res/spade_7.png")));
            paiList.add(new Pai("黑桃8", 8,("src/res/spade_8.png")));
            paiList.add(new Pai("黑桃9", 9,("src/res/spade_9.png")));
            paiList.add(new Pai("黑桃10", 10,("src/res/spade_10.png")));
            paiList.add(new Pai("黑桃J", 11,("src/res/spade_11.png")));
            paiList.add(new Pai("黑桃Q", 12,("src/res/spade_12.png")));
            paiList.add(new Pai("黑桃K", 13,("src/res/spade_13.png")));
            paiList.add(new Pai("黑桃A", 14,("src/res/spade_14.png")));
            paiList.add(new Pai("草花2", 2,("src/res/club_15.png")));
            paiList.add(new Pai("草花3", 3,("src/res/club_3.png")));
            paiList.add(new Pai("草花4", 4,("src/res/club_4.png")));
            paiList.add(new Pai("草花5", 5,("src/res/club_5.png")));
            paiList.add(new Pai("草花6", 6,("src/res/club_6.png")));
            paiList.add(new Pai("草花7", 7,("src/res/club_7.png")));
            paiList.add(new Pai("草花8", 8,("src/res/club_8.png")));
            paiList.add(new Pai("草花9", 9,("src/res/club_9.png")));
            paiList.add(new Pai("草花10", 10,("src/res/club_10.png")));
            paiList.add(new Pai("草花J", 11,("src/res/club_11.png")));
            paiList.add(new Pai("草花Q", 12,("src/res/club_12.png")));
            paiList.add(new Pai("草花K", 13,("src/res/club_13.png")));
            paiList.add(new Pai("草花A", 14,("src/res/club_14.png")));
            paiList.add(new Pai("方片2", 2,("src/res/diamond_15.png")));
            paiList.add(new Pai("方片3", 3,("src/res/diamond_3.png")));
            paiList.add(new Pai("方片4", 4,("src/res/diamond_4.png")));
            paiList.add(new Pai("方片5", 5,("src/res/diamond_5.png")));
            paiList.add(new Pai("方片6", 6,("src/res/diamond_6.png")));
            paiList.add(new Pai("方片7", 7,("src/res/diamond_7.png")));
            paiList.add(new Pai("方片8", 8,("src/res/diamond_8.png")));
            paiList.add(new Pai("方片9", 9,("src/res/diamond_9.png")));
            paiList.add(new Pai("方片10", 10,("src/res/diamond_10.png")));
            paiList.add(new Pai("方片J", 11,("src/res/diamond_11.png")));
            paiList.add(new Pai("方片Q", 12,("src/res/diamond_12.png")));
            paiList.add(new Pai("方片K", 13,("src/res/diamond_13.png")));
            paiList.add(new Pai("方片A", 14,("src/res/diamond_14.png")));
            paiList.add(new Pai("小王", 16,("src/res/little_joker.png")));
            paiList.add(new Pai("大王", 17,("src/res/joker_big.png")));
        }
    }

    public void setDaJi(int daJi) {
        DaJi = daJi;
    }

    public int getDaJi() {
        return DaJi;
    }
}
