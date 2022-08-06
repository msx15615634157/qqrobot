package pro.shuangxi.pojo;

/**
 * @author ：mengshx
 * @date ：Created in 2022/3/29 14:48
 * @description：艾特
 */
public class AtLine extends MessageLine {
    String content ;

    public AtLine(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
