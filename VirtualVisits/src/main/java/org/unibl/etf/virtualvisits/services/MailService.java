package org.unibl.etf.virtualvisits.services;

import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.User;
import org.unibl.etf.virtualvisits.models.entities.TicketEntity;

import javax.mail.MessagingException;

public interface MailService {

    void sendNotificationMail(String recipient, String content);

    void sendTicket(TicketEntity ticketEntity, User user) throws NotFoundException, MessagingException;
}
