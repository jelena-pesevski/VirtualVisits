package org.unibl.etf.virtualvisits.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.unibl.etf.virtualvisits.models.HourNumOfUsersPair;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsResponse {

    Integer numOfLoggedIn;
    Integer numOfRegistrated;
    List<HourNumOfUsersPair> pairs;
}
