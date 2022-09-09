package mst.example.ordervalidation.controllers;

import mst.example.ordervalidation.models.ValidationRequest;
import mst.example.ordervalidation.models.order.OrderStatus;
import mst.example.ordervalidation.repositories.ValidationRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import javax.annotation.Resource;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureWebClient
class OrderValidationControllerTest {

    @Resource
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        validationRequestRepository.save(new ValidationRequest(new Date(),"REPAIR","IT department", OrderStatus.VALID));
        validationRequestRepository.save(new ValidationRequest(new Date(),"ANALYSIS","GOoD analysis department",OrderStatus.NOT_VALID));
        validationRequestRepository.save(new ValidationRequest(new Date(),"REPLACEMENT","GOoD replacement department",OrderStatus.VALID));

    }

    @Resource
    private ValidationRequestRepository validationRequestRepository;

    @Test
    void WhenValidateCorrectAnalysisOrder_ThenReturnValid() throws Exception {

        String analysisOrderJson = "{\"type\":\"ANALYSIS\",\"department\":\"GOoD analysis department\",\"start_date\":\"2020-08-13\",\"end_date\":\"2020-08-15\",\"currency\":\"USD\",\"cost\":123.12,\"parts\":[{\"inventory_number\":\"InventoryNumber1\",\"name\":\"PartNumber1\",\"count\":1},{\"inventory_number\":\"InventoryNumber2\",\"name\":\"PartNumber2\",\"count\":2}]}";

        mockMvc.perform(post("/api/validation/validateOrder").contentType(APPLICATION_JSON)
                        .content(analysisOrderJson)).andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.validationResult.valid").value(true));
    }

    @Test
    void WhenValidateIncorrectAnalysisOrder_ThenReturnInvalid() throws Exception {

        String analysisOrderJson = "{\"type\":\"ANALYSIS\",\"department\":\"GOoD analysis department\",\"start_date\":\"2020-08-13\",\"end_date\":\"2020-07-15\",\"currency\":\"USD\",\"cost\":123.12,\"parts\":[{\"inventory_number\":\"InventoryNumber1\",\"name\":\"PartNumber1\",\"count\":1},{\"inventory_number\":\"InventoryNumber2\",\"name\":\"PartNumber2\",\"count\":2}]}";

        mockMvc.perform(post("/api/validation/validateOrder").contentType(APPLICATION_JSON)
                        .content(analysisOrderJson)).andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.validationResult.valid").value(false));
    }

    @Test
    void WhenValidateCorrectRepairOrder_ThenReturnValid() throws Exception {

        String analysisOrderJson = "{\"type\":\"REPAIR\",\"department\":\"GOoD repair department\",\"start_date\":\"2020-08-13\",\"end_date\":\"2020-08-16\",\"analysis_date\":\"2020-08-14\",\"test_date\":\"2020-08-15\",\"responsible_person\":\"GOoD repairmaster\",\"currency\":\"USD\",\"cost\":123.12,\"parts\":[{\"inventory_number\":\"InventoryNumber3\",\"name\":\"PartNumber3\",\"count\":3},{\"inventory_number\":\"InventoryNumber4\",\"name\":\"PartNumber4\",\"count\":4}]}";

        mockMvc.perform(post("/api/validation/validateOrder").contentType(APPLICATION_JSON)
                        .content(analysisOrderJson)).andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.validationResult.valid").value(true));
    }

    @Test
    void WhenValidateIncorrectRepairOrder_ThenReturnInvalid() throws Exception {

        String analysisOrderJson = "{\"type\":\"REPAIR\",\"department\":\"GOoD repair department\",\"start_date\":\"2020-08-13\",\"end_date\":\"2020-08-16\",\"analysis_date\":\"2020-08-14\",\"test_date\":\"2020-08-15\",\"responsible_person\":\"\",\"currency\":\"USD\",\"cost\":123.12,\"parts\":[{\"inventory_number\":\"InventoryNumber3\",\"name\":\"PartNumber3\",\"count\":3},{\"inventory_number\":\"InventoryNumber4\",\"name\":\"PartNumber4\",\"count\":4}]}";

        mockMvc.perform(post("/api/validation/validateOrder").contentType(APPLICATION_JSON)
                        .content(analysisOrderJson)).andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.validationResult.valid").value(false));
    }

    @Test
    void WhenValidateUnknownOrderType_ThenReturnValid() throws Exception {

        String analysisOrderJson = "{\"type\":\"INSTALL\",\"department\":\"GOoD repair department\",\"start_date\":\"2020-08-13\",\"end_date\":\"2020-08-16\",\"analysis_date\":\"2020-08-14\",\"test_date\":\"2020-08-15\",\"responsible_person\":\"GOoD repairmaster\",\"currency\":\"USD\",\"cost\":123.12,\"parts\":[{\"inventory_number\":\"InventoryNumber3\",\"name\":\"PartNumber3\",\"count\":3},{\"inventory_number\":\"InventoryNumber4\",\"name\":\"PartNumber4\",\"count\":4}]}";

        mockMvc.perform(post("/api/validation/validateOrder").contentType(APPLICATION_JSON)
                        .content(analysisOrderJson)).andExpect(status().isBadRequest())
                        .andExpect(content().contentType("application/json"))
                        .andExpect(jsonPath("$.message").value("Input Parameter Error"));
    }

    @Test
    void validationRequestHistory() throws Exception {

        mockMvc.perform(get("/api/validation/validationHistory")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andExpect(jsonPath("$.message").value("OK"))
                .andExpect(jsonPath("$.validationRequests.*").exists());
    }
}