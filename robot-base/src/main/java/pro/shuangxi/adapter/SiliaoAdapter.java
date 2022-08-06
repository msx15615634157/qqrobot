package pro.shuangxi.adapter;

import pro.shuangxi.handlertype.PrivateHandler;
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
 * @date ：Created in 2022/3/28 19:21
 * @description：私聊适配器
 */
public class SiliaoAdapter implements HandlerAdapter {

    @Override
    public void handle(DispatcherParam param, List<Handler> handlers) {
        Map<String, Object> data = param.getData();
        Object personCode = data.get("personCode");

        for (Handler handler : handlers) {
            if(handler instanceof PrivateHandler){
                String text = param.getKey();
                String regex = handler.getCommondName();
                if(Pattern.matches(regex, text)){
                    try {
                        Message message= handler.process(param);
                        if (message != null) {
                            MessageUtils.sendGroupMsg(personCode.toString(), message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
