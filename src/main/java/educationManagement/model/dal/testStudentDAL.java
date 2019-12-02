package educationManagement.model.dal;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import educationManagement.model.entity.College;
import educationManagement.model.entity.Contact;
import educationManagement.model.entity.Department;
import educationManagement.model.entity.DepartmentChapter;
import educationManagement.model.entity.Lesson;
import educationManagement.model.entity.Teacher;

public class testStudentDAL {

	public static void main(String[] args) {
		
//		Collection<Department> departments = DBContext.getInstance().derpartments().getAll();
//			System.out.println(departments.g
//			System.out.println(chapter);
//			for (Lesson lesson : chapter.getLessons()) {
//				System.out.println(lesson);
//			}
//		}
		
		

		Teacher teacher = new Teacher();
		teacher.setTcNo("12345678222");
		teacher.setFirstName("Furkan");
		teacher.setLastName("Okçu");
		teacher.setStartDate(new Date());
		College college = new College();
		college.setId(3);
		college.setCollegeName("Hukuk Fakültesi");
		teacher.setCollege(college);
		Department department = new Department();
		department.setId(2);
		department.setDepartmentName("Öğretim Görevlisi");
		teacher.setDepartment(department);
		DepartmentChapter departmentChapter = new DepartmentChapter();
		departmentChapter.setId(2);
		departmentChapter.setDepartmentChapterName("Akademisyen");
		teacher.setDepartmentChapter(departmentChapter);
		Set<Lesson> lessons = new TreeSet<>();
		Lesson lesson1 = new Lesson();
		lesson1.setId(5);
		lesson1.setLessonName("Anayasa Hukuku");
		Lesson lesson2 = new Lesson();
		lesson2.setId(6);
		lesson2.setLessonName("Ceza Hukuku");
		lessons.add(lesson1);
		lessons.add(lesson2);
		teacher.setLessons(lessons);
		Contact contact = new Contact();
		contact.setPhone("5333353365");
		contact.setMail("furk454an@gmail.com");
		contact.setAddress("Adres Değişti");
		contact.setDistrict("Maltepe1");
		contact.setCity("Istanbul");
		contact.setZipCode(68021);
		teacher.setContact(contact);
		
		System.out.println(lessons);
		
//		INSERT USER
		
		boolean result = new TeacherDAL().insertEntity(teacher);
		System.out.println( "teacher result " + result);
		System.out.println(teacher);
		
		//UPDATE USER
		
//		boolean result = new StudentDAL().updateEntity(student);
//		System.out.println( "student result " + result);
//		System.out.println(student);
//		boolean resultContact = new ContactDAL().updateEntity(contact);
//		System.out.println("result contact " + resultContact);
//		System.out.println(contact);
		
		//DELETE USER
		
//		boolean resultContact = new ContactDAL().deleteEntity(contact);
//		System.out.println("result contact " + resultContact);
//		System.out.println(contact);
//		boolean result = new StudentDAL().deleteEntity(student);
//		System.out.println( "student result " + result);
//		System.out.println(student);
		
		//GETBYID GETBYTCNO GETBYALL
		
//		System.out.println("Id Sorgusu :" + DBContext.getInstance().students().getById(12313));
//		System.out.println("Tc no Sorgusu:" + DBContext.getInstance().students().getByTcNo("12345678922"));
//		System.out.println("Bütün Öğrenciler");
//		Collection<Student> students =  DBContext.getInstance().students().getAll();
//		for (Student student2 : students) {
//			System.out.println(student2);
//		}
	}
}
