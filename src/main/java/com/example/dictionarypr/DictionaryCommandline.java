package com.example.dictionarypr;

public class DictionaryCommandline {
    DictionaryManagement d = new DictionaryManagement();

    /** Hàm showAllWords() có chức năng hiển thị danh sách dữ liệu từ điển. */
    void showAllWords() {
        System.out.println("No\t\t|English\t\t\t|Vietnamese");
        for(int i = 0; i < d.count; i++)
           { if(d.k.wordsArray[i].word_target.length() < 8) {
             System.out.println(i + 1 + "\t\t|" + d.k.wordsArray[i].word_target + "\t\t\t\t|" + d.k.wordsArray[i].word_explain);
           } else if (d.k.wordsArray[i].word_target.length() < 15) {
             System.out.println(i + 1 + "\t\t|" + d.k.wordsArray[i].word_target + "\t\t\t|" + d.k.wordsArray[i].word_explain);
           } else {
             System.out.println(i + 1 + "\t\t|" + d.k.wordsArray[i].word_target + "\t\t|" + d.k.wordsArray[i].word_explain);
           }
        }

    }

    /** Hàm gọi hàm insertFromCommandline() và showAllWords(). */
    void dictionaryBasic() {
        d.insertFromCommandline();
        showAllWords();
    }

}
