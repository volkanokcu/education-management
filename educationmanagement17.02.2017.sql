CREATE DATABASE educationmanagement CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE educationmanagement;

CREATE TABLE colleges
(
college_id			INT 			AUTO_INCREMENT,
college_name 		NVARCHAR(60) 	NOT NULL UNIQUE,
college_information NVARCHAR(255) 	NULL,
CONSTRAINT pk_colleges_college_id
	PRIMARY KEY (college_id)
);

CREATE TABLE chapters
(
chapter_id			INT 			AUTO_INCREMENT,
college_id  		INT				NOT NULL,
chapter_name 		NVARCHAR(60) 	NOT NULL UNIQUE,
chapter_information NVARCHAR(255) 	NULL,
CONSTRAINT pk_chapters_chapter_id
	PRIMARY KEY (chapter_id),
CONSTRAINT fk_chapters_chapter_college_id
	FOREIGN KEY (college_id)
		REFERENCES colleges (college_id)
);

CREATE TABLE lessons
(
lesson_id 			INT 			AUTO_INCREMENT,
lesson_name			NVARCHAR(50)	NOT NULL	UNIQUE,
CONSTRAINT pk_lessons_lesson_id
	PRIMARY KEY (lesson_id)
);

CREATE TABLE chapters_to_lessons
(
chapter_id			INT,
lesson_id			INT,
CONSTRAINT pk_lessons_to_chapters_chapter_id_lesson_id
	PRIMARY KEY (chapter_id, lesson_id),
CONSTRAINT fk_lessons_to_chapters_chapter_id
	FOREIGN KEY (chapter_id)
		REFERENCES chapters (chapter_id),
CONSTRAINT fk_lessons_to_chapters_lesson_id
	FOREIGN KEY (lesson_id)
		REFERENCES lessons (lesson_id)
);

CREATE TABLE departments
(
department_id 					INT				AUTO_INCREMENT,
department_name 				NVARCHAR(60)	NOT NULL,
department_information 			NVARCHAR(255)		NULL,
CONSTRAINT pk_deparments_department_id
	PRIMARY KEY (department_id)
);

CREATE TABLE departments_chapters
(
department_chapter_id 						INT				AUTO_INCREMENT,
department_id					INT				NOT NULL,
department_chapter_name 					NVARCHAR(60)	NOT NULL,
department_chapter_information 			NVARCHAR(255)		NULL,
CONSTRAINT pk_deparments_chapters_department_chapter_id
	PRIMARY KEY (department_chapter_id),
CONSTRAINT fk_departments_chapters_department_id
	FOREIGN KEY (department_id)
		REFERENCES departments (department_id)
);

CREATE TABLE users
(
user_id 			INT,
user_tcno			NVARCHAR(11)	NOT NULL 	UNIQUE,
user_startdate		Date 			NOT NULL,
user_firstname 		NVARCHAR(50) 	NOT NULL,
user_lastname 		NVARCHAR(50)  	NOT NULL,
user_password 		NVARCHAR(50)  	NOT NULL,
college_id			INT				NOT NULL,
CONSTRAINT pk_users_user_id
	PRIMARY KEY (user_id),
CONSTRAINT fk_users_college_id
	FOREIGN KEY (college_id)
		REFERENCES colleges (college_id)
);

CREATE TABLE contacts
(
user_id					INT,
phone 					NVARCHAR(10)	NOT NULL 	UNIQUE,
mail 					NVARCHAR(60)	NOT NULL 	UNIQUE,
address 				NVARCHAR(100)	NOT NULL,
district 				NVARCHAR(50)	NOT NULL,			
city 					NVARCHAR(50)	NOT NULL,
zip_code 				INT(5)			NOT NULL,
CONSTRAINT pk_contacts_user_id
	PRIMARY KEY (user_id),
CONSTRAINT fk_contacts_user_id 
	FOREIGN KEY (user_id)
		REFERENCES users (user_id)
);

CREATE TABLE employees
(
employee_id				INT,
department_id			INT				NOT NULL,
department_chapter_id				INT				NOT NULL,
CONSTRAINT pk_employees_emloyee_id
	PRIMARY KEY (employee_id),
CONSTRAINT fk_employees_employee_id
	FOREIGN KEY (employee_id)
		REFERENCES users (user_id),
CONSTRAINT fk_employees_department_id
	FOREIGN KEY (department_id)
		REFERENCES departments_chapters (department_id),
CONSTRAINT fk_employees_department_chapter_id
	FOREIGN KEY (department_chapter_id)
		REFERENCES departments_chapters (department_chapter_id)
);


