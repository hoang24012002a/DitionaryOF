package com.example.dictionarypr;

import java.util.ArrayList;

public class Dictionary {

    private ArrayList<ArrayList<Word>> wordsArray = new ArrayList <ArrayList<Word>>();

    //getter cho thuộc tính wordsArray.
    public ArrayList<ArrayList<Word>> getWordsArray() {
        return wordsArray;
    }

    /** Hàm khởi tạo có chức năng tạo ArrayList 2 chiều cho 26 chữ cái đầu. */
    public Dictionary() {
        for( int i = 0; i < 26; i++) {
            wordsArray.add(new ArrayList<Word>());
        }
    }
}
