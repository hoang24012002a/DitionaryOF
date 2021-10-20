package com.example.dictionarypr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TextToText {
    public String translate(String text) {
        String textVi = "";
        try {
            String urlStr = "https://script.google.com/macros/s/AKfycbw3Ca2EjUeK4KRpXoo1oYXv5F3KJNtJJMF1K9vJFY0PD18Pv6utetTKHmSng3r4EmKW/exec" +
                    "?q=" + URLEncoder.encode(text, "UTF-8");
            URL url = new URL(urlStr);
            StringBuilder response = new StringBuilder();
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            textVi = response.toString();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textVi;
    }
}
/*
public class TextToText {
    private String textVi;
    private class TextToTextThread implements Runnable {
        private String text;
        public TextToTextThread(String text) {
            this.text = text;
        }
        @Override
        public void run() {
            try {
                String urlStr = "https://script.google.com/macros/s/AKfycbw3Ca2EjUeK4KRpXoo1oYXv5F3KJNtJJMF1K9vJFY0PD18Pv6utetTKHmSng3r4EmKW/exec" +
                        "?q=" + URLEncoder.encode(text, "UTF-8");
                URL url = new URL(urlStr);
                StringBuilder response = new StringBuilder();
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                textVi = response.toString();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String translate(String text) {
        Thread thread = new Thread(new TextToTextThread(text));
        thread.setDaemon(false);
        thread.start();
        return textVi;
    }

}
 */
