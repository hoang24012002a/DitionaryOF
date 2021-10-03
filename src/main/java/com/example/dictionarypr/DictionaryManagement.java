package com.example.dictionarypr;
import java.util.Scanner;

public class DictionaryManagement {
    Dictionary  k = new Dictionary();

    int count =0;
    /** Hàm insertFromCommandline() có chức năng nhập liệu từ. */
    public void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        System.out.print("số lượng từ mới muốn nhập vào:");
        int n = scan.nextInt();
        for(int i = count;i < count + n ;i++ ) {
            Scanner input = new Scanner(System.in);
            Word V = new Word();
            System.out.print(i+1+".English:");
            V.word_target = input.nextLine();
            System.out.print(i+1+".Vietnamese:");
            V.word_explain = input.nextLine();
            k.wordsArray[i] = V;
        }
        count = count +n;
    }


}
