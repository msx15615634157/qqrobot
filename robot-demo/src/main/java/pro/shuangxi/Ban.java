package pro.shuangxi;

import org.springframework.stereotype.Component;
import pro.shuangxi.handlertype.GroupHandler;
import pro.shuangxi.pojo.DispatcherParam;
import pro.shuangxi.pojo.Message;
import pro.shuangxi.pojo.TextLine;
import pro.shuangxi.utils.MemberUtils;

import java.util.Map;

/**
 * @author ：mengshx
 * @date ：Created in 2022/8/6 17:32
 * @description：
 */
@Component
public class Ban implements GroupHandler {
    @Override
    public Message process(DispatcherParam param) {
        Map<String, Object> data = param.getData();
        Object groupCode = data.get("groupCode");
        Message hello = Message.getInstance().addLine(new TextLine("hello"));
        MemberUtils.chuo((String) groupCode,"1691982637");
        MemberUtils.ban((String) groupCode,"1691982637",10);
        return hello;
    }

    @Override
    public String getCommondName() {
        return "禁言";
    }
}
