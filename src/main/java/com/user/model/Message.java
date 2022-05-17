package com.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message", schema = "public")
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "sender_id")
    private Long senderId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "chat_id")
    private Message message;

}
