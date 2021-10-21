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
                //System.out.println(rs.getString(3).charAt(rs.getString(3).length()-1));
            }
            con.close();
       } catch (Exception e) {
            System.out.println(e);
       }
       if(arr.size() == 0){return null;}
       return arr;
    }

    //String[0]:nội động từ ,[1] ngoại động từ,[2] động từ,[3] danh từ,[4] phó từ
    //[5] tính từ ,[6] thán từ
    public String[] Phanloai(Word v) {
        String[] str1 = new String[1];
        // Nếu từ đấy ko có các kiểu danh từ,động từ,... trả vêg mảng 1 phần tử là cả nghĩa
        if (!v.getWord_explain().contains("*")) {
            str1[0] = v.getWord_explain();;
            return str1;
        }
        // Nếu từ đấy có các kiểu danh từ,động từ,........
        String[] str2 = new String[7];
        for (int i = 0; i < 7; i++) {
            str2[i] = "";
        }
        String[] arr = v.getWord_explain().split("\n");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
            if (arr[i].contains("nội động từ")) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j].charAt(0) == '*') break;
                    str2[0] = str2[0] + arr[j];
                }
            } else if (arr[i].contains("ngoại động từ")) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j].charAt(0) == '*') break;
                    str2[1] = str2[1] + arr[j];
                }
            } else if (arr[i].contains("động từ")) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j].charAt(0) == '*') break;
                    str2[2] = str2[2] + arr[j];
                }
            } else if (arr[i].contains("danh từ")) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j].charAt(0) == '*') break;
                    str2[3] = str2[3] + arr[j];
                }
            } else if (arr[i].contains("phó từ")) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j].charAt(0) == '*') break;
                    str2[4] = str2[4] + arr[j];
                }
            } else if (arr[i].contains("tính từ")) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j].charAt(0) == '*') break;
                    str2[5] = str2[5] + arr[j];
                }
            } else if (arr[i].contains("thán từ")) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j].charAt(0) == '*') break;
                    str2[6] = str2[6] + arr[j];
                }
            }
        }
        return str2;
    }

}