CREATE TABLE teachers
(
teacher_id				INT,
CONSTRAINT pk_teachers_uteacher_id	
	PRIMARY KEY (teacher_id),
CONSTRAINT fk_teachers_teacher_id
	FOREIGN KEY (teacher_id	)
		REFERENCES employees (employee_id)
);

CREATE TABLE teachers_lessons
(
teacher_id			    INT,
lesson_id				INT,
CONSTRAINT pk_teachers_lessons_teacher_id_lesson_id
	PRIMARY KEY (teacher_id, lesson_id),
CONSTRAINT fk_teachers_lessons__teacher_id
	FOREIGN KEY (teacher_id)
		REFERENCES teachers (teacher_id),
CONSTRAINT fk_teachers_lessons_lesson_id
	FOREIGN KEY (lesson_id)
		REFERENCES lessons (lesson_id)
);

CREATE TABLE admins
(
admin_id			INT,
authority			NVARCHAR(20)	NOT NULL,
CONSTRAINT pk_admins_admin_id
	PRIMARY KEY (admin_id),
CONSTRAINT fk_admins_admin_id
	FOREIGN KEY (admin_id)
		REFERENCES employees (employee_id)
);

CREATE TABLE students
(
student_id			INT,
chapter_id			INT				NOT NULL,
CONSTRAINT pk_students_student_id
	PRIMARY KEY (student_id),
CONSTRAINT fk_students_student_id
	FOREIGN KEY (student_id)
		REFERENCES users (user_id)
);

CREATE TABLE students_lessons_points
(
student_id			INT,
lesson_id			INT,
visapoint1			TINYINT			NULL,
visapoint2			TINYINT 		NULL,
finalpoint			TINYINT			NULL,
CONSTRAINT pk_students_lessons_points_student_id_lesson_id
	PRIMARY KEY (student_id,lesson_id),
CONSTRAINT fk_students_lessons_points_student_id
	FOREIGN KEY (student_id)
		REFERENCES students (student_id),
CONSTRAINT fk_students_lessons_points_lesson_id
	FOREIGN KEY (lesson_id)
		REFERENCES lessons (lesson_id)
);

CREATE TABLE educationclasses
(
educationclass_id 			INT				AUTO_INCREMENT,
educationclass_name			NVARCHAR(50) 	UNIQUE,
chapter_id					INT,
responsible_teacher_id		INT,
CONSTRAINT pk_educationclasses_edicationclass_id
	PRIMARY KEY (educationclass_id),
CONSTRAINT fk_edicationclasses_chapter_id
	FOREIGN KEY (chapter_id)
		REFERENCES chapters (chapter_id),
CONSTRAINT fk_edicationclasses_responsible_teacher_id
	FOREIGN KEY (responsible_teacher_id)
		REFERENCES teachers (teacher_id)
);

CREATE TABLE educationclasses_students
(
educationclass_id			INT,
student_id					INT,
CONSTRAINT pk_educationclasses_students_educationclass_id_student_id
	PRIMARY KEY (educationclass_id, student_id),
CONSTRAINT fk_educationclasses_students_educationclass_id
	FOREIGN KEY (educationclass_id)
		REFERENCES educationclasses (educationclass_id),
CONSTRAINT fk_educationclasses_students_student_id
	FOREIGN KEY (student_id)
		REFERENCES students (student_id)
);

CREATE TABLE educationclasses_lessons_teachers
(
educationclass_id			INT,
teacher_id					INT,
lesson_id 					INT,
CONSTRAINT pk_educationclasses_lessons_teachers
	PRIMARY KEY (educationclass_id, teacher_id, lesson_id),
CONSTRAINT fk_educationclasses_lessons_teachers_educationclass_id
	FOREIGN KEY (educationclass_id)
		REFERENCES educationclasses (educationclass_id),
CONSTRAINT fk_educationclasses_lessons_teachers_teacher_id
	FOREIGN KEY (teacher_id)
		REFERENCES teachers (teacher_id),
CONSTRAINT fk_educationclasses_lessons_teachers_lesson_id
	FOREIGN KEY (lesson_id)
		REFERENCES lessons (lesson_id)
);



