package mst.example.ordervalidation.models.order;

import mst.example.ordervalidation.models.ValidationResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class AnalysisOrderTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void WhenValidateCorrectAnalysisOrder_ThenReturnTrueValidationResult() {
        AnalysisOrder analysisOrder = new AnalysisOrder();
        analysisOrder.setType("ANALYSIS")
                .setDepartment("GOoD analysis department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 13).getTime())
                .setCurrency("USD")
                .setCost(22.12);

        ValidationResult validationResult = analysisOrder.validate();

        assertTrue(validationResult.isValid());
        assertEquals(0,validationResult.getValidationErrors().size());

    }

    @Test
    void WhenValidateAnalysisOrderWithEmptyOrInvalidDepartment_ThenReturnFalseValidationResult() {
        AnalysisOrder analysisOrder = new AnalysisOrder();


        analysisOrder.setType("ANALYSIS")
                // Test with invalid department
                .setDepartment("G department")
                .setStartDate(new GregorianCalendar(2022, Calendar.SEPTEMBER, 1).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.SEPTEMBER, 3).getTime())
                .setCurrency("GBP")
                .setCost(10);

        ValidationResult validationResult = analysisOrder.validate();

        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("Provided department is not valid",validationResult.getValidationErrors().get(0).getError());

        // Test with empty department
        analysisOrder.setDepartment("");
        validationResult = analysisOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("Department field is empty",validationResult.getValidationErrors().get(0).getError());

    }

    @Test
    void WhenValidateAnalysisOrderWithStartDateAfterCurrentDayOrNull_ThenReturnFalseValidationResult() {
        AnalysisOrder analysisOrder = new AnalysisOrder();
        analysisOrder.setType("ANALYSIS")
                .setDepartment("GOoD analysis department")
                .setStartDate(new GregorianCalendar(2023, Calendar.SEPTEMBER, 11).getTime())
                .setEndDate(new GregorianCalendar(2023, Calendar.SEPTEMBER, 13).getTime())
                .setCurrency("USD")
                .setCost(22.12);

        ValidationResult validationResult = analysisOrder.validate();

        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("startDate field is not before current date",validationResult.getValidationErrors().get(0).getError());

        analysisOrder.setStartDate(null);
        validationResult = analysisOrder.validate();
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("startDate field is empty",validationResult.getValidationErrors().get(0).getError());

    }

    @Test
    void WhenValidateAnalysisOrderWithEndDateBeforeStartDateOrNull_ThenReturnFalseValidationResult() {
        AnalysisOrder analysisOrder = new AnalysisOrder();
        analysisOrder.setType("ANALYSIS")
                .setDepartment("GOoD analysis department")
                .setStartDate(new GregorianCalendar(2022, Calendar.SEPTEMBER, 1).getTime())
                .setEndDate(new GregorianCalendar(2021, Calendar.SEPTEMBER, 13).getTime())
                .setCurrency("USD")
                .setCost(22.12);

        ValidationResult validationResult = analysisOrder.validate();

        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("endDate field is not after startDate",validationResult.getValidationErrors().get(0).getError());

        analysisOrder.setEndDate(null);
        validationResult = analysisOrder.validate();
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("endDate field is empty",validationResult.getValidationErrors().get(0).getError());

    }

    @Test
    void WhenValidateAnalysisOrderWithInvalidStartDateAndEndDate_ThenReturnFalseValidationResultWithTwoMessage() {
        AnalysisOrder analysisOrder = new AnalysisOrder();
        analysisOrder.setType("ANALYSIS")
                .setDepartment("GOoD analysis department")
                .setStartDate(new GregorianCalendar(2023, Calendar.SEPTEMBER, 1).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.SEPTEMBER, 13).getTime())
                .setCurrency("USD")
                .setCost(22.12);

        ValidationResult validationResult = analysisOrder.validate();

        assertFalse(validationResult.isValid());
        assertEquals(2,validationResult.getValidationErrors().size());

    }

    @Test
    void WhenValidateAnalysisOrderWithInvalidCurrency_ThenReturnFalseValidationResult() {
        AnalysisOrder analysisOrder = new AnalysisOrder();
        analysisOrder.setType("ANALYSIS")
                .setDepartment("GOoD analysis department")
                .setStartDate(new GregorianCalendar(2022, Calendar.SEPTEMBER, 1).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.SEPTEMBER, 3).getTime())
                .setCurrency("UFD")
                .setCost(22.12);

        ValidationResult validationResult = analysisOrder.validate();

        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("currency code is not valid iso currency code",validationResult.getValidationErrors().get(0).getError());

    }

    @Test
    void WhenValidateAnalysisOrderWithInvalidCost_ThenReturnFalseValidationResult() {
        AnalysisOrder analysisOrder = new AnalysisOrder();
        analysisOrder.setType("ANALYSIS")
                .setDepartment("GOoD analysis department")
                .setStartDate(new GregorianCalendar(2022, Calendar.SEPTEMBER, 1).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.SEPTEMBER, 3).getTime())
                .setCurrency("GBP")
                .setCost(0);

        ValidationResult validationResult = analysisOrder.validate();

        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("cost is not greater than 0",validationResult.getValidationErrors().get(0).getError());

    }


}