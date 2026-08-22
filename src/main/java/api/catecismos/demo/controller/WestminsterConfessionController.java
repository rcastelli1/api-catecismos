package api.catecismos.demo.controller;

import api.catecismos.demo.dto.WestminsterConfessionChapterResponse;
import api.catecismos.demo.dto.WestminsterConfessionChapterSummaryResponse;
import api.catecismos.demo.dto.WestminsterConfessionEntryResponse;
import api.catecismos.demo.service.WestminsterConfessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/westminster-confession")
@Tag(
        name = "Westminster Confession",
        description = "Endpoints for the Westminster Confession of Faith."
)
public class WestminsterConfessionController {

    private final WestminsterConfessionService service;

    public WestminsterConfessionController(WestminsterConfessionService service) {
        this.service = service;
    }

    @GetMapping("/chapters")
    @Operation(summary = "List all Westminster Confession chapters")
    public List<WestminsterConfessionChapterSummaryResponse> findAllChapters() {
        return service.findAllChapters();
    }

    @GetMapping("/chapters/{chapterNumber}")
    @Operation(summary = "Find a Westminster Confession chapter by number")
    public WestminsterConfessionChapterResponse findChapter(
            @PathVariable Integer chapterNumber
    ) {
        return service.findChapter(chapterNumber);
    }

    @GetMapping("/chapters/{chapterNumber}/paragraphs/{paragraphNumber}")
    @Operation(summary = "Find a Westminster Confession paragraph")
    public WestminsterConfessionEntryResponse findParagraph(
            @PathVariable Integer chapterNumber,
            @PathVariable Integer paragraphNumber
    ) {
        return service.findParagraph(chapterNumber, paragraphNumber);
    }
}
