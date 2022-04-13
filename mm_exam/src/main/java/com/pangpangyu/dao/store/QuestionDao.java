package com.pangpangyu.dao.store;

import com.pangpangyu.domain.store.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
