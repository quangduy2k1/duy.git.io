package com.project4.admin.models;

import javax.persistence.*;

@Entity
@Table(name="feedback")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;
    @Column(nullable = false, name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public FeedBack() {

    }
    public FeedBack(String name,User user) {
        this.name=name;
        this.user=user;
    }
    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
