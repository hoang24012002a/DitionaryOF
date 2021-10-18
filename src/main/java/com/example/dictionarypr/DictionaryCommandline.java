package com.example.dictionarypr;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.*;

public class DictionaryCommandline {
    private DictionaryManagement d = new DictionaryManagement();

    public DictionaryCommandline() {
        d.insertFto1Array();
    }

    /** Hàm partition(ArrayList<Word> arr, int low, int high) của sắp xếp Quicksort. */
    private int partition(ArrayList<Word> arr, int low, int high) {
        Word pivot = arr.get(high);
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            if (arr.get(j).getWord_target().compareTo(pivot.getWord_target()) < 0 ) {
                i++;
                Collections.swap(arr, i, j);
            }
        }

        Collections.swap(arr, i+1, high);
        return i + 1;
    }

    /** Hàm sort của sắp xếp Quicksort.*/
    private void sort(ArrayList<Word> arr, int low, int high) {
        if (low < high) {

            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    /** Hàm có chức năng sắp xếp 26 arrayList<Word> theo thứ tự chữ cái anphabet của từ. */
    private void sort_arrayWord() {
        for(int i = 0; i < 27; i++) {
            sort(d.getK().getWordsArray().get(i),0,d.getK().getWordsArray().get(i).size()-1);
        }
    }

    /** Hàm có chức năng trả về mảng dữ liệu từ điển cá nhân. */
    public ArrayList<Word> myList() {
            sort_arrayWord();
            return d.insertFto1Array();
    }

    /** Hàm showAllWords() có chức năng hiển thị danh sách dữ liệu từ điển. */
    public void showAllWords() {
        System.out.println("No\t\t|English\t\t\t|Vietnamese");
        int count = 1;
        for(int j = 0; j <27 ; j++) {
            for(int i = 0; i < d.getK().getWordsArray().get(j).size(); i++) {
                if(d.getK().getWordsArray().get(j).get(i).getWord_target().length() < 3) {
                    System.out.print(count + "\t\t|" + d.getK().getWordsArray().get(j).get(i).getWord_target());
                    System.out.println("\t\t\t\t\t|" + d.getK().getWordsArray().get(j).get(i).getWord_explain());
                }else if(d.getK().getWordsArray().get(j).get(i).getWord_target().length() < 7) {
                    System.out.print(count + "\t\t|" + d.getK().getWordsArray().get(j).get(i).getWord_target());
                    System.out.println("\t\t\t\t|" + d.getK().getWordsArray().get(j).get(i).getWord_explain());
                } else if (d.getK().getWordsArray().get(j).get(i).getWord_target().length() <= 10) {
                    System.out.print(count + "\t\t|" + d.getK().getWordsArray().get(j).get(i).getWord_target());
                    System.out.println("\t\t\t|" + d.getK().getWordsArray().get(j).get(i).getWord_explain());
                } else {
                    System.out.print(count + "\t\t|" + d.getK().getWordsArray().get(j).get(i).getWord_target());
                    System.out.println("\t\t|" + d.getK().getWordsArray().get(j).get(i).getWord_explain());
                }
                count++;
            }
        }
    }


    /** Hàm có chức năng gọi hàm dictionaryLookup(String English). */
    public String vLookup(String English) {
       return d.dictionaryLookup(English);
    }

    /** Hàm có chức năng gọi hàm dictionaryExportToFile(). */
    public void exportToFile () {
        try {
            sort_arrayWord();
            d.dictionaryExportToFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /** Hàm có chức năng gọi hàm add(String English, String Vietnamese) và xuất dữ liệu ra file sau khi xóa. */
    public void add(String English, String Vietnamese) {
        d.addNewWord(English,Vietnamese);
        sort_arrayWord();
        exportToFile();

    }

    /** Hàm có chức năng gọi hàm fixWord(String English, String Vietnamese) và xuất dữ liệu ra file sau khi xóa. */
    public boolean fix(String English, String Vietnamese) {
        if (d.fixWord(English, Vietnamese) == true) {
            exportToFile();
            return true;
        }
        return false;
    }

    /** Hàm có chức năng gọi deleteWord(String English) và xuất dữ liệu ra file sau khi xóa. */
    public boolean delete(String English) {
        if (d.deleteWord(English) == true) {
            exportToFile();
            return true;
        }
        return false;

    }

    /**
     * hàm có chức năng tra từ trả về 1 danh sách các đối tượng có liên quan.
     * @param keyWord argument.
     * @return ArrayList<Word>.
     */
    public ArrayList<Word> dictionarySearcher(String keyWord) {
        ArrayList<Word> searchArray = new ArrayList<Word>();
        keyWord = keyWord.toLowerCase();
        if (keyWord.length() == 0) {
            return null;
        }
        int n = (int)keyWord.charAt(0)-96;
        for (int i = 0; i < d.getK().getWordsArray().get(n).size(); i++) {
            if (d.getK().getWordsArray().get(n).get(i).getWord_target().startsWith(keyWord)) {
                searchArray.add(d.getK().getWordsArray().get(n).get(i));
            }


        }
        if (searchArray.size() > 0) {
            return searchArray;
        }
        return null;
    }

    public ArrayList<Word> MySQLCon(String keyWord) {
       keyWord = keyWord.toLowerCase();
       ArrayList<Word> arr = new ArrayList<>();
       try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/chinhsua", "root", "");
            //chinhsua là tên của database, root là username và password là rỗng
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_edict WHERE word LIKE '" + keyWord + "%'");
            while (rs.next()){
                if (rs.getString(2).startsWith(keyWord)) {
                    Word V = new Word(rs.getString(2), rs.getString(3));
                    arr.add(V);
                }
            }
            con.close();
       } catch (Exception e) {
            System.out.println(e);
       }
       if(arr.size() == 0){return null;}
       return arr;
    }

}
