package web.core.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import web.core.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
