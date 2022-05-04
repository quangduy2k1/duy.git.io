package com.project4.admin.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
@Data
@RequiredArgsConstructor
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;
    @Column(nullable = false, name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
