package com.example.language.german;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.language.R;
import com.example.language.View.MainActivity;


public class Homepage extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Content set to main_activity
        setContentView(R.layout.content_main);

        //Get Button View from number id
        Button numbers = findViewById(R.id.number_button);

        //Get Button View from color id
        Button colors = findViewById(R.id.color_button);
        Button months = findViewById(R.id.month_button);
        Button things = findViewById(R.id.things_button);
        Button sentences = findViewById(R.id.sentence_button);


        //Add onClickListener on the number button
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to open number activity
                Intent numbersIntent = new Intent(Homepage.this, NumbersActivity.class);
                startActivity(numbersIntent);
                Toast.makeText(getApplicationContext(),"Numbers Selected",Toast.LENGTH_SHORT).show();

            }
        });

        //Add onClickListener on the color button
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent to open colors activity
                Intent colorsIntent = new Intent(Homepage.this, ColorsActivity.class);
                startActivity(colorsIntent);
                Toast.makeText(getApplicationContext(),"Colors Selected",Toast.LENGTH_SHORT).show();
            }
        });

        months.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monthsIntent = new Intent(Homepage.this, MonthsActivity.class);
                startActivity(monthsIntent);
                Toast.makeText(getApplicationContext(),"Months Selected",Toast.LENGTH_SHORT).show();

            }
        });

        things.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thingsIntent = new Intent(Homepage.this, SimpleWordActivity.class);
                startActivity(thingsIntent);
                Toast.makeText(getApplicationContext(),"Things Selected",Toast.LENGTH_SHORT).show();

            }
        });

        sentences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sentencesIntent = new Intent(Homepage.this, SentencesActivity.class);
                startActivity(sentencesIntent);
                Toast.makeText(getApplicationContext(),"Sentences Selected",Toast.LENGTH_SHORT).show();

            }
        });
    }





}
