package ru.otus.kupipodariday.user.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.kupipodariday.user.model.User;
import ru.otus.kupipodariday.user.model.UserView;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<UserView> findAllByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(String username, String email);

    Optional<UserView> findByUsername(String username);

    Optional<User> getByUsername(String email);

}
