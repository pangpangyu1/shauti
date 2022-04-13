package com.pangpangyu.service.front;

import com.pangpangyu.domain.front.ExamQuestion;
import com.pangpangyu.domain.store.Question;

import java.util.List;

public interface ExamService {

    List<Question> getPaper();

    boolean applyPaper(String memberId, List<ExamQuestion> examQuestionList);
}
