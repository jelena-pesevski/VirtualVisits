package org.unibl.etf.virtualvisits.utils;

import org.unibl.etf.virtualvisits.services.MailService;

public class MailSenderRunnable implements Runnable{

    private final MailService mailService;
    private final String recipient;
    private final String content;

    public MailSenderRunnable(MailService mailService, String recipient, String content){
        this.mailService=mailService;
        this.recipient=recipient;
        this.content=content;
    }

    @Override
    public void run() {
        mailService.sendNotificationMail(recipient, content);
    }
}
