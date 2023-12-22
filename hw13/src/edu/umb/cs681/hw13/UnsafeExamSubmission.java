// A simplified model of an exam submission system

package edu.umb.cs681.hw13;
public class UnsafeExamSubmission {
    // This method is thread-unsafe
    public void submitAnswer(String studentId, String answer) {
        // Simulate the process of submitting an answer
        System.out.println(studentId + " is submitting an answer...");
        // Code here to save the answer, which is not thread-safe
        // Issues might arise when multiple threads (students) access this simultaneously
    }
}

