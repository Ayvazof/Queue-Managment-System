package arm.ayvazoff.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "task")
@ApiModel(description = "Details about task")
public class Task {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @ApiModelProperty(notes = "The uniq id of task")
    private String id;
    private String name;
    private String prefix;
    private int priority;

    @OneToOne
    @JoinColumn(name = "superTask_id", referencedColumnName = "id")
    @ApiModelProperty(notes = "The root task")
    private Task superTask;

    @ManyToMany(mappedBy = "tasks")
    @ApiModelProperty(notes = "List of task's offices")
    private List<Office> offices;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setSuperTaskId(Task task) {
        this.superTask = task;
    }

    public Task getSuperTaskId() {
        return superTask;
    }
}
