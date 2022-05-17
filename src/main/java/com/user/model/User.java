package com.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name =  "surname")
    private String surname;

    @Column(name = "age")
    private int age;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="user_friend",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="friend_id")})
    private Set<User> friends = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "friends")
    private Set<User> theFriends = new HashSet<>();

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Chat> chats;
}
