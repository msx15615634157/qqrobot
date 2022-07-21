package pro.shuangxi.pojo;

import java.util.Map;

/**
 * @author ：mengshx
 * @date ：Created in 2022/3/28 10:47
 * @description：转发参数
 */
public class DispatcherParam {
    private Class adapter;
    private String key;
    private Map<String,Object> data;

    public DispatcherParam(Class adapter) {
        this.adapter = adapter;
    }

    public Class getAdapter() {
        return adapter;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
