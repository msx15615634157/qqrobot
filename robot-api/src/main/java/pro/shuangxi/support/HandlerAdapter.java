package pro.shuangxi.support;

import pro.shuangxi.pojo.DispatcherParam;

import java.util.List;

/**
 * @author ：mengshx
 * @date ：Created in 2022/3/28 10:39
 * @description：处理器
 */
public interface HandlerAdapter {
    default boolean supports(DispatcherParam param){
        if (param.getAdapter().getName().equals(this.getClass().getName())) {
            return true;
        }
        return false;
    }

    void handle(DispatcherParam param, List<Handler> handlers);
}
