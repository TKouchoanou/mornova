package org.mornova.repository.jpa.repository;

import org.mornova.domain.core.model.entities.User;
import org.mornova.domain.core.model.objectValue.ids.UserId;
import org.mornova.domain.core.repository.UserRepository;
import org.mornova.repository.jpa.entity.UserJPA;
import org.mornova.repository.jpa.mapper.domain.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserRepositoryImpl implements UserRepository{
    UserJPARepository userJPARepository;
    UserMapper userMapper;

    public UserRepositoryImpl(UserJPARepository userJPARepository, UserMapper userMapper) {
        this.userJPARepository = userJPARepository;
        this.userMapper = userMapper;
    }


    @Override
    public User save(User user) {
        UserJPA userJPA=userMapper.toJpaEntity(user,u->{
            //pour comment par exemple on reçoit post donc l'hydratation de commentJPA post se ferra par ses consummer
        });
        return userMapper.toDomainEntity(userJPARepository.save(userJPA));
    }

    @Override
    public Optional<User> find(UserId userId) {

        Optional<UserJPA> userJPA= userJPARepository.findById(userId);
        return userJPA.map(user->userMapper.toDomainEntity(user));
    }

    @Override
    public User delete(UserId userId) {
        return null;
    }
}
