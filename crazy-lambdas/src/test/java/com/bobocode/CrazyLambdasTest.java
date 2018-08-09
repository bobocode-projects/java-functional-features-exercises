package com.bobocode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;
import java.util.function.*;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CrazyLambdasTest {

    @Test
    public void testHelloSupplier() {
        Supplier<String> helloSupplier = CrazyLambdas.helloSupplier();

        assertEquals("Hello", helloSupplier.get());
    }


    @Test
    public void testIsEmptyPredicate() {
        Predicate<String> isEmptyPredicate = CrazyLambdas.isEmptyPredicate();

        boolean nonEmptyStringResult = isEmptyPredicate.test("fasdfa");
        boolean emptyStringResult = isEmptyPredicate.test("");

        assertFalse(nonEmptyStringResult);
        assertTrue(emptyStringResult);
    }

    @Test
    public void testToDollarStringFunction() {
        Function<BigDecimal, String> toDollarStringFunction = CrazyLambdas.toDollarStringFunction();
        String tenDollarStr = toDollarStringFunction.apply(BigDecimal.TEN.setScale(2));

        assertEquals("$10.00", tenDollarStr);
    }

    @Test
    public void testLengthInRangePredicate() {
        Predicate<String> lengthInRangePredicate = CrazyLambdas.lengthInRangePredicate(4, 10);

        boolean twoLetterStringResult = lengthInRangePredicate.test("Hi");
        boolean fourLetterStringResult = lengthInRangePredicate.test("Hola");
        boolean fiveLetterStringResult = lengthInRangePredicate.test("Amigo");
        boolean eightLetterStringResult = lengthInRangePredicate.test("Lalaland");
        boolean thirteenLetterStringResult = lengthInRangePredicate.test("Lambda rocks!");

        assertFalse(twoLetterStringResult);
        assertTrue(fourLetterStringResult);
        assertTrue(fiveLetterStringResult);
        assertTrue(eightLetterStringResult);
        assertFalse(thirteenLetterStringResult);
    }

    @Test
    public void testRandomIntSupplier() {
        IntSupplier randomIntSupplier = CrazyLambdas.randomIntSupplier();

        int firstValue = randomIntSupplier.getAsInt();
        int secondValue = randomIntSupplier.getAsInt();

        assertNotEquals(firstValue, secondValue);
    }

    @Test
    public void testBoundedRandomIntSupplier() {
        IntUnaryOperator boundedRandomIntSupplier = CrazyLambdas.boundedRandomIntSupplier();

        int randomIntLessThan10 = boundedRandomIntSupplier.applyAsInt(10);
        int randomIntLessThan100 = boundedRandomIntSupplier.applyAsInt(100);
        int randomIntLessThan1000 = boundedRandomIntSupplier.applyAsInt(1000);
        int randomIntLessThan10000 = boundedRandomIntSupplier.applyAsInt(1000);

        assertTrue(randomIntLessThan10 < 10);
        assertTrue(randomIntLessThan100 < 100);
        assertTrue(randomIntLessThan1000 < 1000);
        assertTrue(randomIntLessThan10000 < 10000);
    }

    @Test
    public void testIntSquareOperation() {
        IntUnaryOperator squareOperation = CrazyLambdas.intSquareOperation();

        int squareOfFour = squareOperation.applyAsInt(4);
        int squareOfZero = squareOperation.applyAsInt(0);

        assertEquals(16, squareOfFour);
        assertEquals(0, squareOfZero);
    }

    @Test
    public void testLongSumOperation() {
        LongBinaryOperator sumOperation = CrazyLambdas.longSumOperation();


        long sumOfSevenAndEight = sumOperation.applyAsLong(7, 8);
        long sumOfTenAndZero = sumOperation.applyAsLong(10, 0);
        long sumOfFiveAndMinusTen = sumOperation.applyAsLong(5, -10);

        assertEquals(15, sumOfSevenAndEight);
        assertEquals(10, sumOfTenAndZero);
        assertEquals(-5, sumOfFiveAndMinusTen);
    }

    @Test
    public void testStringToIntConverter() {
        ToIntFunction<String> stringToIntConverter = CrazyLambdas.stringToIntConverter();

        int num = stringToIntConverter.applyAsInt("234");
        int negativeNum = stringToIntConverter.applyAsInt("-122");

        assertEquals(234, num);
        assertEquals(-122, negativeNum);
    }

    @Test
    public void testNMultiplyFunctionSupplier() {
        Supplier<IntUnaryOperator> fiveMultiplyFunctionSupplier = CrazyLambdas.nMultiplyFunctionSupplier(5);

        IntUnaryOperator multiplyByFiveOperation = fiveMultiplyFunctionSupplier.get();
        int result = multiplyByFiveOperation.applyAsInt(11); // 11 * 5 = 55

        assertEquals(55, result);
    }
}
