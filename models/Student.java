package models;

public class Student {

    String ID, name;
    float Average;

    public Student() {
    }

    public Student(String iD, String name, float average) {
        ID = iD;
        this.name = name;
        Average = average;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAverage() {
        return Average;
    }

    public void setAverage(float average) {
        Average = average;
    }

    @Override
    public String toString() {
        return "Student [ID=" + ID + ", name=" + name + ", Average=" + Average + "]";
    }

    public String stringToWrite() {
        return ID + ";" + name + ";" + Average;
    }

}
