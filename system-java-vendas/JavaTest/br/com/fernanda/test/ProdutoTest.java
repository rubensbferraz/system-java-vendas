package br.com.fernanda.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.fernanda.produto.Produto;
import br.com.fernanda.util.HibernateUtil;

public class ProdutoTest {

	private static Session sessao;
	private static Transaction transacao;
	
	@BeforeClass
	public static void abreConexao(){
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
		transacao = sessao.beginTransaction();
	}

	@AfterClass
	public static void fechaConexao(){
		try {
			transacao.commit();
			
		} catch (Throwable e) {
			System.out.println("Deu problema no commit: " + e.getMessage());
		}finally{
			try {
				if(sessao.isOpen()){
					sessao.close();
				}
			} catch (Exception e2) {
				System.out.println("Deu erro no fechamento da sessão" + e2.getMessage());
			}
			
		}
	}
	
	//método vai ser utilizado antes de qualquer teste
	@Before //chama o metodo toda vez que executar um teste
	public void setup(){
		Produto p1 = new Produto("lote", "Caderno", new Date(), 50, 7.0f);
		Produto p2 = new Produto("lote2", "Regua", new Date(), 30, 2.5f);
		Produto p3 = new Produto("fardo", "Papel", new Date(), 300, 1.5f);
		Produto p4 = new Produto("edicao", "Livro", new Date(), 10, 30.0f);
		Produto p5 = new Produto("caixa", "Caneta", new Date(), 90, 1.5f);
		
		sessao.save(p1);
		sessao.save(p2);
		sessao.save(p3);
		sessao.save(p4);
		sessao.save(p5);
	}
	
	
	@After
	public void limpaBanco(){
		Criteria lista = sessao.createCriteria(Produto.class);
		@SuppressWarnings("unchecked")
		List<Produto> produtos = lista.list();
		
		for (Produto produto : produtos) {
			sessao.delete(produto);
		}
	}
	
	@Test
	public void salvarProdutoTest(){
		Query consulta = pesquisar("Re");
		Produto produtoPesquisado = (Produto) consulta.uniqueResult(); //busque um unico resultado da consulta
		
		assertEquals("lote2", produtoPesquisado.getUnidade());
	}
	
	@Test
	public void listaProdutosTest(){
		Criteria lista = sessao.createCriteria(Produto.class);
		@SuppressWarnings("unchecked")
		List<Produto> produtos = lista.list();
		
		assertEquals(0, produtos.size());
	}
	
	@Test
	public void excluirProdutoTest(){
		Query consulta = pesquisar("Papel");
		Produto produtoDeletado = (Produto) consulta.uniqueResult();
		sessao.delete(produtoDeletado);
		
		produtoDeletado = (Produto) consulta.uniqueResult();
		
		assertNull(produtoDeletado);
	}
	
	@Test
	public void alteracaoProdutoTest(){
		Query consulta = pesquisar("Livro");
		Produto produtoAlterado = (Produto) consulta.uniqueResult();
		produtoAlterado.setEstoque(100);
		
		sessao.update(produtoAlterado);
		
		produtoAlterado = (Produto) consulta.uniqueResult();
		
		assertEquals(100, produtoAlterado.getEstoque().intValue());
	}

	private Query pesquisar(String parametro) {
		String sql = "from Produto p where p.descricao like :descricao";
		Query consulta = sessao.createQuery(sql);
		consulta.setString("descricao", "%"+parametro+"%");
		return consulta;
	}
	
	
	
	/*
	@Test
	public void salvarProdutoTest(){
		Produto p1 = new Produto();
		p1.setDescrição("Produto Teste");
		p1.setEstoque(20);
		p1.setDataCadastro(new Date());
		p1.setUnidade("Quilo");
		p1.setValor(1.6f);
		
		//abreConexao(); //anotação nao necessária pois estamos usando o @BeforeClass
		this.sessao.save(p1);
		//fechaConexao(); //anotação nao necessária pois estamos usando o @AfterClass
		
		assertNull(null);
	}*/
}
