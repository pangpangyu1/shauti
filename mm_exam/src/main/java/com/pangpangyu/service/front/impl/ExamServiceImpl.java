package com.pangpangyu.service.front.impl;

import com.pangpangyu.dao.front.ExamPaperDao;
import com.pangpangyu.dao.front.ExamQuestionDao;
import com.pangpangyu.dao.store.QuestionDao;
import com.pangpangyu.domain.front.ExamPaper;
import com.pangpangyu.domain.front.ExamQuestion;
import com.pangpangyu.domain.store.Question;
import com.pangpangyu.factory.MapperFactory;
import com.pangpangyu.service.front.ExamService;
import com.pangpangyu.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ExamServiceImpl implements ExamService {
    @Override
    public List<Question> getPaper() {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            QuestionDao questionDao = MapperFactory.getMapper(sqlSession, QuestionDao.class);
            List<Question> questionList = questionDao.findAll();
//            for (Question question : questionList) {
//                System.out.println(question);
//            }
            return questionList;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean applyPaper(String memberId, List<ExamQuestion> examQuestionList) {
        SqlSession sqlSession = null;
        try{
            boolean flag = true;
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            ExamPaperDao examPaperDao = MapperFactory.getMapper(sqlSession, ExamPaperDao.class);
            ExamQuestionDao examQuestionDao = MapperFactory.getMapper(sqlSession, ExamQuestionDao.class);
            //3.提交保存的试卷信息
            ExamPaper examPaper  = new ExamPaper();
            String paperId = UUID.randomUUID().toString();
            examPaper.setId(paperId);
            examPaper.setApplyTime(new Date());
            examPaper.setMemberId(memberId);
            examPaper.setState("1");
            flag = flag && examPaperDao.save(examPaper) > 0;
            //4.提交保存的试卷中的所有题目对应的答案信息
            for(ExamQuestion eq: examQuestionList) {
                eq.setId(UUID.randomUUID().toString());
                eq.setExamPaperId(paperId);
                flag = flag && examQuestionDao.save(eq) > 0;
            }
            TransactionUtil.commit(sqlSession);
            return flag;
        }catch (Exception e){
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        }finally {
            try {
                TransactionUtil.close(sqlSession);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
