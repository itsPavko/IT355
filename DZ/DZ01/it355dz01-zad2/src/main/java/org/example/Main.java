package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StudentReader studentReader = new StudentReader();
        try {
            studentReader.readStudents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}