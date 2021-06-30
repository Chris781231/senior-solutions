package testconverter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileService {

    private static final String SEPARATOR = ";";
    private final List<TestQuestion> questions = new ArrayList<>();

    public void readFromFile(Path file) {
        try {
            List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

            List<String> testQuestions = getTestQuestions(lines);

            if (testQuestions.size() == 0) return;

            for (String testQuestion : testQuestions) {
                processTestQuestion(testQuestion);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file", ioe);
        }
    }

    public void saveToFile(Path file) {
        try (BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(file, StandardCharsets.UTF_8))) {
            for (TestQuestion question : questions) {
                writer.write(question.getQuestion() + SEPARATOR);
                writer.write(question.getCorrectRowNum() + SEPARATOR);
                writer.write(question.getFirstAnswer() + SEPARATOR);
                writer.write(question.getSecondAnswer() + SEPARATOR);
                writer.write(question.getThirdAnswer() + SEPARATOR);
                writer.write(question.getFourthAnswer() + System.lineSeparator());
            }

        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot reach file", ioe);
        }
    }

    private List<String> getTestQuestions(List<String> lines) {
        boolean questionStart = false;
        StringBuilder sb = new StringBuilder();
        List<String> testQuestions = new ArrayList<>();

        for (String line : lines) {
            if (line.startsWith("<h1") && !questionStart) {
                questionStart = true;
            }
            if (questionStart) {
                sb.append(line);
            }
            if (line.endsWith("</ul>")) {
                questionStart = false;
                testQuestions.add(sb.toString());
                sb = new StringBuilder();
            }
        }

        return testQuestions;
    }

    private void processTestQuestion(String s) {
        String question = s.substring(s.indexOf("<h1"), s.indexOf("<ul>"));
        question = trimQuestion(question);

        List<String> answers = new ArrayList<>();

        int correctAnswer = getAnswers(s, answers);

        addToQuestionList(question, correctAnswer, answers);
    }

    private String trimQuestion(String s) {
        String header = s.substring(s.indexOf(">") + 1, s.indexOf("</h1>"));
        String description = s.substring(s.indexOf("<p>") + 3, s.indexOf("</p>"));
        return header + " - " + description;
    }

    private String trimAnswer(String s) {
        String result = s.substring(s.indexOf(">") + 1);
        if (result.contains("input")) {
            result = result.substring(result.indexOf("/>") + 2).trim();
        }
        return result;
    }

    private int getAnswers(String s, List<String> answers) {
        int correctAnswer = 0;
        int actIdxOfLi = s.indexOf("<li");
        int actIdxOfClosingLi = s.indexOf("</li>");

        while (actIdxOfLi != -1) {
            String answer = s.substring(actIdxOfLi, actIdxOfClosingLi);
            if (answer.contains("<input")) {
                correctAnswer = answers.size() + 1;
            }
            answer = trimAnswer(answer);
            answers.add(answer);
            actIdxOfLi = s.indexOf("<li", actIdxOfClosingLi);
            actIdxOfClosingLi = s.indexOf("</li", actIdxOfLi);
        }
        return correctAnswer;
    }

    private String getCodeString(String s) {
        String result = s;
        return result;
    }

    private void addToQuestionList(String question, int correctAnswer, List<String> answers) {
        questions.add(new TestQuestion(
                question,
                correctAnswer,
                answers.get(0),
                answers.get(1),
                answers.get(2),
                answers.get(3)
        ));
    }

    public List<TestQuestion> getQuestions() {
        return new ArrayList<>(questions);
    }
}
