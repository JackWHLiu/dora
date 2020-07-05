package dora.bugskiller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogInfo implements Info {

    private String tag = "dora";
    private String content = "";

    public LogInfo(String content) {
        this.content = content;
    }

    public LogInfo(String tag, String content) {
        this.tag = tag;
        this.content = content;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
