import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
    private int typeid;//消息类型

    private int playerid;//玩家id

    private String content;//消息内容

    private List<Pai>pais;//扑克列表

    public int getPlayerid() {
        return playerid;
    }

    public int getTypeid() {
        return typeid;
    }

    public List<Pai> getPais() {
        return pais;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPais(List<Pai> pais) {
        this.pais = pais;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public Message(){}

    public Message(int typeid,int playerid,String content,List<Pai>pais){
        this.typeid = typeid;
        this.content = content;
        this.pais = pais;
        this.playerid = playerid;
    }
}
