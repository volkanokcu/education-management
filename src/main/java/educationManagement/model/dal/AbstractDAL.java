package educationManagement.model.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import educationManagement.model.entity.GenericEntity;

//CLASS
//Tüm dal classlarımız bu classımızı miras alacak. Bu classımızda GenericDAL'ı implement ederek tüm alt classlarada aynı işlevi kazandıracak.
//Clasımız tipini Generic Entityden miras alan nesneler olarak sınırladık. <E extends GenericEntity>
////METHODS
////-Methodlarımızın izinleri protected olacak. Bu package dışında kullanılmayacak.
public abstract class AbstractDAL<E extends GenericEntity> implements GenericDAL<E> { 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6386250074422972046L;
	//Database bağlantısı için gerekli değişkenler belirledik.
	private static final String DB_URL = "jdbc:mysql://54.67.123.195:3306/educationmanagement";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "Passw0rd";
	static Connection connection;
	
	//Connectionımızı static blok içinde tanımladık.
	static {
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Hataları tek bir methodla yakalamak için SQLException tipinde veri alan static bir method oluşturuyoruz.
	public static void printSQLException(SQLException e) {
		System.err.println("Error Message: " + e.getMessage());
		System.err.println("Error Code: " + e.getErrorCode()); //Retrieves the vendor-specific exception code for this SQLException object.
		System.err.println("SQL State: " + e.getSQLState()); //Retrieves the SQLState for this SQLException object.
	}
	
	abstract E createEntity(ResultSet resultSet) throws SQLException;
	
	public E getByTcNo(String tcNo) {
		return null;
	}
	
	public E isUserExist(int id, String password) {
		return null;
	}
	
	public boolean insertLessons(E entity) {
		return false;
	}
	
}