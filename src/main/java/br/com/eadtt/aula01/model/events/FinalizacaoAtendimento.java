package br.com.eadtt.aula01.model.events;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Jacksonized
public class FinalizacaoAtendimento {
    private Integer idAtendimento;
    private Integer idCliente;
    private LocalDateTime dataFinalizacao;
    private String nomeCliente;
    private String email;
}
