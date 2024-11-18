package com.example.MovieBookingSystem.Repository;

import com.example.MovieBookingSystem.Models.User;
import com.example.MovieBookingSystem.Services.ShowService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
