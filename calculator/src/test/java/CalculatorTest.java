import com.witSoftware.CalculatorOperations;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private final CalculatorOperations calculator = new CalculatorOperations();

    @Test
    public void testSumWithPositiveNumbers() {
        BigDecimal result = calculator.sum(new BigDecimal("1.5"), new BigDecimal("2.5"));
        assertEquals(new BigDecimal("4.0").stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    public void testSumWithNegativeNumbers() {
        BigDecimal result = calculator.sum(new BigDecimal("-3.0"), new BigDecimal("-2.0"));
        assertEquals(new BigDecimal("-5.0").stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    public void testSumWithMixedNumbers() {
        BigDecimal result = calculator.sum(new BigDecimal("-3.0"), new BigDecimal("5.0"));
        assertEquals(new BigDecimal("2.0").stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    public void testSubtractWithPositiveNumbers() {
        BigDecimal result = calculator.subtract(new BigDecimal("5.0"), new BigDecimal("3.0"));
        assertEquals(new BigDecimal("2.0").stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    public void testSubtractWithNegativeNumbers() {
        BigDecimal result = calculator.subtract(new BigDecimal("-5.0"), new BigDecimal("-3.0"));
        assertEquals(new BigDecimal("-2.0").stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    public void testSubtractWithMixedNumbers() {
        BigDecimal result = calculator.subtract(new BigDecimal("-5.0"), new BigDecimal("3.0"));
        assertEquals(new BigDecimal("-8.0").stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    public void testMultiplyWithPositiveNumbers() {
        BigDecimal result = calculator.multiply(new BigDecimal("2.0"), new BigDecimal("3.5"));
        assertEquals(new BigDecimal("7.0").stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    public void testMultiplyWithNegativeNumbers() {
        BigDecimal result = calculator.multiply(new BigDecimal("-2.0"), new BigDecimal("-3.5"));
        assertEquals(new BigDecimal("7.0").stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    public void testMultiplyWithMixedNumbers() {
        BigDecimal result = calculator.multiply(new BigDecimal("-2.0"), new BigDecimal("3.5"));
        assertEquals(new BigDecimal("-7.0").stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    public void testDivideWithPositiveNumbers() {
        BigDecimal result = calculator.divide(new BigDecimal("10.0"), new BigDecimal("4.0"));
        assertEquals(new BigDecimal("2.50").stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    public void testDivideWithNegativeNumbers() {
        BigDecimal result = calculator.divide(new BigDecimal("-10.0"), new BigDecimal("-4.0"));
        assertEquals(new BigDecimal("2.50").stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    public void testDivideWithMixedNumbers() {
        BigDecimal result = calculator.divide(new BigDecimal("-10.0"), new BigDecimal("4.0"));
        assertEquals(new BigDecimal("-2.50").stripTrailingZeros(), result.stripTrailingZeros());
    }

    @Test
    public void testDivideByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(new BigDecimal("10.0"), BigDecimal.ZERO);
        });
        assertEquals("Division by zero is not allowed.", exception.getMessage());
    }
}
