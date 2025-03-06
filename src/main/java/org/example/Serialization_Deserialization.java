package org.example;


//Serialisation==Converting Pojo object into JSON


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class Serialization_Deserialization {
    @Test
    public void ConvetPojoToJson() throws JsonProcessingException {
        //Created Java Object using Pojo Class
        Student data = new Student();
        data.setName("Mani");
        data.setLocation("HYD");
        data.setPhone("12");
        String CoursesArr[] = {"C", "C++"};
        data.setCourses(CoursesArr);

        //Converting Java Object to Json Object
        ObjectMapper obj = new ObjectMapper();
        String value = obj.writerWithDefaultPrettyPrinter().writeValueAsString(data);
        System.out.println(value);
    }

    @Test
    public void JsonToConvetPojo() throws JsonProcessingException {
        String jsondata = "{\n" +
                "  \"name\" : \"Mani\",\n" +
                "  \"Location\" : \"HYD\",\n" +
                "  \"phone\" : \"12\",\n" +
                "  \"Courses\" : [ \"C\", \"C++\" ],\n" +
                "  \"location\" : \"HYD\",\n" +
                "  \"courses\" : [ \"C\", \"C++\" ]\n" +
                "}";
        ObjectMapper obj = new ObjectMapper();
        Student stuobj = obj.readValue(jsondata, Student.class);
        System.out.println(stuobj.getName());
        System.out.println(stuobj.getLocation());
        System.out.println(stuobj.getPhone());
        System.out.println(stuobj.getCourses()[0]);
        System.out.println(stuobj.getCourses()[1]);
    }
}
