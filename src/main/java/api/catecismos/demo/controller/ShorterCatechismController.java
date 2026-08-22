package api.catecismos.demo.controller;

import api.catecismos.demo.dto.CatechismQuestionResponse;
import api.catecismos.demo.dto.CatechismQuestionSummaryResponse;
import api.catecismos.demo.service.CatechismService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shorter-catechism")
@Tag(
        name = "Shorter Catechism",
        description = "Endpoints for the Westminster Shorter Catechism."
)
public class ShorterCatechismController {

    private final CatechismService service;

    public ShorterCatechismController(CatechismService service) {
        this.service = service;
    }

    @GetMapping("/questions")
    @Operation(summary = "List all Shorter Catechism questions")
    public List<CatechismQuestionSummaryResponse> findAll() {
        return service.findAllShorter();
    }

    @GetMapping("/questions/{questionNumber}")
    @Operation(summary = "Find a Shorter Catechism question by number")
    public CatechismQuestionResponse findByQuestionNumber(
            @PathVariable Integer questionNumber
    ) {
        return service.findShorterByQuestionNumber(questionNumber);
    }
}