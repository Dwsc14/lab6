package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import models.Student;

public class School extends ArrayList<Student> {

    static Scanner sc = new Scanner(System.in);

    public Student searchToFindIdDup(String nStudentID) {
        nStudentID = nStudentID.trim().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equals(nStudentID)) {
                return this.get(i);
            }
        }
        return null;
    }

    public boolean checkIdDup(String nStudentID) {
        return searchToFindIdDup(nStudentID) != null;
    }

    public String convertRightFormat(String nName) {
        nName = nName.trim();
        nName = nName.replaceAll("\\s+", " ");
        return nName;
    }

    public void sreachByID() {
        System.out.println("Enter ID: ");
        int count = 0;
        String StudentID = sc.nextLine();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getID().equals(StudentID)) {
                System.out.println(this.get(i).toString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No student was founded");
        } else {
            System.out.println(count + " student was founded");
        }
    }

    public void sreachByName() {
        System.out.println("Enter name: ");
        int count = 0;
        String name = sc.nextLine();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getName().equals(name)) {
                System.out.println(this.get(i).toString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No student was founded");
        } else {
            System.out.println(count + " student was founded");
        }
    }

    public void sreachByAverage() {
        System.out.println("Enter average: ");
        int count = 0;
        float average = sc.nextFloat();
        sc.nextLine();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getAverage() == average) {
                System.out.println(this.get(i).toString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No student was founded");
        } else {
            System.out.println(count + " student was founded");
        }
    }

    public void add() {
        String nStudentID;
        String nName;
        float nAverage;
        boolean idDup = false;

        do {
            System.out.println("Enter student ID: ");
            nStudentID = sc.nextLine();
            nStudentID = nStudentID.trim().toUpperCase();
            idDup = checkIdDup(nStudentID);
            if (idDup) {
                System.out.println("Student with this ID already exists.");
            }
        } while (idDup);

        System.out.println("Enter name: ");
        nName = sc.nextLine();
        nName = convertRightFormat(nName);

        System.out.println("Enter Average: ");
        do {
            try {
                String s = sc.nextLine();
                nAverage = Float.parseFloat(s);
                break;
            } catch (Exception e) {
                System.out.println("Wrong input!");
            }
        } while (true);

        Student nSudent = new Student(nStudentID, nName, nAverage);
        this.add(nSudent);
        writeFile(this.size() - 1);

        System.out.println("Studen with " + nStudentID + " added!");
    }

    public void showStudentList() {
        for (Student std : this) {
            System.out.println(std.toString());
        }
    }

    public void sortStudentByID() {
        Collections.sort(this, Comparator.comparing(Student::getID));
    }

    public void sortStudentByName() {
        Collections.sort(this, Comparator.comparing(Student::getName));
    }

    public void sortStudentByAverage() {
        Collections.sort(this, Comparator.comparing(Student::getAverage));
    }

    public void writeFile(int i) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("sch.txt", true));
            bw.write(this.get(i).stringToWrite());
            bw.newLine();
            bw.close();
        } catch (IOException e) {
        }

    }

    public void readFile() {
        String nStudentID;
        String nName;
        float nAverage;

        if (this.size() == 0) {
            try {
                FileReader fr = new FileReader("sch.txt");
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                while (true) {
                    line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    String txt[] = line.split("\\;");
                    nStudentID = txt[0].trim().toUpperCase();

                    nName = txt[1].trim();
                    nName = convertRightFormat(nName);

                    nAverage = Float.parseFloat(txt[2].trim());

                    Student st = new Student(nStudentID, nName, nAverage);
                    this.add(st);
                }

                fr.close();
                br.close();
            } catch (Exception e) {
            }
        }
    }

}
