package coti.com.br.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import coti.com.br.entity.Produto;
import coti.com.br.repository.ProdutoRepository;
import coti.com.br.service.ValidarProduto;

@Controller
//@RequestMapping(value="/controle")
public class ControllerProduto {

	@Autowired
	private ProdutoRepository dao;

	@Value("${welcome.empresa}")
	private String message;
	
	@GetMapping("formbusca")
	public ModelAndView formaBusca() {
		ModelAndView mv = new ModelAndView("busca");
		mv.addObject("message", message);
		return mv;
	}

	@GetMapping("/")
	public ModelAndView webMvc() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("message", message);
		mv.addObject("lista", dao.findAll());
		return mv;
	}
	
	@GetMapping("help")
	public ModelAndView help() {
		ModelAndView mv = new ModelAndView("help");
		mv.addObject("message", message);
		return mv;
	}

	@GetMapping(value = "produto")
	public ResponseEntity<List<Produto>> findProdutos() {
		List<Produto> produtos = (List<Produto>) dao.findAll();
		return ResponseEntity.ok(produtos);
	}

	@GetMapping(value = "produto/{id}")
	public ResponseEntity<?> produto(@PathVariable Long id) {

		try {

			Optional<Produto> produto = dao.findById(id);
			Produto resp = produto.orElse(null);

			if (resp == null) {
				throw new Exception("código não encontrado");
			}

			return ResponseEntity.ok(resp);

		} catch (Exception e) {
			return new ResponseEntity<String>("Error:" + e.getMessage(), HttpStatus.OK);
		}
	}

	@PostMapping(value = "produto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createEndPoint(@RequestBody Produto produto) {
		try {
			ValidarProduto vp = new ValidarProduto();
			vp.validaNome(produto);
			vp.validaTipo(produto);
			vp.validaPreco(produto);

			if (vp.getMensagem().length() > 0) {
				throw new Exception(vp.getMensagem());
			}

			dao.save(produto);
			return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Error:" + e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping(value="/gravamvc")
	public ModelAndView gravaMvc(
			@RequestParam String nomeProduto,
			@RequestParam String tipo,
			@RequestParam String preco) {
		
		ModelAndView mv = new ModelAndView("index");
		
		try {
			
			Produto pr = new Produto(null, tipo, nomeProduto, new Double(preco));
			Produto resp = dao.save(pr);
			
			mv.addObject("msg", "Dados gravados");
			mv.addObject("produto", resp);
			
			
		} catch (Exception e) {
			mv.addObject("msg", "Erro : " + e.getMessage());
		}
		
		return mv;
		
	}
	
	@PostMapping(value="/buscaCodigoMvc")
	public ModelAndView buscarCodigoMvc(
			 @RequestParam String idProduto 
			) {
	  ModelAndView mv = new ModelAndView("busca"); 	
		try{
		
			
    Optional<Produto> resp = dao.findById(new Long(idProduto));
		
         Produto resposta = resp.orElse(null);
         if (resposta !=null) {
        	mv.addObject("flag","1");
			mv.addObject("msg","Produto Encontrado");
			mv.addObject("produto",resposta);
         }else {
           mv.addObject("flag","0");
           mv.addObject("msg","Produto Encontrado nao Encontrado");
         }
         
		}catch(Exception ex) {
			mv.addObject("msg","Error :" + ex.getMessage());
		}
	  return mv;
	}
	
	
	@PostMapping(value="alterarMvc")
	public ModelAndView buscarCodigoMvc(
			@RequestParam String idProduto,
			@RequestParam String nomeProduto,
			@RequestParam String tipo,
			@RequestParam String preco
			) {
		
	  ModelAndView mv = new ModelAndView("index"); 	
		
	  try{
		
		Produto pr = new Produto(new Long(idProduto), tipo, nomeProduto,new Double(preco));
		
		Produto resp = dao.save(pr);
		mv.addObject("msg", "alterado");
		mv.addObject("produto", resp);
    
         
		}catch(Exception ex) {
			mv.addObject("msg","Error :" + ex.getMessage());
		}
	  return mv;
	}
	
	
	@PutMapping(value = "produto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateEndPoint(@RequestBody Produto produto) {
		try {
			
			
			Optional<Produto> buscar = dao.findById(produto.getIdProduto());
			
			Produto resp = buscar.orElse(null);
			
			if(resp !=null) {
				dao.save(produto);
				return new ResponseEntity<Produto>(produto, HttpStatus.OK);
			} else {
				throw new Exception("Id nao encontrado");
			}
			
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Error:" + e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping(value = "produto/{id}")
	public ResponseEntity<?> deleteEndPoint(@PathVariable("id") Long id) {
		try {
			
			
			Optional<Produto> buscar = dao.findById(id);
			
			Produto resp = buscar.orElse(null);
			
			if(resp !=null) {
				dao.delete(resp);
				return new ResponseEntity<String>("Produto deletado", HttpStatus.OK);
			} else {
				throw new Exception("Id nao encontrado");
			}
			
			
		} catch (Exception e) {
			return new ResponseEntity<String>("Error:" + e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	

}
