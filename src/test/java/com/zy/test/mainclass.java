package com.zy.test;

import java.sql.Date;
import java.util.List;

import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.Test;

import COM.ZY.DAO.IMP.userDaoImp;
import COM.ZY.MODEL.user;

public class mainclass {

	@Test
	public void update(){
		user u =new user();
		u.setId(1);
		u.setName("红儿");
		u.setSal(13000);
		u.setCreateDate(new Date(System.currentTimeMillis()));
		userDaoImp dao = new userDaoImp();
		dao.update(u);
		System.out.println();
		/*
		 * 
		 * */
	}
	
	@Test
	public void add(){
		user u =new user();
       
		u.setName("黑儿");
		u.setSal(5000);
		u.setCreateDate(new Date(System.currentTimeMillis()));
		userDaoImp dao = new userDaoImp();
		dao.save(u);
		
	}
	
	@Test
	public void delete(){
		user u =new user();
		u.setId(7);
		u.setName("黑儿");
		u.setSal(5000);
		u.setCreateDate(new Date(System.currentTimeMillis()));
		userDaoImp dao = new userDaoImp();
		//调用delete方法时，如果有数据会根据id来查出所有 ，再进行删除。
		dao.delete(u);
	}
	
	@Test
	public void getuser(){
		 
		userDaoImp dao = new userDaoImp();
		
	  System.out.println(dao.getUser(1));	
	}
	@Test
	public void list(){
		
		userDaoImp dao = new userDaoImp();
		
		System.out.println(dao.list());
	}

	public List<user> list01(user u){
		
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
	    String sql="select * from user where 1=1";
	if (u!=null) {
		if (u.getName()!=null) {
			sql+="and u_name="+u.getName();
		}
		if (u.getSal()!=null) {
			sql+="and u_sal=u.getSal()";
		}
		if (u.getCreateDate()!=null) {
			sql+="and u_CreateDate=u.getCreateDate()";
		}
		if (u.getId()!=null) {
			sql+="and u_Id=u.getId()";
		}
	}
	List<user> list= session.createSQLQuery(sql).list();
	return list;
	
	}
	

@Test
public void list02(){
	
	userDaoImp dao = new userDaoImp();
	
	System.out.println(dao.list02());
}

//二级缓存
@Test
public void query(){
	Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
	SessionFactory sf = cfg.buildSessionFactory();
	Session session = sf.openSession();
	List list = session.getNamedQuery("queryUser").list();
    System.out.println(list);
    
}

}
