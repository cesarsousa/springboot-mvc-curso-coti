package coti.com.br.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import coti.com.br.entity.Produto;

public class ValidarProduto implements ValidacaoProduto {

	private String mensagem = "";

	@Override
	public String validaId(Produto p) {
		if (p.getIdProduto() == null) {
			setMensagem(getMensagem() + "id vazio");
		} else if (p.getIdProduto() <= 0) {
			setMensagem(getMensagem() + "id negativo ou zero");
		}

		return "ok";
	}

	@Override
	public String validaNome(Produto p) {
		Pattern pa = Pattern.compile("[a-z A-Z]{3,50}");
		Matcher m = pa.matcher(p.getNomeProduto());

		if (p.getNomeProduto() == null) {
			setMensagem(getMensagem() + "nome is null");
		} else if (!m.matches()) {
			setMensagem(getMensagem() + "nome fora do padrao");
		}

		return "ok";
	}

	@Override
	public String validaPreco(Produto p) {
		if (p.getPreco() == null) {
			setMensagem(getMensagem() + "preco is null");
		} else if (p.getPreco() < 0) {
			setMensagem(getMensagem() + "preca negativo");
		}

		return null;
	}

	@Override
	public String validaTipo(Produto p) {
		List<Produto> tipos = Arrays.asList(new Produto[] { new Produto("eletronico"), new Produto("videogame") });
		Produto busca = tipos.stream().filter(x -> x.getTipo().equals(p.getTipo())).findAny().orElse(null);
		if (p.getTipo() == null) {
			setMensagem(getMensagem() + "\ttipo vazio");
		} else if (busca == null) {
			setMensagem(getMensagem() + "\ttipo Nao Encontrado");
		}
		return "ok";
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String msg) {
		this.mensagem = msg;
	}

}
