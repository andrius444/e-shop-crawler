package nutrition.model;

import javax.persistence.*;

/**
 * Created by osvaldas on 17.3.27.
 */

@Entity
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(
            cascade = {},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @OneToOne(
            cascade = {},
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "approving_product_id")
    private PersonalProduct approvingProduct;

    public Long getId() {
        return id;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public PersonalProduct getApprovingProduct() {
        return approvingProduct;
    }

    public void setApprovingProduct(PersonalProduct approvingProduct) {
        this.approvingProduct = approvingProduct;
    }
}
