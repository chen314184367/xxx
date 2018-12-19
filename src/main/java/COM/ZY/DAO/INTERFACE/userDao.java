package COM.ZY.DAO.INTERFACE;

import java.util.List;

import COM.ZY.MODEL.user;

public interface userDao {

	
	void  save(user user);
	void  update(user user);
	void  delete(user user);
	user getUser(Integer id);
	List<user> list();

}
