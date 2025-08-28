package com.sarvika.assignment;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sarvika.assignment.*;

public interface UserRepository extends JpaRepository<User, Long> {
}