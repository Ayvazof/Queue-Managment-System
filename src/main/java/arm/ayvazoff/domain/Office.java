package arm.ayvazoff.domain;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "office")
@ApiModel(description = "Details about office")
public class Office {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy="uuid")
    @JsonView(View.Id.class)
    @ApiModelProperty(notes = "The uniq id of office")
    private String id;

    @NotBlank(message = "office name cannot be empty")
    private String name;

    @ManyToMany(mappedBy = "offices")
    @ApiModelProperty(notes = "List of office's users")
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "office_task",
            joinColumns = {@JoinColumn(name = "office_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")}
    )
    @ApiModelProperty(notes = "List of office's tasks")
    private List<Task> tasks;

    private String description;

    public Office() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void attachTask(Task task) {
        tasks.add(task);
    }

    public void detachTask(Task task) {
        tasks.remove(task);
    }

}
