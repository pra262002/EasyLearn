package com.example.language.german;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.language.R;
import com.example.language.View.MainActivity;
import com.example.language.german.Word;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;



public class WordActivity extends ArrayAdapter<Word> {

    private TextToSpeech mTTS;
    // Constructor for word adapter

    public WordActivity(Activity context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    // Overriding getView
    @SuppressLint("RestrictedApi")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }



        // get the current Word
        Word currentWord = getItem(position);

        // takes current word, translates to german, then sets to text view



        TextView germanTextView = listItemView.findViewById(R.id.german_text_view);
        germanTextView.setText(currentWord.getGermanTranslation());



        // takes current word, translates to default language, then sets to text view
        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());


        // returns full list view
        return listItemView;
    }



}
