package com.realestateapp;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ApartmentRaterTest {

    @ParameterizedTest(name = "Apartment area {0} should be rated as {1}")
    @CsvSource(value = {"1750, 6000000", "2000, 8000000", "2500, 10000000000", "2000, 200000000"})
    void should_ReturnCorrectRating_When_CorrectApartment(double area, BigDecimal price) {
        //given
        Apartment apartment = new Apartment(area, price);
        List<Integer> ratings = new ArrayList<>();
        ratings.add(1);
        ratings.add(2);
        ratings.add(0);

        //when
        int rating = ApartmentRater.rateApartment(apartment);

        //then
        assertTrue(ratings.contains(rating));
    }

    @Test
    void should_ReturnErrorValue_When_IncorrectApartment() {
        //given
        Apartment apartment = new Apartment(0, new BigDecimal(200000000));

        //when
        int rating = ApartmentRater.rateApartment(apartment);

        //then
        assertTrue(rating == -1);
    }

    @Test
    void should_CalculateAverageRating_When_CorrectApartmentList() {
        //given
        List<Apartment> apartments = new ArrayList<>();
        Apartment apartment1 = new Apartment(1750, new BigDecimal(6000000));
        Apartment apartment2 = new Apartment(2000, new BigDecimal(8000000));
        Apartment apartment3 = new Apartment(2500, new BigDecimal(new String("10000000000")));
        Apartment apartment4 = new Apartment(2000, new BigDecimal(20000000));
        apartments.add(apartment1);
        apartments.add(apartment2);
        apartments.add(apartment3);
        apartments.add(apartment4);

        //when
        double averageRating = ApartmentRater.calculateAverageRating(apartments);
        //then
        assertTrue(averageRating == 1.0);
    }

    @Test
    void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
        //given
        List<Apartment> apartments = new ArrayList<>();
        //when
        Executable executable = () -> ApartmentRater.calculateAverageRating(apartments);
        //then
        assertThrows(RuntimeException.class, executable, "Apartment list is empty");
    }
          
    
}
