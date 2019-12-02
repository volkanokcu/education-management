package educationManagement.model.bll;

import java.util.Collection;

import educationManagement.model.dal.AbstractDAL;
import educationManagement.model.entity.GenericEntity;

public abstract class AbstractBLL<E extends GenericEntity> implements GenericBLL<E> {
	
	private final AbstractDAL<E> abstractDAL;

	public AbstractBLL(AbstractDAL<E> abstractDAL) {
		this.abstractDAL = abstractDAL; //Polimorfizm kullanıyoruz alt classlarımız buraya AbstractDalın alt classlarını gönderiyor.
	}

	public boolean insertEntity(E entity) {
		boolean result = false;
		if (validateEntity(entity)) result = abstractDAL.insertEntity(entity);
		return result;
	}

	public boolean updateEntity(E entity) {
		boolean result = false;
		if (validateEntity(entity)) result = abstractDAL.updateEntity(entity);
		return result;
	}

	public boolean deleteEntity(E entity) {
		return abstractDAL.deleteEntity(entity);
	}

	public Collection<E> getAll() {
		return abstractDAL.getAll();
	}

	public E getById(int id) {
		return abstractDAL.getById(id);
	}
	
	public E getByTcNo(String string) {
		return abstractDAL.getByTcNo(string); 
	}
	
	public E isUserExist(int id, String password) {
		return abstractDAL.isUserExist(id, password);
	}


}
