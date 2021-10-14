package com.example.dictionarypr;

public class DictionaryCommandline {

    private DictionaryManagement d = new DictionaryManagement();

    /** Hàm showAllWords() có chức năng hiển thị danh sách dữ liệu từ điển. */
    public void showAllWords() {
        System.out.println("No\t\t|English\t\t\t|Vietnamese");
        int count = 1;
        for (int j = 0; j <26; j++) {
            for (int i = 0; i < d.getK().getWordsArray().get(j).size(); i++) {
                if (d.getK().getWordsArray().get(j).get(i).getWord_target().length() < 3) {
                    System.out.print(count + "\t\t|" + d.getK().getWordsArray().get(j).get(i).getWord_target());
                    System.out.println("\t\t\t\t\t|" + d.getK().getWordsArray().get(j).get(i).getWord_explain());
                } else if (d.getK().getWordsArray().get(j).get(i).getWord_target().length() < 7) {
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

    /** Hàm dictionaryBasic() có chức năng gọi hàm insertFromCommandline() và showAllWords(). */
    public void dictionaryBasic() {
        d.insertFromCommandline();
        showAllWords();
    }

}
