// package com.alterra.miniproject.service;

// import com.alterra.miniproject.domain.common.ApiResponse;
// import com.alterra.miniproject.domain.dao.Category;
// import com.alterra.miniproject.domain.dao.Recipe;
// import com.alterra.miniproject.domain.dao.RecipeDetail;
// import com.alterra.miniproject.domain.dao.User;
// import com.alterra.miniproject.domain.dto.RecipeRequest;
// import com.alterra.miniproject.repository.CategoryRepository;
// import com.alterra.miniproject.repository.RecipeRepository;
// import com.alterra.miniproject.repository.UserRepository;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import static org.mockito.ArgumentMatchers.any;

// import java.util.List;
// import java.util.Objects;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.*;

// @ExtendWith(SpringExtension.class)
// @SpringBootTest(classes = RecipeService.class)
// public class RecipeServiceTest {

//     @MockBean
//     private RecipeRepository recipeRepository;

//     @MockBean
//     private UserRepository userRepository;

//     @MockBean
//     private CategoryRepository categoryRepository;

//     @Autowired
//     private RecipeService recipeService;

  
//     @Test
//     void testAddRecipe() {
//         when(userRepository.findOne(anyLong())).thenReturn(Optional.of(User.builder()
//             .id(1L)
//             .username("lisaanis")
//             .password("1234")
//             .active(true)
//             .build()));
        
//         when(categoryRepository.findOne(anyLong())).thenReturn(Optional.of(Category.builder()
//             .id(1L)
//             .category("Masakan Jawa")
//             .build()));

//         when(recipeRepository.save(any())).thenReturn(Recipe.builder()
//             .id(1L)
//             .user(User.builder().id(1L).build())
//             .recipeName("Nasi Goreng Jawa")
//             .category(Category.builder().id(1L).build())
//             .description("Nasi goreng jawa enak")
//             .detail(RecipeDetail.builder()
//                 .ingredients("Bawang putih, bawah merah, Kari, Cabai Merah, Nasi, Ayam")
//                 .directions("1. Siapkan bahan-bahan 2. Tumis bumbu yang sudah disiapkan 3. Masukkan nasi, ayam, kecap, sambal, dan toping 4. Masak hingga tercampur 5. Siap dihidangkan")
//                 .build())
//             .build());
        
//         ResponseEntity<Object> responseEntity = recipeService.addRecipe(RecipeRequest.builder().recipeName("Nasi Goreng Jawa").build());
//         ApiResponse response = (ApiResponse) responseEntity.getBody();
//         Recipe recipe = (Recipe) Objects.requireNonNull(response).getData();

//         assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//         assertEquals("SUCCESS", response.getMessage());
//         assertEquals(1L, recipe.getId());
//         assertEquals(1L, recipe.getUser().getId());
//         assertEquals("Nasi Goreng Jawa", recipe.getRecipeName());
//         assertEquals(1L, recipe.getCategory().getId());
//         assertEquals("Nasi goreng jawa enak", recipe.getDescription());
//         assertEquals("Bawang putih, bawah merah, Kari, Cabai Merah, Nasi, Ayam", recipe.getDetail().getIngredients());
//         assertEquals("1. Siapkan bahan-bahan 2. Tumis bumbu yang sudah disiapkan 3. Masukkan nasi, ayam, kecap, sambal, dan toping 4. Masak hingga tercampur 5. Siap dihidangkan", recipe.getDetail().getDirections());
//     }

//     @Test
//     void testGetAllRecipe() {
        
//         when(recipeRepository.findAll()).thenReturn(List.of(Recipe.builder()
//             .id(1L)
//             .user(User.builder().id(1L).build())
//             .recipeName("Nasi Goreng Jawa")
//             .category(Category.builder().id(1L).build())
//             .description("Nasi goreng jawa enak")
//             .detail(RecipeDetail.builder()
//                 .ingredients("Bawang, Cabai, Nasi, Telur, Ayam")
//                 .directions("1. Siapkan bumbu dan tumis 2. Masukkan telur, nasi, dan ayam 3. Masak hingga matang")
//                 .build())
//             .build()));
        
//         ResponseEntity<Object> responseEntity = recipeService.getAllRecipe();
//         ApiResponse response = (ApiResponse) responseEntity.getBody();
//         List<Recipe> recipes = (List<Recipe>) Objects.requireNonNull(response).getData();
        
//         assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//         assertEquals("SUCCESS", response.getMessage());
//         assertEquals(1, recipes.size());
//     }

//     @Test
//     void testGetRecipeDetail() {
//         when(recipeRepository.findOne(anyLong())).thenReturn(Optional.of(Recipe.builder()
//             .id(1L)
//             .user(User.builder().id(1L).build())
//             .recipeName("Nasi Goreng Jawa")
//             .category(Category.builder().id(1L).build())
//             .description("Nasi goreng jawa enak")
//             .detail(RecipeDetail.builder()
//                 .ingredients("Bawang, Cabai, Nasi, Telur, Ayam")
//                 .directions("1. Siapkan bumbu dan tumis 2. Masukkan telur, nasi, dan ayam 3. Masak hingga matang")
//                 .build())
//             .build()));

//         ResponseEntity<Object> responseEntity = recipeService.getRecipeDetail(1L);
//         ApiResponse response = (ApiResponse) responseEntity.getBody();
//         Recipe recipe = (Recipe) Objects.requireNonNull(response).getData();

//         assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
//         assertEquals("SUCCESS", response.getMessage());
//         assertEquals(1L, recipe.getId());
//         assertEquals(1L, recipe.getUser().getId());
//         assertEquals("Nasi Goreng Jawa", recipe.getRecipeName());
//         assertEquals(1L, recipe.getCategory().getId());
//         assertEquals("Nasi goreng jawa enak", recipe.getDetail().getIngredients());
//         assertEquals("Bawang, Cabai, Nasi, Telur, Ayam", recipe.getDetail().getIngredients());
//         assertEquals("1. Siapkan bumbu dan tumis 2. Masukkan telur, nasi, dan ayam 3. Masak hingga matang", recipe.getDetail().getDirections());
//     }

//     @Test
//     void testUpdateRecipe() {

//     }

//     @Test
//     void testDeleteRecipe() {

//     }
// }
