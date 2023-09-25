package com.realestateapp;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;

import org.junit.jupiter.api.RepeatedTest;

public class RandomApartmentGeneratorTest {
    
    @RepeatedTest(5)
    void should_GenerateCorrectApartment_When_DefaultMinAreaMinPrice() {
        //given
        RandomApartmentGenerator randomApartmentGenerator = new RandomApartmentGenerator();
        //when
        Apartment apartment = randomApartmentGenerator.generate();
        //then
        assertAll("Evaluating Valid Apartment", 
        () -> assertTrue(apartment.getArea() >= 0),
        () -> assertTrue(apartment.getPrice().compareTo(BigDecimal.valueOf(0))>0)
        );
    }

    @RepeatedTest(5)
    void should_GenerateCorrectApartment_When_CustomMinAreaMinPrice() {
        //given
        RandomApartmentGenerator randomApartmentGenerator = new RandomApartmentGenerator(10, BigDecimal.valueOf(100));
        //when
        Apartment apartment = randomApartmentGenerator.generate();
        //then
        assertAll("Evaluating Valid Apartment",
        () -> assertTrue(apartment.getArea() >= 10),
        () -> assertTrue(apartment.getPrice().compareTo(BigDecimal.valueOf(100))>0)
        );
    }
}
