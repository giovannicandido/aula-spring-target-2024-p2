package br.com.eadtt.spring.p2.service.listener;

import br.com.eadtt.aula01.model.events.FinalizacaoAtendimento;
import br.com.eadtt.spring.p2.service.EmailSendService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
@Slf4j
@RequiredArgsConstructor
public class FinalizarAtendimentoListener {
//    private final ObjectMapper objectMapper;
    private final EmailSendService emailSendService;

    @KafkaListener(topics = {"atendimento"})
    public void finalizarAtendimento(FinalizacaoAtendimento message) {
//        FinalizacaoAtendimento finalizacao = null;
//        try {
//            finalizacao = objectMapper.readValue(message, FinalizacaoAtendimento.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        log.info(">>> mensagem recebida: {}", message);
        emailSendService.sendEmail(message.getEmail(), "Atendimento Finalizado", message);
        System.out.println("Finalizando atendimento");
    }

}
