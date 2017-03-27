package com.ss.server.entity;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/27
 * Time: 下午6:47
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class GuideSentence extends BaseEntity {

    /**
     * 引导句
     */
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
