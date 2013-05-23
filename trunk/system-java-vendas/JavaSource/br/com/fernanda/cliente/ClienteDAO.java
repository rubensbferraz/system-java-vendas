package br.com.fernanda.cliente;

import java.util.List;

public interface ClienteDAO {
	
	//so contem a assinatura dos métodos e nada mais
	public void salvar(Cliente cliente);

	public List<Cliente> listar();

}
