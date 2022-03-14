import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    public MyPanel myPanel;
    public Socket socket;
    public String uname;
    public SendThread sendThread;
    public ReceiveThread receiveThread;
    public Player currentPlayer;
    public Player ShangJia;
    public Player DuiJia;
    public Player XiaJia;
    public JLabel TimeLabel;
    public CountThread countThread;
    public JLabel JinGongLabel;
    public boolean isXiaYou;
    public boolean isShangYou;
    public boolean isErYou;
    public boolean isSanYou;
    public List<PokerLabel> pokerLabels = new ArrayList<PokerLabel>();
    public List<PokerLabel> selectedPokerLabels = new ArrayList<PokerLabel>();
    public JLabel chupaiLabel;
    public JLabel buchuLabel;
    public JLabel timeLabel;
    public ChuPaiThread chuPaiThread;
    public JLabel msgLabel;
    public boolean isOut;//选择的是出牌还是不出牌
    public List<PokerLabel> showOutPokerLabels = new ArrayList<PokerLabel>();//存放出牌列表
    public JLabel lordLabel1;
    public JLabel lordLabel2;
    public boolean isLord;
    public static List<Pai>LastPais = new ArrayList<Pai>();
    public static int cnt = 4;//用来计数，发牌人是否继续到他发了
    public JLabel wrongLabel = new JLabel();
    public JLabel smallerLabel = new JLabel();
    public WrongThread wrongThread;
    public MainFrame(String uname, Socket socket){
        this.uname = uname;
        this.socket = socket;
        //设置窗口属性
        this.setSize(1200,700);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //添加mypanel
        myPanel = new MyPanel();
        myPanel.setBounds(0,0,1200,700);
        this.add(myPanel);

        //初始化出牌不出牌
        init();
        //启动发消息的线程
        sendThread = new SendThread(socket,uname);
        sendThread.start();
        //启动接受消息的线程
        receiveThread = new ReceiveThread(socket,this);
        receiveThread.start();
    }
    //窗口初始化
    public void init(){
        msgLabel = new JLabel();

        chupaiLabel = new JLabel();
        chupaiLabel.setBounds(500,350,104,58);
        chupaiLabel.setIcon(new ImageIcon("src/res/btn_chupai.png"));
        chupaiLabel.addMouseListener(new MyMouseEvent());
        chupaiLabel.setVisible(false);
        this.myPanel.add(chupaiLabel);

        buchuLabel = new JLabel();
        buchuLabel.setBounds(600,350,104,58);
        buchuLabel.setIcon(new ImageIcon("src/res/btn_pass.png"));
        buchuLabel.addMouseListener(new MyMouseEvent());
        buchuLabel.setVisible(false);
        this.myPanel.add(buchuLabel);

        timeLabel = new JLabel();
        timeLabel.setBounds(400,350,50,50);
        timeLabel.setFont(new Font("Dialog",0,50));
        timeLabel.setForeground(Color.red);
        timeLabel.setVisible(false);
        this.myPanel.add(timeLabel);
    }
    //显示出牌标签
    public void showChuPaiLabel(){
        timeLabel.setVisible(true);
        if(cnt!=3&&cnt!=4){
            buchuLabel.setVisible(true);
        }
        chupaiLabel.setVisible(true);

        this.repaint();

        //启动出牌的时间线程
        chuPaiThread = new ChuPaiThread(30,this);
        chuPaiThread.start();
    }

    public void showAllPlayersInfo(List<Player> players) {
        //设置四个玩家的头像
        players.get(0).setPic("src/res/gql.jpg");
        players.get(1).setPic("src/res/wjy.jpg");
        players.get(2).setPic("src/res/znx.jpg");
        players.get(3).setPic("src/res/lzh.jpg");
        //显示三个玩家的名称

        //显示当前玩家的扑克列表
        for(int i=0;i<players.size();i++){
            players.get(i).setId(i);
            if(players.get(i).getName().equals(uname)){
                currentPlayer=players.get(i);
                XiaJia = players.get((i+1)%4);
                DuiJia = players.get((i+2)%4);
                ShangJia = players.get((i+3)%4);
            }
        }
        List<Pai> pais = currentPlayer.getPlayerPais();
        for(int i=0;i<pais.size();i++){
            Pai pai = pais.get(i);

            PokerLabel pokerLabel = new PokerLabel(pai);

            pokerLabel.turnUp();

            this.myPanel.add(pokerLabel);

            this.pokerLabels.add(pokerLabel);

            this.myPanel.setComponentZOrder(pokerLabel,0);
            //一张一张地显示出来
            GameUtil.move(pokerLabel,300+20*i,450);
        }
        //显示另外三个玩家的扑克背面
        Pai pai1 = new Pai();
        pai1.setImageIcon("src/res/cardbg.jpg");

        PokerLabel pokerLabel = new PokerLabel(pai1);
        pokerLabel.turnUp();
        this.myPanel.add(pokerLabel);
        this.myPanel.setComponentZOrder(pokerLabel,0);
        pokerLabel.setLocation(280,200);

        Pai pai2 = new Pai();
        pai2.setImageIcon("src/res/cardbg.jpg");
        PokerLabel pokerLabel2 = new PokerLabel(pai2);
        pokerLabel2.setSize(71,96);
        pokerLabel2.setIcon(new ImageIcon("src/res/cardbg.jpg"));
        this.myPanel.add(pokerLabel2);
        pokerLabel2.setLocation(900,200);

        Pai pai3 = new Pai();
        pai3.setImageIcon("src/res/cardbg.jpg");
        PokerLabel pokerLabel3 = new PokerLabel(pai3);
        pokerLabel3.turnUp();
        this.myPanel.add(pokerLabel3);
        pokerLabel3.setLocation(600,20);

        PokerLabel wjyLabel = new PokerLabel();
        wjyLabel.setSize(60,60);
        wjyLabel.setIcon(new ImageIcon(ShangJia.getPic()));
        this.myPanel.add(wjyLabel);
        wjyLabel.setLocation(200,210);

        PokerLabel lzhLabel = new PokerLabel();
        lzhLabel.setSize(60,60);
        lzhLabel.setIcon(new ImageIcon(XiaJia.getPic()));
        this.myPanel.add(lzhLabel);
        lzhLabel.setLocation(980,220);

        PokerLabel zxmLabel = new PokerLabel();
        zxmLabel.setSize(60,60);
        zxmLabel.setIcon(new ImageIcon(DuiJia.getPic()));
        this.myPanel.add(zxmLabel);
        zxmLabel.setLocation(520,20);

        PokerLabel gqlLabel = new PokerLabel();
        gqlLabel.setSize(60,60);
        gqlLabel.setIcon(new ImageIcon(currentPlayer.getPic()));
        this.myPanel.add(gqlLabel);
        gqlLabel.setLocation(220,450);


        addClickEventToPoker();
        if(currentPlayer.getId()==0){
            getLord();//抢地主
        }
    }

    public void getLord() {
        //显示抢地主的按钮和定时器按钮
        lordLabel1 = new JLabel();
        lordLabel1.setBounds(440,380,104,46);
        lordLabel1.setIcon(new ImageIcon("src/res/jiaodizhu.png"));
        lordLabel1.addMouseListener(new MyMouseEvent());
        this.myPanel.add(lordLabel1);

        lordLabel2 = new JLabel();
        lordLabel2.setBounds(550,380,104,46);
        lordLabel2.setIcon(new ImageIcon("src/res/bujiao.png"));
        lordLabel2.addMouseListener(new MyMouseEvent());
        this.myPanel.add(lordLabel2);

        TimeLabel = new JLabel();
        TimeLabel.setBounds(350,380,50,50);
        TimeLabel.setFont(new Font("Dialog",0,50));
        TimeLabel.setForeground(Color.red);
        this.myPanel.add(TimeLabel);

        repaint();

        countThread = new CountThread(20,this);
        countThread.start();
    }

    //给扑克牌添加单击事件
    public void addClickEventToPoker(){
        for(int i=0;i<pokerLabels.size();i++){
            pokerLabels.get(i).addMouseListener(new PokerEvent());
        }
    }

    //显示消息(不抢或者不出)
    public void showMsg(int typeid){
        msgLabel.setVisible(true);
        msgLabel.setBounds(500,300,104,58);
        msgLabel.setIcon(new ImageIcon("src/res/buyao.png"));
        this.myPanel.add(msgLabel);
        this.repaint();
    }

    public void showOutPokerList(int playerid, List<Pai> outPais) {
        //清空
        selectedPokerLabels.clear();
        for(int i=0;i<showOutPokerLabels.size();i++){
            myPanel.remove(showOutPokerLabels.get(i));
        }
        myPanel.remove(msgLabel);
        showOutPokerLabels.clear();

        for(int i=0;i<outPais.size();i++){
            Pai pai = outPais.get(i);
            PokerLabel pokerLabel = new PokerLabel(pai);
            pokerLabel.setLocation(400+30*i,200);
            pokerLabel.turnUp();
            myPanel.add(pokerLabel);
            showOutPokerLabels.add(pokerLabel);
            myPanel.setComponentZOrder(pokerLabel,0);
        }
        this.repaint();
    }

    public void removeOutPokerFromPokerList() {
        //1.从扑克列表中移除
        pokerLabels.removeAll(selectedPokerLabels);

        for(int i=0;i<selectedPokerLabels.size();i++){
            myPanel.remove(selectedPokerLabels.get(i));
        }

        for(int i=0;i<pokerLabels.size();i++){
            myPanel.setComponentZOrder(pokerLabels.get(i),0);
            GameUtil.move(pokerLabels.get(i),300+20*i,450);
        }
        this.repaint();
    }

    class  MyMouseEvent implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource().equals(lordLabel1)){
                countThread.setRun(false);
                lordLabel1.setVisible(false);
                lordLabel2.setVisible(false);
                TimeLabel.setVisible(false);
                isLord = true;
            }
            if(e.getSource().equals(lordLabel2)){
                countThread.setRun(false);
                lordLabel1.setVisible(false);
                lordLabel2.setVisible(false);
                TimeLabel.setVisible(false);
                isLord = false;
            }
            if(e.getSource().equals(chupaiLabel)){
                if(!Rules.TruePais(changePokerLabelToPoker(selectedPokerLabels))){
                    wrongLabel.setBounds(500,550,191,65);
                    wrongLabel.setIcon(new ImageIcon("src/res/wrong.png"));
                    myPanel.add(wrongLabel);
                    wrongLabel.setVisible(true);
                    repaint();
                }
                else if(Rules.PaiCompare(changePokerLabelToPoker(selectedPokerLabels),LastPais)==-1&&cnt!=3){
                    smallerLabel.setBounds(500,550,275,85);
                    smallerLabel.setIcon(new ImageIcon("src/res/smaller.png"));
                    myPanel.add(smallerLabel);
                    smallerLabel.setVisible(true);
                    repaint();
                }else if(Rules.PaiCompare(changePokerLabelToPoker(selectedPokerLabels),LastPais)==0&&cnt!=3){
                    wrongLabel.setBounds(500,550,191,65);
                    wrongLabel.setIcon(new ImageIcon("src/res/wrong.png"));
                    myPanel.add(wrongLabel);
                    wrongLabel.setVisible(true);
                    repaint();
                }
                else{
                    //计时器停止
                    LastPais.clear();
                    LastPais.addAll(changePokerLabelToPoker(selectedPokerLabels));
                    isOut = true;
                    chuPaiThread.setRun(false);
                    chupaiLabel.setVisible(false);
                    buchuLabel.setVisible(false);
                    timeLabel.setVisible(false);
                    wrongLabel.setVisible(false);
                    smallerLabel.setVisible(false);
                    cnt = 0;
                }
            }
            if(e.getSource().equals(buchuLabel)){
                isOut = false;
                //计时器停止
                chuPaiThread.setRun(false);
                chupaiLabel.setVisible(false);
                buchuLabel.setVisible(false);
                timeLabel.setVisible(false);
                wrongLabel.setVisible(false);
                smallerLabel.setVisible(false);
                cnt%=4;
                cnt++;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    class PokerEvent implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            PokerLabel pokerLabel = (PokerLabel) e.getSource();
            //如果之前选择过了则取消选择
            if(pokerLabel.isSelected()){
                pokerLabel.setLocation(pokerLabel.getX(),pokerLabel.getY()+30);
                selectedPokerLabels.remove(pokerLabel);
                pokerLabel.setSelected(false);
            }
            //如果之前没有选中
            else{
                pokerLabel.setLocation(pokerLabel.getX(),pokerLabel.getY()-30);
                selectedPokerLabels.add(pokerLabel);
                pokerLabel.setSelected(true);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
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
