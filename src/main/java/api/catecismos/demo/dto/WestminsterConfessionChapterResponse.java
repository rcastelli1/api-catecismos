package api.catecismos.demo.dto;

import java.util.List;

public record WestminsterConfessionChapterResponse(
        Integer chapterNumber,
        String chapterTitle,
        List<WestminsterConfessionParagraphResponse> paragraphs
) {
}
