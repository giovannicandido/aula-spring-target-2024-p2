package br.com.eadtt.spring.p2.controller;

import br.com.eadtt.spring.p2.controller.response.AddressResponse;
import br.com.eadtt.spring.p2.service.AddressService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public AddressResponse findAddressByCep(@RequestParam String cep) {
        return AddressResponse.fromModel(addressService.findAddressByCep(cep));
    }
}
