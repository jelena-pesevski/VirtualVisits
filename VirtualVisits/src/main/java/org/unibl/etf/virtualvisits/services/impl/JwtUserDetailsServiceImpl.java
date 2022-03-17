package org.unibl.etf.virtualvisits.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualvisits.models.JwtUser;
import org.unibl.etf.virtualvisits.models.enums.UserStatus;
import org.unibl.etf.virtualvisits.repositories.UserEntityRepository;
import org.unibl.etf.virtualvisits.services.JwtUserDetailsService;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    private final ModelMapper modelMapper;

    private final UserEntityRepository repository;

    public JwtUserDetailsServiceImpl(ModelMapper modelMapper, UserEntityRepository repository) {
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return modelMapper.map(repository.findByUsernameAndStatus(username, UserStatus.ACTIVE).orElseThrow(() -> new UsernameNotFoundException(username)), JwtUser.class);
    }
}
