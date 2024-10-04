package br.com.eadtt.spring.p2.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String logradouro;
    private String cidade;
    private String bairro;
    private String estado;
    private String cep;
}
