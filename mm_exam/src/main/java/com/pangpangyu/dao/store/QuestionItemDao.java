package com.pangpangyu.dao.store;

import com.pangpangyu.domain.store.QuestionItem;

import java.util.List;

public interface QuestionItemDao {
    List<QuestionItem> findByQuestionId(String questionId);
}
