package engine.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDaoRepository extends CrudRepository<QuizDao, Integer>,
        PagingAndSortingRepository<QuizDao, Integer> {
}
