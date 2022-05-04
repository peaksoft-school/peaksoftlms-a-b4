package kg.peaksoft.peaksoftlmsab4.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kg.peaksoft.peaksoftlmsab4.model.enums.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private TestEntity testEntity;

    @OneToMany(mappedBy = "question",cascade = {CascadeType.ALL})
    private List<OptionEntity> options;

    public void setOptions(OptionEntity option){
        if (options==null){
            options=new ArrayList<>();
        }
        options.add(option);
        option.setQuestion(this);
    }

}
