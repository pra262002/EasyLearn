package com.example.language.german;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.language.R;

import java.util.ArrayList;

public class SentencesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        //Array of words of numbers
        ArrayList<Word> words = new ArrayList<>();

        //Create words using word constructor and add it to Words ArrayList
        words.add(new Word("My name is…", "Mein Name ist…"));
        words.add(new Word("I’m fine", "Mir geht’s gut"));
        words.add(new Word("I’m learning German.", "Ich lerne Deutsch."));
        words.add(new Word("Good morning", "Guten Morgen "));
        words.add(new Word("Good afternoon", " Guten Tag "));
        words.add(new Word("Good evening", "Guten Abend "));
        words.add(new Word("Good night", "Gute Nacht"));
        words.add(new Word("Thank you very much", "Vielen Dank"));
        words.add(new Word("Nice to meet you!", "Freut mich "));
        words.add(new Word("Where are you from?", "Woher kommen Sie?"));
        words.add(new Word("Goodbye, see you next time", "Tschüss, bis zum nächsten Mal "));


        //Add List View
        WordActivity adapter = new WordActivity(this,  words);

        // Find list view
        ListView listView = findViewById(R.id.list);

        // Add the adapter
        listView.setAdapter(adapter);
    }

}
