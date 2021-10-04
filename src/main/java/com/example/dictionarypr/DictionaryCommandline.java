package com.example.dictionarypr;

import java.io.*;

public class DictionaryCommandline {
    DictionaryManagement d = new DictionaryManagement();

    /** Hàm showAllWords() có chức năng hiển thị danh sách dữ liệu từ điển. */
    void showAllWords() {
        System.out.println("No\t\t|English\t\t\t|Vietnamese");
        for(int i = 0; i < d.k.wordsArray.size(); i++) {
            if(d.k.wordsArray.get(i).word_target.length() < 7) {
             System.out.println(i + 1 + "\t\t|" + d.k.wordsArray.get(i).word_target + "\t\t\t\t|" + d.k.wordsArray.get(i).word_explain);
           } else if (d.k.wordsArray.get(i).word_target.length() <= 10) {
             System.out.println(i + 1 + "\t\t|" + d.k.wordsArray.get(i).word_target + "\t\t\t|" + d.k.wordsArray.get(i).word_explain);
           } else {
             System.out.println(i + 1 + "\t\t|" + d.k.wordsArray.get(i).word_target + "\t\t|" + d.k.wordsArray.get(i).word_explain);
           }
        }

    }

    /** Hàm gọi hàm insertFromCommandline() và showAllWords(). */
    void dictionaryBasic() {
        d.insertFromCommandline();
        showAllWords();
    }

    /**
     * hàm có chức năng gọi các hàm insertFromFile(), showAllWords(), dictionaryLookup().
     */
    void dictionaryAdvanced() {
        try {
            d.insertFromFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
        showAllWords();
        d.dictionaryLookup();
    }

    public static void main(String[] args) {

        DictionaryCommandline Dic = new DictionaryCommandline();
        //Dic.insertFromCommandline();
        //Dic.showAllWords();
        //Dic.dictionaryBasic();
        Dic.dictionaryAdvanced();
    }

}
