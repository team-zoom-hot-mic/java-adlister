
package com.codeup.adlister.dao;


        import com.codeup.adlister.models.Category;

        import java.util.List;

public interface Categories {
    // get a list of all the ads
    List<Category> all();
    // insert a new ad and return the new ad's id
    Long insert(Category category);

    Category singleCat (Long id);
}
