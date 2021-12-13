package com.example.language.german;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.language.R;

import java.util.ArrayList;

public class SimpleWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Array of words of numbers
        ArrayList<Word> words = new ArrayList<>();

        //Create words using word constructor and add it to Words ArrayList
        words.add(new Word("Hello", "Hallo"));
        words.add(new Word("Thank You", "Sprache"));
        words.add(new Word("No/Yes", "Nein/Ja"));
        words.add(new Word("Good", "Gut"));
        words.add(new Word("Beautiful", "Schön"));
        words.add(new Word("Strong", "Stark"));
        words.add(new Word("Cake", "Kuchen"));
        words.add(new Word("Food", "Lebensmittel"));
        words.add(new Word("Water", "Wasser"));
        words.add(new Word("News", "Nachrichten"));
        words.add(new Word("Happy", "glücklich"));


        //Add List View
        WordActivity adapter = new WordActivity(this,  words);

        // Find list view
        ListView listView = findViewById(R.id.list);

        // Add the adapter
        listView.setAdapter(adapter);
    }
}
