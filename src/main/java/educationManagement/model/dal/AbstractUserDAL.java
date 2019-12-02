package educationManagement.model.dal;

import educationManagement.model.entity.User;

public abstract class AbstractUserDAL<E extends User> extends AbstractDAL<E> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6076268477807727887L;
	
	
	public abstract E getByTcNo(String tcNo);
	

	public abstract E isUserExist(int id, String password);

}
