Neeraj Yadav's Repository for Student Training Demo
If running from Eclipse/IDE
  Step 1: Clone or download this training Project.
  Step 2: Import as Maven Project 
  Step 3: Goto Maven and Update Project.
  Step 4: Clean and Build 
  Step 5: Run as Spring Boot Application 
  Step 6: Run Below curl commands to run different API of this service.
  
If not running from Eclipse/IDE
  Step 1: Clone or download this training Project.
  Step 2: Change directory and goto training project folder.
  Step 3: run command : mvn clean package
  Step 4: run command : mvn clean install
  Step 5: run command : java -jar training-0.0.1-SNAPSHOT.jar
  Step 6: Run Below curl commands to run different API of this service.


ADD STUDENT: 
  curl -XPATCH -i  'http://localhost:8081/studentservice/api/addStudent/{studentname}/{dob}'
  i.e. curl -XPATCH -i  'http://localhost:8081/studentservice/api/addStudent/Neeraj/26091980'

GET All STUDENTS: 
 curl -XGET -i  'http://localhost:8081/studentservice/api/getAllStudents'
 i.e. curl -XGET -i  'http://localhost:8081/studentservice/api/getAllStudents'

REMOVE STUDENT: 
 curl -XDELETE -i  'http://localhost:8081/studentservice/api/removeStudent/{studentId}'
 i.e. curl -XDELETE -i  'http://localhost:8081/studentservice/api/removeStudent/ST_53114d72-7f19-4d22-8c2d-605c5a1437e3'

ADD COURSE: 
  curl -XPATCH -i  'http://localhost:8081/studentservice/api/addCourse/{courseName}'
  i.e. curl -XPATCH -i  'http://localhost:8081/studentservice/api/addCourse/Social_Science'

GET ALL COURSES: 
 curl -XGET -i  'http://localhost:8081/studentservice/api/getAllCourses'
 i.e. curl -XGET -i  'http://localhost:8081/studentservice/api/getAllCourses'

REMOVE COURSE:
 
 curl -XDELETE -i  'http://localhost:8081/studentservice/api/removeCourse/{courseName}'
 i.e. curl -XDELETE -i  'http://localhost:8081/studentservice/api/removeCourse/Math'

REGISTER STUDENT:

curl -XPATCH -i  'http://localhost:8081/studentservice/api/registerStudent/{studentId}/{courseName}'
 i.e. curl -XPATCH -i  'http://localhost:8081/studentservice/api/registerStudent/Neeraj/Math'
 
GET COURSEWISE STUDENT LIST:

curl -XGET -i  'http://localhost:8081/studentservice/api/getCourseWiseStudents/{courseName}'
 i.e. curl -XGET -i  'http://localhost:8081/studentservice/api/getCourseWiseStudents/Math'

 
