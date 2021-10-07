package com.example.dictionarypr;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary  k = new Dictionary();
    private final static String FILE = "dictionaries.txt";

    public Dictionary getK() {
        return k;
    }

    /** Hàm insertFromCommandline() có chức năng nhập liệu từ. */
    public void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        System.out.print("số lượng từ mới muốn nhập vào:");
        int n = scan.nextInt();
        for(int i = 0;i < n ;i++ ) {
            Scanner input = new Scanner(System.in);
            System.out.print(i+1+".English:");
            String target = input.nextLine();
            System.out.print(i+1+".Vietnamese:");
            String explain = input.nextLine();
            Word V = new Word(target, explain);
            k.getWordsArray().add(V);
        }

    }

    /**
     * Hàm insertFromFile() nhập liệu từ điển từ tệp dictionaries.txt .
     * @throws IOException ngoại lệ xảy ra khi tệp dictionaries.txt không được tìm thấy.
     */
    public void insertFromFile() throws IOException {
        File file = new File(FILE);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

        int c, dem = 0;
        String target = "";
        String explain = "";
        while ((c = inputStreamReader.read()) != -1) {
            if (Character.toString((char)c).equals("\t")) {
                dem++;
            } else if (Character.toString((char)c).equals("\n")) {
                Word V = new Word(target, explain);
                k.getWordsArray().add(V);
                target = "";explain = "";
                dem = 0;
            } else if (dem <1 )  {
                target = target + (char) c;
            } else {
                explain = explain + (char) c;
            }
        }
    }

    /**
     * Hàm dictionaryLookup() có chức năng tra cứu từ điển bằng dong lệnh.
     */
    public ArrayList<Word> dictionaryLookup(String English) {
        English.toLowerCase();
        for (int i = 0; i < k.getWordsArray().size(); i++)
        {
            if(k.getWordsArray().get(i).equals(English))
            {
                System.out.println("Vietnamese:"+k.getWordsArray().get(i).getWord_explain());
                break;
            }
        }
        return k.getWordsArray();
    }

    /**
     * Hàm  có chức năng thêm từ.
     */
    public void addNewWord(String English, String Vietnamese) {
        English.toLowerCase();
        Vietnamese.toLowerCase();
        Word V = new Word(Vietnamese,English);
        k.getWordsArray().add(V);
    }

    /**
     * Hàm có chức năng sử từ.
     */
    public boolean fixWord(String English, String Vietnamese) {
            English.toLowerCase();
            Vietnamese.toLowerCase();
            for (int j = 0; j < k.getWordsArray().size(); j++) {
                if (k.getWordsArray().get(j).getWord_target().equals(English)) {
                    k.getWordsArray().get(j).setWord_target(Vietnamese);
                    return true;
                }
            }
            return false;
    }

    /**
     * Hàm có chức năng xóa từ.
     */
    public boolean deleteWord(String English){
        English.toLowerCase();
        for (int j = 0; j < k.getWordsArray().size();j++) {
            if (k.getWordsArray().get(j).getWord_target().equals(English)) {
                Word V = k.getWordsArray().remove(j);
                return true;
            }
        }

        return false;
    }
}
