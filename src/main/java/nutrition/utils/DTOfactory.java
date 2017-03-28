package nutrition.utils;

import nutrition.dto.category.CategoryDTO;
import nutrition.dto.meal.MealDTO;
import nutrition.dto.product.AbstractProductDTO;
import nutrition.dto.product.PersonalProductDTO;
import nutrition.dto.product.ProductDTO;
import nutrition.dto.user.UserDTO;
import nutrition.model.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by andrius on 3/27/17.
 */

public class DTOfactory {

    public static ProductDTO makeDTO(Product entity) {
        ProductDTO dto = new ProductDTO();
        mapCommonProductFields(entity, dto);
        dto.setCategoryId(entity.getCategory().getId());
        dto.setInUsersFavoritesIds(Extractor.getUsersIds(new ArrayList<>(entity.getInUsersFavorites())));
        return dto;
    }

    public static PersonalProductDTO makeDTO(PersonalProduct entity) {
        PersonalProductDTO dto = new PersonalProductDTO();
        mapCommonProductFields(entity, dto);
        dto.setCategoryId(entity.getCategory().getId());
        dto.setOwnerId(entity.getOwner().getId());
        return dto;
    }

    public static CategoryDTO makeDTO(Category entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        List<Product> products = entity.getProducts();
        List<ProductDTO> prodDTOs = (products == null) ? new ArrayList<>() : makeProductDTOlist(products);
        dto.setProducts(prodDTOs);
        return dto;
    }

    public static UserDTO makeDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());

        List<Product> favProducts = new ArrayList<>(entity.getMyFavouriteProducts());
        List<ProductDTO> favProductsDTOs = makeProductDTOlist(favProducts);
        dto.setMyFavouriteProducts(favProductsDTOs);

        List<Meal> meals = new ArrayList<>(entity.getMyMeals());
        List<MealDTO> mealsDTOs = makeMealDTOlist(meals);
        dto.setMyMeals(mealsDTOs);

        List<PersonalProduct> personalProducts = new ArrayList<>(entity.getMyPersonalProducts());
        List<PersonalProductDTO> personalProductsDTOs = makePersonalProductDTOlist(personalProducts);
        dto.setMyPersonalProducts(personalProductsDTOs);

        return dto;
    }

    public static MealDTO makeDTO(Meal entity) {
        MealDTO dto = new MealDTO();
        dto.setId(entity.getId());
        dto.setOwnerId(entity.getOwner().getId());
        dto.setName(entity.getName());
        dto.setApproved(entity.isApproved());
        dto.setIngredients(makeIngredientsDTOlist(entity.getIngredients()));
        return dto;
    }

    public static List<ProductDTO> makeProductDTOlist(List<Product> products) {
        return products.stream().map(DTOfactory::makeDTO).collect(Collectors.toList());
    }

    private static List<PersonalProductDTO> makePersonalProductDTOlist(List<PersonalProduct> personalProducts) {
        return personalProducts.stream().map(DTOfactory::makeDTO).collect(Collectors.toList());
    }

    public static List<CategoryDTO> makeCategoryDTOlist(List<Category> categories) {
        return categories.stream().map(DTOfactory::makeDTO).collect(Collectors.toList());
    }

    public static List<MealDTO> makeMealDTOlist(List<Meal> meals) {
        return meals.stream().map(DTOfactory::makeDTO).collect(Collectors.toList());
    }

    public static List<UserDTO> makeUserDTOlist(List<User> users) {
        return users.stream().map(DTOfactory::makeDTO).collect(Collectors.toList());
    }

    // PRIVATE

    private static Map<ProductDTO,Integer> makeIngredientsDTOlist(Map<Product, Integer> ingredients) {
        return ingredients.entrySet()
                          .stream()
                          .map(entry -> {
                              return new AbstractMap.SimpleEntry<>(DTOfactory.makeDTO(entry.getKey()), entry.getValue());
                          })
                          .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
    }

    private static void mapCommonProductFields(AbstractProduct entity, AbstractProductDTO dto) {
        dto.setId(entity.getId());
        dto.setKcals(entity.getKcals());
        dto.setFats(entity.getFats());
        dto.setCarbs(entity.getCarbs());
        dto.setProteins(entity.getProteins());
    }

}
