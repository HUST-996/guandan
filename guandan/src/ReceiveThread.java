import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ReceiveThread extends Thread{
    private Socket socket;
    private MainFrame mainFrame;
    private int step = 0;

    public ReceiveThread(Socket socket,MainFrame mainFrame){
        this.mainFrame = mainFrame;
        this.socket = socket;
    }
    public void run(){
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            while (true){
                System.out.println("循环一次");
                //接收从服务器端传来的消息json字符串
                String jsonString = dataInputStream.readUTF();

                System.out.println(jsonString);
                if(step==0) {
                    System.out.println("步骤0");
                    List<Player> players = new ArrayList<>();

                    //将json字符串转化为json数组
                    JSONArray playerJsonArray = JSONArray.parseArray(jsonString);
                    for (int i = 0; i < playerJsonArray.size(); i++) {
                        JSONObject playerJson = (JSONObject) playerJsonArray.get(i);
                        int id = playerJson.getInteger("id");
                        String name = playerJson.getString("name");
                        List<Pai> pais = new ArrayList<>();
                        JSONArray pokerJsonArray = playerJson.getJSONArray("playerPais");
                        for (int j = 0; j < pokerJsonArray.size(); j++) {
                            JSONObject pokerJson = (JSONObject) pokerJsonArray.get(j);
                            int pability = pokerJson.getInteger("ability");
                            String pname = pokerJson.getString("name");
                            String imageIcon = pokerJson.getString("imageIcon");
                            Pai pai = new Pai(pname, pability);
                            pai.setImageIcon(imageIcon);
                            pais.add(pai);
                        }
                        Player player = new Player(id, name);
                        player.setPlayerPais(pais);
                        players.add(player);
                    }
                    //获得四个玩家的信息了
                    if (players.size() == 4) {
                        mainFrame.showAllPlayersInfo(players);

                        step = 1;
                    }
                }
                else if(step==1){
                    System.out.println("步骤1");
                    JSONObject msgJSONObject = JSONObject.parseObject(jsonString);
                    System.out.println(jsonString);
                    //解析消息对象
                    int typeid = msgJSONObject.getInteger("typeid");
                    int playerid = msgJSONObject.getInteger("playerid");
                    String contentString = msgJSONObject.getString("content");
                    JSONArray pokerJsonArray = msgJSONObject.getJSONArray("pais");
                    if(typeid == 1){
                        //主窗口显示不抢的信息
                        System.out.println("不抢");
                        //设置下一家开始抢地主
                        if(mainFrame.currentPlayer.getId()==playerid+1){
                            mainFrame.getLord();
                        }
                    }
                    if(typeid == 2){
                        System.out.println("抢地主");
                        if(mainFrame.currentPlayer.getId()==0){
                            mainFrame.showChuPaiLabel();
                        }
                        step = 2;
                    }

                }else if(step == 2){
//                    if(mainFrame.currentPlayer.getId()==(lastPlayerId+1)%4){
//                        lastPlayerId+=1;
//                        mainFrame.showChuPaiLabel();
//                        System.out.println("出牌");
//                    }
                    JSONObject msgJSONObject = JSONObject.parseObject(jsonString);
                    System.out.println("步骤二");
                    //解析消息对象
                    int typeid = msgJSONObject.getInteger("typeid");
                    int playerid = msgJSONObject.getInteger("playerid");
                    String contentString = msgJSONObject.getString("content");
                    JSONArray pokerJsonArray = msgJSONObject.getJSONArray("pais");
                    if(typeid==3){
                        //显示不出牌的消息
                        mainFrame.showMsg(3);
                        System.out.println("不出");
                    }
                    if(typeid==4){
                        System.out.println("出牌");
                        //获得出牌列表
//                        JSONArray pokersJsonArray = msgJSONObject.getJSONArray("pais");
                        List<Pai> outPais = new ArrayList<Pai>();
                        for(int i = 0;i < pokerJsonArray.size();i++){
                            JSONObject pokerJsonObject = (JSONObject) pokerJsonArray.get(i);
                            int pability = pokerJsonObject.getInteger("ability");
                            String pname = pokerJsonObject.getString("name");
                            String imageIcon = pokerJsonObject.getString("imageIcon");
                            Pai pai = new Pai(pname, pability);
                            pai.setImageIcon(imageIcon);
                            outPais.add(pai);
                        }
                        mainFrame.showOutPokerList(playerid,outPais);
                    }
                    if(mainFrame.currentPlayer.getId()==playerid+1||(mainFrame.currentPlayer.getId()==0&&playerid==3)){
                        mainFrame.showChuPaiLabel();
                        System.out.println("出牌");
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
