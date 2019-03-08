package coti.com.br.service;

import coti.com.br.entity.Produto;

public interface ValidacaoProduto {
	
	public String validaId(Produto p);
	public String validaNome(Produto p);
	public String validaPreco(Produto p);
	public String validaTipo(Produto p);

}
