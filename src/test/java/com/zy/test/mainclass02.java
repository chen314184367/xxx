package com.zy.test;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

import COM.ZY.MODEL.dept;
import COM.ZY.MODEL.emp;

public class mainclass02 {

	/**
	 * 
	 */
	@Test
	public void adddept(){
		Configuration cfg = new Configuration().configure("/hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		dept d= new dept();
	    
		d.setDname("����");
		d.setLoc("����");
		emp e = new emp();
		e.setSal(8000);e.setCreateDate(new Date(System.currentTimeMillis()));
		e.setEname("���");
		
		emp e2 = new emp();
		e2.setSal(9000);e2.setCreateDate(new Date(System.currentTimeMillis()));
		e2.setEname("��ɫ");
		Set<emp>  s= new HashSet<emp>();
		s.add(e);s.add(e2);
		d.setEmps(s);
		
		session.save(d);
//		session.save(e);
//		session.save(e2);	
		session.getTransaction().commit();
		session.close();
		sf.close();		
	}
	
	@Test
	public void getemp(){
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		dept d = session.get(dept.class,3);
		System.out.println(d);
	
		session.close();
		sf.close();		
	}
	
	@Test
	public void delete(){
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		
		session.beginTransaction();
		dept d = new dept();
		d.setDeptno(3);
		session.delete(d);
		System.out.println(d);
		
		session.getTransaction().commit();
		session.close();
		sf.close();	
	}
	
	@Test
	public void search(){
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		//������   ����������� �����Ӿ����� join ǰ������һ��left 
		String hql="from dept d  join d.emps ";
		//����������  �����Ϊ����
		//String hql01="from dept d join fetch d.emps ";
		 List<Object> list = session.createQuery(hql).list();
		 // List<dept> list = session.createQuery(hql).list(); 
		 //���������ӽ��ս����
		 for (Object object : list) {
			System.out.println(object);
		}
		
		session.getTransaction().commit();
		session.close();
		sf.close();
		
	}
	
	@Test
	public void search01(){
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
	  session.beginTransaction();
	  int number=0;
	  for (int i = 0; i < 100000; i++) {
		dept d =new dept();
		d.setDname(i+"jaj");
		session.save(d);
		number++;
		if (number%50==0) {
			session.flush();
			session.clear();
		}
	}
		
		session.getTransaction().commit();
		session.close();
		sf.close();
		
	}
}
