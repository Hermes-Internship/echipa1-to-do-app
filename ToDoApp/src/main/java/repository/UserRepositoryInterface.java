package repository;

import domain.User;

public interface UserRepositoryInterface {
    void create(User user);
    User findByUsername(String username);
}
