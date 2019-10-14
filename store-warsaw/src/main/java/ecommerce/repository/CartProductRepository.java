package ecommerce.repository;

import ecommerce.domain.CartProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartProductRepository extends CrudRepository<CartProduct, Long> {

    @Override
    Optional<CartProduct> findById(Long id);

    @Override
    CartProduct save(CartProduct cartProduct);
}
