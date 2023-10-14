package com.ly.zipcode.utils;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.infra.entities.addressDetails.AddressDetailsDataModel;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class AddressDetailsDataModelUtils {

    private static final Random random = new Random();
    private static final List<String> streetNames = Arrays.asList(
            "Rua A", "Avenida B", "Praça C", "Alameda D", "Estrada E", "Travessa F"
    );

    private static final List<String> additionalAddresses = Arrays.asList(
            "Apto 101", "Sala 202", "Unidade 303", "Bloco A", "Casa 5", "Escritório 12"
    );

    private static final List<String> neighborhoods = Arrays.asList(
            "Centro", "Subúrbio", "Colina", "Beira-Mar", "Residencial", "Comercial"
    );

    public static AddressDetailsDataModel generateAddressDetails() {
        var randomState = generateRandomState();

        return AddressDetailsDataModel.builder()
                .id(UUID.randomUUID())
                .zipcode(generateRandomZipcode())
                .address(generateRandomStreetName())
                .additionalAddress(generateRandomAdditionalAddress())
                .neighborhood(generateRandomNeighborhood())
                .city(randomState.getRandomCity())
                .state(randomState.getName())
                .ibge(generateRandomIbge())
                .gia(generateRandomGia())
                .ddd(generateRandomDdd())
                .siafi(generateRandomSiafi())
                .build();
    }

    private static String generateRandomZipcode() {
        int part1 = 10000 + random.nextInt(90000);
        int part2 = 100 + random.nextInt(900);
        return String.format("%05d-%03d", part1, part2);
    }

    private static String generateRandomStreetName() {
        int randomIndex = random.nextInt(streetNames.size());
        return streetNames.get(randomIndex);
    }

    private static State generateRandomState() {
        State[] states = State.values();
        return states[random.nextInt(states.length)];
    }

    private static String generateRandomAdditionalAddress() {
        int randomIndex = random.nextInt(additionalAddresses.size());
        return additionalAddresses.get(randomIndex);
    }

    private static String generateRandomNeighborhood() {
        int randomIndex = random.nextInt(neighborhoods.size());
        return neighborhoods.get(randomIndex);
    }

    private static String generateRandomIbge() {
        int ibge = 1000000 + random.nextInt(9000000);
        return String.valueOf(ibge);
    }

    private static String generateRandomGia() {
        int gia = 1000000 + random.nextInt(9000000);
        return String.valueOf(gia);
    }

    private static String generateRandomDdd() {
        int ddd = 10 + random.nextInt(90);
        return String.valueOf(ddd);
    }

    private static String generateRandomSiafi() {
        int siafi = 100000 + random.nextInt(900000);
        return String.valueOf(siafi);
    }
}
