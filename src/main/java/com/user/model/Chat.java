package com.user.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue
    @Column(name = "chat_id")
    private Long id;

    @Column(name = "chat_name")
    private String chatName;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private Chat chat;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Message> messages;
}
