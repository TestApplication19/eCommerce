package ecommerce.service;

import ecommerce.domain.UserEntity;
import ecommerce.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserDBService {
    @Autowired
    private UserEntityRepository userEntityRepository;

    public UserEntity saveUser(UserEntity user) {
        return userEntityRepository.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return userEntityRepository.findAll();
    }
}
