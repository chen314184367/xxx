package COM.ZY.MODEL;

import java.sql.Date;

import lombok.Data;


@Data
public class emp {
   
	private Integer empno;
	private  String ename;
	private  Integer sal;
	private  Date createDate;
	private  Integer deptno;
//	private  dept dept;
}
