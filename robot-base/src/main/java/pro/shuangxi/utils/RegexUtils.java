package pro.shuangxi.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：mengshx
 * @date ：Created in 2022/3/29 16:38
 * @description：正则工具类
 */
public class RegexUtils {
   public static String getFirstGroup(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
   public static String getGroup(String regex, String text,int index) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return matcher.group(index);
        }
        return "";
    }
   public static List<String> getList(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
       ArrayList<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        return list;
    }
}
