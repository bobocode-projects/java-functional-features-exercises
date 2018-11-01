package com.bobocode;

public class Functions {
    /**
     * A static factory method that creates an integer function map with basic functions:
     * - abs (absolute value)
     * - sgn (signum function)
     * - increment
     * - decrement
     * - square
     *
     * @return an instance of {@link FunctionMap} that contains all listed functions
     */
    public static FunctionMap<Integer, Integer> intFunctionMap() {
        FunctionMap<Integer, Integer> intFunctionMap = new FunctionMap<>();

        intFunctionMap.addFunction("abs", i -> i > 0 ? i : i * -1);

        intFunctionMap.addFunction("sgn", i -> i > 0 ? 1 : (i == 0 ? 0 : -1));

        intFunctionMap.addFunction("increment", i -> i + 1);

        intFunctionMap.addFunction("decrement", i -> i - 1);

        intFunctionMap.addFunction("square", i -> i * i);

        return intFunctionMap;
    }
}
