package pro.shuangxi.pojo;

import java.util.ArrayList;

/**
 * @author ：mengshx
 * @date ：Created in 2022/3/29 14:40
 * @description：发送的消息实体
 */
public class Message {



    ArrayList<MessageLine> contents = new ArrayList<>();

    public ArrayList<MessageLine> getContents() {
        return contents;
    }

    public Message addLine(MessageLine line) {
        contents.add(line);
        return this;
    }
    public static Message getInstance(){
        return new Message();
    }
}
