import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private String name;
    private Socket socket;
    private String pic;
    private List<Pai>PlayerPais = new ArrayList<>();

    Player(){}

    Player(int id,Socket socket,List<Pai>PlayerPais){
        this.PlayerPais = PlayerPais;
        this.id = id;
        this.socket = socket;
    }

    Player(List<Pai>PlayerPais){
        this.PlayerPais = PlayerPais;
    }

    Player(int id,String name){
        this.id = id;
        this.name = name;
    }
    public void setPlayerPais(List<Pai> PlayerPais) {
        this.PlayerPais = PlayerPais;
    }
    public List<Pai> getPlayerPais(){
        return PlayerPais;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }


}
