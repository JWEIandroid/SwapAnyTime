package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.finalteam.toolsfinal.StringUtils;

/**
 * Created by Administrator on 2017/11/16.
 */

public class ContentUtils {

    private static  ContentUtils instance  = new ContentUtils();

    public static  ContentUtils getInstance(){
        if (instance==null){
            instance = new ContentUtils();
        }
        return  instance;
    }


    /**
     *
     * @param mobiles 手机号
     * @return 手机号是否合法
     */
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /**
     *
     * @param content
     * @return 判断字符串是否合法
     */
    public  boolean isContentLegal(String content){

        if (StringUtils.isEmpty(content)){
            return  false;
        }
        return  true;
    }

    public boolean isNumLegal(String num,int length){

        if (num.length()!=length){
            return  false;
        }
        return  true;

    }








}
