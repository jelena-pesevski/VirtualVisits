package org.unibl.etf.virtualvisits.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualvisits.exceptions.ConflictException;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.HourNumOfUsersPair;
import org.unibl.etf.virtualvisits.models.Log;
import org.unibl.etf.virtualvisits.models.User;
import org.unibl.etf.virtualvisits.models.entities.LogEntity;
import org.unibl.etf.virtualvisits.models.requests.SignUpRequest;
import org.unibl.etf.virtualvisits.models.entities.UserEntity;
import org.unibl.etf.virtualvisits.models.enums.Role;
import org.unibl.etf.virtualvisits.models.enums.UserStatus;
import org.unibl.etf.virtualvisits.models.responses.StatisticsResponse;
import org.unibl.etf.virtualvisits.repositories.UserEntityRepository;
import org.unibl.etf.virtualvisits.services.LogService;
import org.unibl.etf.virtualvisits.services.UserService;
import org.unibl.etf.virtualvisits.utils.CustomPasswordEncoder;

import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserEntityRepository repository;

    private final CustomPasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final LogService logService;

    public UserServiceImpl(UserEntityRepository repository, CustomPasswordEncoder passwordEncoder, ModelMapper modelMapper, LogService logService) {
        this.repository = repository;
        this.passwordEncoder=passwordEncoder;
        this.modelMapper = modelMapper;
        this.logService = logService;
    }

    @Override
    public void singUp(SignUpRequest request) throws ConflictException {
        if(repository.existsByUsername(request.getUsername())){
            logService.insert(new LogEntity(0, "Sign up conflict for username:"+request.getUsername()  , "SIGN-UP-TRY", Instant.now()));
            throw new ConflictException();
        }
        UserEntity entity=modelMapper.map(request, UserEntity.class);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        entity.setStatus(UserStatus.REQUESTED);
        entity.setRole(Role.USER);
        entity.setOtpToken(null);
        entity.setUserId(null);
        repository.save(entity);

        logService.insert(new LogEntity(0,  "Sign up request for username: "+ request.getUsername(), "SIGN-UP", Instant.now()));
    }

    @Override
    public User findById(Integer id) throws NotFoundException {
        return modelMapper.map(repository.findById(id).orElseThrow(NotFoundException::new), User.class);
    }

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

    @Override
    public StatisticsResponse getStatistics() {
        StatisticsResponse response=new StatisticsResponse();
        response.setNumOfLoggedIn(repository.countByIsLoggedInEquals(true));
        response.setNumOfRegistrated(repository.countByStatusEquals(UserStatus.ACTIVE));

        List<Log> logs=logService.getByActionWithinLast24Hours("LOGIN");
        List<HourNumOfUsersPair> pairs=new ArrayList<>();

        int currHour=Instant.now().atZone(ZoneId.systemDefault()).getHour();
        //group logs by hour of the day
        Map<Integer, Long> activeUsersByHour= logs.stream().collect(Collectors.groupingBy(l-> {
           int hour= l.getDateTime().atZone(ZoneId.systemDefault()).getHour();
           if (hour> currHour) {
               return hour-24;
           }else {
               return hour;
           }
         }
        , Collectors.counting()));

        for(Integer key:activeUsersByHour.keySet()){
            pairs.add(new HourNumOfUsersPair(key, activeUsersByHour.get(key)));
        }

        //sort from last day hour to now
        Collections.sort(pairs, new Comparator<HourNumOfUsersPair>() {
            public int compare(HourNumOfUsersPair pair1, HourNumOfUsersPair pair2) {
                return pair1.getHour().compareTo(pair2.getHour());
            }
        });

        response.setPairs(pairs);
      //  for(String key:)

        return response;
    }
}
