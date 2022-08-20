package pro.shuangxi.utils;

import net.mamoe.mirai.Bot;

/**
 * @author ：mengshx
 * @date ：Created in 2022/8/6 17:25
 * @description：bot持有者
 */
public class BotHolderUtils {
    private static Bot bot;
    public static void setBot(Bot bot) {
        BotHolderUtils.bot = bot;
    }
    public static Bot getBot() {
       return bot;
    }
}
