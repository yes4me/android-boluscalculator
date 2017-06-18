package com.insulet.thomas.boluscalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.insulet.thomas.boluscalculator.bolus.BolusCalculator;
import com.insulet.thomas.boluscalculator.util.StringUtil;

public class MainActivity extends AppCompatActivity implements EditText.OnEditorActionListener, View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private BolusCalculator bolusCalculator;

    // Input values
    public EditText edit_text_bg_calc_min;
    public EditText edit_text_bg_calc_max;
    public EditText edit_text_bg_target;
    public EditText edit_text_bg_correctabove;
    public EditText edit_text_bg_current;
    public EditText edit_text_correction_factor;
    public EditText edit_text_meal_iob;
    public EditText edit_text_correction_iob;
    public EditText edit_text_meal_carbs;
    public EditText edit_text_meal_ratio;
    public Button button_reverse_correction;

    // Result values
    public TextView text_view_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bolusCalculator = new BolusCalculator(this);

        // To render the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            // replace ActionBar with Toolbar by using setSupportActionBar() method
            String title = getResources().getString(R.string.app_name);
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);
        }

        // Input values
        edit_text_bg_calc_min   = (EditText) findViewById(R.id.edit_text_bg_calc_min);
        edit_text_bg_calc_max   = (EditText) findViewById(R.id.edit_text_bg_calc_max);
        edit_text_bg_target     = (EditText) findViewById(R.id.edit_text_bg_target);
        edit_text_bg_correctabove = (EditText) findViewById(R.id.edit_text_bg_correctabove);
        edit_text_bg_current    = (EditText) findViewById(R.id.edit_text_bg_current);
        edit_text_correction_factor = (EditText) findViewById(R.id.edit_text_correction_factor);
        edit_text_meal_iob      = (EditText) findViewById(R.id.edit_text_meal_iob);
        edit_text_correction_iob= (EditText) findViewById(R.id.edit_text_correction_iob);
        edit_text_meal_carbs    = (EditText) findViewById(R.id.edit_text_meal_carbs);
        edit_text_meal_ratio    = (EditText) findViewById(R.id.edit_text_meal_ratio);
        /*
        edit_text_bg_current.requestFocus();
        //edit_text_bg_current.clearFocus();
        */

        edit_text_bg_calc_min.setOnEditorActionListener(this);
        edit_text_bg_calc_max.setOnEditorActionListener(this);
        edit_text_bg_target.setOnEditorActionListener(this);
        edit_text_bg_correctabove.setOnEditorActionListener(this);
        edit_text_bg_current.setOnEditorActionListener(this);
        edit_text_correction_factor.setOnEditorActionListener(this);
        edit_text_meal_iob.setOnEditorActionListener(this);
        edit_text_correction_iob.setOnEditorActionListener(this);
        edit_text_meal_carbs.setOnEditorActionListener(this);
        edit_text_meal_ratio.setOnEditorActionListener(this);

        button_reverse_correction = (Button)  findViewById(R.id.button_reverse_correction);
        button_reverse_correction.setOnClickListener(this);
        // updateBackgroundReverseCorrection();
        updateData();

        // Calculate the result
        bolusCalculator.updateResult();
        String result = bolusCalculator.getResult();
        text_view_result        = (TextView) findViewById(R.id.text_view_result);
        text_view_result.setText(result);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        String text = String.valueOf(v.getText());

        double value;
        try {
            value = Double.parseDouble(text);
        } catch (Exception e) {
            value = 0;
            v.setText("0");
        }

        switch(v.getId()){
            case R.id.edit_text_bg_calc_min:
                bolusCalculator.setBgCalc_min(value);
                edit_text_bg_calc_min.setText( StringUtil.simplify(bolusCalculator.getBgCalc_min()) );
                break;
            case R.id.edit_text_bg_calc_max:
                bolusCalculator.setBgCalc_max(value);
                edit_text_bg_calc_max.setText( StringUtil.simplify(bolusCalculator.getBgCalc_max()) );
                break;
            case R.id.edit_text_bg_target:
                bolusCalculator.setBgTarget(value);
                edit_text_bg_target.setText( StringUtil.simplify(bolusCalculator.getBgTarget()) );
                break;
            case R.id.edit_text_bg_correctabove:
                bolusCalculator.setBgCorrectAbove(value);
                edit_text_bg_correctabove.setText( StringUtil.simplify(bolusCalculator.getBgCorrectAbove()) );
                break;
            case R.id.edit_text_bg_current:
                bolusCalculator.setBgCurrent(value);
                edit_text_bg_current.setText( StringUtil.simplify(bolusCalculator.getBgCurrent()) );
                break;
            case R.id.edit_text_correction_factor:
                bolusCalculator.setCorrectionFactor(value);
                edit_text_correction_factor.setText( StringUtil.simplify(bolusCalculator.getCorrectionFactor()) );
                break;
            case R.id.edit_text_meal_iob:
                bolusCalculator.setAdjustmentMeal_IOB(value);
                edit_text_meal_iob.setText( StringUtil.simplify(bolusCalculator.getAdjustmentMeal_IOB()) );
                break;
            case R.id.edit_text_correction_iob:
                bolusCalculator.setAdjustmentCorrection_IOB(value);
                edit_text_correction_iob.setText( StringUtil.simplify(bolusCalculator.getAdjustmentCorrection_IOB()) );
                break;
            case R.id.edit_text_meal_carbs:
                bolusCalculator.setMealCarbs(value);
                edit_text_meal_carbs.setText( StringUtil.simplify(bolusCalculator.getMealCarbs()) );
                break;
            case R.id.edit_text_meal_ratio:
                bolusCalculator.setMeal_ic_ratio(value);
                edit_text_meal_ratio.setText( StringUtil.simplify(bolusCalculator.getMeal_ic_ratio()) );
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

    /* =============================================================================================
    Private methods for updating the values on the screen
    ============================================================================================= */

    public void updateData() {
        edit_text_bg_calc_min.setText( StringUtil.simplify(bolusCalculator.getBgCalc_min()) );
        edit_text_bg_calc_max.setText( StringUtil.simplify(bolusCalculator.getBgCalc_max()) );
        edit_text_bg_target.setText( StringUtil.simplify(bolusCalculator.getBgTarget()) );
        edit_text_bg_correctabove.setText( StringUtil.simplify(bolusCalculator.getBgCorrectAbove()) );
        edit_text_bg_current.setText( StringUtil.simplify(bolusCalculator.getBgCurrent()) );
        edit_text_correction_factor.setText( StringUtil.simplify(bolusCalculator.getCorrectionFactor()) );
        edit_text_meal_iob.setText( StringUtil.simplify(bolusCalculator.getAdjustmentMeal_IOB()) );
        edit_text_correction_iob.setText( StringUtil.simplify(bolusCalculator.getAdjustmentCorrection_IOB()) );
        edit_text_meal_carbs.setText( StringUtil.simplify(bolusCalculator.getMealCarbs()) );
        edit_text_meal_ratio.setText( StringUtil.simplify(bolusCalculator.getMeal_ic_ratio()) );
        updateBackgroundReverseCorrection();
    }
    private void updateBackgroundReverseCorrection() {
        boolean status = bolusCalculator.isReverseCorrection();
        if (status) {
            button_reverse_correction.setBackgroundColor(Color.parseColor("#e1f7a9"));
            button_reverse_correction.setText("Reverse\nCorrection ON");
        } else {
            button_reverse_correction.setBackgroundColor(Color.parseColor("#e63900"));
            button_reverse_correction.setText("Reverse\nCorrection OFF");
        }
    }

    /* =============================================================================================
    Toolbar methods
    ============================================================================================= */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Activity's overrided method used to handle click events on action bar menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar will automatically handle clicks on the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.about) {
            String author = getResources().getString(R.string.author);

            Toast.makeText(getApplicationContext(), author, Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
