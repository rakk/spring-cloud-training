package com.gft.academy.selen.controller;

import com.gft.academy.selen.constant.LoanStatus;
import com.gft.academy.selen.domain.Loan;
import com.gft.academy.selen.domain.Security;
import com.gft.academy.selen.feign.SecuritiesClient;
import com.gft.academy.selen.service.LoanService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(value = LoanController.class)
public class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoanService loanServiceMock;

    @MockBean
    private SecuritiesClient securitiesClientMock;

    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldReturnAllLoans() throws Exception {
        // given
        List<Loan> loans = new ArrayList<>();
        loans.add(createLoan(1L, "IBM", 100, LoanStatus.NEW));
        loans.add(createLoan(2L, "GFT", 200, LoanStatus.RETURNED));
        Mockito.when(loanServiceMock.findAll()).thenReturn(loans.stream());

        // when
        String response = mockMvc.perform(get("/loan")).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        // then
        JSONAssert.assertEquals("[{\"id\":1,\"securityId\":\"IBM\",\"quantity\":100,\"client\":null,\"status\":\"NEW\"},{\"id\":2,\"securityId\":\"GFT\",\"quantity\":200,\"client\":null,\"status\":\"RETURNED\"}]", response, true);
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldReturnLoanById() throws Exception {
        // given
        Long loanId = 1L;
        Loan loan = createLoan(loanId, "IBM", 100, LoanStatus.NEW);
        Mockito.when(loanServiceMock.findOne(loanId)).thenReturn(Optional.of(loan));

        // when
        String response = mockMvc.perform(get("/loan/{id}", loanId)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        // then
        JSONAssert.assertEquals("{\"id\":1,\"securityId\":\"IBM\",\"quantity\":100,\"client\":null,\"status\":\"NEW\"}", response, true);
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldReturnLoan() throws Exception {
        // given
        Long loanId = 1L;
        Loan loan = createLoan(loanId, "IBM", 100, LoanStatus.ACTIVE);
        Loan loanReturned = createLoan(loanId, "IBM", 100, LoanStatus.RETURNED);
        Mockito.when(loanServiceMock.findOne(loanId)).thenReturn(Optional.of(loan));
        Mockito.when(loanServiceMock.returnLoan(loan)).thenReturn(loanReturned);

        // when
        String response = mockMvc.perform(put("/loan/{id}", loanId)).andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        // then
        JSONAssert.assertEquals("{\"id\":1,\"securityId\":\"IBM\",\"quantity\":100,\"client\":null,\"status\":\"RETURNED\"}", response, true);
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    public void shouldTakeOutLoan() throws Exception {
        // given
        String securityId = "IBM";
        Integer quantity = 100;
        List<Security> securities = new ArrayList<>();
        securities.add(new Security("IBM", 500));
        Mockito.when(securitiesClientMock.getAvailableSecurities()).thenReturn(securities);
        Mockito.when(loanServiceMock.takeOutLoan(securityId, quantity)).thenReturn(createLoan(1L, securityId, quantity, LoanStatus.ACTIVE));

        // when
        String location = mockMvc.perform(post("/loan").param("securityId", securityId).param("quantity", String.valueOf(quantity))).andDo(print()).andExpect(status().isCreated()).andReturn().getResponse().getHeader("Location");

        // then
        Assertions.assertThat(location).isEqualTo("http://localhost/loan/1");
    }

    private Loan createLoan(Long id, String securityId, Integer quantity, LoanStatus status) {
        Loan loan = new Loan();
        loan.setId(id);
        loan.setSecurityId(securityId);
        loan.setQuantity(quantity);
        loan.setStatus(status);
        return loan;
    }
}
