package br.com.fernanda.venda;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.fernanda.cliente.Cliente;
import br.com.fernanda.produto.Produto;

@Entity
@Table(name="venda")
public class Venda {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	/* Aqui faz o mapeamento do relacionamento entre as tabelas do sistema, onde é adicionado tbm
	 * as chaves estrangeitas correspondentes*/
	
	@ManyToOne
	@JoinColumn(name="id_cliente", nullable=false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="id_produto", nullable=false)
	private Produto produto;
	
	/*Fim*/
	
	@Column(name="data_venda")
	private Date dataVenda;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	

}
