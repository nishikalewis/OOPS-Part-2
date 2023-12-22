package edu.umb.cs681.hw13;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeExamSubmission {
    private final Lock submitLock = new ReentrantLock();
    private volatile boolean isSubmissionOpen = true; // Flag for 2-step termination

    // This method is now thread-safe
    public void submitAnswer(String studentId, String answer) {
        if (!isSubmissionOpen) {
            System.out.println("Submission is closed for " + studentId);
            return;
        }

        submitLock.lock(); // Ensure only one student can submit at a time
        try {
            // Critical section - only one thread at a time
            if (isSubmissionOpen) {
                System.out.println(studentId + " is submitting an answer safely...");
                // Code here to safely save the answer
            } else {
                System.out.println("Submission was closed while " + studentId + " was submitting.");
            }
        } finally {
            submitLock.unlock();
        }
    }

    // Method to close submissions, initiating the 2-step termination
    public void closeSubmissions() {
        System.out.println("Closing submissions...");
        isSubmissionOpen = false;
    }
}

