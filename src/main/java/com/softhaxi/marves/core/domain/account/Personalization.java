package com.softhaxi.marves.core.domain.account;

import java.io.Serializable;

/**
 * @author Raja Sihombing
 * @since 1
 */
public class Personalization implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 665081220211361523L;
    protected String id;
    protected User user;
    protected int sequence;
    protected String question;
    protected String answer;
    
    /**
     * 
     */
    public Personalization() {
        this(null, null, 0, null, null);
    }

    /**
     * 
     * @param id
     * @param user
     * @param sequence
     * @param question
     * @param answer 
     */
    public Personalization(String id, User user, int sequence, String question, String answer) {
        this.id = id;
        this.user = user;
        this.sequence = sequence;
        this.question = question;
        this.answer = answer;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the sequence
     */
    public int getSequence() {
        return sequence;
    }

    /**
     * @param sequence the sequence to set
     */
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Personalization{" 
                + "id=" + id 
                + ", user=" + user 
                + ", sequence=" + sequence 
                + ", question=" + question 
                + ", answer=" + answer 
                + '}';
    }
    
    
}
