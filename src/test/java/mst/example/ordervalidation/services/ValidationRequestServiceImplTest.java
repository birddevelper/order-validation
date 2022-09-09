package mst.example.ordervalidation.services;

import mst.example.ordervalidation.models.ValidationRequest;
import mst.example.ordervalidation.models.order.AnalysisOrder;
import mst.example.ordervalidation.models.order.OrderStatus;
import mst.example.ordervalidation.repositories.ValidationRequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ValidationRequestServiceImplTest {

    @Resource
    private ValidationRequestServiceImpl validationRequestService;

    @Resource
    private ValidationRequestRepository validationRequestRepository;

    @Test
    void WhenStoreValidationRequest_ThenItSuccessfullySaved() {

        AnalysisOrder analysisOrder = new AnalysisOrder();
        analysisOrder.setType("ANALYSIS")
                .setDepartment("GOoD analysis department")
                .setStartDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 11).getTime())
                .setEndDate(new GregorianCalendar(2022, Calendar.FEBRUARY, 13).getTime())
                .setCurrency("USD")
                .setCost(22.12);

        ValidationRequest validationRequest = new ValidationRequest(
                new Date(),
                analysisOrder.getType(),
                analysisOrder.getDepartment(),
                OrderStatus.VALID);

        validationRequestService.storeValidationRequest(validationRequest);

        assertEquals(1,validationRequestRepository.findAll().size());

    }

    @Test
    void WhenCallValidationRequestHistory_ThenReturnsRecords() {

        validationRequestRepository.save(new ValidationRequest(new Date(),"REPAIR","IT department",OrderStatus.VALID));
        validationRequestRepository.save(new ValidationRequest(new Date(),"ANALYSIS","GOoD analysis department",OrderStatus.NOT_VALID));
        validationRequestRepository.save(new ValidationRequest(new Date(),"REPLACEMENT","GOoD replacement department",OrderStatus.VALID));

        assertEquals(3,validationRequestService.validationRequestHistory().size());
    }

}