CREATE
    EXTENSION "uuid-ossp";

CREATE TABLE address_details
(
    id                 UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    zipcode            VARCHAR(255),
    address            VARCHAR(255),
    additional_address VARCHAR(255),
    neighborhood       VARCHAR(255),
    city               VARCHAR(255),
    state              VARCHAR(255),
    ibge               VARCHAR(255),
    gia                VARCHAR(255),
    ddd                VARCHAR(255),
    siafi              VARCHAR(255)
);

INSERT INTO address_details (id, zipcode, address, additional_address, neighborhood, city, state, ibge, gia, ddd, siafi)
VALUES (uuid_generate_v4(), '01310-200', 'Avenida Paulista', 'Lado ímpar', 'Bela Vista', 'São Paulo', 'SP', '3550308', '1004', '11', '7107');

INSERT INTO address_details (id, zipcode, address, additional_address, neighborhood, city, state, ibge, gia, ddd, siafi)
VALUES (uuid_generate_v4(), '70050-906', 'SHCS 710', 'Lado ímpar', 'Asa Sul', 'Brasília', 'DF', '5300108', '1002', '61', '9701');

INSERT INTO address_details (id, zipcode, address, additional_address, neighborhood, city, state, ibge, gia, ddd, siafi)
VALUES (uuid_generate_v4(), '90010-230', 'Rua da Praia', 'Lado par', 'Centro', 'Porto Alegre', 'RS', '4314902', '4301', '51', '8801');
