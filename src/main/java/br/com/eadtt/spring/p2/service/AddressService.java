package br.com.eadtt.spring.p2.service;

import br.com.eadtt.spring.p2.model.Address;

public interface AddressService {
    Address findAddressByCep(String cep);
}
