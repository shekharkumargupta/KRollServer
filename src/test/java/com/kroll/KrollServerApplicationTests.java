package com.kroll;

import com.kroll.dao.ProductDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KrollServerApplicationTests {


	@Autowired
	MockMvc mockMvc;

	@MockBean
	ProductDAO productDAO;

	@Test
	public void contextLoads() throws Exception {

		/*
		Mockito.when(productDAO.findAllMasterProducts(Mockito.anyLong()))
				.thenReturn(Collections.emptyList());

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders
						.get("/product/findAllMasterProducts/1")
						.accept(MediaType.APPLICATION_JSON)
		).andReturn();

		System.out.println(mvcResult.getResponse());

		Mockito.verify(productDAO).findAllMasterProducts(Mockito.anyLong());
		*/
	}

}