INSERT INTO colleges (college_name) VALUES ('Rektörlük Merkez Bina');
INSERT INTO colleges (college_name) VALUES ('Tıp Fakültesi');
INSERT INTO colleges (college_name) VALUES ('Hukuk Fakültesi');
INSERT INTO chapters (college_id, chapter_name) VALUES (2, 'Tıp');
INSERT INTO chapters (college_id, chapter_name) VALUES (2, 'Tıp(İngilizce)');
INSERT INTO chapters (college_id, chapter_name) VALUES (3, 'Hukuk');
INSERT INTO chapters (college_id, chapter_name) VALUES (3, 'Hukuk(İngilizce)');
INSERT INTO lessons (lesson_name) VALUES ('Türkçe');
INSERT INTO lessons (lesson_name) VALUES ('Tarih');
INSERT INTO lessons (lesson_name) VALUES ('Matematik');
INSERT INTO lessons (lesson_name) VALUES ('Anatomi');
INSERT INTO lessons (lesson_name) VALUES ('Anayasa Hukuku');
INSERT INTO lessons (lesson_name) VALUES ('Ceza Hukuku');
INSERT INTO chapters_to_lessons VALUES (1,1);
INSERT INTO chapters_to_lessons VALUES (1,2);
INSERT INTO chapters_to_lessons VALUES (1,3);
INSERT INTO chapters_to_lessons VALUES (1,4);
INSERT INTO chapters_to_lessons VALUES (2,1);
INSERT INTO chapters_to_lessons VALUES (2,2);
INSERT INTO chapters_to_lessons VALUES (2,3);
INSERT INTO chapters_to_lessons VALUES (2,4);
INSERT INTO chapters_to_lessons VALUES (3,1);
INSERT INTO chapters_to_lessons VALUES (3,2);
INSERT INTO chapters_to_lessons VALUES (3,5);
INSERT INTO chapters_to_lessons VALUES (3,6);
INSERT INTO chapters_to_lessons VALUES (4,1);
INSERT INTO chapters_to_lessons VALUES (4,2);
INSERT INTO chapters_to_lessons VALUES (4,5);
INSERT INTO chapters_to_lessons VALUES (4,6);
INSERT INTO departments (department_name) VALUES ('Bilgi İşlem');
INSERT INTO departments (department_name) VALUES ('Öğretim Görevlisi');
INSERT INTO departments_chapters (department_id, department_chapter_name) VALUES (1,'Admin');
INSERT INTO departments_chapters (department_id, department_chapter_name) VALUES (2,'Akademisyen');
INSERT INTO users VALUES (10000000,'10739111014','2017/01/17', 'Volkan', 'Okçu', 'ferrari', 1);
INSERT INTO contacts VALUES (10000000,'5333353201','archervolcano@mail.com','Küçükyalı mah. Mektep cad. Baki sok. 4/9','Maltepe','İstanbul','48480');
INSERT INTO employees VALUES (10000000,1,1);
INSERT INTO admins VALUES (10000000, 'Admin');

-- TEACHER STORED PROSEDURES

