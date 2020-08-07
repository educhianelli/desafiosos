package com.desafio.sos.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "PATRIMONIO")
public class PatrimonioEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4081725508945098578L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@NotBlank(message="Campo 'NOME' não pode ser vazio")
	@Column(name = "nome", length = 100, nullable = false)
	@Size(min = 1, max = 100)
	private String nome;
	

	
	@Size(min = 0, max = 100)
	@Column(name = "descricao", length = 100)
	private String descricao;
	
    @Column(name = "tombo", unique = true, nullable = false, insertable = true, updatable = false)
    private String tombo;
    
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "marca_id", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MarcaEntity marca;

	
	public PatrimonioEntity() {}

	
	public PatrimonioEntity(long id, @NotEmpty @Size(min = 3, max = 100) String nome,
			@Size(min = 0, max = 100) String descricao, String tombo, MarcaEntity marca) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.tombo = tombo;
		this.marca = marca;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getTombo() {
		return tombo;
	}

	public void setTombo(String tombo) {
		this.tombo = tombo;
	}

	public MarcaEntity getMarca() {
		return marca;
	}

	public void setMarca(MarcaEntity marca) {
		this.marca = marca;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tombo == null) ? 0 : tombo.hashCode());
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
		PatrimonioEntity other = (PatrimonioEntity) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tombo == null) {
			if (other.tombo != null)
				return false;
		} else if (!tombo.equals(other.tombo))
			return false;
		return true;
	}
	
	

}
