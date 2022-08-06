package pro.shuangxi.config;

import kotlin.coroutines.EmptyCoroutineContext;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.Member;
import net.mamoe.mirai.event.ConcurrencyKind;
import net.mamoe.mirai.event.EventPriority;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.shuangxi.ProcessDispatcher;
import pro.shuangxi.adapter.QunliaoAdapter;
import pro.shuangxi.adapter.SiliaoAdapter;
import pro.shuangxi.pojo.DispatcherParam;
import pro.shuangxi.utils.BotHolderUtils;
import pro.shuangxi.utils.RegexUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mengshx
 * @description <p>启动bot</p>
 * @date 2022-07-15 16:15
 **/
@Configuration
@EnableConfigurationProperties(MyBotProperties.class)
public class BotAutoConfig {

    @Bean
    public Bot newBot(ProcessDispatcher dispatcher,MyBotProperties properties) {
        BotConfiguration configuration = new BotConfiguration();
        Bot bot = BotFactory.INSTANCE.newBot(properties.getAccount(), properties.getPassword(), configuration);
        bot.login();
        bot.getEventChannel().subscribeAlways(FriendMessageEvent.class, EmptyCoroutineContext.INSTANCE, ConcurrencyKind.CONCURRENT, EventPriority.NORMAL, event -> {
            Friend sender = event.getSender();
            MessageChain message = event.getMessage();
            DispatcherParam param = new DispatcherParam(SiliaoAdapter.class);
            param.setKey(message.contentToString());
            Map<String, Object> data = new HashMap<>();
            data.put("personCode", Long.toString(sender.getId()));
            data.put("personName", sender.getNick());
            param.setData(data);
            dispatcher.dispatcher(param);
        });
        bot.getEventChannel().subscribeAlways(GroupMessageEvent.class, EmptyCoroutineContext.INSTANCE, ConcurrencyKind.CONCURRENT, EventPriority.NORMAL, event -> {
            Group group = event.getGroup();
            MessageChain message = event.getMessage();
            Member sender = event.getSender();
            DispatcherParam param = new DispatcherParam(QunliaoAdapter.class);
            param.setKey(message.contentToString());
            Map<String, Object> data = new HashMap<>();
            data.put("groupCode", Long.toString(group.getId()));
            data.put("serializeMessage", message.serializeToMiraiCode());
            data.put("groupName", group.getName());
            data.put("personCode", Long.toString(sender.getId()));
            data.put("personName", sender.getNick());
            List<String> atList = RegexUtils.getList("@(\\d+) ", message.contentToString());
            data.put("atList", atList);
            param.setData(data);
            dispatcher.dispatcher(param);
        });


        BotHolderUtils.setBot(bot);
        return bot;
    }

    @Bean
    public QunliaoAdapter qunliaoAdapter() throws Exception {
        return new QunliaoAdapter();
    }

    @Bean
    public SiliaoAdapter siliaoAdapter() throws Exception {
        return new SiliaoAdapter();
    }

    @Bean
    public ProcessDispatcher processDispatcher() throws Exception {
        return new ProcessDispatcher();
    }

}
