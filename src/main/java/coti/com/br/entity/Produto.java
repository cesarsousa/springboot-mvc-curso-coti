package coti.com.br.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto;

	@Column(columnDefinition = "enum ('videogame','eletronico') ")
	private String tipo;

	@Column(length = 50, unique = true)
	private String nomeProduto;

	@Column
	private Double preco;
	
	public Produto() {
		super();
	}

	public Produto(Long idProduto, String tipo, String nomeProduto, Double preco) {
		super();
		this.idProduto = idProduto;
		this.tipo = tipo;
		this.nomeProduto = nomeProduto;
		this.preco = preco;
	}
	
	public Produto(String tipo) {
		super();
		this.tipo = tipo;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", tipo=" + tipo + ", nomeProduto=" + nomeProduto + ", preco="
				+ preco + "]";
	}

	@Override
	public int hashCode() {
		return String.valueOf(this.idProduto).length();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this == obj) return true;
		
		Produto p = (Produto) obj;
		
		if(this.nomeProduto.equals(p.getNomeProduto())) {
			return true;
		}else {
			return false;
		}
	}

}
