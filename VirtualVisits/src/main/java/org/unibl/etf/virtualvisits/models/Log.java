package org.unibl.etf.virtualvisits.models;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.time.Instant;

@Data
public class Log {

    private String info;
    private String action;
    private Instant dateTime;
}
