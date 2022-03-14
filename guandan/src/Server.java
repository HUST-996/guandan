import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.alibaba.fastjson.JSON.toJSONString;

public class Server {
    //创建玩家列表
    public List<Player> players = new ArrayList<>();
    //玩家数量
    public int index = 0;
    public int step = 0;
    public Server(){


        try {
            //创建服务器端socket
            ServerSocket serverSocket = new ServerSocket(8888);
            //接受客户端的socket
            while (true) {
                Socket socket = serverSocket.accept();
                //开启线程处理客户端的socket
                AcceptThread acceptThread = new AcceptThread(socket);
                acceptThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //创建一个接收线程
    class AcceptThread extends Thread{
        Socket socket;
        public AcceptThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                while (true){
                    System.out.println("循环一次");
                    String msg = dataInputStream.readUTF();
                    if(step == 0) {//创建player对象
                        Player player = new Player(index++, msg);
                        player.setSocket(socket);
                        //存入玩家列表
                        players.add(player);
                        System.out.println(msg + "上线了");

                        System.out.println("当前上线人数：" + players.size());

                        if (players.size() == 4) {
                            fapai();
                            step = 1;
                        }
                    }
                    else if(step == 1){
                        //接收抢地主
                        JSONObject msgJsonObject = JSON.parseObject(msg);
                        int typeid = msgJsonObject.getInteger("typeid");
                        int playerid = msgJsonObject.getInteger("playerid");
                        String content = msgJsonObject.getString("content");
                        if(typeid == 1){
                            Message sendMessage = new Message(typeid,playerid,content,null);

                            msg = JSON.toJSONString(sendMessage);

                            sendMessageToClient(msg);
                        }
                        //二代表抢地主
                        if(typeid == 2){
                            Message sendMessage = new Message(typeid,playerid,content,null);

                            msg = JSON.toJSONString(sendMessage);

                            sendMessageToClient(msg);

                            step = 2;
                        }
                    }
                    //出牌和不出牌
                    else if(step==2){
                        System.out.println("第二步");
                        sendMessageToClient(msg);//转发给所有客户端
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    //群发消息给客户端
    public void sendMessageToClient(String msg){
        for(int i = 0;i<players.size();i++){
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(players.get(i).getSocket().getOutputStream());

                dataOutputStream.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fapai(){
        PaiJu paiJu = new PaiJu();
        paiJu.initialPais();
        //洗牌
        Collections.shuffle(paiJu.paiList);
        //初始化四名玩家
        for(int i=0;i<4;i++){
            List<Pai>temp = paiJu.paiList.subList(i*27,27*i+27);
            List<Pai>list1 = new ArrayList<>(temp);
            players.get(i).setPlayerPais(list1);
        }
        //给牌排序
        for(int i=0;i<4;i++){
            players.get(i).getPlayerPais().sort(Pai.paiComparator);
        }
        for(int i=0;i<players.size();i++){
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(players.get(i).getSocket().getOutputStream());
                String jsonString = JSON.toJSONString(players);
                dataOutputStream.writeUTF(jsonString);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
