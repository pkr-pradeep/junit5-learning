package com.pkrstudies;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class BMICalculatorTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each");
    }

    @Nested
    @DisplayName("Algebra Unit Tests")
    class AlgebraTest {
        @ParameterizedTest
        @ValueSource(ints = { 2, 4, 6, 8, 18 })
        void test_GetEven(int inputVal) {
            // given
            int a = inputVal;
            // when
            int result = a % 2;
            // then
            assertEquals(0, result);
        }

        @ParameterizedTest(name = "numerator={0}, denominator={1}")
        @CsvSource(value = { "6,3", "8,2", "16,8" })
        void testIsDivisible(int numerator, int denominator) {
            // given
            int a = numerator;
            int b = denominator;
            // when
            int result = a % b;
            // then
            assertEquals(0, result);
        }

        @ParameterizedTest(name = "numerator={0}, denominator={1}")
        @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
        void testIsDivisibleInputFromFile(int numerator, int denominator) {
            // given
            int a = numerator;
            int b = denominator;
            // when
            int result = a % b;
            // then
            assertEquals(0, result);
        }
    }

    @Nested
    @DisplayName("Diet Recommended Unit Tests")
    class IsDietRecommendedTests {
        @Test
        void testIsDietRecommended() {
            // given
            double weight = 89.0;
            double height = 1.72;
            // when
            boolean isDietRecommended = BMICalculator.isDietRecommended(weight, height);
            // then
            assertTrue(isDietRecommended);
        }

        @Test
        void testIsDiet_NotRecommended() {
            // given
            double weight = 89.0;
            double height = 2.72;
            // when
            boolean isDietRecommended = BMICalculator.isDietRecommended(weight, height);
            // then
            assertFalse(isDietRecommended);
        }

        @Test
        void testIsDietRecommended_throwsException() {
            // given
            double weight = 89.0;
            double height = 0.0;
            // when
            Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
            // then
            assertThrows(ArithmeticException.class, executable, "Height must not be zero");
        }
    }

    @Test
    @Disabled
    void test_Timeout() {
        // given
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10000000; i++) {
            list.add(i);
        }
        // when
        Executable executable = () -> Collections.max(list, Collections.reverseOrder());
        // then
        assertTimeout(Duration.ofMillis(15), executable);
    }

    @Test
    @DisabledOnOs(OS.LINUX)
    void test_Timeout1() {
        // given
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 1000000; i++) {
            list.add(i);
        }
        // when
        Executable executable = () -> Collections.max(list, Collections.reverseOrder());
        // then
        assertTimeout(Duration.ofMillis(50), executable);
    }

    @Test
    void testArray() {
        // given //when
        int[] arr = { 1, 2, 3 };
        int[] arr2 = { 1, 2, 3 };
        // then
        assertArrayEquals(arr, arr2);
        // comment :: assertEquals will fail even if it has same element inside array as
        // it compares object reference
    }

    @Test
    void testWorstBMIWithAssertAll() {
        // given
        List<Coder> list = new ArrayList<>();
        Coder coder = new Coder(1.86, 52);
        Coder coder2 = new Coder(1.8, 72);
        list.add(0, coder);
        list.add(coder2);
        List<Coder> list2 = new ArrayList<>();
        // when
        Coder worstCoder = BMICalculator.findCoderWithWorstBMI(list);
        Coder worstCoder2 = BMICalculator.findCoderWithWorstBMI(list2);
        // then
        assertAll(
                () -> assertEquals(1.8, worstCoder.getHeight()),
                () -> assertEquals(72, worstCoder.getWeight()),
                () -> assertNull(worstCoder2));
    }

}