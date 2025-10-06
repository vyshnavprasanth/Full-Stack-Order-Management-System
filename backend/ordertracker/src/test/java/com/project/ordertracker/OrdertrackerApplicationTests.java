package com.project.ordertracker;

import com.project.ordertracker.Controller.Orders;
import com.project.ordertracker.Dto.OrderWithCustomerDTO;
import com.project.ordertracker.Dto.PaginatedOrderResponse;
import com.project.ordertracker.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Orders.class)
class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderService orderService;

	@Test
	void testGetAllOrders() throws Exception {
		OrderWithCustomerDTO dto1 = new OrderWithCustomerDTO(
				1L, "OrderA", LocalDate.now(), 100.00, "Delivered", "John Doe");
		OrderWithCustomerDTO dto2 = new OrderWithCustomerDTO(
				2L, "OrderB", LocalDate.now().minusDays(1), 150.00, "Completed", "Jane Smith");
		OrderWithCustomerDTO dto3 = new OrderWithCustomerDTO(
				2L, "OrderC", LocalDate.now().minusDays(1), 350.00, "Cancelled", "Jane");
		OrderWithCustomerDTO dto4 = new OrderWithCustomerDTO(
				2L, "OrderD", LocalDate.now().minusDays(1), 650.00, "Continue", "Joy");
		OrderWithCustomerDTO dto5 = new OrderWithCustomerDTO(
				2L, "OrderE", LocalDate.now().minusDays(1), 750.00, "Restitute", "Bornd");

		List<OrderWithCustomerDTO> dtoList = List.of(dto1, dto2, dto3, dto4, dto5);
		PaginatedOrderResponse response = new PaginatedOrderResponse(
				1, 5, dtoList // totalPages, totalElements, orders
		);
		Mockito.when(orderService.getAllOrders("all",0,10))
				.thenReturn(response);
		mockMvc.perform(get("/api/orders")
						.param("status", "all")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.orders.length()").value(5))
				.andExpect(jsonPath("$.orders[0].orderName").value("OrderA"))
				.andExpect(jsonPath("$.orders[0].customerName").value("John Doe"))
				.andExpect(jsonPath("$.orders[1].orderName").value("OrderB"))
				.andExpect(jsonPath("$.orders[1].customerName").value("Jane Smith"))
				.andExpect(jsonPath("$.orders[2].orderName").value("OrderC"))
				.andExpect(jsonPath("$.orders[2].customerName").value("Jane"))
				.andExpect(jsonPath("$.orders[3].orderName").value("OrderD"))
				.andExpect(jsonPath("$.orders[3].customerName").value("Joy"))
				.andExpect(jsonPath("$.orders[4].orderName").value("OrderE"))
				.andExpect(jsonPath("$.orders[4].customerName").value("Bornd"))
				.andExpect(jsonPath("$.totalPages").value(1))
				.andExpect(jsonPath("$.totalElements").value(5));
	}

	@Test
	void testGetAllCancelledOrders() throws Exception {
		OrderWithCustomerDTO dto1 = new OrderWithCustomerDTO(
				2L, "OrderC", LocalDate.now().minusDays(1), 350.00, "Cancelled", "Jane");

		List<OrderWithCustomerDTO> dtoList = List.of(dto1);
		PaginatedOrderResponse response = new PaginatedOrderResponse(
				1, 1, dtoList // totalPages, totalElements, orders
		);
		Mockito.when(orderService.getAllOrders("cancelled",0,10))
				.thenReturn(response);
		mockMvc.perform(get("/api/orders")
						.param("status", "cancelled")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.orders.length()").value(1))
				.andExpect(jsonPath("$.orders[0].orderName").value("OrderC"))
				.andExpect(jsonPath("$.orders[0].customerName").value("Jane"))
				.andExpect(jsonPath("$.orders[0].deliveryStatus").value("Cancelled"))
				.andExpect(jsonPath("$.totalPages").value(1))
				.andExpect(jsonPath("$.totalElements").value(1));
	}

	@Test
	void testGetAllContinueOrders() throws Exception {
		OrderWithCustomerDTO dto4 = new OrderWithCustomerDTO(
				2L, "OrderD", LocalDate.now().minusDays(1), 650.00, "Continue", "Joy");

		List<OrderWithCustomerDTO> dtoList = List.of(dto4);
		PaginatedOrderResponse response = new PaginatedOrderResponse(
				1, 1, dtoList // totalPages, totalElements, orders
		);
		Mockito.when(orderService.getAllOrders("Continue",0,10))
				.thenReturn(response);
		mockMvc.perform(get("/api/orders")
						.param("status", "Continue")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.orders.length()").value(1))
				.andExpect(jsonPath("$.orders[0].orderName").value("OrderD"))
				.andExpect(jsonPath("$.orders[0].customerName").value("Joy"))
				.andExpect(jsonPath("$.orders[0].deliveryStatus").value("Continue"))
				.andExpect(jsonPath("$.totalPages").value(1))
				.andExpect(jsonPath("$.totalElements").value(1));
	}

	@Test
	void testGetAllRestituteOrders() throws Exception {
		OrderWithCustomerDTO dto5 = new OrderWithCustomerDTO(
				2L, "OrderE", LocalDate.now().minusDays(1), 750.00, "Restitute", "Bornd");

		List<OrderWithCustomerDTO> dtoList = List.of(dto5);
		PaginatedOrderResponse response = new PaginatedOrderResponse(
				1, 1, dtoList // totalPages, totalElements, orders
		);
		Mockito.when(orderService.getAllOrders("Restitute",0,10))
				.thenReturn(response);
		mockMvc.perform(get("/api/orders")
						.param("status", "Restitute")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.orders.length()").value(1))
				.andExpect(jsonPath("$.orders[0].orderName").value("OrderE"))
				.andExpect(jsonPath("$.orders[0].customerName").value("Bornd"))
				.andExpect(jsonPath("$.orders[0].deliveryStatus").value("Restitute"))
				.andExpect(jsonPath("$.totalPages").value(1))
				.andExpect(jsonPath("$.totalElements").value(1));
	}
}
