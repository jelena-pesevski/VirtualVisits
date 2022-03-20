package org.unibl.etf.virtualvisits.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualvisits.exceptions.ConflictException;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.User;
import org.unibl.etf.virtualvisits.models.requests.SignUpRequest;
import org.unibl.etf.virtualvisits.models.entities.UserEntity;
import org.unibl.etf.virtualvisits.models.enums.Role;
import org.unibl.etf.virtualvisits.models.enums.UserStatus;
import org.unibl.etf.virtualvisits.repositories.UserEntityRepository;
import org.unibl.etf.virtualvisits.services.UserService;
import org.unibl.etf.virtualvisits.utils.CustomPasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository repository;
    private final CustomPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserEntityRepository repository, CustomPasswordEncoder passwordEncoder,  ModelMapper modelMapper) {
        this.repository = repository;
        this.passwordEncoder=passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void singUp(SignUpRequest request) throws ConflictException {
        if(repository.existsByUsername(request.getUsername())){
            throw new ConflictException();
        }
        UserEntity entity=modelMapper.map(request, UserEntity.class);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setStatus(UserStatus.REQUESTED);
        entity.setRole(Role.USER);
        entity.setOtpToken(null);
        entity.setUserId(null);
        repository.save(entity);
    }

    @Override
    public User findById(Integer id) throws NotFoundException {
        return modelMapper.map(repository.findById(id).orElseThrow(NotFoundException::new), User.class);
    }

    //used only for otp token setting
    @Override
    public void update(Integer id, User user) throws NotFoundException {
        if (!repository.existsById(id))
            throw new NotFoundException();
        UserEntity oldEntity=repository.getById(id);
        UserEntity newEntity=modelMapper.map(user, UserEntity.class);
        //persisting password from old entity because User class doesn't have password, so it would be set at null
        newEntity.setPassword(oldEntity.getPassword());
        repository.save(newEntity);
    }


}
