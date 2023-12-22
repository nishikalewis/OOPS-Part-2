package edu.umb.cs681.hw13;

public class StudentThread extends Thread {
    private UnsafeExamSubmission exam;
    private String studentId;

    public StudentThread(UnsafeExamSubmission exam, String studentId) {
        this.exam = exam;
        this.studentId = studentId;
    }

    public void run() {
        exam.submitAnswer(studentId, "My Answer");
    }

    public static void main(String[] args) {
        UnsafeExamSubmission exam = new UnsafeExamSubmission();
        // Simulating multiple students submitting at the same time
        new StudentThread(exam, "Student1").start();
        new StudentThread(exam, "Student2").start();
    }
}
