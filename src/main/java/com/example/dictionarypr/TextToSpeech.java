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
    private final TextToSpeechThread thread;

    private class TextToSpeechThread extends Thread {
        private synchronized void init(String text, Double speed, String languageCode ,SynthesiserV2 synthesiser) {
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
        thread = new TextToSpeechThread();
    }

    public void speak(String text) {
        thread.init(text, 0.75, "en-us", synthesiser);
        thread.setDaemon(false);
        if (!thread.isAlive()) {
            thread.start();
        }
    }
}
