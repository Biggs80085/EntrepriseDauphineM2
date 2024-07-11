package com.example.user.Repository;

import com.example.user.Model.Auser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Auser, Long> {
}
