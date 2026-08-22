package api.catecismos.demo.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "larger_catechism", schema = "westminster")
@Getter
@Setter
@NoArgsConstructor
public class LargerCatechism implements CatechismQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_number", nullable = false, unique = true)
    private Integer questionNumber;

    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(name = "answer", nullable = false, columnDefinition = "TEXT")
    private String answer;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "larger_catechism_references",
            schema = "westminster",
            joinColumns = @JoinColumn(name = "larger_catechism_id")
    )
    @OrderColumn(name = "reference_order")
    @Column(name = "reference_text", nullable = false, columnDefinition = "TEXT")
    private List<String> references = new ArrayList<>();
}
