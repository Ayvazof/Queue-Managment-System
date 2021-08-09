package arm.ayvazoff.domain;

import javax.persistence.*;

@Entity
@Table(name = "ord")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String createTimeDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateTimeDate() {
        return createTimeDate;
    }

    public void setCreateTimeDate(String createTimeDate) {
        this.createTimeDate = createTimeDate;
    }
}
