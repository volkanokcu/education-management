package educationManagement.model.dal;

import java.io.Serializable;
import java.util.Collection;

import educationManagement.model.entity.GenericEntity;

//INTERFACE
//-DAL packageımız databse sorugu işlemlerini yapar. GenericDAL interfaceimiz ile alt classlarımızın yapılabilirliklerini belirliyoruz.
//-Interfaceimizin tipini GenericEntityden miras alan nesneler olarak belirledik <E extends GenericEntity>
////METHODS
////-Methodlarımız yukarda tanımladığımız E türünden nesnelerle çalışmalı. 
	interface GenericDAL<E extends GenericEntity> extends Serializable { 

	public abstract boolean insertEntity(E entity); 
	
	public abstract boolean updateEntity(E entity);
	
	public abstract boolean deleteEntity(E entity);

	public abstract Collection<E> getAll();

	public abstract E getById(int id);
	
}
