package com.source.code.BlockingQueue;

public class Message {
    private Integer messId;
    private String content;

    public Message(Integer messId, String content) {
        this.messId = messId;
        this.content = content;
    }

    public Integer getMessId() {
        return messId;
    }

    public void setMessId(Integer messId) {
        this.messId = messId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "[" +
                "messId=" + messId +
                ", content='" + content + '\'' +
                ']';
    }
}
