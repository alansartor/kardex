package com.alan.kardex.stock.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.alan.kardex.stock.dto.ItemDTO;
import com.alan.kardex.stock.service.ItemSrvImpl;

@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
@ContextConfiguration(classes = {ItemController.class, ItemSrvImpl.class})
@WebMvcTest(ItemController.class)
public class ItemControllerTest {
	   
	@Autowired
	private MockMvc mockMvc;
    
    @MockBean
    private ItemSrvImpl itemSrv;
    
	@Test
	@WithMockUser(username="empleado",roles={"EMPLEADO"})
	public void shouldReturnDefaultMessage() throws Exception {		
	    when(itemSrv.findAllDTO()).thenReturn(Arrays.asList(
	            new ItemDTO(1L, "CAM_0001", "Camiseta", "Camiseta Marvel"),
	            new ItemDTO(2L, "CAM_0002", "Camiseta", "Camiseta DC comics")));
	    
        mockMvc.perform(get("/items")
        	.header(HttpHeaders.AUTHORIZATION,
                "Bearer Token..."))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", Matchers.hasSize(2)))
            .andExpect(jsonPath("$.[0].id", is(1)))
            .andExpect(jsonPath("$.[0].codigo", is("CAM_0001")))
            .andExpect(jsonPath("$.[0].tipo", is("Camiseta")))
            .andExpect(jsonPath("$.[0].descripcion", is("Camiseta Marvel")))
            .andExpect(jsonPath("$.[1].id", is(2)))
            .andExpect(jsonPath("$.[1].codigo", is("CAM_0002")))
            .andExpect(jsonPath("$.[1].tipo", is("Camiseta")))
            .andExpect(jsonPath("$.[1].descripcion", is("Camiseta DC comics")))
            .andDo(print())           
            .andDo(document("items", 
            	requestHeaders( 
            		headerWithName("Authorization").description("Bearer Token...")),
            	responseFields(
            		fieldWithPath("[].id").description("El identificador unico del articulo"),
            		fieldWithPath("[].codigo").description("El codigo del articulo (es unico)"),
            		fieldWithPath("[].tipo").description("Tipo de articulo"),
                    fieldWithPath("[].descripcion").description("Descripcion del articulo"))));
	}
}
