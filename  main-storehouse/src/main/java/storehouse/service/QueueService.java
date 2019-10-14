package storehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import storehouse.domain.Queue;
import storehouse.repository.QueueRepository;

@Transactional
@Service
public class QueueService {

    @Autowired
    private QueueRepository queueRepository;

    public Queue addQueue(final Queue queue){
        return queueRepository.save(queue);
    }
}
