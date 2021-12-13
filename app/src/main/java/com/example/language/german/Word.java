package com.example.language.german;


// Contains default translation and German word translation for the word

import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech;
import java.util.Locale;
import android.content.Context;
public class Word {
TextToSpeech mTTS;

    private String mDefaultTranslation;

    private String mGermanTranslation;

    // Constructor for words
    public Word(String defaultTranslation, String germanTranslation) {
        mDefaultTranslation = defaultTranslation;
        mGermanTranslation = germanTranslation;

    }


    // Return English/Default translation
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    // Return German translation

    public String getGermanTranslation() {

        return mGermanTranslation;
    }


}



