package ru.gb.hw8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.hw8.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
