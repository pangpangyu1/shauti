package com.pangpangyu.domain.front;

import java.util.Date;

public class ExamPaper {
    private String id;
    private String memberId;
    private Date applyTime;
    private String state;   //1-可用  0-不可用
    private Integer score;

    @Override
    public String toString() {
        return "ExamPaper{" +
                "id='" + id + '\'' +
                ", memberId='" + memberId + '\'' +
                ", applyTime=" + applyTime +
                ", state='" + state + '\'' +
                ", score=" + score +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
