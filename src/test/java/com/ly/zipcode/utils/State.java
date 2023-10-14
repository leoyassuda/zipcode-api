package com.ly.zipcode.utils;

import lombok.Getter;

import java.util.Random;

public enum State {
    SP("São Paulo", "Campinas", "Santos"),
    RJ("Rio de Janeiro", "Niterói", "Petrópolis"),
    MG("Minas Gerais", "Belo Horizonte", "Uberlândia"),
    DF("Distrito Federal", "Brasília"),
    BA("Bahia", "Salvador", "Feira de Santana"),
    PR("Paraná", "Curitiba", "Londrina");

    @Getter
    private String name;
    private String[] cities;

    State(String name, String... cities) {
        this.name = name;
        this.cities = cities;
    }

    public String getRandomCity() {
        int randomIndex = new Random().nextInt(cities.length);
        return cities[randomIndex];
    }
}

