package api.catecismos.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "westminster_confession",
        schema = "westminster",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_westminster_confession_chapter_paragraph",
                        columnNames = {"chapter_number", "paragraph_number"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class WestminsterConfession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chapter_number", nullable = false)
    private Integer chapterNumber;

    @Column(name = "chapter_title", nullable = false)
    private String chapterTitle;

    @Column(name = "paragraph_number", nullable = false)
    private Integer paragraphNumber;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
}
