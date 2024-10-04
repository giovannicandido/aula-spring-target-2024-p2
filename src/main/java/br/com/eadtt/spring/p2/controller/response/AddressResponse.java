package br.com.eadtt.spring.p2.controller.response;

import br.com.eadtt.spring.p2.model.Address;
import br.com.eadtt.spring.p2.service.mappers.AddressViaCepMapper;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {
    private String logradouro;
    private String cidade;
    private String bairro;
    private String estado;
    private String cep;

    public static AddressResponse fromModel(Address address) {
        return AddressResponse.builder()
                .cep(address.getCep())
                .cidade(address.getCidade())
                .estado(address.getEstado())
                .bairro(address.getBairro())
                .logradouro(address.getLogradouro())
                .build();
    }
}
