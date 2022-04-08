package org.unibl.etf.virtualvisits.services;

import org.unibl.etf.virtualvisits.exceptions.ConflictException;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.User;
import org.unibl.etf.virtualvisits.models.requests.SignUpRequest;
import org.unibl.etf.virtualvisits.models.responses.StatisticsResponse;

public interface UserService {

    void singUp(SignUpRequest request) throws ConflictException;

    User findById(Integer id) throws NotFoundException;

    void update(Integer id, User user) throws NotFoundException;

    StatisticsResponse getStatistics();
}
