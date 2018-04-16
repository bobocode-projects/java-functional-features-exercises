package com.bobocode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * FunctionFactory is an API that allows you to store and retrieve functions by name. Functions are stored in a HashMap,
 * where the key is a function name, and the value is a Function<T,R> instance. Methods addFunction(), and getFunction()
 * are already implemented.
 * <p>
 * The task is to create different types of functions and manage them using FunctionFactory. The instruction is placed
 * to the main method.
 */
public class FunctionFactory<T, R> {
    private Map<String, Function<T, R>> functionMap;

    private FunctionFactory() {
        functionMap = new HashMap<>();
    }

    public static FunctionFactory<Integer, Integer> integerFunctionFactory(){
        return new FunctionFactory<>();
    }

    public void addFunction(String name, Function<T, R> function) {
        functionMap.put(name, function);
    }

    public Function<T, R> getFunction(String name) {
        if (functionMap.containsKey(name)) {
            return functionMap.get(name);
        } else {
            throw new InvalidFunctionNameException(name);
        }
    }
}