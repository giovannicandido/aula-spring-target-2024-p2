package br.com.eadtt.spring.p2.service;

import br.com.eadtt.aula01.model.events.FinalizacaoAtendimento;
import br.com.eadtt.spring.p2.config.EmailProperties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailSendService {

    private final EmailProperties emailProperties;


    public void sendEmail(String to, String subject, FinalizacaoAtendimento finalizacaoAtendimento) {

        String body = getEmailMessage(finalizacaoAtendimento);
        log.info("Sending email to " + to);
        log.info("Subject: " + subject);
        log.info("Body: " + body);
        if(emailProperties.getSimulate()) {
            log.info("Email enviado com simulacao");
            return;
        }

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", emailProperties.getSsl());
        prop.put("mail.smtp.host", emailProperties.getHost());
        prop.put("mail.smtp.port", emailProperties.getPort());



        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProperties.getUsername(), emailProperties.getPassword());
            }
        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(emailProperties.getFrom()));

            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);


            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(message, "text/html; charset=utf-8");


            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Email enviado com sucesso");
    }

    private String getEmailMessage(FinalizacaoAtendimento message) {
        return """
                 Senhor(a) %s atendimento id %s na oficina foi finalizado com sucesso na data de %s
                """.formatted(message.getNomeCliente(), message.getIdAtendimento(), formatDate(message.getDataFinalizacao()));
    }

    private String formatDate(LocalDateTime date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return date.format(dateTimeFormatter);
    }
}
