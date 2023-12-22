### README for Online Exam System

#### Overview:
This project showcases two versions of an online exam system: the thread-unsafe `UnsafeExamSubmission` and the thread-safe `SafeExamSubmission` with 2-step termination. These demonstrate handling student submissions with and without proper synchronization.

#### Files:
- `UnsafeExamSubmission.java`: Original version prone to race conditions.
- `SafeExamSubmission.java`: Revised version ensuring thread safety and graceful termination.

#### Thread-Unsafe vs. Thread-Safe:
- **Unsafe Version**: Allows simultaneous submissions, risking data corruption.
- **Safe Version**: Uses `ReentrantLock` and a 2-step termination process to manage submissions securely and reliably.

#### Running the Code:
- Compile and run each file to observe the differences in handling concurrent submissions.
- The unsafe version shows potential submission overlaps, while the safe version demonstrates synchronized submissions and orderly termination.

#### Conclusion:
Implementing thread safety and a 2-step termination process in the `SafeExamSubmission` ensures reliable and fair handling of student submissions, addressing the critical issues found in the `UnsafeExamSubmission`.
