package nutrition.dto.product;

/**
 * Created by andrius on 3/28/17.
 */

public class PersonalProductDTO extends AbstractProductDTO {

    private Long ownerId;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

}
