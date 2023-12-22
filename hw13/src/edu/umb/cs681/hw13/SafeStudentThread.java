package edu.umb.cs681.hw13;

public class SafeStudentThread extends Thread {
    private SafeExamSubmission exam;
    private String studentId;

    public SafeStudentThread(SafeExamSubmission exam, String studentId) {
        this.exam = exam;
        this.studentId = studentId;
    }

    public void run() {

        exam.submitAnswer(studentId, "My Answer");
    }

    public static void main(String[] args) throws InterruptedException {
        SafeExamSubmission exam = new SafeExamSubmission();
        // Starting threads as if students are submitting
        new SafeStudentThread(exam, "Student1").start();
        new SafeStudentThread(exam, "Student2").start();

        // Simulate end of exam after a brief moment
        Thread.sleep(1000); // 1 second until exam ends
        exam.closeSubmissions(); // Initiate 2-step termination
    }
}
