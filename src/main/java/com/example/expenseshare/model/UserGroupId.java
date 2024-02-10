package com.example.expenseshare.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Embeddable
public class UserGroupId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private User user;

    @ManyToOne
    private Group group;

    public void setUser(User user) {
        this.user = user;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
