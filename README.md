# KrimsonKourses
A console-based academic course registration system written in Java.
## Features
- Add and manage students
- Add and manage courses
- Assign courses to students
- View detailed student and course information
- Summary of total students and courses
- Command-based input processing with validation
## Tech Stack
- Java (JDK 11+)
- Standard Java Collections (HashMap, ArrayList, etc.)
- Modular OOP design (Classes: KrimsonKourses, Student, Course)
## How to Run
1.	Clone the repository
git clone https://github.com/your-username/KrimsonKourses.git  
cd KrimsonKourses  
2.	Compile the project
javac com/krimsonkourses/platform/*.java  
3.	Run the program
Create a main class to drive the system (e.g., Main.java) or integrate with a CLI interface.
java com.krimsonkourses.platform.Main  
# Example Commands
ADD_STUDENT Alice Johnson 101  
ADD_COURSE Math101 1  
ASSIGN_COURSE 101 1  
GET_STUDENT_DETAILS 101  
GET_COURSE_DETAILS 1  
GET_SUMMARY  
# Notes
•	Duplicate student IDs and course sequences are rejected.
•	Invalid commands return error messages like REQUEST_PATTERN_INVALID or COMMAND_NOT_FOUND.

