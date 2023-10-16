package com.ly.zipcode.infra.adapters.api;

//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
public record CEPResponse(String cep, String logradouro, String complemento, String bairro, String localidade,
                          String uf, String ibge, String gia, String ddd, String siafi) {
}

