package com.autobots.automanager.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
public class Cliente {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String nome;
  @Column
  private String nomeSocial;
  @Column
  private Date dataNascimento;
  @Column
  private Date dataCadastro;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "cliente")
  @JsonManagedReference
  private List<Documento> documentos = new ArrayList<>();

  @OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true, mappedBy = "cliente")
  @JsonManagedReference
  private Endereco endereco;

  @JsonManagedReference
  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL,mappedBy = "cliente")
  private List<Telefone> telefones = new ArrayList<>();

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, nomeSocial, dataNascimento, dataCadastro);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Cliente cliente = (Cliente) obj;
    return Objects.equals(id, cliente.id);
  }
}
