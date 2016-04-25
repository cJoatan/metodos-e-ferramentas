package br.com.alura.loja;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;
import junit.framework.Assert;

public class ClienteTest {

	private HttpServer server;

	@Before
	public void iniciaServidor(){
		
		server = Servidor. inicializaServidor();
	}
	
	@After
	public void terminaServidor(){
		server.stop();
	}
	
	@Test
	public void adicionaCarrinho(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		
		Carrinho carrinho = new Carrinho();
		carrinho.adiciona(new Produto(314L, "Tablet", 999,1));
		carrinho.setRua("Rua Vergueiro");
		carrinho.setCidade("Sao Paulo");
		
		String xml = carrinho.toXml();
		Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
		
		Response response = target.path("/carrinhos").request().post(entity);
		
		Assert.assertEquals("<status>sucesso</status>", response.readEntity(String.class));
		
	}
}
