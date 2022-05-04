package com.project4;
import com.project4.admin.models.Category;
import com.project4.admin.repository.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CategoryRepositoryTest {
    @Autowired private CategoryRepository repo;
    @Test
    public  void testAddNew(){
        Category category= new Category();
        category.setCategoryName("abc");
        Category save = repo.save(category);
        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getCategoryId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<Category> categories=repo.findAll();
        Assertions.assertThat(categories).hasSizeGreaterThan(0);
        for (Category category:categories){
            System.out.println(category);
        }
    }

}
