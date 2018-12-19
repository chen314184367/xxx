package COM.ZY.MODEL;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class dept {
    
	private Integer deptno;
	private  String dname;
	private  String loc;
	private  Set<emp> emps= new HashSet<emp>();
}
