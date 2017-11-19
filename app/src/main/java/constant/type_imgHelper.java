package constant;

import com.example.swapanytime.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weij on 2017/11/19.
 */

public class type_imgHelper {

    public static final int adidas = R.mipmap.adidas;
    public static final int apple = R.mipmap.apple;
    public static final int bingx = R.mipmap.bingx;
    public static final int boy_chenshan = R.mipmap.boy_chenshan;
    public static final int boy_niuzaiku = R.mipmap.boy_niuzaiku;
    public static final int boy_t = R.mipmap.boy_t;
    public static final int chaj = R.mipmap.chaj;
    public static final int chuang = R.mipmap.chuang;
    public static final int chuangd = R.mipmap.chuangd;
    public static final int dayinj = R.mipmap.dayinj;
    public static final int dianfb = R.mipmap.dianfb;
    public static final int fendi = R.mipmap.fendi;
    public static final int girl_niuzaiku = R.mipmap.girl_niuzaiku;
    public static final int girl_t = R.mipmap.girl_t;
    public static final int girl_waitao = R.mipmap.girl_waitao;
    public static final int girl_zhenzhi = R.mipmap.girl_zhenzhi;
    public static final int goul = R.mipmap.goul;
    public static final int goulongz = R.mipmap.goulongz;
    public static final int gouwo = R.mipmap.gouwo;
    public static final int huawei = R.mipmap.huawei;
    public static final int huj = R.mipmap.huj;
    public static final int jingz = R.mipmap.jingz;
    public static final int kafeiji = R.mipmap.kafeiji;
    public static final int kindle = R.mipmap.kindle;
    public static final int lanqiu = R.mipmap.lanqiu;
    public static final int lining = R.mipmap.lining;
    public static final int maol = R.mipmap.maol;
    public static final int maozi = R.mipmap.maozi;
    public static final int meiz = R.mipmap.meiz;
    public static final int mi = R.mipmap.mi;
    public static final int mianm = R.mipmap.mianm;
    public static final int newb = R.mipmap.newb;
    public static final int nike = R.mipmap.nike;
    public static final int oppo = R.mipmap.oppo;
    public static final int paobuj = R.mipmap.paobuj;
    public static final int puma = R.mipmap.puma;
    public static final int samsung = R.mipmap.samsung;
    public static final int saomy = R.mipmap.saomy;
    public static final int shaf = R.mipmap.shaf;
    public static final int suannj = R.mipmap.suannj;
    public static final int tv = R.mipmap.tv;
    public static final int xiangs = R.mipmap.xiangs;
    public static final int xifa = R.mipmap.xifa;
    public static final int xiyiji = R.mipmap.xiyiji;
    public static final int yacmei = R.mipmap.yacmei;
    public static final int yal = R.mipmap.yal;
    public static final int yx = R.mipmap.yx;
    public static final int zhazj = R.mipmap.zhazj;
    public static final int zuqiu = R.mipmap.zuqiu;
    public static final int vivo = R.mipmap.vivo;


    private List<Integer> imglist;

    private type_imgHelper() {

        imglist = new ArrayList<>();
        imglist.add(R.mipmap.ic_launcher);
        imglist.add(boy_chenshan);
        imglist.add(girl_zhenzhi);
        imglist.add(girl_niuzaiku);
        imglist.add(boy_t);
        imglist.add(boy_chenshan);
        imglist.add(boy_niuzaiku);
        imglist.add(R.mipmap.ic_launcher);
        imglist.add(apple);
        imglist.add(huawei);
        imglist.add(samsung);
        imglist.add(mi);
        imglist.add(oppo);
        imglist.add(vivo);
        imglist.add(meiz);
        imglist.add(R.mipmap.ic_launcher);
        imglist.add(girl_waitao);
        imglist.add(girl_t);
        imglist.add(girl_zhenzhi);
        imglist.add(girl_niuzaiku);
        imglist.add(boy_chenshan);
        imglist.add(R.mipmap.ic_launcher);
        imglist.add(boy_niuzaiku);
        imglist.add(girl_waitao);
        imglist.add(boy_chenshan);
        imglist.add(boy_t);
        imglist.add(maozi);
        imglist.add(R.mipmap.ic_launcher);
        imglist.add(tv);
        imglist.add(xiyiji);
        imglist.add(bingx);
        imglist.add(yx);
        imglist.add(dianfb);
        imglist.add(kafeiji);
        imglist.add(zhazj);
        imglist.add(suannj);
        imglist.add(R.mipmap.ic_launcher);
        imglist.add(paobuj);
        imglist.add(yal);
        imglist.add(huj);
        imglist.add(lanqiu);
        imglist.add(zuqiu);
        imglist.add(R.mipmap.ic_launcher);
        imglist.add(chuang);
        imglist.add(shaf);
        imglist.add(chuangd);
        imglist.add(jingz);
        imglist.add(chaj);
        imglist.add(R.mipmap.ic_launcher);
        imglist.add(nike);
        imglist.add(adidas);
        imglist.add(newb);
        imglist.add(puma);
        imglist.add(lining);
        imglist.add(R.mipmap.ic_launcher);
        imglist.add(dayinj);
        imglist.add(saomy);
        imglist.add(yacmei);
        imglist.add(kindle);
        imglist.add(R.mipmap.ic_launcher);
        imglist.add(mianm);
        imglist.add(xiangs);
        imglist.add(fendi);
        imglist.add(xifa);
        imglist.add(R.mipmap.ic_launcher);
        imglist.add(maol);
        imglist.add(goul);
        imglist.add(goulongz);
        imglist.add(gouwo);


    }

    private static type_imgHelper type_imgHelper;

    public static type_imgHelper getInstance() {
        if (type_imgHelper == null) {
            type_imgHelper = new type_imgHelper();
        }
        return type_imgHelper;
    }


    public List<Integer> getImglist() {
        if (imglist == null) {
            return null;
        }
        return imglist;
    }


}
