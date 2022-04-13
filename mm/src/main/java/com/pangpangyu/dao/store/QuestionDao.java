package com.pangpangyu.dao.store;

import com.pangpangyu.domain.store.Question;

import java.util.List;

public interface QuestionDao {

    int save(Question question);

    int delete(Question question);

    int update(Question question);

    Question findById(String id);

    List<Question> findAll();
}
