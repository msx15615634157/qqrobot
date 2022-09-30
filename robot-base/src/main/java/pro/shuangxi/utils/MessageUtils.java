package pro.shuangxi.utils;

import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.MessageReceipt;
import net.mamoe.mirai.message.code.MiraiCode;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;
import pro.shuangxi.pojo.Message;
import pro.shuangxi.pojo.*;

import java.io.ByteArrayInputStream;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ：mengshx
 * @date ：Created in 2021/11/22 12:09
 * @description：
 */
public class MessageUtils {
    public static void sendSerializedGroupMsg(String groupCode,String msg) {
        Group group = BotHolderUtils.getBot().getGroup(Long.valueOf(groupCode));
        if (group != null) {
            MessageChain chain = MiraiCode.deserializeMiraiCode(msg);
            group.sendMessage(chain);
        }

    }
    public static void sendGroupMsg(String groupCode,String msg) {
        Group group = BotHolderUtils.getBot().getGroup(Long.valueOf(groupCode));
        if (group != null) {
            group.sendMessage(msg);
        }

    }
    public static MessageReceipt sendGroupMsg(String groupCode,MessageChainBuilder builder) {
        Group group = BotHolderUtils.getBot().getGroup(Long.valueOf(groupCode));
        if (group != null) {
            MessageReceipt message = group.sendMessage(builder.build());
            return message;
        }
        return  null;

    }
    public static void sendGroupMsg(String groupCode, Message message) {
        MessageChainBuilder builder = new MessageChainBuilder();
        Group group = BotHolderUtils.getBot().getGroup(Long.valueOf(groupCode));
        if(group==null) return;
        for (MessageLine line : message.getContents()) {
            if (line instanceof TextLine) {
                builder.append(new PlainText(((TextLine) line).getContent()));
                continue;
            }
            if (line instanceof ImageLine) {

                byte[] bytes = ((ImageLine) line).getContent();
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                Image image = ExternalResource.uploadAsImage(byteArrayInputStream, group);
                builder.append(image);
                continue;
//                builder.image(((ImageLine) line).getContent());
            }
            if (line instanceof AtLine) {
                builder.append(new At(Long.valueOf(((AtLine) line).getContent())));
                continue;
            }
            if (line instanceof AtAllLine) {
                builder.append(AtAll.INSTANCE);
                continue;
            }
        }
        MessageReceipt groupMsg = MessageUtils.sendGroupMsg(groupCode, builder);
        if (message.getRecall() > 0) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    groupMsg.recall();
                }
            },message.getRecall());
        }
    }

    public static void senPrivateMsg(String code,String msg) {
        MessageChainBuilder builder = new MessageChainBuilder();
        Friend friend = BotHolderUtils.getBot().getFriend(Long.valueOf(code));
        friend.sendMessage(msg);
    }
}
