package br.com.eadtt.spring.p2.service.listener;

import br.com.eadtt.aula01.model.events.FinalizacaoAtendimento;
import br.com.eadtt.spring.p2.service.EmailSendService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class FinalizarAtendimentoListener {
//    private final ObjectMapper objectMapper;
    private final EmailSendService emailSendService;

    @KafkaListener(topics = {"atendimento"})
    public void finalizarAtendimento(FinalizacaoAtendimento message, Acknowledgment acknowledgment) {
//        FinalizacaoAtendimento finalizacao = null;
//        try {
//            finalizacao = objectMapper.readValue(message, FinalizacaoAtendimento.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        log.info(">>> mensagem recebida: {}", message);
        try {
            emailSendService.sendEmail(message.getEmail(), "Atendimento Finalizado", message);
            log.info("Atendimento finalizado com sucesso");
            acknowledgment.acknowledge();
        } catch (Exception e) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }
            // nao commita no kafka o processamento da mensagem e espera 5 segundos para pegar a mensagem de volta
            acknowledgment.nack(Duration.of(5, ChronoUnit.SECONDS));
            log.error("Erro ao finalizar atendimento", e);
        }

    }

}
