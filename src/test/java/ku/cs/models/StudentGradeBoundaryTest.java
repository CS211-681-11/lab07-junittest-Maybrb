package ku.cs.models;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentGradeBoundaryTest {

    @ParameterizedTest(name = "score {0} => grade {1}")
    @CsvSource({
            "80, A", "79.99, B",
            "70, B", "69.99, C",
            "60, C", "59.99, D",
            "50, D", "49.99, F",
            "0, F"
    })
    void boundaryGrades(double score, String expected) {
        Student s = new Student("X", "Boundary");
        s.addScore(score);
        assertEquals(expected, s.grade());
    }

}
