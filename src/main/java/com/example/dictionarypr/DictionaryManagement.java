package com.example.dictionarypr;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private Dictionary  k = new Dictionary();
    private final static String FILE = "dictionaries.txt";

    /** Hàm tìm kiếm nhị phân phục vụ cho tìm kiếm nhanh hơn. */
    private Word binarySearch(ArrayList<Word> arr, int l, int r, String x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            // Nếu phần tử có ở chính giữa
            if (arr.get(mid).getWord_target().equals(x))
                return arr.get(mid);
            // hiện diện trong mảng con bên trái
            if (arr.get(mid).getWord_target().compareTo(x)>0)
                return binarySearch(arr, l, mid - 1, x);
            // trong mảng con bên phải
            return binarySearch(arr, mid + 1, r, x);
        }
        // Nếu phầnt tử không có trong mảng
        return null;
    }

    public Dictionary getK() {
        return k;
    }

    private Word binarySearch1(ArrayList<Word> arr, String key) {
        int low = 0;
        int high = arr.size() - 1;
        while (high >= low) {
            int mid = (low + high) / 2;
            if (arr.get(mid).getWord_target().compareTo(key)>0)
                high = mid - 1;
            else if (arr.get(mid).getWord_target().equals(key))
                return arr.get(mid);
            else
                low = mid + 1;
        }
        return null; /* Now high < low, key not found */
    }

/*
    public void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        System.out.print("so luong");
        int n = scan.nextInt();
        for(int i = 0;i < n ;i++ ) {
            Scanner input = new Scanner(System.in);
            System.out.print(i+1+".English:");
            String target = input.nextLine();
            System.out.print(i+1+".Vietnamese:");
            String explain = input.nextLine();
            Word V = new Word(target, explain);
            k.getWordsArray().get(i).add(V);
        }
    }
*/

    /**
     * Hàm insertFromFile() nhập liệu từ điển từ tệp dictionaries.txt.
     * @throws IOException ngoại lệ xảy ra khi tệp dictionaries.txt không được tìm thấy.
     */
    public ArrayList<ArrayList<Word>> insertFromFile() throws IOException {
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
                k.getWordsArray().get((int)V.getWord_target().charAt(0)-96).add(V);
                target = "";explain = "";
                dem = 0;
            } else if (dem <1 )  {
                target = target + (char) c;
            } else if (!Character.toString((char)c).equals("\r")){
                explain = explain + (char) c;
            }
        }
        return getK().getWordsArray();
    }

    public ArrayList<Word> insertFto1Array() {
        for(int j = 0; j <27 ; j++) {
            for (int i = 0; i < getK().getWordsArray().get(j).size(); i++) {
                if (k.getWordsArray().get(j).get(i) != null) {
                    k.getMyListWord().add(k.getWordsArray().get(j).get(i));
                }
            }
        }
        return getK().getMyListWord();
    }

    /**
     * Hàm dictionaryLookup() có chức năng tra cứu từ điển bằng dòng lệnh.
     * @param English argument.
     * @return String.
     */
    public String dictionaryLookup(String English) {
        English = English.toLowerCase();
        int n = (int)English.charAt(0)-96;
        return binarySearch1(k.getWordsArray().get(n),English).getWord_explain();
    }

    /**
     * Hàm có chức năng thêm từ , có sử dụng tìm kiếm nhị phân.
     * @param English argument.
     * @param Vietnamese argument.
     */
    public void addNewWord(String English, String Vietnamese) {
        English = English.toLowerCase();
        Vietnamese = Vietnamese.toLowerCase();
        Word V = new Word(English,Vietnamese);
        int n = (int)English.charAt(0)-96;
        if(n < 0) { n = 0;}
        Word F = binarySearch(k.getWordsArray().get(n),0,k.getWordsArray().get(n).size()-1,English);
        if(F == null) {
            k.getWordsArray().get(n).add(V);
        } else {
            String word_explain = F.getWord_explain() + ", " + Vietnamese;
            F.setWord_explain(word_explain);
        }
    }

    /**
     * Hàm có chức năng sửa từ.
     * @param English argument.
     * @param Vietnamese argument.
     * @return boolean.
     */
    public boolean fixWord(String English, String Vietnamese) {
        English = English.toLowerCase();
        Vietnamese = Vietnamese.toLowerCase();
        int n = (int)English.charAt(0)-96;
        if(n < 0) { n = 0;}
        Word F = binarySearch(k.getWordsArray().get(n),0,k.getWordsArray().get(n).size()-1,English);
        if(F != null) {
            F.setWord_explain(Vietnamese);
            return true;
        }
        return false;
    }

    /**
     * Hàm có chức năng xóa từ.
     * @param English argument.
     * @return boolean.
     */
    public boolean deleteWord(String English){
        English = English.toLowerCase();
        int n = (int)English.charAt(0)-96;
        if(n < 0) { n = 0;}
        Word F = binarySearch(k.getWordsArray().get(n),0,k.getWordsArray().get(n).size()-1,English);
        if(F != null) {
            k.getWordsArray().get(n).remove(F);
            return true;
        }
        return false;
    }

    /**
     * hàm dictionaryExportToFile() có chức năng xuất dữ liệu từ điển ra file dictionaries.txt .
     * @throws IOException ngoại lệ khi không tìm thấy file (file không tồn tại) .
     */
    public void dictionaryExportToFile() throws IOException{
        File file = new File(FILE);
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        for(int i = 0; i < 27 ; i++) {
            for (int j = 0; j < k.getWordsArray().get(i).size(); j++) {
                outputStreamWriter.write(k.getWordsArray().get(i).get(j).getWord_target());
                outputStreamWriter.write("\t");
                outputStreamWriter.write(k.getWordsArray().get(i).get(j).getWord_explain());
                outputStreamWriter.write("\n");
            }
        }
        outputStreamWriter.flush();

    }


}