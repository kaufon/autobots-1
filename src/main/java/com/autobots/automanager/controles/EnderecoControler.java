package com.autobots.automanager.controles;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

import lombok.Data;

@RestController
@RequestMapping("/endereco")
public class EnderecoControler {
  @Autowired
  private EnderecoRepositorio enderecoRepositorio;

  @Autowired
  private ClienteRepositorio clienteRepositorio;

  @Autowired
  private EnderecoAtualizador enderecoAtualizador;

  @Data
  private static class CriarEnderecoRequest{
    Endereco endereco;
    long clienteId;
  }

  @GetMapping("/enderecos")
  public List<Endereco> obterEnderecos() {
    List<Endereco> enderecos = enderecoRepositorio.findAll();
    return enderecos;
  }
  @GetMapping("/endereco/{id}")
  public Endereco obterEndereco(@PathVariable long id) {
    return enderecoRepositorio.findById(id).get();
  }
  @PostMapping("/cadastro")
  public void cadastrarEndereaco(@RequestBody CriarEnderecoRequest request){
    var cliente = clienteRepositorio.findById(request.clienteId).get();
    var endereco = request.endereco;
    endereco.setCliente(cliente);
    endereco = enderecoRepositorio.save(endereco);
    cliente.setEndereco(endereco);
    clienteRepositorio.save(cliente);
  }
  @PutMapping("/atualizar")
  public void atualizarEndereco(@RequestBody Endereco enderecoAtualizado){
    var endereco = enderecoRepositorio.findById(enderecoAtualizado.getId()).get();
    enderecoAtualizador.atualizar(endereco, enderecoAtualizado);
    enderecoRepositorio.save(endereco);
  }
  @DeleteMapping("/deletar")
  public void deletarEndereco(@RequestBody Endereco endereco){
    var enderecoASerDeletado = enderecoRepositorio.findById(endereco.getId()).get();
    var cliente = enderecoASerDeletado.getCliente();
    cliente.setEndereco(null);
    clienteRepositorio.save(cliente);
    enderecoRepositorio.delete(enderecoASerDeletado);
  }
}
