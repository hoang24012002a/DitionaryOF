package com.example.dictionarypr;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {

    private Dictionary k = new Dictionary();
    private final static String FILE = "dictionaries.txt";

    /** Hàm tìm kiếm nhị phân phục vụ cho tìm kiếm nhanh hơn. */
    private Word binarySearch(ArrayList<Word> arr, int l, int r, String x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (arr.get(mid).getWord_target().equals(x))
                return arr.get(mid);
            if (arr.get(mid).getWord_target().compareTo(x) > 0)
                return binarySearch(arr, l, mid - 1, x);
            return binarySearch(arr, mid + 1, r, x);
        }
        // Nếu phần tử không có trong mảng
        return null;
    }

    //getter cho thuộc tính k.
    public Dictionary getK() {
        return k;
    }

    /**
     * Hàm insertFromCommandline() có chức năng nhập liệu từ.
     */
    public ArrayList<ArrayList<Word>> insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        System.out.print("nhập số lượng từ muốn nhập vào:");
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            Scanner input = new Scanner(System.in);
            System.out.print(i + 1 + ".English:");
            String target = input.nextLine();
            System.out.print(i + 1 + ".Vietnamese:");
            String explain = input.nextLine();
            Word V = new Word(target, explain);
            int index = (int) target.charAt(0) - 97;
            k.getWordsArray().get(index).add(V);
        }
        return k.getWordsArray();
    }

    /**
     * Hàm insertFromFile() nhập liệu từ điển từ tệp dictionaries.txt.
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
                target = target.toLowerCase();
                explain = explain.toLowerCase();
                Word V = new Word(target, explain);
                k.getWordsArray().get((int)V.getWord_target().charAt(0)-97).add(V);
                target = "";explain = "";
                dem = 0;
            } else if (dem < 1 )  {
                target = target + (char) c;
            } else if (!Character.toString((char)c).equals("\r")){
                explain = explain + (char) c;
            }
        }
    }

    /**
     * Hàm dictionaryLookup() có chức năng tra cứu từ điển bằng dòng lệnh.
     * @param English argument
     * @return String.
     */
    public String dictionaryLookup(String English) {
        English = English.toLowerCase();
        int n = (int)English.charAt(0) - 97;
        return binarySearch(k.getWordsArray().get(n),0,k.getWordsArray().get(n).size()-1, English).getWord_explain();
    }

}