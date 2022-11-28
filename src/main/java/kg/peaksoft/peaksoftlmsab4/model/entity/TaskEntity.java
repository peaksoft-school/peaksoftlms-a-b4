package kg.peaksoft.peaksoftlmsab4.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @SequenceGenerator(name = "tasks_gen", sequenceName = "tasks_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_gen")
    Long id;

    @Column(name = "task_name")
    private String taskName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaskTypeEntity> taskTypes;

    @OneToOne(cascade = {MERGE, DETACH, REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private LessonEntity lesson;

}
