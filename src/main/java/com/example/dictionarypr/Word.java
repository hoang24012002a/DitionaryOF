package com.example.dictionarypr;

public class Word {
    private String word_target = "";
    private String word_explain = "";
    public String danhTu = "";
    public String dongTu = "";
    public String noiDongTu = "";
    public String ngoaiDongTu = "";
    public String tinhTu = "";
    public String thanTu = "";
    public String phoTu = "";
    public String transcription ;
    public String undetermined = "";

    public String getTranscription() {
        return transcription;
    }

    public String getDanhTu() {
        return danhTu;
    }

    public String getDongTu() {
        return dongTu;
    }

    public String getNgoaiDongTu() {
        return ngoaiDongTu;
    }

    public String getNoiDongTu() {
        return noiDongTu;
    }

    public String getTinhTu() {
        return tinhTu;
    }

    public String getThanTu() {
        return thanTu;
    }

    public String getPhoTu() {
        return phoTu;
    }

    // getter cho thuộc tính word_taget.
    public String getWord_target() {
        return word_target;
    }

    // setter cho thuộc tính word_taget.
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    //getter cho thuộc tính word_explain.
    public String getWord_explain() {
        return word_explain;
    }

    // setter cho thuộc tính word_taget.
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }


    public Word() {
    }

    /**Hàm khởi tạo 2.*/
    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }
}
