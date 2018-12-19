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

		// 加载配置文件
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		// 获得session工厂
		SessionFactory sf = cfg.buildSessionFactory();
		// 3.获取session
		Session session = sf.openSession();
		// 4.通过session 获取事务管理器
		Transaction tr = session.beginTransaction();
		// 5.通过session 通过save实例化
//		session.save(user);
//		session.saveOrUpdate(user);
		session.persist(user);
		// 6.提交事务
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
		Session session = sf.getCurrentSession();// 从当前上下文中获取的session。
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
		// 加载配置文件
		Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
		// 获得session工厂
		SessionFactory sf = cfg.buildSessionFactory();
		// 3.获取session
		Session session = sf.openSession();
		// 4.通过session 获取事务管理器
		Transaction tr = session.beginTransaction();
		// 5.通过session 通过save实例化
		Query qy = session.createQuery("from user");
		List<user> list = qy.list();
		// 6.提交事务
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
		query.setFirstResult(0);//从第几个数据开始查询
		query.setMaxResults(6);//查询结果有几条。
		List<user> list = query.list();
		String hql2="select count(u) from user u";
		Query query2 =session.createQuery(hql2);
		Long sum =(Long)query2.uniqueResult();
		System.out.println("总条数"+sum);
		session.close();
		return list;
	}



}
