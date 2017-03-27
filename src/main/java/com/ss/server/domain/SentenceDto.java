package com.ss.server.domain;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/27
 * Time: 下午10:01
 * To change this template use File | Settings | File Templates.
 */
public class SentenceDto {

    private String sentence;

    private String author;

    /**
     * Gets sentence.
     *
     * @return the sentence
     */
    public String getSentence() {
        return sentence;
    }

    /**
     * Sets sentence.
     *
     * @param sentence the sentence
     */
    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    /**
     * Gets author.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }
}
