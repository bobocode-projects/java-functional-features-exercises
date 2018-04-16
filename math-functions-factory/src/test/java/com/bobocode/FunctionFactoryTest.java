package com.bobocode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class FunctionFactoryTest {
    private FunctionFactory<Integer, Integer> integerFunctionFactory =  FunctionFactory.integerFunctionFactory();

    @Test
    public void testAddSquareFunction(){
        integerFunctionFactory.addFunction("square", n -> n * n);

        Function<Integer, Integer> square = integerFunctionFactory.getFunction("square");
        assertEquals(25, square.apply(5).intValue());
    }
}
