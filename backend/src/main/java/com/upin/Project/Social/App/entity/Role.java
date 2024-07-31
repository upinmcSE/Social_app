package com.upin.Project.Social.App.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name_role",length = 15)
    String name;

    @Column(length = 127)
    String description;

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();


}
