package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Projeto;
import junit.framework.Assert;

public class ProjetoTest {
	
	private HttpServer server;

	@Before
	public void iniciaServidor(){
		
		server = Servidor. inicializaServidor();
	}
	
	@After
	public void terminaServidor(){
		server.stop();
	}
}
