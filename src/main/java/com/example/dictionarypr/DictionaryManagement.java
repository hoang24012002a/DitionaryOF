package com.example.dictionarypr;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {

    private Dictionary k = new Dictionary();

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




}