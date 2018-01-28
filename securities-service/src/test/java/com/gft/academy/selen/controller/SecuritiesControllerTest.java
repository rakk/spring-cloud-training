package com.gft.academy.selen.controller;

import com.gft.academy.selen.constant.DebtStatus;
import com.gft.academy.selen.domain.Debt;
import com.gft.academy.selen.domain.Security;
import com.gft.academy.selen.repository.DebtRepository;
import com.gft.academy.selen.repository.SecurityRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SecuritiesController.class)
public class SecuritiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SecurityRepository securityRepositoryMock;

    @MockBean
    private DebtRepository debtRepositoryMock;

    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldReturnAvailableSecurities() throws Exception {
        // given
        List<Security> securities = new ArrayList<>();
        securities.add(new Security("IBM", 100));
        securities.add(new Security("GFT", 200));
        Mockito.when(securityRepositoryMock.findAll()).thenReturn(securities.stream());

        String response = mockMvc.perform(get("/security")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        JSONAssert.assertEquals("[{\"id\":\"IBM\",\"availableQuantity\":100},{\"id\":\"GFT\",\"availableQuantity\":200}]", response, true);
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldReturnAllDebtsBySecurityId() throws Exception {
        // given
        List<Debt> debts = new ArrayList<>();
        debts.add(createDebt(1L, "IBM", 100, DebtStatus.ACTIVE));
        debts.add(createDebt(2L, "IBM", 200, DebtStatus.RETURNED));
        Mockito.when(debtRepositoryMock.findBySecurityId("IBM")).thenReturn(debts.stream());

        // when
        String response = mockMvc.perform(get("/debt").param("securityId", "IBM")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        //then
        JSONAssert.assertEquals("[{\"id\":1,\"client\":null,\"securityId\":\"IBM\",\"quantity\":100,\"status\":\"ACTIVE\"},{\"id\":2,\"client\":null,\"securityId\":\"IBM\",\"quantity\":200,\"status\":\"RETURNED\"}]", response, true);
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldReturnDebtById() throws Exception {
        // given
        Long debtId = 1L;
        Debt debt = createDebt(debtId, "IBM", 100, DebtStatus.ACTIVE);
        Mockito.when(debtRepositoryMock.findById(debtId)).thenReturn(Optional.of(debt));

        // when
        String response = mockMvc.perform(get("/debt/{id}", debtId)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        //then
        JSONAssert.assertEquals("{\"id\":1,\"client\":null,\"securityId\":\"IBM\",\"quantity\":100,\"status\":\"ACTIVE\"}", response, true);
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldReturnDebt() throws Exception {
        // given
        Long debtId = 1L;
        String securityId = "IBM";
        Debt debt = createDebt(debtId, securityId, 100, DebtStatus.ACTIVE);
        Security security = new Security(securityId, 500);
        Mockito.when(debtRepositoryMock.findById(debtId)).thenReturn(Optional.of(debt));
        Mockito.when(securityRepositoryMock.findById(securityId)).thenReturn(Optional.of(security));

        // when
        String response = mockMvc.perform(put("/debt/{id}", debtId)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        //then
        JSONAssert.assertEquals("{\"id\":1,\"client\":null,\"securityId\":\"IBM\",\"quantity\":100,\"status\":\"RETURNED\"}", response, true);
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldIncurDebt() throws Exception {
        // given
        Long debtId = 1L;
        String securityId = "IBM";
        Security security = new Security(securityId, 500);
        Mockito.when(debtRepositoryMock.findById(debtId)).thenReturn(Optional.empty());
        Mockito.when(debtRepositoryMock.save(Mockito.any(Debt.class))).thenReturn(createDebt(1L, securityId, 100, DebtStatus.ACTIVE));
        Mockito.when(securityRepositoryMock.findById(securityId)).thenReturn(Optional.of(security));

        // when
        String location = mockMvc.perform(post("/debt").contentType(MediaType.APPLICATION_JSON).content("{\"id\":1,\"client\":null,\"securityId\":\"IBM\",\"quantity\":100,\"status\":\"NEW\"}")).andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");

        //then
        Assertions.assertThat(location).isEqualTo("http://localhost/debt/1");
    }

    private Debt createDebt(Long id, String securityId, Integer quantity, DebtStatus status) {
        Debt debt = new Debt();
        debt.setId(id);
        debt.setSecurityId(securityId);
        debt.setQuantity(quantity);
        debt.setStatus(status);
        return debt;
    }
}
