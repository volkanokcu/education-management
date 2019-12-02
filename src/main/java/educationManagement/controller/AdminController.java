package educationManagement.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import educationManagement.model.DBContext;
import educationManagement.model.entity.Admin;
import educationManagement.model.entity.Chapter;
import educationManagement.model.entity.College;
import educationManagement.model.entity.Contact;
import educationManagement.model.entity.Department;
import educationManagement.model.entity.DepartmentChapter;
import educationManagement.model.entity.Lesson;
import educationManagement.model.entity.Student;
import educationManagement.model.entity.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

public class AdminController implements Initializable{

	ObservableList<College> collegeList;
	
	ObservableList<Chapter> chapterList;
	
	ObservableList<Department> departmentList;
	
	ObservableList<DepartmentChapter> departmentChapterList;
	
	ObservableList<Lesson> lessonList;
	
	Collection<Chapter> selectedChapters;
	
	Collection<DepartmentChapter> selectedDepartmentChapters;
	
	@FXML
	private Button studentQueryIdBTN;
		
	@FXML
	private Label studentIdLBL;

	@FXML
	private TextField studentQueryIdTF;

	@FXML
    private Button studentQueryTcNoBTN;

    @FXML
    private TextField studentQueryTcNoTF;

    @FXML
    private TextField studentLastNameTF;

    @FXML
    private ComboBox<College> studentCollegeCM;

    @FXML
    private TextField studentTcNoTF;

    @FXML
    private TextField studentFirstNameTF;

    @FXML
    private Button studentClearBTN;

    @FXML
    private Button studentSaveBTN;

    @FXML
    private TextField studentPhoneTF;

    @FXML
    private TextField studentMailTF;

    @FXML
    private TextField studentAddressTF;

    @FXML
    private TextField studentDistrictTF;

    @FXML
    private TextField studentCityTF;

    @FXML
    private TextField studentZipCodeTF;

    @FXML
    private ComboBox<Chapter> studentChapterCM;

    @FXML
    private Button studentDeleteBTN;
    
    @FXML
    private Button studentUpdateBTN;
    
    @FXML
    private Button teacherQueryIdBTN;

    @FXML
    private TextField teacherQueryIdTF;

    @FXML
    private Button teacherQueryTcNoBTN;

    @FXML
    private TextField teacherQueryTcNoTF;

    @FXML
    private TextField teacherLastNameTF;
    
    @FXML
    private Label teacherIdLBL;

    @FXML
    private ComboBox<College> teacherCollegeCM;

    @FXML
    private TextField teacherTcNoTF;

    @FXML
    private TextField teacherFirstNameTF;

    @FXML
    private ComboBox<Department> teacherDepartmentCM;

    @FXML
    private Button teacherClearBTN;

    @FXML
    private Button teacherSaveBTN;
    
    @FXML
    private Button teacherDeleteLessonBTN;

    @FXML
    private TextField teacherPhoneTF;

    @FXML
    private TextField teacherMailTF;

    @FXML
    private TextField teacherAddressTF;

    @FXML
    private TextField teacherDistrictTF;

    @FXML
    private TextField teacherCityTF;

    @FXML
    private TextField teacherZipCodeTF;

    @FXML
    private ComboBox<DepartmentChapter> teacherDepChapterCM;

    @FXML
    private ChoiceBox<Lesson> teacherLessonsCM;

    @FXML
    private ListView<Lesson> teacherLessonsLW;

    @FXML
    private Button teacherAddLessonBTN;

    @FXML
    private Button teacherDeleteBTN;
    
    @FXML 
    private Button teacherUpdateBTN;

    @FXML
    private Button adminQueryIdBTN;

    @FXML
    private TextField adminQueryIdTF;

    @FXML
    private Button adminQueryTcNoBTN;

    @FXML
    private TextField adminQueryTcNoTF;

    @FXML
    private TextField adminLastNameTF;

    @FXML
    private ComboBox<College> adminCollegeCM;

    @FXML
    private TextField adminTcNoTF;

    @FXML
    private TextField adminFirstNameTF;

    @FXML
    private ComboBox<Department> adminDepartmentCM;

    @FXML
    private Button adminClearBTN;
    
    @FXML
    private Label adminIdLBL;

    @FXML
    private Button adminSaveBTN;

    @FXML
    private TextField adminPhoneTF;

    @FXML
    private TextField adminMailTF;

    @FXML
    private TextField adminAddressTF;

    @FXML
    private TextField adminDistrictTF;

    @FXML
    private TextField adminCityTF;

    @FXML
    private TextField adminZipCodeTF;

    @FXML
    private ComboBox<DepartmentChapter> adminDepChapterCM;

    @FXML
    private Button adminDeleteBTN;
    
