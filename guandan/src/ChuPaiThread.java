import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class ChuPaiThread extends Thread{
    private int time;

    private MainFrame mainFrame;

    private boolean isRun;

    public void setRun(boolean run) {
        isRun = run;
    }

    public boolean isRun() {
        return isRun;
    }

    public ChuPaiThread(int time, MainFrame mainFrame){
        isRun = true;
        this.time = time;
        this.mainFrame = mainFrame;
    }

    public void run(){
        while (time>=0&&isRun){
            mainFrame.timeLabel.setText(time+"");
            time--;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Message message = null;
        //如果是不出
        if(time==-1||isRun==false&&mainFrame.isOut==false){
            message = new Message(3,mainFrame.currentPlayer.getId(),"不出",null);
        }
        //如果是出牌
        if(isRun==false&&mainFrame.isOut==true){
            message = new Message(4,mainFrame.currentPlayer.getId(),"出牌",changePokerLabelToPoker(mainFrame.selectedPokerLabels));

            mainFrame.removeOutPokerFromPokerList();
        }
        //转换为json，交给sendThread发给服务器
        String msg = JSON.toJSONString(message);
        mainFrame.sendThread.setMsg(msg);
    }

    public List<Pai> changePokerLabelToPoker(List<PokerLabel>selectedPokerLabels){
        List<Pai>list = new ArrayList<Pai>();
        for(int i=0;i<selectedPokerLabels.size();i++){
            PokerLabel pokerLabel = selectedPokerLabels.get(i);
            Pai pai = new Pai();
            pai = pokerLabel.getPai();
            list.add(pai);
        }
        return list;
    }
}
