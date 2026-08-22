package api.catecismos.demo.service;

import api.catecismos.demo.dto.CatechismQuestionResponse;
import api.catecismos.demo.dto.CatechismQuestionSummaryResponse;
import api.catecismos.demo.entity.CatechismQuestion;
import api.catecismos.demo.entity.LargerCatechism;
import api.catecismos.demo.entity.ShorterCatechism;
import api.catecismos.demo.exception.ResourceNotFoundException;
import api.catecismos.demo.repository.LargerCatechismRepository;
import api.catecismos.demo.repository.ShorterCatechismRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CatechismService {

    private final ShorterCatechismRepository shorterRepository;
    private final LargerCatechismRepository largerRepository;

    public CatechismService(
            ShorterCatechismRepository shorterRepository,
            LargerCatechismRepository largerRepository
    ) {
        this.shorterRepository = shorterRepository;
        this.largerRepository = largerRepository;
    }

    public List<CatechismQuestionSummaryResponse> findAllShorter() {
        return shorterRepository.findAllByOrderByQuestionNumberAsc()
                .stream()
                .map(this::toSummaryResponse)
                .toList();
    }

    public CatechismQuestionResponse findShorterByQuestionNumber(Integer questionNumber) {
        ShorterCatechism catechism = shorterRepository
                .findByQuestionNumber(questionNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Shorter Catechism question " + questionNumber + " was not found."
                ));

        return toResponse(catechism);
    }

    public List<CatechismQuestionSummaryResponse> findAllLarger() {
        return largerRepository.findAllByOrderByQuestionNumberAsc()
                .stream()
                .map(this::toSummaryResponse)
                .toList();
    }

    public CatechismQuestionResponse findLargerByQuestionNumber(Integer questionNumber) {
        LargerCatechism catechism = largerRepository
                .findByQuestionNumber(questionNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Larger Catechism question " + questionNumber + " was not found."
                ));

        return toResponse(catechism);
    }

    private CatechismQuestionSummaryResponse toSummaryResponse(
            CatechismQuestion catechism
    ) {
        return new CatechismQuestionSummaryResponse(
                catechism.getQuestionNumber(),
                catechism.getQuestion()
        );
    }

    private CatechismQuestionResponse toResponse(
            CatechismQuestion catechism
    ) {
        return new CatechismQuestionResponse(
                catechism.getQuestionNumber(),
                catechism.getQuestion(),
                catechism.getAnswer(),
                List.copyOf(catechism.getReferences())
        );
    }
}