package mst.example.ordervalidation.services;

import mst.example.ordervalidation.models.Part;
import mst.example.ordervalidation.models.ValidationResult;
import mst.example.ordervalidation.models.order.AnalysisOrder;
import mst.example.ordervalidation.models.order.RepairOrder;
import mst.example.ordervalidation.models.order.ReplacementOrder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class OrderValidationServiceImplTest {

    @Resource
    private OrderValidationServiceImpl orderValidationService;

    @Test
    void WhenValidateCorrectAnalysisOrder_ThenReturnTrueValidationResult() {
        AnalysisOrder analysisOrder = new AnalysisOrder();
        analysisOrder.setType("ANALYSIS")
                .setDepartment("GOoD analysis department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 13).getTime())
                .setCurrency("USD")
                .setCost(22.12);

        ValidationResult validationResult = orderValidationService.validateOrder(analysisOrder);

        assertTrue(validationResult.isValid());
        assertEquals(0,validationResult.getValidationErrors().size());
    }

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


        ValidationResult validationResult = orderValidationService.validateOrder(repairOrder);

        assertTrue(validationResult.isValid());
        assertEquals(0,validationResult.getValidationErrors().size());
    }

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

        ValidationResult validationResult = orderValidationService.validateOrder(replacementOrder);

        assertTrue(validationResult.isValid());
        assertEquals(0,validationResult.getValidationErrors().size());

    }



}