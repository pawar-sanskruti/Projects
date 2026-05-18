package com.college.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.college.exam.entity.Faculty;

/*
import org.springframework.data.jpa.repository.JpaRepository;
import com.college.exam.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    Faculty findByUsernameAndPassword(String username, String password);
}*/
@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> {

    @Query("SELECT f FROM Faculty f WHERE f.username = :username AND f.password = :password")
    Faculty findByUsernameAndPassword(@Param("username") String username,
                                      @Param("password") String password);
}