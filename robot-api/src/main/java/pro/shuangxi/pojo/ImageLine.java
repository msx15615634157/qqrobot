package pro.shuangxi.pojo;

/**
 * @author ：mengshx
 * @date ：Created in 2022/3/29 14:48
 * @description：
 */
public class ImageLine extends MessageLine {
    byte[] content;

    public ImageLine(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
