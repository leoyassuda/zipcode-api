package com.ly.zipcode.controllers.addressDetails;

import com.ly.zipcode.domains.addressDetails.AddressDetails;
import com.ly.zipcode.useCases.addressDetails.AddressDetailsUseCase;
import com.ly.zipcode.utils.AddressUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressDetailsControllerTestIT {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AddressDetailsUseCase addressDetailsUseCase;

  private final AddressDetails addressDetails = AddressUtils.generateAddress(AddressDetails.class);
  private final List<AddressDetails> addressDetailsList = List.of(
      Objects.requireNonNull(AddressUtils.generateAddress(AddressDetails.class)),
      Objects.requireNonNull(AddressUtils.generateAddress(AddressDetails.class)),
      Objects.requireNonNull(AddressUtils.generateAddress(AddressDetails.class))
  );

  @Test
  public void shouldReturnOkResponseWhenCallById() throws Exception {
    when(addressDetailsUseCase.findById(any(UUID.class))).thenReturn(addressDetails);

    this.mockMvc.perform(get("/addressDetails/" + addressDetails.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(addressDetails.getId().toString()))
        .andExpect(jsonPath("$.zipcode").value(addressDetails.getZipcode()))
        .andExpect(jsonPath("$.address").value(addressDetails.getAddress()))
        .andExpect(jsonPath("$.additionalAddress").value(addressDetails.getAdditionalAddress()))
        .andExpect(jsonPath("$.neighborhood").value(addressDetails.getNeighborhood()))
        .andExpect(jsonPath("$.city").value(addressDetails.getCity()))
        .andExpect(jsonPath("$.state").value(addressDetails.getState()));
  }

  @Test
  public void shouldReturnErrorResponseWhenCallByIdUsingWrongUUID() throws Exception {
    when(addressDetailsUseCase.findById(any(UUID.class))).thenReturn(addressDetails);

    this.mockMvc.perform(get("/addressDetails/123"))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void shouldReturnOkResponseWhenCallByZipCode() throws Exception {
    when(addressDetailsUseCase.findByZipCode(any(String.class))).thenReturn(addressDetails);

    this.mockMvc.perform(get("/addressDetails/zipcode/" + addressDetails.getZipcode()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(addressDetails.getId().toString()))
        .andExpect(jsonPath("$.zipcode").value(addressDetails.getZipcode()))
        .andExpect(jsonPath("$.address").value(addressDetails.getAddress()))
        .andExpect(jsonPath("$.additionalAddress").value(addressDetails.getAdditionalAddress()))
        .andExpect(jsonPath("$.neighborhood").value(addressDetails.getNeighborhood()))
        .andExpect(jsonPath("$.city").value(addressDetails.getCity()))
        .andExpect(jsonPath("$.state").value(addressDetails.getState()));
  }

  @Test
  public void shouldReturnOkResponseWithThreeElementsWhenCallFindAll() throws Exception {
    when(addressDetailsUseCase.findAll(any(Integer.class), any(Integer.class))).thenReturn(addressDetailsList);

    this.mockMvc.perform(get("/addressDetails?page=1&size=3"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.length()").value(addressDetailsList.size()))
        .andExpect(jsonPath("$[0].id").value(addressDetailsList.get(0).getId().toString()))
        .andExpect(jsonPath("$[0].zipcode").value(addressDetailsList.get(0).getZipcode()))
        .andExpect(jsonPath("$[0].address").value(addressDetailsList.get(0).getAddress()))
        .andExpect(jsonPath("$[0].additionalAddress").value(addressDetailsList.get(0).getAdditionalAddress()))
        .andExpect(jsonPath("$[0].neighborhood").value(addressDetailsList.get(0).getNeighborhood()))
        .andExpect(jsonPath("$[0].city").value(addressDetailsList.get(0).getCity()))
        .andExpect(jsonPath("$[0].state").value(addressDetailsList.get(0).getState()));
  }

  @Test
  public void shouldReturnAddressDetailsWhenPostCreate() throws Exception {
    when(addressDetailsUseCase.create(any(AddressDetails.class))).thenReturn(addressDetails);

    assert addressDetails != null;
    this.mockMvc.perform(post("/addressDetails")
            .contentType(MediaType.APPLICATION_JSON)
            .content(AddressUtils.asJsonString(addressDetails)))
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.zipcode").value(addressDetails.getZipcode()))
        .andExpect(jsonPath("$.address").value(addressDetails.getAddress()))
        .andExpect(jsonPath("$.additionalAddress").value(addressDetails.getAdditionalAddress()))
        .andExpect(jsonPath("$.neighborhood").value(addressDetails.getNeighborhood()))
        .andExpect(jsonPath("$.city").value(addressDetails.getCity()))
        .andExpect(jsonPath("$.state").value(addressDetails.getState()));
  }

  @Test
  public void shouldReturnAddressDetailsWhenUpdate() throws Exception {
    when(addressDetailsUseCase.update(any(UUID.class), any(AddressDetails.class))).thenReturn(addressDetails);

    assert addressDetails != null;
    this.mockMvc.perform(put("/addressDetails/" + addressDetails.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(AddressUtils.asJsonString(addressDetails)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(jsonPath("$.zipcode").value(addressDetails.getZipcode()))
        .andExpect(jsonPath("$.address").value(addressDetails.getAddress()))
        .andExpect(jsonPath("$.additionalAddress").value(addressDetails.getAdditionalAddress()))
        .andExpect(jsonPath("$.neighborhood").value(addressDetails.getNeighborhood()))
        .andExpect(jsonPath("$.city").value(addressDetails.getCity()))
        .andExpect(jsonPath("$.state").value(addressDetails.getState()));
  }

  @Test
  public void shouldReturnAddressDetailsWhenPostDelete() throws Exception {
    doNothing().when(addressDetailsUseCase).delete(any(UUID.class));


    this.mockMvc.perform(delete("/addressDetails/" + UUID.randomUUID())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

}