DELIMITER //
CREATE PROCEDURE InsertTeacher(IN in_user_id INT, IN in_user_tcno NVARCHAR(11), IN in_user_startdate Date, IN in_user_firstname NVARCHAR(50), 
IN in_user_lastname NVARCHAR(50), IN in_user_password NVARCHAR(50), IN in_college_id INT, 
IN in_department_id INT, IN in_department_chapter_id INT, 
IN in_phone NVARCHAR(10), IN in_mail NVARCHAR(60), IN in_address NVARCHAR(100), IN in_district NVARCHAR(50), IN in_city NVARCHAR(50), IN in_zip_code INT(5))
BEGIN
INSERT INTO users VALUES (in_user_id, in_user_tcno, in_user_startdate, in_user_firstname, in_user_lastname, in_user_password, in_college_id);
INSERT INTO contacts VALUES (in_user_id, in_phone, in_mail, in_address, in_district, in_city, in_zip_code);
INSERT INTO employees VALUES (in_user_id, in_department_id, in_department_chapter_id);
INSERT INTO teachers VALUES (in_user_id);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateTeacher(IN in_user_id INT, IN in_user_tcno NVARCHAR(11), IN in_user_startdate Date, IN in_user_firstname NVARCHAR(50), 
IN in_user_lastname NVARCHAR(50), IN in_user_password NVARCHAR(50), IN in_college_id INT, 
IN in_department_id INT, IN in_department_chapter_id INT, 
IN in_phone NVARCHAR(10), IN in_mail NVARCHAR(60), IN in_address NVARCHAR(100), IN in_district NVARCHAR(50), IN in_city NVARCHAR(50), IN in_zip_code INT(5))
BEGIN
UPDATE users SET user_tcno = in_user_tcno, user_startdate = in_user_startdate, user_firstname = in_user_firstname, user_lastname = in_user_lastname, user_password = in_user_password, college_id = in_college_id WHERE user_id = in_user_id;
UPDATE contacts SET phone = in_phone, mail = in_mail, address = in_address, district = in_district, city = in_city, zip_code = in_zip_code WHERE user_id = in_user_id;
UPDATE employees SET  department_id = in_department_id, department_chapter_id = in_department_chapter_id WHERE employee_id = in_user_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteTeacher(IN in_user_id INT)
BEGIN
DELETE FROM contacts WHERE user_id = in_user_id;
DELETE FROM teachers_lessons WHERE teacher_id = in_user_id;
DELETE FROM teachers WHERE teacher_id = in_user_id;
DELETE FROM employees WHERE employee_id = in_user_id;
DELETE FROM users WHERE user_id = in_user_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetAllTeachers()
BEGIN
SELECT * FROM users us 
INNER JOIN colleges col 
INNER JOIN departments dep
INNER JOIN departments_chapters dchap
INNER JOIN employees emp
INNER JOIN teachers teac 
INNER JOIN contacts con 
INNER JOIN lessons les
INNER JOIN teachers_lessons tl
ON us.college_id = col.college_id AND us.user_id = emp.employee_id AND teac.teacher_id = us.user_id AND us.user_id = con.user_id AND us.user_id = tl.teacher_id AND les.lesson_id = tl.lesson_id AND emp.department_id = dep.department_id AND emp.department_chapter_id = dchap.department_chapter_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetTeacherById(IN in_user_id INT)
BEGIN
SELECT * FROM users us 
INNER JOIN colleges col 
INNER JOIN departments dep
INNER JOIN departments_chapters dchap
INNER JOIN employees emp
INNER JOIN teachers teac 
INNER JOIN contacts con 
INNER JOIN lessons les
INNER JOIN teachers_lessons tl
ON us.college_id = col.college_id AND us.user_id = emp.employee_id AND teac.teacher_id = us.user_id AND us.user_id = con.user_id AND us.user_id = tl.teacher_id AND les.lesson_id = tl.lesson_id AND emp.department_id = dep.department_id AND emp.department_chapter_id = dchap.department_id
WHERE us.user_id = in_user_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetTeacherByTcNo(IN in_user_tcno NVARCHAR(11))
BEGIN
SELECT * FROM users us 
INNER JOIN colleges col 
INNER JOIN departments dep
INNER JOIN departments_chapters dchap
INNER JOIN employees emp
INNER JOIN teachers teac 
INNER JOIN contacts con 
INNER JOIN lessons les
INNER JOIN teachers_lessons tl
ON us.college_id = col.college_id AND us.user_id = emp.employee_id AND teac.teacher_id = us.user_id AND us.user_id = con.user_id AND us.user_id = tl.teacher_id AND les.lesson_id = tl.lesson_id AND emp.department_id = dep.department_id AND emp.department_chapter_id = dchap.department_id
WHERE us.user_tcno = in_user_tcno;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetTeacherByIdAndPassword(IN in_user_id INT, IN in_user_password NVARCHAR(50))
BEGIN
SELECT * FROM users us 
INNER JOIN colleges col 
INNER JOIN departments dep
INNER JOIN departments_chapters dchap
INNER JOIN employees emp
INNER JOIN teachers teac 
INNER JOIN contacts con 
INNER JOIN lessons les
INNER JOIN teachers_lessons tl
ON us.college_id = col.college_id AND us.user_id = emp.employee_id AND teac.teacher_id = us.user_id AND us.user_id = con.user_id AND us.user_id = tl.teacher_id AND les.lesson_id = tl.lesson_id AND emp.department_id = dep.department_id AND emp.department_chapter_id = dchap.department_id
WHERE us.user_id = in_user_id AND us.user_password = in_user_password;
END //
DELIMITER ;

-- ADMIN STORED PROSEDURES

