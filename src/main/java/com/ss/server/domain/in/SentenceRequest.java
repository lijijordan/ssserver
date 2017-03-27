package com.ss.server.domain.in;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/16
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public class SentenceRequest {

    private String sentence;

    private String author;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
