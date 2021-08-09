package arm.ayvazoff.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ticket")
@ApiModel(description = "Details about ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ApiModelProperty(notes = "queue number, auto generates")
    private int queueNum;

    @NotBlank
    @OneToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    @ApiModelProperty(notes = "Attached task")
    private Task task;
    @ApiModelProperty(notes = "Ticket creation date and time")
    private String createTimeDate;
    @ApiModelProperty(notes = "State changing date and time")
    private String stateChangeDateTime;
    @ApiModelProperty(notes = "state (DONE, WAIT, PROCESS, PASSED)")
    private State state;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ApiModelProperty(notes = "Ticket manager user")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQueueNum() {
        return queueNum;
    }

    public void setQueueNum(int queueNum) {
        this.queueNum = queueNum;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getCreateTimeDate() {
        return createTimeDate;
    }

    public void setCreateTimeDate(String createTimeDate) {
        this.createTimeDate = createTimeDate;
    }

    public String getStateChangeDateTime() {
        return stateChangeDateTime;
    }

    public void setStateChangeDateTime(String stateChangeDateTime) {
        this.stateChangeDateTime = stateChangeDateTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
