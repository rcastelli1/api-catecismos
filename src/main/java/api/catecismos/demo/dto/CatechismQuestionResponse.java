package api.catecismos.demo.dto;

import java.util.List;

public record CatechismQuestionResponse(
        Integer questionNumber,
        String question,
        String answer,
        List<String> references
) {
}