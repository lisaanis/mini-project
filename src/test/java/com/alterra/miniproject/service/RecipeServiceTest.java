// package com.alterra.miniproject.service;

// import com.alterra.miniproject.constant.constants;
// import com.alterra.miniproject.domain.dao.Recipe;
// import com.alterra.miniproject.repository.RecipeRepository;

// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import static org.mockito.ArgumentMatchers.any;

// import java.time.LocalDateTime;
// import static org.mockito.Mockito.when;

// @ExtendWith(SpringExtension.class)
// @SpringBootTest(classes = RecipeService.class)
// public class RecipeServiceTest {

//     @MockBean
//     private RecipeRepository recipeRepository;

//     @Autowired
//     private RecipeService recipeService;

//     @Test
//     void testAddRecipe() {
//         when(recipeRepository.save(any()))
//             .thenReturn(Recipe.builder())
//                 .id(1L)
//                 .createdAt(LocalDateTime.now())
//                 .createdBy(constants.DEFAULT_SYSTEM)
//                 .user(1L)
//                 .recipeName("Nasi Goreng Jawa")
//                 .description("Nasi Goreng Jawa merupakan makanan khas asli Jawa yang berbeda dengan nasi goreng China. Nasi Goreng Jawa berwarna agak kuning karena dicampur kari.")
//                 .ingredients("Bawang putih, bawah merah, Kari, Cabai Merah, Nasi, Ayam")
//                 .directions("1. Siapkan bahan-bahan 2. Tumis bumbu yang sudah disiapkan 3. Masukkan nasi, ayam, kecap, sambal, dan toping 4. Masak hingga tercampur 5. Siap dihidangkan")
//                 .build();

//     }

//     @Test
//     void testDeleteRecipe() {

//     }

//     @Test
//     void testGetAllRecipe() {

//     }

//     @Test
//     void testGetRecipeDetail() {

//     }

//     @Test
//     void testUpdateRecipe() {

//     }
// }
