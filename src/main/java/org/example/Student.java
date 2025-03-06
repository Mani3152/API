package org.example;

public class Student {
   public String name;
   public String Location;
   public String phone;

   public String[] getCourses() {
      return Courses;
   }

   public void setCourses(String[] courses) {
      Courses = courses;
   }

   public String Courses[];

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getLocation() {
      return Location;
   }

   public void setLocation(String location) {
      Location = location;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

}
