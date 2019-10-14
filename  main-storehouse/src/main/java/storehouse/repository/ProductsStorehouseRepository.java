package storehouse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import storehouse.domain.ProductsStorehouse;

import java.util.Optional;

@Repository
public interface ProductsStorehouseRepository extends CrudRepository<ProductsStorehouse, Long> {
    @Override
    ProductsStorehouse save(ProductsStorehouse productsStorehouse);

    @Override
    Optional<ProductsStorehouse> findById(Long id);
}
