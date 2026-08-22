package api.catecismos.demo.repository;

import api.catecismos.demo.entity.LargerCatechism;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LargerCatechismRepository extends JpaRepository<LargerCatechism, Long> {

    List<LargerCatechism> findAllByOrderByQuestionNumberAsc();

    Optional<LargerCatechism> findByQuestionNumber(Integer questionNumber);
}
