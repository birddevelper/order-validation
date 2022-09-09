package mst.example.ordervalidation.models.order;

import mst.example.ordervalidation.models.Part;
import mst.example.ordervalidation.models.ValidationResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepairOrderTest {

    @Test
    void WhenValidateCorrectRepairOrder_ThenReturnTrueValidationResult() {
        RepairOrder repairOrder = new RepairOrder();
        List<Part> parts = new ArrayList<>();
        parts.add(new Part("12345","part name",3));
        repairOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 12).getTime())
                .setResponsiblePerson("Alex")
                .setTestDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 13).getTime())
                .setParts(parts)
                .setType("REPAIR")
                .setDepartment("GOoD repair department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 15).getTime())
                .setCurrency("USD")
                .setCost(22.12);


        ValidationResult validationResult = repairOrder.validate();

        assertTrue(validationResult.isValid());
        assertEquals(0,validationResult.getValidationErrors().size());

    }

    @Test
    void WhenValidateRepairOrderWithInvalidOrEmptyAnalysisDate_ThenReturnFalseValidationResult() {
        RepairOrder repairOrder = new RepairOrder();
        List<Part> parts = new ArrayList<>();
        parts.add(new Part("12345","part name",3));


        repairOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 10).getTime())
                .setResponsiblePerson("Alex")
                .setTestDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 13).getTime())
                .setParts(parts)
                .setType("REPAIR")
                .setDepartment("GOoD repair department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 15).getTime())
                .setCurrency("USD")
                .setCost(22.12);


        ValidationResult validationResult = repairOrder.validate();

        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("analysisDate is not valid",validationResult.getValidationErrors().get(0).getError());

        //When analysisDate is null
        repairOrder.setAnalysisDate(null);
        validationResult = repairOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("analysisDate field is empty",validationResult.getValidationErrors().get(0).getError());

        //When startDate is empty and analysisDate is after endDate and testDate is before endDate
        repairOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 16).getTime())
                .setStartDate(null);
        validationResult = repairOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(3,validationResult.getValidationErrors().size());


    }


    @Test
    void WhenValidateRepairOrderWithEmptyPerson_ThenReturnFalseValidationResult() {
        RepairOrder repairOrder = new RepairOrder();
        List<Part> parts = new ArrayList<>();
        parts.add(new Part("12345","part name",3));
        repairOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 12).getTime())
                .setResponsiblePerson("")
                .setTestDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 13).getTime())
                .setParts(parts)
                .setType("REPAIR")
                .setDepartment("GOoD repair department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 15).getTime())
                .setCurrency("USD")
                .setCost(22.12);


        ValidationResult validationResult = repairOrder.validate();

        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("responsiblePerson field is empty",validationResult.getValidationErrors().get(0).getError());

        repairOrder.setResponsiblePerson(null);
        validationResult = repairOrder.validate();

        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("responsiblePerson field is empty",validationResult.getValidationErrors().get(0).getError());

    }


    @Test
    void WhenValidateRepairOrderWithInvalidOrEmptyTestDate_ThenReturnFalseValidationResult() {
        RepairOrder repairOrder = new RepairOrder();
        List<Part> parts = new ArrayList<>();
        parts.add(new Part("12345","part name",3));
        repairOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 12).getTime())
                .setResponsiblePerson("Alex")
                .setTestDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setParts(parts)
                .setType("REPAIR")
                .setDepartment("GOoD repair department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 15).getTime())
                .setCurrency("USD")
                .setCost(22.12);


        ValidationResult validationResult = repairOrder.validate();

        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("testDate is not valid",validationResult.getValidationErrors().get(0).getError());

        repairOrder.setTestDate(null);
        validationResult = repairOrder.validate();

        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("testDate field is empty",validationResult.getValidationErrors().get(0).getError());

    }

    @Test
    void WhenValidateRepairOrderWithInvalidTestDateAndInvalidAnalysisDate_ThenReturnFalseValidationResult() {
        RepairOrder repairOrder = new RepairOrder();
        List<Part> parts = new ArrayList<>();
        parts.add(new Part("12345","part name",3));

        // When analysisDate is afterEnd and testDate is before analysisDate
        repairOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 18).getTime())
                .setResponsiblePerson("Alex")
                .setTestDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setParts(parts)
                .setType("REPAIR")
                .setDepartment("GOoD repair department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 12).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 15).getTime())
                .setCurrency("USD")
                .setCost(22.12);


        ValidationResult validationResult = repairOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(2,validationResult.getValidationErrors().size());

        // When analysisDate is empty and testDate is before startDate
        repairOrder.setAnalysisDate(null);
        validationResult = repairOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(2,validationResult.getValidationErrors().size());

        // When analysisDate is empty and testDate is after startDate
        repairOrder.setAnalysisDate(null);
        repairOrder.setTestDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 13).getTime());
        validationResult = repairOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());

        // When analysisDate is empty and testDate is after endDate
        repairOrder.setAnalysisDate(null);
        repairOrder.setTestDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 16).getTime());
        validationResult = repairOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(2,validationResult.getValidationErrors().size());

    }

    @Test
    void WhenValidateRepairOrderWithPartsCountLessThanOne_ThenReturnTrueValidationResult() {
        RepairOrder repairOrder = new RepairOrder();
        List<Part> parts = new ArrayList<>();
        //When we have parts, but with zero count
        parts.add(new Part("12345","part name",0));
        parts.add(new Part("12346","second part name",0));
        repairOrder.setAnalysisDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 12).getTime())
                .setResponsiblePerson("Alex")
                .setTestDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 13).getTime())
                .setParts(parts)
                .setType("REPAIR")
                .setDepartment("GOoD repair department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 15).getTime())
                .setCurrency("USD")
                .setCost(22.12);


        ValidationResult validationResult = repairOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("parts total count is not greater than 0",validationResult.getValidationErrors().get(0).getError());

        // When we have no part
        repairOrder.setParts(null);
        validationResult = repairOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("parts field is empty",validationResult.getValidationErrors().get(0).getError());

    }
}