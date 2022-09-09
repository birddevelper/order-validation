package mst.example.ordervalidation.models.order;

import mst.example.ordervalidation.models.Part;
import mst.example.ordervalidation.models.ValidationResult;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReplacementOrderTest {

    @Test
    void WhenValidateCorrectReplacementOrder_ThenReturnTrueValidationResult() {
        ReplacementOrder replacementOrder = new ReplacementOrder();
        List<Part> parts = new ArrayList<>();
        parts.add(new Part("12345","part name",3));
        replacementOrder.setFactoryName("Good Factory")
                .setFactoryOrderNumber("LK98076547")
                .setType("REPLACEMENT")
                .setParts(parts)
                .setDepartment("GOoD replacement department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 13).getTime())
                .setCurrency("USD")
                .setCost(22.12);

        ValidationResult validationResult = replacementOrder.validate();

        assertTrue(validationResult.isValid());
        assertEquals(0,validationResult.getValidationErrors().size());

    }

    @Test
    void WhenValidateReplacementOrderWithEmptyFactoryName_ThenReturnFalseValidationResult() {
        ReplacementOrder replacementOrder = new ReplacementOrder();
        List<Part> parts = new ArrayList<>();
        parts.add(new Part("12345","part name",3));
        // When factoryName empty
        replacementOrder.setFactoryName("")
                .setFactoryOrderNumber("LK98076547")
                .setType("REPLACEMENT")
                .setParts(parts)
                .setDepartment("GOoD replacement department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 13).getTime())
                .setCurrency("USD")
                .setCost(22.12);

        ValidationResult validationResult = replacementOrder.validate();


        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("factoryName field is empty", validationResult.getValidationErrors().get(0).getError());

        // When factoryName is null
        replacementOrder.setFactoryName(null);
        validationResult = replacementOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("factoryName field is empty", validationResult.getValidationErrors().get(0).getError());


    }

    @Test
    void WhenValidateReplacementOrderWithInvalidFactoryOrderNumber_ThenReturnFalseValidationResult() {
        ReplacementOrder replacementOrder = new ReplacementOrder();
        List<Part> parts = new ArrayList<>();
        parts.add(new Part("12345","part name",3));
        // When factoryOrderNumber length less than 10
        replacementOrder.setFactoryName("Factory1")
                .setFactoryOrderNumber("LK9807654")
                .setType("REPLACEMENT")
                .setParts(parts)
                .setDepartment("GOoD replacement department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 13).getTime())
                .setCurrency("USD")
                .setCost(22.12);

        ValidationResult validationResult = replacementOrder.validate();


        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("factoryOrderNumber is invalid", validationResult.getValidationErrors().get(0).getError());

        // When factoryName start with 3 letters
        replacementOrder.setFactoryOrderNumber("YRD9087654");
        validationResult = replacementOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("factoryOrderNumber is invalid", validationResult.getValidationErrors().get(0).getError());

        // When factoryName start with number
        replacementOrder.setFactoryOrderNumber("9087654DGT");
        validationResult = replacementOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("factoryOrderNumber is invalid", validationResult.getValidationErrors().get(0).getError());


        // When factoryName is null
        replacementOrder.setFactoryOrderNumber(null);
        validationResult = replacementOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("factoryOrderNumber field is empty", validationResult.getValidationErrors().get(0).getError());


    }

    @Test
    void WhenValidateReplacementOrderWithEmptyInventoryNumber_ThenReturnFalseValidationResult() {
        ReplacementOrder replacementOrder = new ReplacementOrder();
        List<Part> parts = new ArrayList<>();
        parts.add(new Part("","part name",3));
        parts.add(new Part(null,"part name2",3));
        parts.add(new Part("4567845","part name",3));
        // When factoryName empty
        replacementOrder.setFactoryName("Factory 5")
                .setFactoryOrderNumber("LK98076547")
                .setType("REPLACEMENT")
                .setParts(parts)
                .setDepartment("GOoD replacement department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 13).getTime())
                .setCurrency("USD")
                .setCost(22.12);

        ValidationResult validationResult = replacementOrder.validate();


        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("2 InventoryNumber(s) are empty", validationResult.getValidationErrors().get(0).getError());

        // When factoryName is null
        replacementOrder.setParts(null);
        validationResult = replacementOrder.validate();
        assertFalse(validationResult.isValid());
        assertEquals(1,validationResult.getValidationErrors().size());
        assertEquals("parts field is empty", validationResult.getValidationErrors().get(0).getError());


    }
}