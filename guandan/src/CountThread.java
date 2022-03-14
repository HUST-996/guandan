import com.alibaba.fastjson.JSON;

public class CountThread extends Thread{
    private int i;
    private MainFrame mainFrame;
    private boolean isRun;
    public CountThread(int i,MainFrame mainFrame){
        isRun = true;
        this.i = i;
        this.mainFrame = mainFrame;
    }
    public void run(){
        while(i>=0 && isRun){
            mainFrame.TimeLabel.setText(i+"");
            i--;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Message msg = null;
        //如果时间到了
        if(i==-1||(!isRun && !mainFrame.isLord)){
            msg = new Message(1,mainFrame.currentPlayer.getId(),"不抢",null);
            System.out.println("aaaaaaaaaaaaa");
        }
        //如果点击了抢地主
        if(!isRun&& mainFrame.isLord){
            msg = new Message(2,mainFrame.currentPlayer.getId(),"抢地主",null);
            System.out.println("aaaaaaaaaa");
        }

        //将消息传到服务器端
        mainFrame.sendThread.setMsg(JSON.toJSONString(msg));
        System.out.println("传到了");
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    public boolean isRun() {
        return isRun;
    }
}
