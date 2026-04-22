package com.example.dao;

import com.example.model.Module;
import com.example.model.Student;
import com.example.model.Teacher;
import com.example.model.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class MockDatabase {
    public static final List<Teacher> TEACHERS = new ArrayList<>();
    public static final List<Student> STUDENTS = new ArrayList<>();
    public static final List<Module> MODULES = new ArrayList<>();
    public static final Map<String, Room> ROOMS = new HashMap<>();
    public static final Map<String, Sensor> SENSORS = new HashMap<>();
    public static final Map<String, List<SensorReading>> READINGS = new HashMap<>();

    static {
        // Initialise Teachers
        TEACHERS.add(new Teacher(1, "Mr. Smith"));
        TEACHERS.add(new Teacher(2, "Ms. Johnson"));

        // Initialise Students
        STUDENTS.add(new Student(1, "John Doe"));
        STUDENTS.add(new Student(2, "Jane Doe"));

        // Initialise Modules
        MODULES.add(new Module(1, "Math", TEACHERS.get(0)));
        MODULES.add(new Module(2, "Science", TEACHERS.get(1)));
    }
}