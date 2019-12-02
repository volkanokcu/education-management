package educationManagement.model.bll;

import educationManagement.model.entity.GenericEntity;

public interface GenericBLL<E extends GenericEntity> {

	public abstract boolean validateEntity(E entity) throws IllegalArgumentException;
}
