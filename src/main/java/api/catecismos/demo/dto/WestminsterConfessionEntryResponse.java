package api.catecismos.demo.dto;

public record WestminsterConfessionEntryResponse(
        Integer chapterNumber,
        String chapterTitle,
        Integer paragraphNumber,
        String content
) {
}
