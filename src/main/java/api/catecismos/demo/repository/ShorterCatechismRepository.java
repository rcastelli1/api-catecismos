package api.catecismos.demo.repository;

import api.catecismos.demo.entity.ShorterCatechism;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShorterCatechismRepository extends JpaRepository<ShorterCatechism, Long> {

    List<ShorterCatechism> findAllByOrderByQuestionNumberAsc();

    Optional<ShorterCatechism> findByQuestionNumber(Integer questionNumber);
}
