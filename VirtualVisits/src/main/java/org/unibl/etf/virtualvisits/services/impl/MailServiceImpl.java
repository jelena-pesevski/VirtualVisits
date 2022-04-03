package org.unibl.etf.virtualvisits.services.impl;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualvisits.exceptions.NotFoundException;
import org.unibl.etf.virtualvisits.models.Ticket;
import org.unibl.etf.virtualvisits.models.User;
import org.unibl.etf.virtualvisits.models.VirtualVisit;
import org.unibl.etf.virtualvisits.models.entities.TicketEntity;
import org.unibl.etf.virtualvisits.services.MailService;
import org.unibl.etf.virtualvisits.services.VirtualVisitService;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    private final ModelMapper modelMapper;

    private final VirtualVisitService virtualVisitService;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${mail.subject}")
    private String subject;

    @Value("${mail.text}")
    private String text;

    @Value("${mail.notif.subject}")
    private String notifSubject;

    public MailServiceImpl(JavaMailSender mailSender, ModelMapper modelMapper, VirtualVisitService virtualVisitService) {
        this.mailSender = mailSender;
        this.modelMapper = modelMapper;
        this.virtualVisitService = virtualVisitService;
    }

    @Override
    public void sendNotificationMail(String recipient, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(recipient);
        message.setSubject(notifSubject);
        message.setText(content);
        mailSender.send(message);
    }

    @Override
    public void sendTicket(TicketEntity ticketEntity, User user) throws NotFoundException, MessagingException {
        VirtualVisit virtualVisit=virtualVisitService.findById(ticketEntity.getVirtualVisitId());
        Ticket ticket=modelMapper.map(ticketEntity, Ticket.class);

        //constructing mail
        MimeMessage message =mailSender.createMimeMessage();
        MimeMessageHelper mailMessage = new MimeMessageHelper(message, true);

        mailMessage.setFrom(username);
        mailMessage.setTo(user.getMail());
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        ByteArrayOutputStream outputStream = null;

        //writing pdf to the output stream
        outputStream = new ByteArrayOutputStream();
        writePdf(outputStream, virtualVisit, ticket);
        byte[] bytes = outputStream.toByteArray();

        //construct the pdf body part
        DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");

        //add attachment
        mailMessage.addAttachment("ticket.pdf", dataSource);

        mailSender.send(message);
    }

    private void writePdf(ByteArrayOutputStream outputStream, VirtualVisit virtualVisit, Ticket ticket ) {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        Paragraph p = new Paragraph("Virtual Visits Ticket");
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        p = new Paragraph();
        p.add(new Chunk("Ticket number: "+ ticket.getTicketNumber() +"\n", new Font(Font.TIMES_ROMAN, 12)));
        document.add(p);
        p=new Paragraph();
        p.add(new Chunk("Virtual visit of "+ virtualVisit.getMuseumName() +" starts on: "+ virtualVisit.getDate() +" at "+virtualVisit.getStart() +
                "h and will last "+ virtualVisit.getDuration(), new Font(Font.TIMES_ROMAN, 12)));
        document.add(p);

        document.close();
    }
}
