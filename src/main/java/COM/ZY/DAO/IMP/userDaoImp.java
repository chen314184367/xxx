package COM.ZY.DAO.IMP;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import COM.ZY.DAO.INTERFACE.userDao;
import COM.ZY.MODEL.user;

public class userDaoImp implements userDao {

	public void save(user user) {

		// ���������ļ�
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		// ���session����
		SessionFactory sf = cfg.buildSessionFactory();
		// 3.��ȡsession
		Session session = sf.openSession();
		// 4.ͨ��session ��ȡ���������
		Transaction tr = session.beginTransaction();
		// 5.ͨ��session ͨ��saveʵ����
//		session.save(user);
//		session.saveOrUpdate(user);
		session.persist(user);
		// 6.�ύ����
		tr.commit();
		session.close();
		sf.close();
		// session.saveOrupdate(null);
		// session.persist(null);
		// session.load(object.class,null);
	}

	@SuppressWarnings("deprecation")
	public void update(user user) {
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.getCurrentSession();// �ӵ�ǰ�������л�ȡ��session��
		Transaction tr = session.beginTransaction();
		//session.update(user);	
	  Criteria criteria = session.createCriteria(user.getClass());
		 
		tr.commit();
		session.close();
		sf.close();
	}

	public void delete(user user) {
		// TODO Auto-generated method stub
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		session.delete(user);
		tr.commit();
		session.close();
		sf.close();
	}

	public user getUser(Integer id) {
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();

		user user = session.get(user.class, id);
		tr.commit();
		session.close();
		sf.close();

		return user;
	}

	public java.util.List<user> list() {
		// ���������ļ�
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		// ���session����
		SessionFactory sf = cfg.buildSessionFactory();
		// 3.��ȡsession
		Session session = sf.openSession();
		// 4.ͨ��session ��ȡ���������
		Transaction tr = session.beginTransaction();
		// 5.ͨ��session ͨ��saveʵ����
		Query qy = session.createQuery("from user");
		List<user> list = qy.list();
		// 6.�ύ����
		tr.commit();
		session.close();
		sf.close();
		return list;
	}
	
public List<user> list02(){
		
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		String hql="select u from user u";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);//�ӵڼ������ݿ�ʼ��ѯ
		query.setMaxResults(6);//��ѯ����м�����
		List<user> list = query.list();
		String hql2="select count(u) from user u";
		Query query2 =session.createQuery(hql2);
		Long sum =(Long)query2.uniqueResult();
		System.out.println("������"+sum);
		session.close();
		return list;
	}



}
