package pro.shuangxi.support;

import pro.shuangxi.pojo.DispatcherParam;

/**
 * @author ：mengshx
 * @date ：Created in 2022/4/5 10:51
 * @description：Filter
 */
public interface Filter {

    public void doFilter(DispatcherParam param);
}
