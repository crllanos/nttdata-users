package com.nttdata.users.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;
    private String email;
    transient private String password;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean active;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<PhoneEntity> phones;
}