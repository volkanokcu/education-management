package educationManagement.model.entity;

import java.io.Serializable;

public interface GenericEntity extends Serializable {
	
	final static String ADMINDEPERTMENT = "Bilgi İşlem";
	final static String TEACHERDEPERTMENT = "Akademisyen";

	public abstract int getId();
	
	public abstract void setId(int id);

}
