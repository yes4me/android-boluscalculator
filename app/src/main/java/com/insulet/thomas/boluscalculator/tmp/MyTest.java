package com.insulet.thomas.boluscalculator.tmp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by thomas on 6/12/2017.
 */

public class MyTest {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "\\app\\src\\main\\java\\com\\insulet\\thomas\\boluscalculator\\tmp\\";
        String filename = "BolusCalculatorTest.json";

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(path + filename));
            JSONObject jsonObject = (JSONObject) obj;
            // System.out.println("jsonObject" + jsonObject);

            // String title = (String) jsonObject.get("title");
            // System.out.println(name);

            JSONArray datas = (JSONArray) jsonObject.get("data");
            for (Object object : datas) {
                JSONObject data = (JSONObject) object;
                JSONObject input    = (JSONObject) data.get("INPUT");
                JSONObject result   = (JSONObject) data.get("RESULT");

                String bg_calc_min      = String.valueOf((Long) input.get("bg_calc_min"));
                double bg_calc_max      = (Long) input.get("bg_calc_max");
                double bg_target        = (Long) input.get("bg_target");
                double bg_correctabove  = (Long) input.get("bg_correctabove");
                double bg_current       = (Long) input.get("bg_target");
                double correction_factor = (Long) input.get("correction_factor");
                double meal_iob         = (double) input.get("meal_iob");
                double correction_iob   = (double) input.get("correction_iob");
                double meal_carbs       = (Long) input.get("meal_carbs");
                double meal_ratio       = (Long) input.get("meal_ratio");
                String flag = (String) input.get("reverse_correction");
                boolean reverse_correction = (flag.equals("true"))? true : false;

                double correction_bolus = (double) result.get("correction_bolus");
                double meal_bolus       = (double) result.get("meal_bolus");
                double total_bolus      = (double) result.get("total_bolus");

                //String result_text = "correction bolus = 0\nmeal bolus = 3.1\nTOTAL BOLUS = 3.1";
                String result_text = "correction bolus = "+ correction_bolus;
                result_text += "\\nmeal bolus = "+ meal_bolus;
                result_text += "\\ntotal_bolus = "+ total_bolus;
                System.out.println(result_text);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}