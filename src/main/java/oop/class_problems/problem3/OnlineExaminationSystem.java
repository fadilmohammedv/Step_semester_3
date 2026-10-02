package oop.class_problems.problem3;

import java.util.ArrayList;
import java.util.List;

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Student student = new Student("Student 1");
        Examination examination = new Examination("Exam A");
        examination.addQuestion(new MultipleChoiceQuestion("Question 1", "C", 5));
        examination.addQuestion(new TrueFalseQuestion("Question 2", true, 5));

        Attempt attempt = student.start(examination);
        System.out.println(attempt.answer(0, "C"));
        System.out.println(attempt.answer(1, true));
        System.out.println(attempt.submit());
        System.out.println(attempt.answer(0, "A"));
    }
}

interface Question {
    boolean evaluate(Object answer);

    int getPoints();

    String getPrompt();
}

class MultipleChoiceQuestion implements Question {
    private final String prompt;
    private final String correctOption;
    private final int points;

    public MultipleChoiceQuestion(String prompt, String correctOption, int points) {
        this.prompt = prompt;
        this.correctOption = correctOption;
        this.points = points;
    }

    @Override
    public boolean evaluate(Object answer) {
        return correctOption.equals(answer);
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public String getPrompt() {
        return prompt;
    }
}

class TrueFalseQuestion implements Question {
    private final String prompt;
    private final boolean correctAnswer;
    private final int points;

    public TrueFalseQuestion(String prompt, boolean correctAnswer, int points) {
        this.prompt = prompt;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }

    @Override
    public boolean evaluate(Object answer) {
        return answer instanceof Boolean && correctAnswer == (Boolean) answer;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public String getPrompt() {
        return prompt;
    }
}

class Examination {
    private final String title;
    private final List<Question> questions = new ArrayList<>();

    public Examination(String title) {
        this.title = title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getTitle() {
        return title;
    }
}

class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public Attempt start(Examination examination) {
        return new Attempt(this, examination);
    }

    public String getName() {
        return name;
    }
}

class Attempt {
    private final Student student;
    private final Examination examination;
    private final List<Object> answers = new ArrayList<>();
    private boolean submitted;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        for (int i = 0; i < examination.getQuestions().size(); i++) {
            answers.add(null);
        }
    }

    public String answer(int questionIndex, Object answer) {
        if (submitted) {
            return "Cannot change answers for a submitted examination";
        }
        answers.set(questionIndex, answer);
        return "Answer recorded for " + examination.getQuestions().get(questionIndex).getPrompt();
    }

    public String submit() {
        if (submitted) {
            return "Examination already submitted";
        }
        submitted = true;
        int score = 0;
        for (int i = 0; i < answers.size(); i++) {
            Question question = examination.getQuestions().get(i);
            if (question.evaluate(answers.get(i))) {
                score += question.getPoints();
            }
        }
        return "Exam " + examination.getTitle() + " submitted by " + student.getName()
                + ". Total score: " + score + "/" + totalPoints();
    }

    private int totalPoints() {
        return examination.getQuestions().stream().mapToInt(Question::getPoints).sum();
    }
}
