package engine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuizCompletionRepository extends CrudRepository<QuizCompletion, Long>,
        PagingAndSortingRepository<QuizCompletion, Long> {
    Page<QuizCompletion> findByUserDao(UserDao userDao, Pageable pageable);
}
