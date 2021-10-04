package com.example.dictionarypr;

import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    Dictionary  k = new Dictionary();
    private final static String FILE = "dictionaries.txt";

    /** Hàm insertFromCommandline() có chức năng nhập liệu từ. */
    public void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        System.out.print("số lượng từ mới muốn nhập vào:");
        int n = scan.nextInt();
        for(int i = 0;i < n ;i++ ) {
            Scanner input = new Scanner(System.in);
            Word V = new Word();
            System.out.print(i+1+".English:");
            V.word_target = input.nextLine();
            System.out.print(i+1+".Vietnamese:");
            V.word_explain = input.nextLine();
            k.wordsArray.add(V);
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
        Word V = new Word();
        while ((c = inputStreamReader.read()) != -1) {
            if (Character.toString((char)c).equals("\t")) {
                dem++;
            } else if (Character.toString((char)c).equals("\n")) {
                k.wordsArray.add(V);
                dem = 0;
                V = new Word();
            } else if (dem <1 )  {
                V.word_target = V.word_target + (char) c;
            } else {
                V.word_explain = V.word_explain + (char) c;
            }
        }
    }

    /**
     * Hàm dictionaryLookup() có chức năng tra cứu từ điển bằng dong lệnh.
     */
    void dictionaryLookup() {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập từ cần tra,English:");
        String EInput = input.nextLine();
        boolean check = false;
        for (int i = 0; i < k.wordsArray.size(); i++)
        {
            if(k.wordsArray.get(i).word_target.equals(EInput))
            {   check = true;
                System.out.println("Vietnamese: "+k.wordsArray.get(i).word_explain);
                break;
            }
        }
        if(!check) {
            System.out.println("Không có từ cần tìm");}
    }


}
