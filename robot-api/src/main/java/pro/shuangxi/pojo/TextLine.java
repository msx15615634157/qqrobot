package pro.shuangxi.pojo;

/**
 * @author ：mengshx
 * @date ：Created in 2022/3/29 14:48
 * @description：文本
 */
public class TextLine extends MessageLine {
    String content ;

    public TextLine(String content) {
       this(content, true);
    }
    public TextLine(String content,boolean isEnter) {
        if (isEnter) {
            this.content = content+"\r\n";
        }else {
            this.content = content;
        }

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
