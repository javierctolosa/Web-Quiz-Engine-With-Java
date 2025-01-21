package engine.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDaoRepository extends CrudRepository<UserDao, Long> {
    Optional<UserDao> findUserDaoByEmail(String email);

    boolean existsByEmail(String email);
}
