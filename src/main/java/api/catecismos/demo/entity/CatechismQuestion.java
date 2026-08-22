package api.catecismos.demo.entity;

import java.util.List;

public interface CatechismQuestion {

    Integer getQuestionNumber();

    String getQuestion();

    String getAnswer();

    List<String> getReferences();
}