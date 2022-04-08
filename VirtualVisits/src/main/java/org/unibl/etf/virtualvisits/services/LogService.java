package org.unibl.etf.virtualvisits.services;

import org.unibl.etf.virtualvisits.models.Log;
import org.unibl.etf.virtualvisits.models.entities.LogEntity;

import java.util.List;

public interface LogService {

    void insert(LogEntity log);

    List<Log> findAll();

    byte[] getPdf();

    List<Log> getByActionWithinLast24Hours(String action);
}