DELIMITER //
CREATE PROCEDURE InsertAdmin(IN in_user_id INT, IN in_user_tcno NVARCHAR(11), IN in_user_startdate Date, IN in_user_firstname NVARCHAR(50), 
IN in_user_lastname NVARCHAR(50), IN in_user_password NVARCHAR(50), IN in_college_id INT, 
IN in_department_id INT, IN in_department_chapter_id INT, 
IN in_authority NVARCHAR(20),
IN in_phone NVARCHAR(10), IN in_mail NVARCHAR(60), IN in_address NVARCHAR(100), IN in_district NVARCHAR(50), IN in_city NVARCHAR(50), IN in_zip_code INT(5))
BEGIN
INSERT INTO users VALUES (in_user_id, in_user_tcno, in_user_startdate, in_user_firstname, in_user_lastname, in_user_password, in_college_id);
INSERT INTO contacts VALUES (in_user_id, in_phone, in_mail, in_address, in_district, in_city, in_zip_code);
INSERT INTO employees VALUES (in_user_id, in_department_id, in_department_chapter_id);
INSERT INTO admins VALUES (in_user_id, in_authority);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateAdmin(IN in_user_id INT, IN in_user_tcno NVARCHAR(11), IN in_user_startdate Date, IN in_user_firstname NVARCHAR(50), 
IN in_user_lastname NVARCHAR(50), IN in_user_password NVARCHAR(50), IN in_college_id INT, 
IN in_department_id INT, IN in_department_chapter_id INT, 
IN in_authority NVARCHAR(20),
IN in_phone NVARCHAR(10), IN in_mail NVARCHAR(60), IN in_address NVARCHAR(100), IN in_district NVARCHAR(50), IN in_city NVARCHAR(50), in_zip_code INT(5))
BEGIN
UPDATE users SET user_tcno = in_user_tcno, user_startdate = in_user_startdate, user_firstname = in_user_firstname, user_lastname = in_user_lastname, user_password = in_user_password, college_id = in_college_id WHERE user_id = in_user_id;
UPDATE contacts SET phone = in_phone, mail = in_mail, address = in_address, district = in_district, city = in_city, zip_code = in_zip_code WHERE user_id = in_user_id;
UPDATE employees SET  department_id = in_department_id, department_chapter_id = in_department_chapter_id WHERE employee_id = in_user_id;
UPDATE admins SET authority = in_authority WHERE admin_id = in_user_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteAdmin(IN in_user_id INT)
BEGIN
DELETE FROM contacts WHERE user_id = in_user_id;
DELETE FROM admins WHERE admin_id = in_user_id;
DELETE FROM employees WHERE employee_id = in_user_id;
DELETE FROM users WHERE user_id = in_user_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetAllAdmins()
BEGIN
SELECT * FROM users us 
INNER JOIN colleges col 
INNER JOIN employees emp
INNER JOIN admins ad
INNER JOIN contacts con
INNER JOIN departments dep
INNER JOIN departments_chapters dchap
ON us.college_id = col.college_id AND us.user_id = emp.employee_id AND ad.admin_id = us.user_id AND us.user_id = con.user_id AND emp.department_id = dep.department_id AND emp.department_chapter_id = dchap.department_chapter_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetAdminById(IN in_user_id INT)
BEGIN
SELECT * FROM users us 
INNER JOIN colleges col 
INNER JOIN employees emp
INNER JOIN admins ad
INNER JOIN contacts con
INNER JOIN departments dep
INNER JOIN departments_chapters dchap
ON us.college_id = col.college_id AND us.user_id = emp.employee_id AND ad.admin_id = us.user_id AND us.user_id = con.user_id AND emp.department_id = dep.department_id AND emp.department_chapter_id = dchap.department_chapter_id
WHERE us.user_id = in_user_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetAdminByTcNo(IN in_user_tcno NVARCHAR(11))
BEGIN
SELECT * FROM users us 
INNER JOIN colleges col 
INNER JOIN employees emp
INNER JOIN admins ad
INNER JOIN contacts con
INNER JOIN departments dep
INNER JOIN departments_chapters dchap
ON us.college_id = col.college_id AND us.user_id = emp.employee_id AND ad.admin_id = us.user_id AND us.user_id = con.user_id AND emp.department_id = dep.department_id AND emp.department_chapter_id = dchap.department_chapter_id
WHERE us.user_tcno = in_user_tcno;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetAdminByIdAndPassword(IN in_user_id INT, IN in_user_password NVARCHAR(50))
BEGIN
SELECT * FROM users us 
INNER JOIN colleges col 
INNER JOIN employees emp
INNER JOIN admins ad
INNER JOIN contacts con
INNER JOIN departments dep
INNER JOIN departments_chapters dchap
ON us.college_id = col.college_id AND us.user_id = emp.employee_id AND ad.admin_id = us.user_id AND us.user_id = con.user_id AND emp.department_id = dep.department_id AND emp.department_chapter_id = dchap.department_chapter_id
WHERE us.user_id = in_user_id AND us.user_password = in_user_password;
END //
DELIMITER ;

