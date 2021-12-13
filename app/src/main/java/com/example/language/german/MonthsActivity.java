package com.example.language.german;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.language.R;

import java.util.ArrayList;


public class MonthsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Array of words of numbers
        ArrayList<Word> words = new ArrayList<>();

        //Create words using word constructor and add it to Words ArrayList
        words.add(new Word("Week", "Woche"));
        words.add(new Word("Today", "Heute"));
        words.add(new Word("Tomorrow", "Morgen"));
        words.add(new Word("Yesterday", "Gestern"));
        words.add(new Word("January", "Januar"));
        words.add(new Word("February", "Februar"));
        words.add(new Word("March", "März"));
        words.add(new Word("April", "April"));
        words.add(new Word("May", "Mai"));
        words.add(new Word("June", "Juni"));
        words.add(new Word("July", "Juli"));
        words.add(new Word("August", "August"));
        words.add(new Word("September", "September"));
        words.add(new Word("October", "Oktober"));
        words.add(new Word("November", "November"));
        words.add(new Word("December", "Dezember"));


        //Add List View
        WordActivity adapter = new WordActivity(this,  words);

        // Find list view
        ListView listView = findViewById(R.id.list);

        // Add the adapter
        listView.setAdapter(adapter);
    }
}
