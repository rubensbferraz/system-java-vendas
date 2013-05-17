package br.com.fernanda.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;

import br.com.fernanda.vendas.HibernateUtil;

public class ConexaoHibernateFilter implements Filter {
	
	private SessionFactory sf;

	@Override
	public void destroy() {	
	}

	@Override
	public void doFilter(ServletRequest servletFilter, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		try {
			//pega a conexao, inicia a transação e comita
			this.sf.getCurrentSession().beginTransaction(); 
			chain.doFilter(servletFilter, servletResponse); 
			this.sf.getCurrentSession().getTransaction().commit();
			this.sf.getCurrentSession().close();
		} catch (Throwable e) {
			try {
				if(this.sf.getCurrentSession().getTransaction().isActive()){ //tenta ver se a transaç]ao está ativa
					this.sf.getCurrentSession().getTransaction().rollback(); //se sim,  dá um rollback...1
				}
			} catch (Throwable e2) {
				e2.printStackTrace();
			}
			throw new ServletException(); //...1 e joga aqui na exceção do servlet
		}	
		
	}

	@Override
	public void init(FilterConfig conf) throws ServletException {
		this.sf = HibernateUtil.getSessionFactory();
	}

}
