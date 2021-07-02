package com.example.demo.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.entities.Categoria;
import com.example.demo.entities.Pagamento;
import com.example.demo.entities.Pedido;
import com.example.demo.entities.PedidoItem;
import com.example.demo.entities.Produto;
import com.example.demo.entities.Usuario;
import com.example.demo.enumeration.StatusPedido;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.PedidoItemRepository;
import com.example.demo.repositories.PedidoRepository;
import com.example.demo.repositories.ProdutoRepository;
import com.example.demo.repositories.UsuarioRepository;

//Classe de configuração específica para o perfil de teste
//Precisará de 2(duas) anotations @Configuration e @Profile

// Através de CommandLineRunner, que é uma interface, esse método será executado quando a aplicação for iniciada
// A interface obrigado que o método run seja implementado

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoItemRepository produtoPedidoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Usuario usuario1 = new Usuario(null, "Maria Brown", "maria@gmail,com", "988888888", "123456");
		Usuario usuario2 = new Usuario(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		Usuario usuario3 = new Usuario(null, "Drexler", "drexler@gmail.com", "967777777", "123456");
		
		// Data no formato ISO 8601(permite várias possibilidades de formato)
		// Z -> Indica que esse horário está no padrão UTC(greenwich)
		Pedido pedido1 = new Pedido(null, Instant.parse("2019-06-20T19:53:07Z"), StatusPedido.PAGO, usuario1);
		Pedido pedido2 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"), StatusPedido.AGUARDANDO_PAGAMENTO, usuario2);
		Pedido pedido3 = new Pedido(null, Instant.parse("2019-07-22T15:21:22Z"), StatusPedido.AGUARDANDO_PAGAMENTO, usuario1);
		
		Categoria categoria1 = new Categoria(null, "Eletrônicos");
		Categoria categoria2 = new Categoria(null, "Livros");
		Categoria categoria3 = new Categoria(null, "Computadores");
		
		Produto produto1 = new Produto(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
		Produto produto2 = new Produto(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
		Produto produto3 = new Produto(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis", 1250.0, "");
		Produto produto4 = new Produto(null, "Pc Gamer", "Donec aliquet odio a rhoncus cursus.", 1200.0, "");
		Produto produto5 = new Produto(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus", 100.99, "");

		usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2, usuario3));
		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2, pedido3));
		categoriaRepository.saveAll(Arrays.asList(categoria1, categoria2, categoria3));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5));
		
		produto1.getCategorias().add(categoria2);
		produto2.getCategorias().add(categoria1);
		produto2.getCategorias().add(categoria3);
		produto3.getCategorias().add(categoria3);
		produto4.getCategorias().add(categoria3);
		produto5.getCategorias().add(categoria2);
		
		produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3, produto4, produto5));
		
		PedidoItem pp1 = new PedidoItem(pedido1, produto1, 2, produto1.getPreco());
		PedidoItem pp2 = new PedidoItem(pedido1, produto3, 1, produto3.getPreco());
		PedidoItem pp3 = new PedidoItem(pedido2, produto3, 2, produto3.getPreco());
		PedidoItem pp4 = new PedidoItem(pedido3, produto5, 2, produto5.getPreco());

		produtoPedidoRepository.saveAll(Arrays.asList(pp1, pp2, pp3, pp4));
		
		Pagamento pay1 = new Pagamento(null, Instant.parse("2019-06-20T21:53:07Z"), pedido1);
		pedido1.setPagamento(pay1);
		
		pedidoRepository.save(pedido1);
	}
	
}
