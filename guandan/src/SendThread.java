import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SendThread extends Thread{
    private String msg;
    private Socket socket;
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public SendThread(Socket socket){
        this.socket = socket;
    }

    public SendThread(Socket socket,String msg){
        this.socket = socket;
        this.msg = msg;
    }

    public SendThread(){}

    @Override
    public void run() {
        DataOutputStream dataOutputStream;
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                //如果消息不为空
                if(msg!=null) {
                    //发送消息
                    dataOutputStream.writeUTF(msg);
                    //消息发送完毕，清空
                    msg = null;
                }
                Thread.sleep(500);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
