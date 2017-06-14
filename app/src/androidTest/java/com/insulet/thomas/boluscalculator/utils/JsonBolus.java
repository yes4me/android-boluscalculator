package com.insulet.thomas.boluscalculator.utils;

/**
 * Created by thomas on 6/14/2017.
 */

public class JsonBolus {
    JsonBolusInput input;
    JsonBolusResult result;

    public JsonBolus(JsonBolusInput input, JsonBolusResult result) {
        this.input = input;
        this.result = result;
    }

    /* =============================================================================================
    GETTER AND SETTERS
    ============================================================================================= */

    public JsonBolusInput getInput() {
        return input;
    }

    public JsonBolusResult getResult() {
        return result;
    }
}
