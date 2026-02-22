
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class quizgame {
    static int reward = 0;
    static boolean fiftyUsed = false;
    static boolean pollUsed = false;

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(null, "Enter Player Name:", "Quiz Game Login", JOptionPane.QUESTION_MESSAGE);
        if (name == null || name.isEmpty()) name = "Guest";

        JOptionPane.showMessageDialog(null, "Login Successful!\nWelcome " + name, "Welcome", JOptionPane.INFORMATION_MESSAGE);

        // Data for 10 Questions
        String[] questions = {
            "Q1: Which loop executes at least once?",
            "Q2: Which keyword is used to inherit a class?",
            "Q3: Which of these is not a primitive data type?",
            "Q4: What is the default value of an integer variable?",
            "Q5: Which package contains the Random class?",
            "Q6: Which method is the entry point of a Java program?",
            "Q7: What is the size of double in Java?",
            "Q8: Which keyword is used to define a constant?",
            "Q9: Which of these is used to handle exceptions?",
            "Q10: Which company developed Java?"
        };

        String[][] options = {
            {"1. for", "2. while", "3. do-while", "4. if"},
            {"1. implements", "2. extends", "3. import", "4. super"},
            {"1. int", "2. float", "3. boolean", "4. String"},
            {"1. 0", "2. 1", "3. null", "4. -1"},
            {"1. java.lang", "2. java.util", "3. java.io", "4. java.net"},
            {"1. start()", "2. init()", "3. main()", "4. run()"},
            {"1. 2 bytes", "2. 4 bytes", "3. 8 bytes", "4. 16 bytes"},
            {"1. static", "2. final", "3. constant", "4. fixed"},
            {"1. try-catch", "2. throw-check", "3. error-fix", "4. if-else"},
            {"1. Microsoft", "2. Apple", "3. Sun Microsystems", "4. Google"}
        };

        int[] answers = {3, 2, 4, 1, 2, 3, 3, 2, 1, 3}; // Correct options 1-4

        // Main Game Loop for 10 Questions
        for (int i = 0; i < questions.length; i++) {
            boolean result = quizLogic(name, questions[i], options, answers, i);
            if (!result) return; // Exit if user gets it wrong or cancels
        }

        JOptionPane.showMessageDialog(null, "CONGRATULATIONS " + name + 
            "\nYou completed all 10 questions!\nFinal Reward: " + reward, "Ultimate Winner", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean quizLogic(String name, String questionText, String[][] options, int[] answers, int l) {
        List<Integer> visibleIndices = new ArrayList<>();
        for (int i = 0; i < 4; i++) visibleIndices.add(i);

        while (true) {
            StringBuilder q = new StringBuilder(questionText + "\n\n");
            
            for (int idx : visibleIndices) {
                q.append(options[l][idx]).append("\n");
            }

            if (l != 9) { // Lifelines available until the last question
                if (!fiftyUsed) q.append("\n* 50-50 lifeline");
                if (!pollUsed) q.append("\n+ Audience Poll");
            }

            q.append("\n\nEnter option number:");

            String input = JOptionPane.showInputDialog(null, q.toString(), "Question " + (l + 1), JOptionPane.QUESTION_MESSAGE);

            if (input == null) return false;

            // 50-50 logic
            if (input.equals("*") && l != 9) {
                if (fiftyUsed) {
                    JOptionPane.showMessageDialog(null, "50-50 already used!", "Lifeline", JOptionPane.WARNING_MESSAGE);
                } else {
                    applyFiftyFifty(visibleIndices, answers[l]);
                    fiftyUsed = true;
                }
                continue;
            }

            // Audience Poll logic
            if (input.equals("+") && l != 9) {
                if (pollUsed) {
                    JOptionPane.showMessageDialog(null, "Poll already used!", "Lifeline", JOptionPane.WARNING_MESSAGE);
                } else {
                    showAudiencePoll(answers[l]);
                    pollUsed = true;
                }
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice == answers[l]) {
                    reward += 200;
                    JOptionPane.showMessageDialog(null, "Correct! Reward: " + reward, "Success", JOptionPane.INFORMATION_MESSAGE);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong Answer! You are eliminated!", "Game Over", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid choice!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    static void applyFiftyFifty(List<Integer> visible, int correct) {
        List<Integer> wrong = new ArrayList<>();
        for (int idx : visible) {
            if (idx + 1 != correct) wrong.add(idx);
        }
        Collections.shuffle(wrong);
        visible.remove(wrong.get(0));
        visible.remove(wrong.get(1));
    }

    static void showAudiencePoll(int correct) {
        Random r = new Random();
        int[] percent = new int[4];
        int remain = 100;

        percent[correct - 1] = 45 + r.nextInt(16);
        remain -= percent[correct - 1];

        for (int i = 0; i < 4; i++) {
            if (i == correct - 1) continue;
            percent[i] = remain / 3;
        }

        StringBuilder poll = new StringBuilder("Audience Response:\n\n");
        for (int i = 0; i < 4; i++) {
            poll.append((i + 1)).append(". ");
            for (int j = 0; j < percent[i] / 2; j++) poll.append("|");
            poll.append(" (").append(percent[i]).append("%)\n");
        }
        JOptionPane.showMessageDialog(null, poll.toString(), "Audience Poll", JOptionPane.INFORMATION_MESSAGE);
    }
}