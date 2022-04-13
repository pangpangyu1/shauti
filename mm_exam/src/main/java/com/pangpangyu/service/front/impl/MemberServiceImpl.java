package com.pangpangyu.service.front.impl;

import com.pangpangyu.dao.front.MemberDao;
import com.pangpangyu.domain.front.Member;
import com.pangpangyu.factory.MapperFactory;
import com.pangpangyu.service.front.MemberService;
import com.pangpangyu.utils.JedisUtils;
import com.pangpangyu.utils.MD5Util;
import com.pangpangyu.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.UUID;

public class MemberServiceImpl implements MemberService {
    @Override
    public boolean register(Member member) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            MemberDao memberDao = MapperFactory.getMapper(sqlSession, MemberDao.class);
            //id使用UUID的生成策略来获取
            String id = UUID.randomUUID().toString();
            member.setId(id);
            member.setRegisterDate(new Date());
            member.setState("1");
            member.setPassword(MD5Util.md5(member.getPassword()));
            //3.调用Dao层操作
            int row = memberDao.save(member);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
            return row > 0;
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

    @Override
    public Member login(String email, String password) {
        SqlSession sqlSession = null;
        try{
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            MemberDao memberDao = MapperFactory.getMapper(sqlSession, MemberDao.class);
            password = MD5Util.md5(password);
            Member member = memberDao.findByEmailAndPwd(email,password);

//            System.out.println(member.getNickName());

            //3.将登录人的信息保存到redis中
            Jedis jedis = JedisUtils.getResource();
            //使用登录人的id作为key，设定3600秒的过期时间，value值待定
            jedis.setex(member.getId(),3600,member.getNickName());
            jedis.close();

            return member;
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
    public String getLoginInfo(String id) {
        //使用给定的id去查找redis中是否存在当前数据
        Jedis jedis = JedisUtils.getResource();
        String nickName = jedis.get(id);
        jedis.close();
        return nickName;
    }

    @Override
    public boolean logout(String id) {
        Jedis jedis = JedisUtils.getResource();
        Long row = jedis.del(id);
        jedis.close();
        return row > 0 ;
    }
}
