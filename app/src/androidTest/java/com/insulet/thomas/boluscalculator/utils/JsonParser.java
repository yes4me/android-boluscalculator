package com.insulet.thomas.boluscalculator.utils;

import android.support.test.InstrumentationRegistry;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 6/14/2017.
 */

public class JsonParser extends MyJson {
    public List<JsonBolus> readMessagesArray(JsonReader reader) throws IOException {
        List<JsonBolus> messages = new ArrayList<JsonBolus>();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readMessage(reader));
        }
        reader.endArray();
        return messages;
    }

    public JsonBolus readMessage(JsonReader reader) throws IOException {
        JsonBolusInput input     = null;
        JsonBolusResult result   = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("INPUT")) {
                input = readInput(reader);
            } else if (name.equals("RESULT")) {
                result = readResult(reader);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new JsonBolus(input, result);
    }

    public JsonBolusInput readInput(JsonReader reader) throws IOException {
        int bg_calc_min = 0;
        int bg_calc_max = 0;
        int bg_target = 0;
        int bg_correctabove = 0;
        int bg_current = 0;
        int correction_factor = 0;
        double meal_iob = 0.0;
        double correction_iob = 0.0;
        int meal_carbs = 0;
        int meal_ratio = 0;
        boolean reverse_correction = false;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("bg_calc_min")) {
                bg_calc_min = reader.nextInt();
            } else if (name.equals("bg_calc_max")) {
                bg_calc_max = reader.nextInt();
            } else if (name.equals("bg_target")) {
                bg_target = reader.nextInt();
            } else if (name.equals("bg_correctabove")) {
                bg_correctabove = reader.nextInt();
            } else if (name.equals("bg_current")) {
                bg_current = reader.nextInt();
            } else if (name.equals("correction_factor")) {
                correction_factor = reader.nextInt();
            } else if (name.equals("meal_iob")) {
                meal_iob = reader.nextDouble();
            } else if (name.equals("correction_iob")) {
                correction_iob = reader.nextDouble();
            } else if (name.equals("meal_carbs")) {
                meal_carbs = reader.nextInt();
            } else if (name.equals("meal_ratio")) {
                meal_ratio = reader.nextInt();
            } else if (name.equals("reverse_correction")) {
                reverse_correction = reader.nextBoolean();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new JsonBolusInput(bg_calc_min, bg_calc_max, bg_target, bg_correctabove, bg_current, correction_factor, meal_iob, correction_iob, meal_carbs, meal_ratio, reverse_correction);
    }

    public JsonBolusResult readResult(JsonReader reader) throws IOException {
        double correction_bolus = 0.0;
        double meal_bolus       = 0.0;
        double total_bolus      = 0.0;
        String text_displayed   = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("correction_bolus")) {
                correction_bolus = reader.nextDouble();
            } else if (name.equals("meal_bolus")) {
                meal_bolus = reader.nextDouble();
            } else if (name.equals("total_bolus")) {
                total_bolus = reader.nextDouble();
            } else if (name.equals("text_displayed")) {
                text_displayed = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new JsonBolusResult(correction_bolus, meal_bolus, total_bolus, text_displayed);
    }
}
