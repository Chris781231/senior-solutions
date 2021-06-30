package testconverter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestQuestion {

    private final String question;
    private final int correctRowNum;
    private final String firstAnswer;
    private final String secondAnswer;
    private final String thirdAnswer;
    private final String fourthAnswer;

}
