import com.sun.javafx.css.Rule;

import java.util.Comparator;
import java.util.List;

public class Rules {
    //单张
    public static boolean DanZhang(List<Pai> pais){
        if(pais.size()==1) return true;
        else return false;
    }
    //对子
    public static boolean DuiZi(List<Pai> pais){
        if(pais.size()==2&&pais.get(0).getAbility()==pais.get(1).getAbility()) return true;
        else return false;
    }
    //三不带
    public static boolean SanBuDai(List<Pai> pais){
        if(pais.size()==3&&(pais.get(0).getAbility()==pais.get(1).getAbility())&&(pais.get(1).getAbility()==pais.get(2).getAbility()))
            return true;
        else return false;
    }
    //炸弹
    public static boolean ZhaDan(List<Pai> pais){
        if(pais.size()<4) return false;
        else{
            for(int i=0;i<pais.size()-1;i++){
                if(pais.get(i).getAbility()!=pais.get(i+1).getAbility())
                    return false;
            }
            return true;
        }
    }
    //顺子
    public static boolean ShunZi(List<Pai> pais){
        pais.sort(Pai.paiComparator);
        if(pais.size()!=5) return false;
        else {
            for(int i=0;i<pais.size()-1;i++){
                if(pais.get(i).getAbility()!=pais.get(i+1).getAbility()-1)
                    return false;
            }
            return true;
        }
    }
    //同花顺
    public static boolean TongHuaShun(List<Pai> pais){
        if(!Rules.ShunZi(pais)) return false;
        else{
            for(int i=0;i<pais.size()-1;i++){
                if(!pais.get(i).getName().substring(0, 2).equals(pais.get(i + 1).getName().substring(0, 2)))
                    return false;
            }
            return true;
        }
    }
    //三带二
    public static boolean SanDaiEr(List<Pai> pais){
        pais.sort(Pai.paiComparator);
        if(pais.size()!=5) return false;
        else{
            if(pais.get(0).getAbility()!=pais.get(1).getAbility()) return false;
            else if(pais.get(3).getAbility()!=pais.get(4).getAbility()) return false;
            else if(pais.get(2).getAbility()!=pais.get(3).getAbility()&&pais.get(1).getAbility()!=pais.get(2).getAbility()) return false;
            return true;
        }
    }
    //三连对
    public static boolean SanLianDui(List<Pai> pais){
        pais.sort(Pai.paiComparator);
        if(pais.size()!=6) return false;
        else {
            if(pais.get(0).getAbility()==pais.get(1).getAbility()&&
               pais.get(2).getAbility()==pais.get(3).getAbility()&&
               pais.get(4).getAbility()==pais.get(5).getAbility()&&
               pais.get(1).getAbility()==pais.get(2).getAbility()-1&&
               pais.get(3).getAbility()==pais.get(4).getAbility()-1)
                return true;
        }
        return false;
    }

    //钢板
    public static boolean GangBan(List<Pai>pais){
        pais.sort(Pai.paiComparator);
        if(pais.size()!=6) return false;
        else{
            if(pais.get(0).getAbility()==pais.get(1).getAbility()&&
               pais.get(1).getAbility()==pais.get(2).getAbility()&&
               pais.get(3).getAbility()==pais.get(4).getAbility()&&
               pais.get(4).getAbility()==pais.get(5).getAbility()&&
               pais.get(2).getAbility()==pais.get(3).getAbility()-1)
                return true;
        }
        return false;
    }
    //王炸
    public static boolean WangZha(List<Pai>pais){
        pais.sort(Pai.paiComparator);
        if(pais.size()!=4) return false;
        else{
            if(pais.get(0).getAbility()==16) return true;
        }
        return false;
    }

    public static boolean TruePais(List<Pai>pais){
        if(Rules.ShunZi(pais)) return true;
        if(Rules.DanZhang(pais)) return true;
        if(Rules.DuiZi(pais)) return true;
        if(Rules.GangBan(pais)) return true;
        if(Rules.SanBuDai(pais)) return true;
        if(Rules.SanDaiEr(pais)) return true;
        if(Rules.WangZha(pais)) return true;
        if(Rules.SanLianDui(pais)) return true;
        if(Rules.TongHuaShun(pais)) return true;
        if(Rules.ZhaDan(pais)) return true;
        return false;
    }

