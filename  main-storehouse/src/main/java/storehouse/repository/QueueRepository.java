package storehouse.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import storehouse.domain.Queue;

@Repository
public interface QueueRepository extends CrudRepository<Queue, Long> {
    @Override
    Queue save(Queue productsStorehouse);
}
