package dora.bugskiller;

public class LogInfo implements Info {

    private String content = "";

    public LogInfo(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
