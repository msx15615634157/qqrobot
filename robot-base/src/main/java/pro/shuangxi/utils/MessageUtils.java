package pro.shuangxi.utils;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.utils.ExternalResource;
import pro.shuangxi.pojo.*;

import java.io.ByteArrayInputStream;

/**
 * @author ：mengshx
 * @date ：Created in 2021/11/22 12:09
 * @description：
 */
public class MessageUtils {
    private static Bot bot;
    public static void setBot(Bot bot) {
        MessageUtils.bot = bot;
    }

    public static void senGroupMsg(String groupCode,String msg) {
        Group group = bot.getGroup(Long.valueOf(groupCode));
        group.sendMessage(msg);
    }
    public static void senGroupMsg(String groupCode,MessageChainBuilder builder) {
        Group group = bot.getGroup(Long.valueOf(groupCode));
        group.sendMessage(builder.build());
    }
    public static void senGroupMsg(String groupCode, Message message) {
        MessageChainBuilder builder = new MessageChainBuilder();
        Group group = bot.getGroup(Long.valueOf(groupCode));
        for (MessageLine line : message.getContents()) {
            if (line instanceof TextLine) {
                builder.append(new PlainText(((TextLine) line).getContent()));
            }
            if (line instanceof ImageLine) {

                byte[] bytes = ((ImageLine) line).getContent();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                Image image = ExternalResource.uploadAsImage(byteArrayInputStream, group);
                builder.append(image);
//                builder.image(((ImageLine) line).getContent());
            }
            if (line instanceof AtLine) {
                builder.append(new At(Long.valueOf(((AtLine) line).getContent())));
            }
        }
        MessageUtils.senGroupMsg(groupCode, builder);
    }

    public static void senPrivateMsg(String code,String msg) {
        MessageChainBuilder builder = new MessageChainBuilder();
        Friend friend = bot.getFriend(Long.valueOf(code));
        friend.sendMessage(msg);
    }
}
