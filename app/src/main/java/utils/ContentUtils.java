package utils;

import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.finalteam.toolsfinal.StringUtils;

/**
 * Created by Administrator on 2017/11/16.
 */

public class ContentUtils {

    private static ContentUtils instance = new ContentUtils();

    public static ContentUtils getInstance() {
        if (instance == null) {
            instance = new ContentUtils();
        }
        return instance;
    }


    /**
     * @param mobiles 手机号
     * @return 手机号是否合法
     */
    public boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /**
     * @param content
     * @return 判断字符串是否合法
     */
    public boolean isContentLegal(String content) {

        if (StringUtils.isEmpty(content)) {
            return false;
        }
        return true;
    }

    public boolean isNumLegal(String num, int length) {

        if (num.length() < length) {
            return false;
        }
        return true;

    }


    // 计算时间差
    public TimeDiffTools getTimePass(String time) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Date d1 = new Date(time);
        Date d2 = new Date(System.currentTimeMillis());

        long diff = d1.getTime() - d2.getTime();
        long day = diff / (1000 * 60 * 60 * 24);
        long hours = (diff - day * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (diff - day * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);

        TimeDiffTools timeDiffTools = new TimeDiffTools();
        timeDiffTools.setDay((int) day);
        timeDiffTools.setHour((int) hours);
        timeDiffTools.setMin((int) minutes);


        return timeDiffTools;


    }


    public class TimeDiffTools {

        private int day;
        private int hour;
        private int min;

        public int getDay() {
            return day;
        }

        public TimeDiffTools setDay(int day) {
            this.day = day;
            return this;
        }

        public int getHour() {
            return hour;
        }

        public TimeDiffTools setHour(int hour) {
            this.hour = hour;
            return this;
        }

        public int getMin() {
            return min;
        }

        public TimeDiffTools setMin(int min) {
            this.min = min;
            return this;
        }
    }


}
