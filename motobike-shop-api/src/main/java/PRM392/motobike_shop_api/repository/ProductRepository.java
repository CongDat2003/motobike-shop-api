package PRM392.motobike_shop_api.repository;

import PRM392.motobike_shop_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
    
    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findDistinctCategories();
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.brand LIKE %:keyword% OR p.category LIKE %:keyword%")
    List<Product> searchProducts(@Param("keyword") String keyword);
}









