package pro.shuangxi.support;

import pro.shuangxi.pojo.DispatcherParam;

import java.util.List;

/**
 * @author ：mengshx
 * @date ：Created in 2022/3/28 11:37
 * @description：默认适配器
 */
public class DefaultHandlerAdapter implements HandlerAdapter {

    @Override
    public void handle(DispatcherParam param, List<Handler> handlers) {
        for (Handler handler : handlers) {
            handler.process(param);
        }
    }
}
