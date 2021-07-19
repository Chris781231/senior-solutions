package training360.activitytracker;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

    @TableGenerator(name = "act_id_gen",
            table = "act_id_gen",
            pkColumnName = "id_gen",
            valueColumnName = "id_val")
    @Id
    @GeneratedValue(generator = "act_id_gen")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "description", nullable = false, length = 200)
    private String desc;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Type type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "labels", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "label")
    private Set<String> labels;

    public Activity(LocalDateTime startTime, String desc, Type type) {
        this.startTime = startTime;
        this.desc = desc;
        this.type = type;
    }
}
