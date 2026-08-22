package api.catecismos.demo.repository;

import api.catecismos.demo.entity.WestminsterConfession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WestminsterConfessionRepository extends JpaRepository<WestminsterConfession, Long> {

    List<WestminsterConfession> findAllByOrderByChapterNumberAscParagraphNumberAsc();

    List<WestminsterConfession> findByChapterNumberOrderByParagraphNumberAsc(Integer chapterNumber);

    Optional<WestminsterConfession> findByChapterNumberAndParagraphNumber(
            Integer chapterNumber,
            Integer paragraphNumber
    );
}
