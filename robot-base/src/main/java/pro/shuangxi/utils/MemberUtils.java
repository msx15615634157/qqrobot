package pro.shuangxi.utils;

import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.NormalMember;
import net.mamoe.mirai.contact.PermissionDeniedException;

/**
 * @author ：mengshx
 * @date ：Created in 2022/8/6 17:23
 * @description：群员工具类
 */
public class MemberUtils {
    public static void ban(String groupCode,String personCode,int time) {
        Group group = BotHolderUtils.getBot().getGroup(Long.valueOf(groupCode));
        NormalMember member = group.get(Long.valueOf(personCode));
        try {
            member.mute(time);
        } catch (PermissionDeniedException exception) {
            MessageUtils.sendGroupMsg(groupCode,"权限不足！！");
        }
    }
    public static void chuo(String groupCode,String personCode) {
        Group group = BotHolderUtils.getBot().getGroup(Long.valueOf(groupCode));
        NormalMember member = group.get(Long.valueOf(personCode));
            member.nudge().sendTo(group);
    }
}
