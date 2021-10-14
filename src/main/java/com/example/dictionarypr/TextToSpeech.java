package com.example.dictionarypr;

import java.io.InputStream;

import java.io.IOException;

import com.darkprograms.speech.synthesiser.SynthesiserV2;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;


/**
 * Provide speak() method to convert text to speech.
 */
public class TextToSpeech {
    private final SynthesiserV2 synthesiser;

    private class TextToSpeechThread implements Runnable {
        private String text;
        private String languageCode;
        private double speed;
        private SynthesiserV2 synthesiser;

        public TextToSpeechThread(String text, String languageCode, double speed, SynthesiserV2 synthesiser) {
            this.languageCode = languageCode;
            this.text = text;
            this.speed = speed;
            this.synthesiser = synthesiser;
        }
        @Override
        public void run() {
            try {
                synthesiser.setSpeed(speed);
                synthesiser.setLanguage(languageCode);
                InputStream dataSpeech = synthesiser.getMP3Data(text);
                AdvancedPlayer player = new AdvancedPlayer(dataSpeech);
                player.play();
                player.close();
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }
    public TextToSpeech() {
        synthesiser = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    }

    public void speak(String text, String languageCode, double speed) {
        if (speed == 0) {
            speed = 0.75;
        }
        Thread thread = new Thread(new TextToSpeechThread(text, languageCode, speed, synthesiser));
        thread.setDaemon(false);
        thread.start();
    }
}