-- STUDENT STORED PROCEDURES

DELIMITER //
CREATE PROCEDURE InsertStudent(IN in_user_id INT, IN in_user_tcno NVARCHAR(11), IN in_user_startdate Date, IN in_user_firstname NVARCHAR(50), 
IN in_user_lastname NVARCHAR(50), IN in_user_password NVARCHAR(50), IN in_college_id INT, 
IN in_chapter_id INT,
IN in_phone NVARCHAR(10), IN in_mail NVARCHAR(60), IN in_address NVARCHAR(100), IN in_district NVARCHAR(50), IN in_city NVARCHAR(50), IN in_zip_code INT(5))
BEGIN
INSERT INTO users VALUES (in_user_id, in_user_tcno, in_user_startdate, in_user_firstname, in_user_lastname, in_user_password, in_college_id);
INSERT INTO contacts VALUES (in_user_id, in_phone, in_mail, in_address, in_district, in_city, in_zip_code);
INSERT INTO students VALUES (in_user_id, in_chapter_id);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE UpdateStudent(IN in_user_id INT, IN in_user_tcno NVARCHAR(11), IN in_user_startdate Date, IN in_user_firstname NVARCHAR(50), 
IN in_user_lastname NVARCHAR(50), IN in_user_password NVARCHAR(50), IN in_college_id INT, 
IN in_chapter_id INT,
IN in_phone NVARCHAR(10), IN in_mail NVARCHAR(60), IN in_address NVARCHAR(100), IN in_district NVARCHAR(50), IN in_city NVARCHAR(50), IN in_zip_code INT(5))
BEGIN
UPDATE users SET user_tcno = in_user_tcno, user_startdate = in_user_startdate, user_firstname = in_user_firstname, user_lastname = in_user_lastname, user_password = in_user_password, college_id = in_college_id WHERE user_id = in_user_id;
UPDATE contacts SET phone = in_phone, mail = in_mail, address = in_address, district = in_district, city = in_city, zip_code = in_zip_code WHERE user_id = in_user_id;
UPDATE students SET  chapter_id = in_chapter_id WHERE student_id = in_user_id; 
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE DeleteStudent(IN in_user_id INT)
BEGIN
DELETE FROM contacts WHERE user_id = in_user_id;
DELETE FROM students_lessons_points WHERE student_id = in_user_id;
DELETE FROM students WHERE student_id = in_user_id;
DELETE FROM users WHERE user_id = in_user_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetAllStudents()
BEGIN
SELECT * FROM users us 
INNER JOIN colleges col 
INNER JOIN students sut 
INNER JOIN chapters cap 
INNER JOIN contacts con 
ON us.college_id = col.college_id AND us.user_id = sut.student_id AND sut.chapter_id = cap.chapter_id AND us.user_id = con.user_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetStudentById(IN in_user_id INT)
BEGIN
SELECT * FROM users us 
INNER JOIN colleges col 
INNER JOIN students sut 
INNER JOIN chapters cap 
INNER JOIN contacts con 
ON us.college_id = col.college_id AND us.user_id = sut.student_id AND sut.chapter_id = cap.chapter_id AND us.user_id = con.user_id
WHERE us.user_id = in_user_id;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetStudentByTcNo(IN in_user_tcno NVARCHAR(11))
BEGIN
SELECT * FROM users us 
INNER JOIN colleges col 
INNER JOIN students sut 
INNER JOIN chapters cap 
INNER JOIN contacts con 
ON us.college_id = col.college_id AND us.user_id = sut.student_id AND sut.chapter_id = cap.chapter_id AND us.user_id = con.user_id
WHERE us.user_tcno = in_user_tcno;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE GetStudentByIdAndPassword(IN in_user_id INT, IN in_user_password NVARCHAR(50))
BEGIN
SELECT * FROM users us 
INNER JOIN colleges col 
INNER JOIN students sut 
INNER JOIN chapters cap 
INNER JOIN contacts con 
ON us.college_id = col.college_id AND us.user_id = sut.student_id AND sut.chapter_id = cap.chapter_id AND us.user_id = con.user_id
WHERE us.user_id = in_user_id AND us.user_password = in_user_password;
END //
DELIMITER ;


commit;

