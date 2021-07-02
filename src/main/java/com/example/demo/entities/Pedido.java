package com.example.demo.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.enumeration.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

// Classe Order
@Entity
// @Table é Opcional caso queria definir um nome da tabela diferente no BD
// Caso o nome da classe seja o mesmo nome da tabela, não precisa
@Table(name = "tb_pedido") 
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//JsonFormat no padrão timeZone UTC
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	// Após o java 8 o instant(muito melhor que o Date) substituiu o date
	private Instant momento;
	
//	private StatusPedido statusPedido;
	private Integer statusPedido; // Esse tratamento é somente interno (ajustado para o código no enum)
	
//	@JsonIgnore // -> Para evitar um loop quando o mapeamento estiver nos dois lados(Nesse caso é melhor do lado do usuário) 
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Usuario cliente;
	
	@OneToMany(mappedBy = "id.pedido")
	private Set<PedidoItem> itens = new HashSet<>();
	
	// Nesse mapeamento um para um, está sendo mapeado para que as duas entidades tenham o mesmo id,
	// com isso, o CascadeType.ALL se faz obrigatório
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pagamento pagamento;
	
	public Pedido() {
	}

	public Pedido(Long id, Instant momento, StatusPedido statusPedido, Usuario cliente) {
		super();
		this.id = id;
		this.momento = momento;
//		this.statusPedido = statusPedido;
		this.setStatusPedido(statusPedido);
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMomento() {
		return momento;
	}

	public void setMomento(Instant momento) {
		this.momento = momento;
	}

	// Ajustado o método para o código inserido no enum
	public StatusPedido getStatusPedido() {
//		return statusPedido;
		return StatusPedido.valueOf(statusPedido);
	}

	// Ajustado o método para o código inserido no enum
	public void setStatusPedido(StatusPedido statusPedido) {
//		this.statusPedido = statusPedido;
		if (statusPedido != null) {
			this.statusPedido = statusPedido.getCodigo();
		}
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	
	public Set<PedidoItem> getItens() {
		return itens;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public Double getTotal() {
		double soma = 0.0;
		for (PedidoItem item: itens) {
			soma += item.getSubTotal();
		}
		return soma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
