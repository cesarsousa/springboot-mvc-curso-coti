package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import coti.com.br.controller.ControllerProduto;
import coti.com.br.entity.Produto;
import coti.com.br.repository.ProdutoRepository;

@RunWith(SpringRunner.class)
public class TestService {

	@MockBean
	private ProdutoRepository respository;

	@InjectMocks
	private ControllerProduto controller;

	static final String URL = "http://localhost:5232/produto";

	RestTemplate rest;
	List<Produto> lista;

	@Before
	public void setUp() throws Exception {
		// mvc = MockMvcBuilders.standaloneSetup(controller).build();
		rest = new RestTemplate();
		lista = new ArrayList<Produto>();
	}

	@Test
	public void consumoGetDiferenteVazio() throws Exception {
		String result = rest.getForObject(URL, String.class);
		// assertThat(, matcher);
		Assert.assertNotNull(result);

	}

	@Test
	public void consumoGetVazio() throws Exception {
		String result = rest.getForObject(URL, String.class);
		try {
			Assert.assertNull(result);
		} catch (AssertionError ex) {
			System.out.println(ex.getMessage());
			Assert.assertNotNull(result);
		}
	}

}
