package ecommerce.repository;

import ecommerce.domain.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
    @Override
    UserEntity save(UserEntity order);

    Optional<UserEntity> findByUserName(String userName);

    @Override
    List<UserEntity> findAll();
}
