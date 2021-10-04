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
    private SynthesiserV2 synthesiser = new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
    private class textToSpeechThread extends Thread {
        private void init(String text, SynthesiserV2 synthesiser) {
            try {
                synthesiser.setSpeed(0.75);
                InputStream dataSpeech = synthesiser.getMP3Data(text);
                AdvancedPlayer player = new AdvancedPlayer(dataSpeech);
                player.play();
            } catch (IOException | JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }
    public void speak(String text) {
        textToSpeechThread thread = new textToSpeechThread();
        thread.init(text, synthesiser);
        thread.setDaemon(false);
        thread.start();
    }

    public static void main(String[] args) {
        TextToSpeech textToSpeech = new TextToSpeech();
        textToSpeech.speak("application");
    }
}
