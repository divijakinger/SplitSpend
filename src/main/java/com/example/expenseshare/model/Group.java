package com.example.expenseshare.model;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupid")
    private Long groupId;

    @OneToMany(mappedBy = "group")
    private Set<UserGroup> userGroups = new HashSet<>();

    @Column(name = "name")
    private String groupName;

    public Group() {
    }

    public Group(String name) {
        this.groupName = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public Long getGroupId() {
        return groupId;
    }

}
