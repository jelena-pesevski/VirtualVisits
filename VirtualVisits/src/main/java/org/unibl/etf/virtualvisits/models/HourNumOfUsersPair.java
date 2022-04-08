package org.unibl.etf.virtualvisits.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HourNumOfUsersPair {

    Integer hour;
    Long numOfUsers;
}
