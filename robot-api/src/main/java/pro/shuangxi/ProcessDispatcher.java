package pro.shuangxi;

import org.springframework.beans.factory.annotation.Autowired;
import pro.shuangxi.pojo.DispatcherParam;
import pro.shuangxi.support.Filter;
import pro.shuangxi.support.Handler;
import pro.shuangxi.support.HandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：mengshx
 * @date ：Created in 2022/3/28 10:44
 * @description：中央处理器
 */

public class ProcessDispatcher {
    @Autowired
    private List<HandlerAdapter> handlerAdapters;
    @Autowired(required = false)
    List<Handler> handlers=new ArrayList<>();
    @Autowired(required = false)
    private List<Filter> filters=new ArrayList<>();
    public void dispatcher(DispatcherParam param){
        for (Filter filter : filters) {
            filter.doFilter(param);
        }
        HandlerAdapter handlerAdapter = getHandlerAdapter(param);
        handlerAdapter.handle(param,handlers);

    }
    protected HandlerAdapter getHandlerAdapter(DispatcherParam param) {
        if (this.handlerAdapters != null) {
            for (HandlerAdapter adapter : this.handlerAdapters) {
                if (adapter.supports(param)) {
                    return adapter;
                }
            }
        }
        throw new RuntimeException("没有可用的HandlerAdapter适配器");
    }
}
