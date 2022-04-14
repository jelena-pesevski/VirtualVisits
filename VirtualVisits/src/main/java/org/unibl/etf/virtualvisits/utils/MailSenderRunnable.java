package org.unibl.etf.virtualvisits.utils;

import org.unibl.etf.virtualvisits.services.MailService;

public class MailSenderRunnable implements Runnable{

    private final MailService mailService;
    private final Integer recipientId;
    private final String content;

    public MailSenderRunnable(MailService mailService, Integer recipientId, String content){
        this.mailService=mailService;
        this.recipientId=recipientId;
        this.content=content;
    }

    @Override
    public void run() {
        mailService.sendNotificationMail(recipientId, content);
    }
}
