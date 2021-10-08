package com.example.dictionarypr;

import java.io.*;
import java.util.ArrayList;

public class DictionaryCommandline {
    private DictionaryManagement d = new DictionaryManagement();

    /** Hàm showAllWords() có chức năng hiển thị danh sách dữ liệu từ điển. */
    public void showAllWords() {
        System.out.println("No\t\t|English\t\t\t|Vietnamese");
        for(int i = 0; i < d.getK().getWordsArray().size(); i++) {
                if(d.getK().getWordsArray().get(i).getWord_target().length() < 3) {
                    System.out.print(i + 1 + "\t\t|" + d.getK().getWordsArray().get(i).getWord_target());
                    System.out.println("\t\t\t\t\t|" + d.getK().getWordsArray().get(i).getWord_explain());
                }else if(d.getK().getWordsArray().get(i).getWord_target().length() < 7) {
                    System.out.print(i + 1 + "\t\t|" + d.getK().getWordsArray().get(i).getWord_target());
                    System.out.println("\t\t\t\t|" + d.getK().getWordsArray().get(i).getWord_explain());
                } else if (d.getK().getWordsArray().get(i).getWord_target().length() <= 10) {
                    System.out.print(i + 1 + "\t\t|" + d.getK().getWordsArray().get(i).getWord_target());
                    System.out.println("\t\t\t|" + d.getK().getWordsArray().get(i).getWord_explain());
                } else {
                    System.out.print(i + 1 + "\t\t|" + d.getK().getWordsArray().get(i).getWord_target());
                    System.out.println("\t\t|" + d.getK().getWordsArray().get(i).getWord_explain());
                }
        }
    }

    /** Hàm gọi hàm insertFromCommandline() và showAllWords(). */
    public void dictionaryBasic() {
        d.insertFromCommandline();
        showAllWords();
    }

    /**
     * hàm có chức năng gọi các hàm insertFromFile(), showAllWords(), dictionaryLookup().
     */
    public void dictionaryAdvanced(String English) {
        try {
            d.insertFromFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
        showAllWords();

        System.out.println(d.dictionaryLookup(English));

    }

    public void add(String English, String Vietnamese) {

        d.addNewWord(English,Vietnamese);
    }

    public boolean fix(String English, String Vietnamese) {
        return d.fixWord(English, Vietnamese);
    }

    public boolean delete(String English) {

        return d.deleteWord(English);
    }

    public ArrayList<Word> dictionarySearcher(String keyWord) {
        ArrayList<Word> searchArray = new ArrayList<>();
        keyWord.toLowerCase();
        boolean check = false;
        for (int i = 0; i < d.getK().getWordsArray().size(); i++) {
            if(d.getK().getWordsArray().get(i).getWord_target().startsWith(keyWord)) {
                searchArray.add(d.getK().getWordsArray().get(i));
                check = true;
            }
        }
        if (check == true) {
            return searchArray;
        }
        return null;
    }

    public void ExportToFile () {
        try {
            d.dictionaryExportToFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}
