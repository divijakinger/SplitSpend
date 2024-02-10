package com.example.expenseshare.repository;

import com.example.expenseshare.model.User;
import com.example.expenseshare.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {
    boolean existsByUser_IdAndGroup_GroupId(Long userId, Long groupId);

    @Query("SELECT ug FROM UserGroup ug WHERE ug.group.groupId = :groupId")
    List<UserGroup> findByGroupId(@Param("groupId") Long groupId);

    List<UserGroup> findByGroup_GroupId(Long groupId);
}
