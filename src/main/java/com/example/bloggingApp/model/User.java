package com.example.bloggingApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //@Column(nullable = false)
    private String email;
    //@Column(nullable = false)
    private String name;
    @Column(name = "pwd")
    private String password;
    private String about;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private Post post;
}
