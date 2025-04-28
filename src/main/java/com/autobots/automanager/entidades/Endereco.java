package com.autobots.automanager.entidades;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class Endereco {
  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = true)
  private String estado;
  @Column(nullable = false)
  private String cidade;
  @Column(nullable = true)
  private String bairro;
  @Column(nullable = false)
  private String rua;
  @Column(nullable = false)
  private String numero;
  @Column(nullable = true)
  private String codigoPostal;
  @Column(unique = false, nullable = true)
  private String informacoesAdicionais;

  @OneToOne
  @JoinColumn(name = "cliente_id")
  @JsonBackReference
  private Cliente cliente;

  @Override
  public int hashCode() {
    return Objects.hash(id, estado, cidade, bairro, rua, numero, codigoPostal, informacoesAdicionais);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Endereco endereco = (Endereco) obj;
    return Objects.equals(id, endereco.id);
  }
}
