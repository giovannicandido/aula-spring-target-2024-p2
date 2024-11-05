package br.com.eadtt.spring.p2.service.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FinalizarAtendimentoListener {

    @KafkaListener(topics = {"atendimento"})
    public void finalizarAtendimento(String message) {
        log.info(">>> mensagem recebida: {}", message);
        System.out.println("Finalizando atendimento");
    }
}
