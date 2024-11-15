package com.example.emailapp.domain;

import com.example.emailapp.utils.EmailConfiguration;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
public class MailBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String emailAddress;
    private String accessSmtp;
    @Enumerated(value = EnumType.STRING)
    private EmailConfiguration emailConfiguration;
}