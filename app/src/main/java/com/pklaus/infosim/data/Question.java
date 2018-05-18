package com.pklaus.infosim.data;

public class Question {

    private int id;
    private String question;
    private String photoURL;
    private String category;
    private String optA;
    private String optB;
    private String optC;
    private String answer;

    public Question(String question, String category, String optA, String optB, String optC, String answer) {
        this.question = question;
        this.category = category;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.answer = answer;
    }

    public Question(String question, String photoURL, String category, String optA, String optB, String optC, String answer) {
        this.question = question;
        this.photoURL = photoURL;
        this.category = category;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.answer = answer;
    }

    public Question(int id, String question, String photoURL, String category, String optA, String optB, String optC, String answer) {
        this.id = id;
        this.question = question;
        this.photoURL = photoURL;
        this.category = category;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.answer = answer;
    }

    public Question(int id, String question, String category, String optA, String optB, String optC, String answer) {
        this.id = id;
        this.question = question;
        this.category = category;
        this.optA = optA;
        this.optB = optB;
        this.optC = optC;
        this.answer = answer;
    }

    public int getId() {

        return id;
    }

    public boolean hasPicture(){
        if(this.photoURL == null)
            return false;
        else
            return true;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptA() {
        return optA;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public String getOptB() {
        return optB;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public String getOptC() {
        return optC;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }




}
