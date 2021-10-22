import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointsTest {


    @Test
    void correctAmountIsSet() {
        Points test = new Points();
        test.addPoints(59);
        assertEquals(59, test.getAmount());
    }

    @Test
    void correctAmountIsConverted() {
        Points test = new Points();
        test.addPoints(55.9);
        assertEquals(55, test.getAmount());
    }

    @Test
    void correctAmountIsAdded() {
        Points test = new Points();
        test.addPoints(60);
        test.addPoints(5);
        assertEquals(65, test.getAmount());
    }

    @Test
    void exceptionThrownWhenToMuchPointsIsRemoved() {
        assertThrows(IllegalArgumentException.class, () -> {
            Points test = new Points();
            test.removePoints(5);
        });
    }

    @Test
    void correctAmountIsRemoved() {
        Points test = new Points();
        test.addPoints(5.9);
        test.removePoints(5.9);
        assertEquals(0, test.getAmount());
    }

    @Test
    void exceptionThrownWhenIncompatibleNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            Points test = new Points();
            test.addPoints(0);
        });
    }
}