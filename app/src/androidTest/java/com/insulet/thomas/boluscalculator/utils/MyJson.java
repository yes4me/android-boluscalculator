package com.insulet.thomas.boluscalculator.utils;

import android.support.test.InstrumentationRegistry;
import android.util.JsonReader;

import com.insulet.thomas.boluscalculator.utils.JsonBolus;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 6/14/2017.
 */

public abstract class MyJson {
    public List<JsonBolus> readJsonStream(String filename) throws IOException {
        InputStream inputStream = InstrumentationRegistry.getContext().getAssets().open(filename);
        return readJsonStream(inputStream);
    }
    public List<JsonBolus> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readMessagesArray(reader);
        } finally {
            reader.close();
        }
    }
    public abstract List<JsonBolus> readMessagesArray(JsonReader reader) throws IOException;
}
