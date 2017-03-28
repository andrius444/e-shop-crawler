package nutrition.dto.product;

import java.util.List;

/**
 * Created by andrius on 3/27/17.
 */

public class ProductDTO extends AbstractProductDTO {

    private List<Long> inUsersFavoritesIds;

    public List<Long> getInUsersFavoritesIds() {
        return inUsersFavoritesIds;
    }

    public void setInUsersFavoritesIds(List<Long> inUsersFavoritesIds) {
        this.inUsersFavoritesIds = inUsersFavoritesIds;
    }
}
