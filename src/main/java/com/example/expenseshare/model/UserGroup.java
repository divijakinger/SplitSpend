package com.example.expenseshare.model;
import jakarta.persistence.*;

@Entity
@Table(name = "user_group")
public class UserGroup {
    @EmbeddedId
    private UserGroupId id;

    @ManyToOne
    @MapsId("user")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("group")
    @JoinColumn(name = "group_id")
    private Group group;

    public UserGroup() {
    }

    public User getUser() {
        return user;
    }


    public Group getGroup() {
        return group;
    }

    public void setId(UserGroupId id) {
        this.id = id;
    }


}

