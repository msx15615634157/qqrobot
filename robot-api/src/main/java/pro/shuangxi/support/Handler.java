package pro.shuangxi.support;

import pro.shuangxi.pojo.DispatcherParam;
import pro.shuangxi.pojo.Message;

/**
 * @author ：mengshx
 * @date ：Created in 2022/3/28 10:39
 * @description：处理器
 */
public interface Handler {
    Message process(DispatcherParam param);
    String  getCommondName();
}
