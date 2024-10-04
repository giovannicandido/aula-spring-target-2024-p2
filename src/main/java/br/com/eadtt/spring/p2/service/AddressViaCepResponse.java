package br.com.eadtt.spring.p2.service;

import lombok.Data;

/**
 * "cep": "01001-000",
 *     "logradouro": "Praça da Sé",
 *     "complemento": "lado ímpar",
 *     "unidade": "",
 *     "bairro": "Sé",
 *     "localidade": "São Paulo",
 *     "uf": "SP",
 *     "estado": "São Paulo",
 *     "regiao": "Sudeste",
 *     "ibge": "3550308",
 *     "gia": "1004",
 *     "ddd": "11",
 *     "siafi": "7107"
 */
@Data
public class AddressViaCepResponse {
    private String logradouro;
    private String complemento;
    private String unidade;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;
}
