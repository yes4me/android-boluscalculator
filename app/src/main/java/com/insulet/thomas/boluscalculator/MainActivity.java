package com.insulet.thomas.boluscalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.insulet.thomas.boluscalculator.bolus.BolusCalculator;

public class MainActivity extends AppCompatActivity implements EditText.OnEditorActionListener, View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private BolusCalculator bolusCalculator;

    public EditText edit_text_bg_calc_min;
    public EditText edit_text_bg_calc_max;
    public EditText text_edit_bg_target;
    public EditText text_edit_bg_correctabove;
    public EditText text_edit_bg_current;
    public EditText text_edit_correction_factor;
    public EditText text_edit_meal_iob;
    public EditText text_edit_correction_iob;
    public EditText text_edit_meal_carbs;
    public EditText text_edit_meal_ratio;

    public Button button_reverse_correction;
    public TextView text_view_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bolusCalculator = new BolusCalculator();

        edit_text_bg_calc_min   = (EditText) findViewById(R.id.edit_text_bg_calc_min);
        edit_text_bg_calc_max   = (EditText) findViewById(R.id.edit_text_bg_calc_max);
        text_edit_bg_target     = (EditText) findViewById(R.id.text_edit_bg_target);
        text_edit_bg_correctabove = (EditText) findViewById(R.id.text_edit_bg_correctabove);
        text_edit_bg_current    = (EditText) findViewById(R.id.text_edit_bg_current);
        text_edit_correction_factor = (EditText) findViewById(R.id.text_edit_correction_factor);
        text_edit_meal_iob      = (EditText) findViewById(R.id.text_edit_meal_iob);
        text_edit_correction_iob= (EditText) findViewById(R.id.text_edit_correction_iob);
        text_edit_meal_carbs    = (EditText) findViewById(R.id.text_edit_meal_carbs);
        text_edit_meal_ratio    = (EditText) findViewById(R.id.text_edit_meal_ratio);

        edit_text_bg_calc_min.setOnEditorActionListener(this);
        edit_text_bg_calc_max.setOnEditorActionListener(this);
        text_edit_bg_target.setOnEditorActionListener(this);
        text_edit_bg_correctabove.setOnEditorActionListener(this);
        text_edit_bg_current.setOnEditorActionListener(this);
        text_edit_correction_factor.setOnEditorActionListener(this);
        text_edit_meal_iob.setOnEditorActionListener(this);
        text_edit_correction_iob.setOnEditorActionListener(this);
        text_edit_meal_carbs.setOnEditorActionListener(this);
        text_edit_meal_ratio.setOnEditorActionListener(this);

        edit_text_bg_calc_min.clearFocus();
        //text_edit_bg_current.requestFocus();

        button_reverse_correction = (Button)  findViewById(R.id.button_reverse_correction);
        button_reverse_correction.setOnClickListener(this);
        updateBackgroundReverseCorrection();

        // Calculate the result
        bolusCalculator.updateResult();
        String result = bolusCalculator.getResult();
        text_view_result        = (TextView) findViewById(R.id.text_view_result);
        text_view_result.setText(result);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        String text = String.valueOf(v.getText());
        double value = Double.parseDouble(text);

        switch(v.getId()){
            case R.id.edit_text_bg_calc_min:
                bolusCalculator.setBgCalc_min(value);
                break;
            case R.id.edit_text_bg_calc_max:
                bolusCalculator.setBgCalc_max(value);
                break;
            case R.id.text_edit_bg_target:
                bolusCalculator.setBgTarget(value);
                break;
            case R.id.text_edit_bg_correctabove:
                bolusCalculator.setBgCorrectAbove(value);
                break;
            case R.id.text_edit_bg_current:
                bolusCalculator.setBgCurrent(value);
                break;
            case R.id.text_edit_correction_factor:
                bolusCalculator.setCorrectionFactor(value);
                break;
            case R.id.text_edit_meal_iob:
                bolusCalculator.setAdjustmentMeal_IOB(value);
                break;
            case R.id.text_edit_correction_iob:
                bolusCalculator.setAdjustmentCorrection_IOB(value);
                break;
            case R.id.text_edit_meal_carbs:
                bolusCalculator.setMealCarbs(value);
                break;
            case R.id.text_edit_meal_ratio:
                bolusCalculator.setMeal_ic_ratio(value);
                break;
            default:
                Log.d(TAG, "Unknown View in onEditorAction");
        }
        bolusCalculator.updateResult();
        String result = bolusCalculator.getResult();
        text_view_result.setText(result);
        return false;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_reverse_correction:
                boolean status = bolusCalculator.isReverseCorrection();
                bolusCalculator.setReverseCorrection(!status);
                updateBackgroundReverseCorrection();
                break;
            default:
                Log.d(TAG, "Unknown View in onClick");
        }
        bolusCalculator.updateResult();
        String result = bolusCalculator.getResult();
        text_view_result.setText(result);
    }

    private void updateBackgroundReverseCorrection() {
        boolean status = bolusCalculator.isReverseCorrection();
        if (status) {
            button_reverse_correction.setBackgroundColor(Color.parseColor("#e1f7a9"));
            button_reverse_correction.setText("Reverse Correction ON");
        } else {
            button_reverse_correction.setBackgroundColor(Color.parseColor("#e63900"));
            button_reverse_correction.setText("Reverse Correction OFF");
        }
    }
}
