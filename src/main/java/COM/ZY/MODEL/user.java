package COM.ZY.MODEL;

import java.sql.Date;

import lombok.Data;

@Data
public class user {
  
	private Integer id;
	private String name;
	private Integer sal;
	private Date createDate;
//	private String sex;
}
