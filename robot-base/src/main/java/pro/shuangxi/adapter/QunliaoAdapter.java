package pro.shuangxi.adapter;

import pro.shuangxi.handlertype.GroupHandler;
import pro.shuangxi.pojo.DispatcherParam;
import pro.shuangxi.pojo.Message;
import pro.shuangxi.support.Handler;
import pro.shuangxi.support.HandlerAdapter;
import pro.shuangxi.utils.MessageUtils;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author ：mengshx
 * @date ：Created in 2022/3/28 19:23
 * @description：群聊adapter
 */
public class QunliaoAdapter implements HandlerAdapter {

        @Override
        public void handle(DispatcherParam param, List<Handler> handlers) {
            Map<String, Object> data = param.getData();
            Object groupCode = data.get("groupCode");

            for (Handler handler : handlers) {
                if(handler instanceof GroupHandler){
                    String text = param.getKey();
                    String regex = handler.getCommondName();
                    if(Pattern.matches(regex, text)){
                        try {
                            Message message= handler.process(param);
                            if (message != null) {
                                MessageUtils.senGroupMsg(groupCode.toString(), message);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }
