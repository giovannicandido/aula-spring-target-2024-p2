package br.com.eadtt.spring.p2.service.mappers;

import br.com.eadtt.spring.p2.model.Address;
import br.com.eadtt.spring.p2.service.AddressViaCepResponse;

public class AddressViaCepMapper {

    public static Address toModel(AddressViaCepResponse address, String cep) {
        return Address.builder()
                .cep(cep)
                .cidade(address.getLocalidade())
                .estado(address.getEstado())
                .bairro(address.getBairro())
                .logradouro(address.getLogradouro())
                .build();
    }
}
