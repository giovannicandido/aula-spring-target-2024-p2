package br.com.eadtt.spring.p2.service;

import br.com.eadtt.spring.p2.model.Address;
import br.com.eadtt.spring.p2.service.mappers.AddressViaCepMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressServiceImpl implements AddressService {
    // Interessante injetar esse cara já configurado
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Address findAddressByCep(String cep) {
        String url = buildUrl(cep);
        AddressViaCepResponse response = restTemplate.getForObject(url, AddressViaCepResponse.class);
        return AddressViaCepMapper.toModel(response, cep);
    }

    private String buildUrl(String cep) {
        return String.format("https://viacep.com.br/ws/%s/json", cep);
    }
}
