package com.example.language.Create_Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.language.R;
import com.example.language.Splash_Activity.Splash_create_quiz;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class create_quiz_main extends AppCompatActivity {
    TextView textView;
    private FloatingActionButton fab1;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

       // textView = findViewById(R.id.textView13);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        toolbar = findViewById(R.id.toolbartst);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.black));
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
     //   fab1 = findViewById(R.id.fab);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(create_quiz_main.this, Splash_create_quiz.class));
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
