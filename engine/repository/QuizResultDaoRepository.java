package engine.repository;

import engine.model.QuizResultDto;
import org.springframework.data.domain.Limit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizResultDaoRepository extends CrudRepository<QuizResultDao, Integer> {
    QuizResultDao findBySuccess(boolean success, Limit limit);
}
