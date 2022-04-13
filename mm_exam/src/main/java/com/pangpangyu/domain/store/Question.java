package com.pangpangyu.domain.store;

import java.util.List;

public class Question {
    private String id;            //题目ID

    private String subject;     //题干

    private String type;       	//题目类型  1:单选，2：多选，3：简答

    private List<QuestionItem> questionItemList;

    public List<QuestionItem> getQuestionItemList() {
        return questionItemList;
    }

    public void setQuestionItemList(List<QuestionItem> questionItemList) {
        this.questionItemList = questionItemList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
