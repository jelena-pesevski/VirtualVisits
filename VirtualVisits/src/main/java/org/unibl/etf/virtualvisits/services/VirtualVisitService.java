package org.unibl.etf.virtualvisits.services;

import org.springframework.core.io.ByteArrayResource;
import org.unibl.etf.virtualvisits.exceptions.InvalidTicketException;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.VirtualVisit;
import org.unibl.etf.virtualvisits.models.requests.AttendVisitRequest;
import org.unibl.etf.virtualvisits.models.responses.AttendVisitResponse;

import java.util.List;

public interface VirtualVisitService {

     List<VirtualVisit> findAll();

     VirtualVisit findById(Integer id) throws NotFoundException;

     List<VirtualVisit> findAllActive();

     List<VirtualVisit> findAllUpcomingByMuseumId(Integer id);

     byte[] getImage(Integer virtualVisitId, String fileName) throws NotFoundException;

     ByteArrayResource getVideo(Integer virtualVisitId, String fileName) throws NotFoundException;

     AttendVisitResponse attendVirtualVisit(AttendVisitRequest request) throws InvalidTicketException;
}