    public static int PaiCompare(List<Pai> o1,List<Pai> o2){
        //空牌对其他
        if(o1.size()==0) return -1;
        else if(o2.size()==0) return 1;
        //单张对单张
        else if(Rules.DanZhang(o1)&&Rules.DanZhang(o2)){
            if(o1.get(0).getAbility()<=o2.get(0).getAbility()) return -1;
            else if(o1.get(0).getAbility()>o2.get(0).getAbility()) return 1;
        }
        //对子对对子
        else if(Rules.DuiZi(o1)&&Rules.DuiZi(o2)){
            if(o1.get(0).getAbility()<=o2.get(0).getAbility()) return -1;
            else if(o1.get(0).getAbility()>o2.get(0).getAbility()) return 1;
        }
        //三不带对三不带
        else if(Rules.SanBuDai(o1)&&Rules.SanBuDai(o2)){
            if(o1.get(0).getAbility()<=o2.get(0).getAbility()) return -1;
            else if(o1.get(0).getAbility()>o2.get(0).getAbility()) return 1;
        }
        //三带二对三带二
        else if(Rules.SanDaiEr(o1)&&Rules.SanDaiEr(o2)){
            if(o1.get(2).getAbility()<=o2.get(2).getAbility()) return -1;
            else if(o1.get(2).getAbility()>o2.get(2).getAbility()) return 1;
        }
        //顺子对顺子
        else if(Rules.ShunZi(o1)&&Rules.ShunZi(o2)){
            if(o1.get(0).getAbility()<=o2.get(0).getAbility()) return -1;
            else if(o1.get(0).getAbility()>o2.get(0).getAbility()) return 1;
        }
        //三连对对三连对
        else if(Rules.SanLianDui(o1)&&Rules.SanLianDui(o2)){
            if(o1.get(0).getAbility()<=o2.get(0).getAbility()) return -1;
            else if(o1.get(0).getAbility()>o2.get(0).getAbility()) return 1;
        }
        //钢板对钢板
        else if(Rules.GangBan(o1)&&Rules.GangBan(o2)){
            if(o1.get(0).getAbility()<=o2.get(0).getAbility()) return -1;
            else if(o1.get(0).getAbility()>o2.get(0).getAbility()) return 1;
        }
        //炸弹对非炸弹
        else if((Rules.ZhaDan(o1)||Rules.TongHuaShun(o1)||Rules.WangZha(o1))&&!(Rules.ZhaDan(o2)||Rules.TongHuaShun(o2)||Rules.WangZha(o2))){
            return 1;
        }
        else if((Rules.ZhaDan(o2)||Rules.TongHuaShun(o2)||Rules.WangZha(o2))&&!(Rules.ZhaDan(o1)||Rules.TongHuaShun(o1)||Rules.WangZha(o1))){
            return -1;
        }
        //炸弹内战
        //普通炸弹
        else if(Rules.ZhaDan(o1)&&Rules.ZhaDan(o2)){
            if(o1.size()==o2.size()){
                if(o1.get(0).getAbility()>o2.get(0).getAbility()) return 1;
                else return -1;
            }else {
                if(o1.size()>o2.size()) return 1;
                else return -1;
            }
        }
        //同花顺和普通炸弹
        else if(Rules.ZhaDan(o1)&&Rules.TongHuaShun(o2)){
            if(o1.size()<=5) return -1;
            else return 1;
        }
        else if(Rules.ZhaDan(o2)&&Rules.TongHuaShun(o1)){
            if(o2.size()<=5) return 1;
            else return -1;
        }
        //同花顺对同花顺包含在在普通顺子对普通顺子里

        //王炸对其他
        else if(Rules.WangZha(o1)) return 1;
        else if(Rules.WangZha(o2)) return -1;
        return 0;
    }
}
