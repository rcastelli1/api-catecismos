package api.catecismos.demo.service;

import api.catecismos.demo.dto.WestminsterConfessionChapterResponse;
import api.catecismos.demo.dto.WestminsterConfessionChapterSummaryResponse;
import api.catecismos.demo.dto.WestminsterConfessionEntryResponse;
import api.catecismos.demo.dto.WestminsterConfessionParagraphResponse;
import api.catecismos.demo.entity.WestminsterConfession;
import api.catecismos.demo.exception.ResourceNotFoundException;
import api.catecismos.demo.repository.WestminsterConfessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class WestminsterConfessionService {

    private final WestminsterConfessionRepository repository;

    public WestminsterConfessionService(WestminsterConfessionRepository repository) {
        this.repository = repository;
    }

    public List<WestminsterConfessionChapterSummaryResponse> findAllChapters() {
        Map<Integer, String> chapters = new LinkedHashMap<>();

        repository.findAllByOrderByChapterNumberAscParagraphNumberAsc()
                .forEach(entry -> chapters.putIfAbsent(
                        entry.getChapterNumber(),
                        entry.getChapterTitle()
                ));

        return chapters.entrySet()
                .stream()
                .map(entry -> new WestminsterConfessionChapterSummaryResponse(
                        entry.getKey(),
                        entry.getValue()
                ))
                .toList();
    }

    public WestminsterConfessionChapterResponse findChapter(Integer chapterNumber) {
        List<WestminsterConfession> entries =
                repository.findByChapterNumberOrderByParagraphNumberAsc(chapterNumber);

        if (entries.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Westminster Confession chapter " + chapterNumber + " was not found."
            );
        }

        WestminsterConfession first = entries.getFirst();

        List<WestminsterConfessionParagraphResponse> paragraphs = entries.stream()
                .map(entry -> new WestminsterConfessionParagraphResponse(
                        entry.getParagraphNumber(),
                        entry.getContent()
                ))
                .toList();

        return new WestminsterConfessionChapterResponse(
                first.getChapterNumber(),
                first.getChapterTitle(),
                paragraphs
        );
    }

    public WestminsterConfessionEntryResponse findParagraph(
            Integer chapterNumber,
            Integer paragraphNumber
    ) {
        WestminsterConfession entry = repository
                .findByChapterNumberAndParagraphNumber(chapterNumber, paragraphNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Westminster Confession chapter " + chapterNumber +
                                ", paragraph " + paragraphNumber + " was not found."
                ));

        return new WestminsterConfessionEntryResponse(
                entry.getChapterNumber(),
                entry.getChapterTitle(),
                entry.getParagraphNumber(),
                entry.getContent()
        );
    }
}
