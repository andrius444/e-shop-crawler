package nutrition.model;

import javax.persistence.*;

/**
 * Created by andrius on 3/28/17.
 */

@Entity
public class PersonalProduct extends Product {

    @ManyToOne(
            cascade = {},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "owner_id")
    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
