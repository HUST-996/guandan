import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new Login();
        List<Pai>CurrentPai = new ArrayList<>();
        PaiJu paiJu = new PaiJu();
        paiJu.setDaJi(2);
        Player[] players = new Player[4];
        Scanner in = new Scanner(System.in);
        int cnt = 0;
        //一个大局的while循环
        while(paiJu.getDaJi()!=1){
            //初始化牌局

            paiJu.initialPais();
            //洗牌
            Collections.shuffle(paiJu.paiList);
            //初始化四名玩家
            for(int i=0;i<4;i++){
                List<Pai>temp = paiJu.paiList.subList(i*27,27*i+27);
                List<Pai>list1 = new ArrayList<>(temp);
                players[i] = new Player(list1);
            }
            //给牌排序
            for(int i=0;i<4;i++){
                players[i].getPlayerPais().sort(Pai.paiComparator);
            }
//            !((players[0].getPlayerPais().size()==0&&players[2].getPlayerPais().size()==0)||(players[1].getPlayerPais().size()==0&&players[3].getPlayerPais().size()==0))
            while(!((players[0].getPlayerPais().size()==0&&players[2].getPlayerPais().size()==0)||(players[1].getPlayerPais().size()==0&&players[3].getPlayerPais().size()==0))) {
                List<Pai>ChuPai = new ArrayList<>();
                //遍历一遍牌
                for (int i = 0; i < 4; i++) {
                    System.out.print("第" + (i + 1) + "名选手的牌为:");
                    for (int j = 0; j < players[i].getPlayerPais().size(); j++) {
                        System.out.print("("+(j+1)+")"+players[i].getPlayerPais().get(j).getName() + " ");
                    }
                    System.out.println();
                }
                for(int l=0;l<4;l++) {
                    System.out.println("轮到第"+(l+1)+"位选手出牌");
                    if(cnt==0) {
                        System.out.println("你是该轮的发起者，必须出牌");
                        cnt++;
                    }
                    System.out.println("请选择你要出的牌(位置),以0结尾");
                    int flag = 1;
                    while(flag==1) {
                        int a = -1;
                        while (a != 0) {
                            a = in.nextInt();
                            if (a != 0)
                                ChuPai.add(players[l].getPlayerPais().get(a - 1));
                        }
                        if(ChuPai.size()==0)
                        {
                            System.out.println("要不起");
                            flag = 0;
                            cnt++;
                            if(cnt==4) {
                                cnt = 0;
                                CurrentPai.clear();
                            }
                        }
                        else if (Rules.TruePais(ChuPai)&&Rules.PaiCompare(ChuPai,CurrentPai)==1) {
                            for (int i = 0; i < ChuPai.size(); i++) {
                                players[l].getPlayerPais().remove(ChuPai.get(i));
                            }
                            CurrentPai.clear();
                            for(int i=0;i<ChuPai.size();i++){
                                CurrentPai.add(ChuPai.get(i));
                            }
                            ChuPai.clear();
                            flag = 0;
                            cnt = 1;

                        } else {
                            System.out.println("出牌错误，请重新出牌");
                            for(int i=0;i<CurrentPai.size();i++){
                                System.out.println(CurrentPai.get(i).getName());
                            }
                            ChuPai.clear();
                        }
                    }
                }
            }
        }
    }
}