    @FXML
    private Button adminUpdateBTN;
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadForms();
		teacherLessonsLW.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}
	
	//STUDENT BUTTON ACTIONEVENT

    @FXML
    void studentButtonAction(ActionEvent e) {
    	if (e.getSource() == studentSaveBTN) 
    	{	
    		if (validateStudentFields()) {
        		boolean result = false;
        		Date startDate = new Date();
    			College college = new College();
    			college = studentCollegeCM.getValue();
    			Chapter chapter = new Chapter();
    			chapter = studentChapterCM.getValue();
    			Contact contact = new Contact();
    			contact.setPhone(studentPhoneTF.getText());
    			contact.setMail(studentMailTF.getText());
    			contact.setAddress(studentAddressTF.getText());
    			contact.setDistrict(studentDistrictTF.getText());
    			contact.setCity(studentCityTF.getText());
    			contact.setZipCode(Integer.parseInt(studentZipCodeTF.getText()));
    			Student entity = new Student(studentTcNoTF.getText(), startDate, studentFirstNameTF.getText(), studentLastNameTF.getText(), college, contact, chapter);
				result = DBContext.getInstance().students().insertEntity(entity);
				if (result) {
					clearStudentForm();
					showAlert("Öğrenci Eklendi", "Öğrenci İşlemleri", AlertType.CONFIRMATION);
				} else
					showAlert("Kayıt Başarısız", "Öğrenci İşlemleri", AlertType.ERROR);
			}
		}
    	else if (e.getSource() == studentUpdateBTN) 
    	{
    		if (validateStudentFields()) {
    			boolean result = false;
        		Date startDate = new Date();
    			College college = new College();
    			college = studentCollegeCM.getValue();
    			Chapter chapter = new Chapter();
    			chapter = studentChapterCM.getValue();
    			Contact contact = new Contact();
    			contact.setPhone(studentPhoneTF.getText());
    			contact.setMail(studentMailTF.getText());
    			contact.setAddress(studentAddressTF.getText());
    			contact.setDistrict(studentDistrictTF.getText());
    			contact.setCity(studentCityTF.getText());
    			contact.setZipCode(Integer.parseInt(studentZipCodeTF.getText()));
    			Student entity = new Student(studentTcNoTF.getText(), startDate, studentFirstNameTF.getText(), studentLastNameTF.getText(), college, contact, chapter);
    			entity.setId(Integer.parseInt(studentIdLBL.getText()));
    			result = DBContext.getInstance().students().updateEntity(entity);
    			if (result) {
    				clearStudentForm();
    				showAlert("Öğrenci Güncellendi", "Öğrenci İşlemleri", AlertType.CONFIRMATION);
    			} else 
    				showAlert("Kayıt Başarısız", "Öğrenci İşlemleri", AlertType.ERROR);
			}
		}
    	else if (e.getSource() == studentDeleteBTN) 
    	{
    		boolean result = false;
			Student entity = new Student();
			entity.setId(Integer.parseInt(studentIdLBL.getText()));
			result = DBContext.getInstance().students().deleteEntity(entity);
			if (result) {
				clearStudentForm();
				showAlert("Öğrenci Güncellendi", "Öğrenci İşlemleri", AlertType.CONFIRMATION);
			} else showAlert("Kayıt Başarısız", "Öğrenci İşlemleri", AlertType.ERROR);
    	}	
    	else if (e.getSource() == studentQueryIdBTN) 
    	{
			Student student = null;
			student = DBContext.getInstance().students().getById(Integer.parseInt(studentQueryIdTF.getText()));
			if (student!=null) {
				studentTcNoTF.setText(student.getTcNo());
				studentFirstNameTF.setText(student.getFirstName());
				studentLastNameTF.setText(student.getLastName());
				studentCollegeCM.getSelectionModel().select(student.getCollege());
				studentChapterCM.getSelectionModel().select(student.getChapter());
				studentPhoneTF.setText(student.getContact().getPhone());
				studentMailTF.setText(student.getContact().getMail());
				studentAddressTF.setText(student.getContact().getAddress());
				studentDistrictTF.setText(student.getContact().getCity());
				studentCityTF.setText(student.getContact().getCity());
				studentZipCodeTF.setText(Integer.toString(student.getContact().getZipCode()));
				studentDeleteBTN.setDisable(false);
				studentUpdateBTN.setDisable(false);
				studentIdLBL.setText(Integer.toString(student.getId()));
				studentSaveBTN.setDisable(true);
			} else showAlert("Böyle Bir Kullanıcı Bulunamadı", "Öğrenci İşlemleri", AlertType.ERROR);
		}
    	else if (e.getSource() == studentQueryTcNoBTN) 
    	{
    		Student student = null;
			student = DBContext.getInstance().students().getByTcNo(studentQueryTcNoTF.getText());
			if (student!=null) {
				studentTcNoTF.setText(student.getTcNo());
				studentFirstNameTF.setText(student.getFirstName());
				studentLastNameTF.setText(student.getLastName());
				studentCollegeCM.getSelectionModel().select(student.getCollege());
				studentChapterCM.getSelectionModel().select(student.getChapter());
				studentPhoneTF.setText(student.getContact().getPhone());
				studentMailTF.setText(student.getContact().getMail());
				studentAddressTF.setText(student.getContact().getAddress());
				studentDistrictTF.setText(student.getContact().getCity());
				studentCityTF.setText(student.getContact().getCity());
				studentZipCodeTF.setText(Integer.toString(student.getContact().getZipCode()));
				studentDeleteBTN.setDisable(false);
				studentUpdateBTN.setDisable(false);
				studentIdLBL.setText(Integer.toString(student.getId()));
				studentSaveBTN.setDisable(true);
			} else showAlert("Böyle Bir Kullanıcı Bulunamadı", "Öğrenci İşlemleri", AlertType.ERROR);
		}
    	else if (e.getSource() == studentClearBTN) 
    	{
			clearStudentForm();
		}
    }
    
   //TEACHER BUTTON ACTIONEVENT
    
    @FXML
    void teacherButtonAction(ActionEvent e) {
    	if (e.getSource() == teacherSaveBTN) 
    	{	
    		if (validateTeacherFields()) {
    			boolean result = false;
        		Date startDate = new Date();
    			College college = new College();
    			college = teacherCollegeCM.getValue();
    			Contact contact = new Contact();
    			List<Lesson> lessonList = teacherLessonsLW.getItems();
    			Set<Lesson> lessons = new TreeSet<Lesson>(lessonList);
    			contact.setPhone(teacherPhoneTF.getText());
    			contact.setMail(teacherMailTF.getText());
    			contact.setAddress(teacherAddressTF.getText());
    			contact.setDistrict(teacherDistrictTF.getText());
    			contact.setCity(teacherCityTF.getText());
    			contact.setZipCode(Integer.parseInt(teacherZipCodeTF.getText()));
    			Department department = teacherDepartmentCM.getValue();
    			DepartmentChapter departmentChapter = teacherDepChapterCM.getValue();
    			Teacher entity = new Teacher(teacherTcNoTF.getText(), startDate, teacherFirstNameTF.getText(), teacherLastNameTF.getText(), college, contact, department, departmentChapter, lessons);
    			result = DBContext.getInstance().teachers().insertEntity(entity);
    			if (result) {
    				clearTeacherForm();
    				showAlert("Öğretmen Eklendi", "Öğretmen Formu", AlertType.CONFIRMATION);
    			} else 
    				showAlert("Kayıt Başarısız", "Öğretmen Girişi", AlertType.ERROR);
			}
		}
    	else if (e.getSource() == teacherUpdateBTN) 
    	{
    		if (validateTeacherFields()) {
    			boolean result = false;
        		Date startDate = new Date();
    			College college = new College();
    			college = teacherCollegeCM.getValue();
    			Contact contact = new Contact();
    			List<Lesson> lessonList = teacherLessonsLW.getItems();
    			Set<Lesson> lessons = new TreeSet<Lesson>(lessonList);
    			contact.setPhone(teacherPhoneTF.getText());
    			contact.setMail(teacherMailTF.getText());
    			contact.setAddress(teacherAddressTF.getText());
    			contact.setDistrict(teacherDistrictTF.getText());
    			contact.setCity(teacherCityTF.getText());
    			contact.setZipCode(Integer.parseInt(teacherZipCodeTF.getText()));
    			Department department = teacherDepartmentCM.getValue();
    			DepartmentChapter departmentChapter = teacherDepChapterCM.getValue();
    			Teacher entity = new Teacher(teacherTcNoTF.getText(), startDate, teacherFirstNameTF.getText(), teacherLastNameTF.getText(), college, contact, department, departmentChapter, lessons);
    			entity.setId(Integer.parseInt(teacherIdLBL.getText()));
    			result = DBContext.getInstance().teachers().updateEntity(entity);
    			if (result) {
    				clearTeacherForm();
    				showAlert("Öğretmen Güncellendi", "Öğretmen İşlemleri", AlertType.CONFIRMATION);
    			} else 
    				showAlert("Kayıt Başarısız", "Öğretmen İşlemleri", AlertType.ERROR);
			}
		}
    	else if (e.getSource() == teacherDeleteBTN) 
    	{
    		boolean result;
    		Teacher entity = new Teacher();
			entity.setId(Integer.parseInt(teacherIdLBL.getText()));
			result = DBContext.getInstance().teachers().deleteEntity(entity);
			if (result) {
				clearTeacherForm();
				showAlert("Öğretmen Silindi", "Öğretmen İşlemleri", AlertType.CONFIRMATION);
			} else showAlert("Silme İşlemi Başarısız", "Öğretmen İşlemleri", AlertType.ERROR);
    	}	
    	else if (e.getSource() == teacherQueryIdBTN) 
    	{
			Teacher teacher = null;
			teacher = DBContext.getInstance().teachers().getById(Integer.parseInt(teacherQueryIdTF.getText()));
			if (teacher!=null) {
				teacherTcNoTF.setText(teacher.getTcNo());
				teacherFirstNameTF.setText(teacher.getFirstName());
				teacherLastNameTF.setText(teacher.getLastName());
				teacherCollegeCM.getSelectionModel().select(teacher.getCollege());
				teacherDepartmentCM.getSelectionModel().select(teacher.getDepartment());
				teacherDepChapterCM.getSelectionModel().select(teacher.getDepartmentChapter());
				teacherLessonsLW.getItems().addAll(new ArrayList<Lesson>(teacher.getLessons()));
				teacherLessonsCM.getItems().removeAll(new ArrayList<Lesson>(teacher.getLessons()));
				teacherPhoneTF.setText(teacher.getContact().getPhone());
				teacherMailTF.setText(teacher.getContact().getMail());
				teacherAddressTF.setText(teacher.getContact().getAddress());
				teacherDistrictTF.setText(teacher.getContact().getCity());
				teacherCityTF.setText(teacher.getContact().getCity());
				teacherZipCodeTF.setText(Integer.toString(teacher.getContact().getZipCode()));
				teacherDeleteBTN.setDisable(false);
				teacherUpdateBTN.setDisable(false);
				teacherIdLBL.setText(Integer.toString(teacher.getId()));
				teacherSaveBTN.setDisable(true);
			} else showAlert("Böyle Bir Kullanıcı Bulunamadı", "Öğretmen İşlemleri", AlertType.ERROR);
		}
    	else if (e.getSource() == teacherQueryTcNoBTN) 
    	{
    		Teacher teacher = null;
			teacher = DBContext.getInstance().teachers().getByTcNo(teacherQueryTcNoTF.getText());
			if (teacher!=null) {
				teacherTcNoTF.setText(teacher.getTcNo());
				teacherFirstNameTF.setText(teacher.getFirstName());
				teacherLastNameTF.setText(teacher.getLastName());
				teacherCollegeCM.getSelectionModel().select(teacher.getCollege());
				teacherDepartmentCM.getSelectionModel().select(teacher.getDepartment());
				teacherDepChapterCM.getSelectionModel().select(teacher.getDepartmentChapter());
				teacherLessonsLW.getItems().addAll(new ArrayList<Lesson>(teacher.getLessons()));
				teacherLessonsCM.getItems().removeAll(new ArrayList<Lesson>(teacher.getLessons()));
				teacherPhoneTF.setText(teacher.getContact().getPhone());
				teacherMailTF.setText(teacher.getContact().getMail());
				teacherAddressTF.setText(teacher.getContact().getAddress());
				teacherDistrictTF.setText(teacher.getContact().getCity());
				teacherCityTF.setText(teacher.getContact().getCity());
				teacherZipCodeTF.setText(Integer.toString(teacher.getContact().getZipCode()));
				teacherDeleteBTN.setDisable(false);
				teacherUpdateBTN.setDisable(false);
				teacherIdLBL.setText(Integer.toString(teacher.getId()));
				teacherSaveBTN.setDisable(true);
			} else showAlert("Böyle Bir Kullanıcı Bulunamadı", "Öğretmen İşlemleri", AlertType.ERROR);
		}
    	else if (e.getSource() == teacherAddLessonBTN) 
    	{
			if (!teacherLessonsCM.getItems().isEmpty()) {
				teacherLessonsLW.getItems().add(teacherLessonsCM.getValue());
				teacherLessonsCM.getItems().remove(teacherLessonsCM.getSelectionModel().getSelectedItem());
				teacherLessonsCM.getSelectionModel().selectFirst();
				teacherLessonsLW.getSelectionModel().selectNext();
				teacherLessonsLW.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			}
		}
    	else if (e.getSource() == teacherDeleteLessonBTN) 
    	{
    		if (!teacherLessonsLW.getItems().isEmpty()) {
        		teacherLessonsCM.getItems().add(teacherLessonsLW.getSelectionModel().getSelectedItem());
        		teacherLessonsCM.getItems().sort(new Comparator<Lesson>() {
    				@Override
    				public int compare(Lesson o1, Lesson o2) {
    					return o1.getLessonName().compareToIgnoreCase(o2.getLessonName());
    				}
    			});
    			teacherLessonsLW.getItems().remove(teacherLessonsLW.getSelectionModel().getSelectedIndex());
    			teacherLessonsLW.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			}
		}
    	else if (e.getSource() == teacherClearBTN) 
    	{
			clearTeacherForm();
		}
    }
    
    //ADMİN BUTTON ACTIONEVENT
    
    @FXML
    void adminButtonAction(ActionEvent e) {
    	if (e.getSource() == adminSaveBTN) 
    	{	
    		if (validateAdminFields()) {
    			boolean result = false;
        		Date startDate = new Date();
    			College college = new College();
    			college = adminCollegeCM.getValue();
    			Contact contact = new Contact();
    			contact.setPhone(adminPhoneTF.getText());
    			contact.setMail(adminMailTF.getText());
    			contact.setAddress(adminAddressTF.getText());
    			contact.setDistrict(adminDistrictTF.getText());
    			contact.setCity(adminCityTF.getText());
    			contact.setZipCode(Integer.parseInt(adminZipCodeTF.getText()));
    			Department department = adminDepartmentCM.getValue();
    			DepartmentChapter departmentChapter = adminDepChapterCM.getValue();
    			Admin entity = new Admin(adminTcNoTF.getText(), startDate, adminFirstNameTF.getText(), adminLastNameTF.getText(), college, contact, department, departmentChapter, "5");
    			result = DBContext.getInstance().admins().insertEntity(entity);
    			if (result) {
    				clearAdminForm();
    				showAlert("Admin Eklendi", "Admin İşlemleri", AlertType.CONFIRMATION);
    			} else 
    				showAlert("Kayıt Başarısız", "Admin İşlemleri", AlertType.ERROR);
			}
		}
    	else if (e.getSource() == adminUpdateBTN) 
    	{
    		if (validateAdminFields()) {
    			boolean result = false;
        		Date startDate = new Date();
    			College college = new College();
    			college = adminCollegeCM.getValue();
    			Contact contact = new Contact();
    			contact.setPhone(adminPhoneTF.getText());
    			contact.setMail(adminMailTF.getText());
    			contact.setAddress(adminAddressTF.getText());
    			contact.setDistrict(adminDistrictTF.getText());
    			contact.setCity(adminCityTF.getText());
    			contact.setZipCode(Integer.parseInt(adminZipCodeTF.getText()));
    			Department department = adminDepartmentCM.getValue();
    			DepartmentChapter departmentChapter = adminDepChapterCM.getValue();
    			Admin entity = new Admin(adminTcNoTF.getText(), startDate, adminFirstNameTF.getText(), adminLastNameTF.getText(), college, contact, department, departmentChapter, "5");
    			entity.setId(Integer.parseInt(adminIdLBL.getText()));
    			result = DBContext.getInstance().admins().updateEntity(entity);
    			if (result) {
    				clearAdminForm();
    				showAlert("Admin Güncellendi", "Admin İşlemleri", AlertType.CONFIRMATION);
    			} else 
    				showAlert("Kayıt Başarısız", "Admin İşlemleri", AlertType.ERROR);
			}
		}
    	else if (e.getSource() == adminDeleteBTN) 
    	{
    		boolean result;
    		Admin entity = new Admin();
			entity.setId(Integer.parseInt(adminIdLBL.getText()));
			result = DBContext.getInstance().admins().deleteEntity(entity);
			if (result) {
				clearTeacherForm();
				showAlert("Admin Silindi", "Admin İşlemleri", AlertType.CONFIRMATION);
			} else showAlert("Silme İşlemi Başarısız", "Admin İşlemleri", AlertType.ERROR);
    	}	
    	else if (e.getSource() == adminQueryIdBTN) 
    	{
			Admin admin = null;
			admin = DBContext.getInstance().admins().getById(Integer.parseInt(adminQueryIdTF.getText()));
			if (admin!=null) {
				adminTcNoTF.setText(admin.getTcNo());
				adminFirstNameTF.setText(admin.getFirstName());
				adminLastNameTF.setText(admin.getLastName());
				adminCollegeCM.getSelectionModel().select(admin.getCollege());
				adminDepartmentCM.getSelectionModel().select(admin.getDepartment());
				adminDepChapterCM.getSelectionModel().select(admin.getDepartmentChapter());
				adminPhoneTF.setText(admin.getContact().getPhone());
				adminMailTF.setText(admin.getContact().getMail());
				adminAddressTF.setText(admin.getContact().getAddress());
				adminDistrictTF.setText(admin.getContact().getCity());
				adminCityTF.setText(admin.getContact().getCity());
				adminZipCodeTF.setText(Integer.toString(admin.getContact().getZipCode()));
				adminDeleteBTN.setDisable(false);
				adminUpdateBTN.setDisable(false);
				adminIdLBL.setText(Integer.toString(admin.getId()));
				adminSaveBTN.setDisable(true);
			} else showAlert("Böyle Bir Kullanıcı Bulunamadı", "Admin İşlemleri", AlertType.ERROR);
		}
    	else if (e.getSource() == adminQueryTcNoBTN) 
    	{
    		Admin admin = null;
			admin = DBContext.getInstance().admins().getByTcNo(adminQueryTcNoTF.getText());
			if (admin!=null) {
				adminTcNoTF.setText(admin.getTcNo());
				adminFirstNameTF.setText(admin.getFirstName());
				adminLastNameTF.setText(admin.getLastName());
				adminCollegeCM.getSelectionModel().select(admin.getCollege());
				adminDepartmentCM.getSelectionModel().select(admin.getDepartment());
				adminDepChapterCM.getSelectionModel().select(admin.getDepartmentChapter());
				adminPhoneTF.setText(admin.getContact().getPhone());
				adminMailTF.setText(admin.getContact().getMail());
				adminAddressTF.setText(admin.getContact().getAddress());
				adminDistrictTF.setText(admin.getContact().getCity());
				adminCityTF.setText(admin.getContact().getCity());
				adminZipCodeTF.setText(Integer.toString(admin.getContact().getZipCode()));
				adminDeleteBTN.setDisable(false);
				adminUpdateBTN.setDisable(false);
				adminIdLBL.setText(Integer.toString(admin.getId()));
				adminSaveBTN.setDisable(true);
			} else showAlert("Böyle Bir Kullanıcı Bulunamadı", "Admin İşlemleri", AlertType.ERROR);
		}
    	else if (e.getSource() == adminClearBTN) 
    	{
			clearAdminForm();
		}
    }
	
	@FXML
    private void changeCM(ActionEvent e) {
		if (e.getSource() == studentCollegeCM) {
			selectedChapters = studentCollegeCM.getValue().getChapters(); 
			chapterList = FXCollections.observableArrayList(selectedChapters);
			studentChapterCM.setItems(chapterList);
			studentChapterCM.getSelectionModel().selectFirst();
		} else if (e.getSource() == teacherDepartmentCM) {
			selectedDepartmentChapters = teacherDepartmentCM.getValue().getDepartmentChapters();
			departmentChapterList = FXCollections.observableArrayList(selectedDepartmentChapters);
			teacherDepChapterCM.setItems(departmentChapterList);
			teacherDepChapterCM.getSelectionModel().selectFirst();
		} else if (e.getSource() == adminDepartmentCM) {
			selectedDepartmentChapters = adminDepartmentCM.getValue().getDepartmentChapters();
			departmentChapterList = FXCollections.observableArrayList(selectedDepartmentChapters);
			adminDepChapterCM.setItems(departmentChapterList);
			adminDepChapterCM.getSelectionModel().selectFirst();
		}
    }
	
	private void loadForms() {
		Collection<College> colleges = DBContext.getInstance().colleges().getAll();
		collegeList = FXCollections.observableArrayList(colleges);
		studentCollegeCM.setItems(collegeList);
		studentCollegeCM.getSelectionModel().selectFirst();
		teacherCollegeCM.setItems(collegeList);
		teacherCollegeCM.getSelectionModel().selectFirst();
		adminCollegeCM.setItems(collegeList);
		adminCollegeCM.getSelectionModel().selectFirst();
		Iterator<College> iteratorCollege = colleges.iterator();
		College firstCollege = iteratorCollege.next();
		selectedChapters = firstCollege.getChapters();
		chapterList = FXCollections.observableArrayList(selectedChapters);
		studentChapterCM.setItems(chapterList);
		studentChapterCM.getSelectionModel().selectFirst();
		Collection<Department> departments = DBContext.getInstance().derpartments().getAll();
		departmentList = FXCollections.observableArrayList(departments);
		teacherDepartmentCM.setItems(departmentList);
		adminDepartmentCM.setItems(departmentList);
		teacherDepartmentCM.getSelectionModel().selectFirst();
		adminDepartmentCM.getSelectionModel().selectFirst();
		Iterator<Department> iteratorDepartments = departments.iterator();
		Department firstDepartment = iteratorDepartments.next();
		selectedDepartmentChapters = firstDepartment.getDepartmentChapters();
		departmentChapterList = FXCollections.observableArrayList(selectedDepartmentChapters);
		teacherDepChapterCM.setItems(departmentChapterList);
		adminDepChapterCM.setItems(departmentChapterList);
		teacherDepChapterCM.getSelectionModel().selectFirst();
		adminDepChapterCM.getSelectionModel().selectFirst();
		Collection<Lesson> lessons = DBContext.getInstance().lessons().getAll();
		lessonList = FXCollections.observableArrayList(lessons);
		teacherLessonsCM.setItems(lessonList);
		teacherLessonsCM.getSelectionModel().selectFirst();
	}
	
	private void clearStudentForm() {
		studentTcNoTF.setText("");
		studentFirstNameTF.setText("");
		studentLastNameTF.setText("");
		studentCollegeCM.getSelectionModel().selectFirst();
		studentChapterCM.getSelectionModel().selectFirst();
		studentPhoneTF.setText("");
		studentMailTF.setText("");
		studentAddressTF.setText("");
		studentDistrictTF.setText("");
		studentCityTF.setText("");
		studentZipCodeTF.setText("");
		studentQueryIdTF.setText("");
		studentQueryTcNoTF.setText("");
		studentIdLBL.setText("");
		studentDeleteBTN.setDisable(true);
		studentUpdateBTN.setDisable(true);
		studentSaveBTN.setDisable(false);
	}
	
	private void clearTeacherForm() {
		teacherTcNoTF.setText("");
		teacherFirstNameTF.setText("");
		teacherLastNameTF.setText("");
		teacherCollegeCM.getSelectionModel().selectFirst();
		teacherDepartmentCM.getSelectionModel().selectFirst();
		teacherDepChapterCM.getSelectionModel().selectFirst();
		teacherLessonsLW.getItems().clear();
		teacherPhoneTF.setText("");
		teacherMailTF.setText("");
		teacherAddressTF.setText("");
		teacherDistrictTF.setText("");
		teacherCityTF.setText("");
		teacherZipCodeTF.setText("");
		teacherQueryIdTF.setText("");
		teacherQueryTcNoTF.setText("");
		teacherIdLBL.setText("");
		teacherDeleteBTN.setDisable(true);
		teacherUpdateBTN.setDisable(true);
		teacherSaveBTN.setDisable(false);
	}
	
	private void clearAdminForm() {
		adminTcNoTF.setText("");
		adminFirstNameTF.setText("");
		adminLastNameTF.setText("");
		adminCollegeCM.getSelectionModel().selectFirst();
		adminDepartmentCM.getSelectionModel().selectFirst();
		adminDepChapterCM.getSelectionModel().selectFirst();
		adminPhoneTF.setText("");
		adminMailTF.setText("");
		adminAddressTF.setText("");
		adminDistrictTF.setText("");
		adminCityTF.setText("");
		adminZipCodeTF.setText("");
		adminQueryIdTF.setText("");
		adminQueryTcNoTF.setText("");
		adminIdLBL.setText("");
		adminDeleteBTN.setDisable(true);
		adminUpdateBTN.setDisable(true);
		adminSaveBTN.setDisable(false);
	}
	
	private boolean validateStudentFields() {
		boolean result = false;
		if (studentTcNoTF.getText().isEmpty() || studentFirstNameTF.getText().isEmpty() || studentLastNameTF.getText().isEmpty()
		|| studentPhoneTF.getText().isEmpty() || studentMailTF.getText().isEmpty() || studentAddressTF.getText().isEmpty()
		|| studentDistrictTF.getText().isEmpty() || studentCityTF.getText().isEmpty() || studentZipCodeTF.getText().isEmpty()){
			showAlert("Lütfen Zorunlu(*) Alanları Doldurun","Eksik Girdi", AlertType.WARNING);
				}
		else if (studentTcNoTF.getText().length() < 11 || studentTcNoTF.getText().length() > 11 || !isNumeric(studentTcNoTF.getText())) {
			showAlert("Tc No 11 Rakamdan Oluşmalıdır", "Geçersiz Tc No", AlertType.ERROR);
			
		}	
		else if (!isAlphabetic(studentFirstNameTF.getText()) || !isAlphabetic(studentLastNameTF.getText()) 
		|| studentFirstNameTF.getText().length()>30 || studentLastNameTF.getText().length()>30) {
			showAlert("İsim vyea Soyisim En Fazla 30 Alfabetik Karakterden Oluşabilir", "Geçersiz İsim/Soyisim", AlertType.WARNING);
		} 
		else if (!isNumeric(studentPhoneTF.getText()) || studentPhoneTF.getText().length()<10 || studentPhoneTF.getText().length()>10) {
			showAlert("Telefon Numarası 10 Rakamdan Oluşmalıdır", "Geçersiz Telefon Numarsı", AlertType.WARNING);
		} else if (!isValidEmailAddress(studentMailTF.getText()) || studentMailTF.getText().length()>60) {
			showAlert("Geçersiz Mail Adres veya 60 Karakterden Büyük Mail Adres", "Geçerisz E-Mail", AlertType.WARNING);
		} else if (studentAddressTF.getText().length()>100) {
			showAlert("Adres 100 Karakterden Fazla Olamaz", "Geçersiz Adres", AlertType.WARNING);
		} else if (studentDistrictTF.getText().length()>50 || studentCityTF.getText().length()>50) {
			showAlert("İlçe ve Şehir 50 Karakterden Fazla Olamaz", "Geçersiz İl/İlçe", AlertType.WARNING);
		} else if (!isNumeric(studentZipCodeTF.getText()) || studentZipCodeTF.getText().length()>10) {
			showAlert("Posta Kodu En Fazla 10 Rakamdan Oluşabilir", "Geçersiz Posta Kodu", AlertType.WARNING);
		}else {
			result = true;
		}
		return result;
	}
	
	private boolean validateTeacherFields() {
		boolean result = false;
		if (teacherTcNoTF.getText().isEmpty() || teacherFirstNameTF.getText().isEmpty() || teacherLastNameTF.getText().isEmpty()
		|| teacherPhoneTF.getText().isEmpty() || teacherMailTF.getText().isEmpty() || teacherAddressTF.getText().isEmpty()
		|| teacherDistrictTF.getText().isEmpty() || teacherCityTF.getText().isEmpty() || teacherZipCodeTF.getText().isEmpty()){
			showAlert("Lütfen Zorunlu(*) Alanları Doldurun","Eksik Girdi", AlertType.WARNING);
				}
		else if (teacherTcNoTF.getText().length() < 11 || teacherTcNoTF.getText().length() > 11 || !isNumeric(teacherTcNoTF.getText())) {
			showAlert("Tc No 11 Rakamdan Oluşmalıdır", "Geçersiz Tc No", AlertType.ERROR);
			
		}	
		else if (!isAlphabetic(teacherFirstNameTF.getText()) || !isAlphabetic(teacherLastNameTF.getText()) 
		|| teacherFirstNameTF.getText().length()>30 || teacherLastNameTF.getText().length()>30) {
			showAlert("İsim vyea Soyisim En Fazla 30 Alfabetik Karakterden Oluşabilir", "Geçersiz İsim/Soyisim", AlertType.WARNING);
		} 
		else if (!isNumeric(teacherPhoneTF.getText()) || teacherPhoneTF.getText().length()<10 || teacherPhoneTF.getText().length()>10) {
			showAlert("Telefon Numarası 10 Rakamdan Oluşmalıdır", "Geçersiz Telefon Numarsı", AlertType.WARNING);
		} 
		else if (!isValidEmailAddress(teacherMailTF.getText()) || teacherMailTF.getText().length()>60) {
			showAlert("Geçersiz Mail Adres veya 60 Karakterden Büyük Mail Adres", "Geçerisz E-Mail", AlertType.WARNING);
		} 
		else if (teacherAddressTF.getText().length()>100) {
			showAlert("Adres 100 Karakterden Fazla Olamaz", "Geçersiz Adres", AlertType.WARNING);
		} 
		else if (teacherDistrictTF.getText().length()>50 || teacherCityTF.getText().length()>50) {
			showAlert("İlçe ve Şehir 50 Karakterden Fazla Olamaz", "Geçersiz İl/İlçe", AlertType.WARNING);
		} 
		else if (!isNumeric(teacherZipCodeTF.getText()) || teacherZipCodeTF.getText().length()>10) {
			showAlert("Posta Kodu En Fazla 10 Rakamdan Oluşabilir", "Geçersiz Posta Kodu", AlertType.WARNING);
		}
		else if (teacherLessonsLW.getItems().isEmpty()) {
			showAlert("En Az Bir Ders Eklenmelidir", "Boş Öğretmen Uzmanlık Dersleri", AlertType.WARNING);
		}
		else {
			result = true;
		}
		return result;
	}
	
	private boolean validateAdminFields() {
		boolean result = false;
		if (adminTcNoTF.getText().isEmpty() || adminFirstNameTF.getText().isEmpty() || adminLastNameTF.getText().isEmpty()
		|| adminPhoneTF.getText().isEmpty() || adminMailTF.getText().isEmpty() || adminAddressTF.getText().isEmpty()
		|| adminDistrictTF.getText().isEmpty() || adminCityTF.getText().isEmpty() || adminZipCodeTF.getText().isEmpty()){
			showAlert("Lütfen Zorunlu(*) Alanları Doldurun","Eksik Girdi", AlertType.WARNING);
				}
		else if (adminTcNoTF.getText().length() < 11 || adminTcNoTF.getText().length() > 11 || !isNumeric(adminTcNoTF.getText())) {
			showAlert("Tc No 11 Rakamdan Oluşmalıdır", "Geçersiz Tc No", AlertType.ERROR);
			
		}	
		else if (!isAlphabetic(adminFirstNameTF.getText()) || !isAlphabetic(adminLastNameTF.getText()) 
		|| adminFirstNameTF.getText().length()>30 || adminLastNameTF.getText().length()>30) {
			showAlert("İsim vyea Soyisim En Fazla 30 Alfabetik Karakterden Oluşabilir", "Geçersiz İsim/Soyisim", AlertType.WARNING);
		} 
		else if (!isNumeric(adminPhoneTF.getText()) || adminPhoneTF.getText().length()<10 || adminPhoneTF.getText().length()>10) {
			showAlert("Telefon Numarası 10 Rakamdan Oluşmalıdır", "Geçersiz Telefon Numarsı", AlertType.WARNING);
		} 
		else if (!isValidEmailAddress(adminMailTF.getText()) || adminMailTF.getText().length()>60) {
			showAlert("Geçersiz Mail Adres veya 60 Karakterden Büyük Mail Adres", "Geçerisz E-Mail", AlertType.WARNING);
		} 
		else if (adminAddressTF.getText().length()>100) {
			showAlert("Adres 100 Karakterden Fazla Olamaz", "Geçersiz Adres", AlertType.WARNING);
		} 
		else if (adminDistrictTF.getText().length()>50 || adminCityTF.getText().length()>50) {
			showAlert("İlçe ve Şehir 50 Karakterden Fazla Olamaz", "Geçersiz İl/İlçe", AlertType.WARNING);
		} 
		else if (!isNumeric(adminZipCodeTF.getText()) || adminZipCodeTF.getText().length()>10) {
			showAlert("Posta Kodu En Fazla 10 Rakamdan Oluşabilir", "Geçersiz Posta Kodu", AlertType.WARNING);
		}
		else {
			result = true;
		}
		return result;
	}
	
	private boolean isNumeric(String text){
		Pattern pattern = Pattern.compile("\\D");
		Matcher matcher = pattern.matcher(text);
		return !matcher.find();
	}
	
	private boolean isAlphabetic(String text) {
		Pattern pattern = Pattern.compile("[^a-zA-Z_İğĞşŞöÖüÜçÇ]+");
		Matcher matcher = pattern.matcher(text);
		return !matcher.find();
	}
	
	public static boolean isValidEmailAddress(String mail) {
		  Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		  Matcher matcher = pattern.matcher(mail);
		  return matcher.find();
		}
	
	private void showAlert(String message, String title, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
//	public void collegeChapterMap() {
//		
//		Collection<Chapter> chapters = DBContext.getInstance().chapters().getAll();
//		collegeList = FXCollections.observableArrayList(colleges);
//		studentAddCollegeCm.setItems(collegeList);
//		studentAddCollegeCm.getSelectionModel().selectFirst();
//		collegesChapters = new TreeMap<>();
//		for (College college : colleges) {
//			Collection<Chapter> chapterList = new TreeSet<>();                                                                                             ;
//			for (Chapter chapter : chapters) {
//				if (college.getId() == chapter.getCollege().getId()) {
//					chapterList.add(chapter);
//				}
//			}
//			collegesChapters.put(college, chapterList);
//		}
//	}
	
	
}
