package br.com.fernanda.util;

import br.com.fernanda.cliente.ClienteDAO;
import br.com.fernanda.cliente.ClienteDAOHibernate;


public class DAOFactory {
	//atribui a sessao ao objeto usuarioDAO
	public static ClienteDAO criaClienteDAO() {
		ClienteDAOHibernate clienteDAOHibernate = new ClienteDAOHibernate();
		clienteDAOHibernate.setSessao(HibernateUtil.getSessionFactory().getCurrentSession());
		return clienteDAOHibernate;
	}
	
	
	

}
