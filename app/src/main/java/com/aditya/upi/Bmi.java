package com.aditya.upi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bmi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        final EditText Weight,Height;
        TextView txtEnter, txtBMI;
        Button btnResult,btnReset;

        Weight=(EditText) findViewById(R.id.weight);
        Height=(EditText) findViewById(R.id.height);

        txtEnter=(TextView) findViewById(R.id.txtenter);
        txtBMI=(TextView) findViewById(R.id.txtbmi);

        btnReset=(Button) findViewById(R.id.btnreset);
        btnResult=(Button) findViewById(R.id.btnresult);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strW= Weight.getText().toString();
                String strH= Height.getText().toString();

                if (strW.equals(""))
                {
                    Weight.setError("Enter your Weight");
                    Weight.requestFocus();
                    return;
                }
                if (strH.equals(""))
                {
                    Height.setError("Enter your Height");
                    Height.requestFocus();
                    return;
                }


            }
        });

    }

    public float BMICalculate(float weight,float height)
    {
        return weight/(height * height);
    }

    public String interpretateBMI(float BMIvalues)
    {
        if( BMIvalues < 16)
        {
            return "Severly UnderWeight";
        }
        else if (BMIvalues < 18.5 )
        {
            return "UnderWeight";
        }
        else if (BMIvalues <25)
        {
            return "Normal";

        }
        else if (BMIvalues < 30)
        {
            return "OverWeight";
        }
        else
            return "Obese";
    }
}