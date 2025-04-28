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

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

import lombok.Data;

@RestController
@RequestMapping("/telefone")
public class TelefoneControler {

  @Autowired
  private TelefoneRepositorio telefoneRepositorio;

  @Autowired
  private TelefoneAtualizador telefoneAtualizador;

  @Autowired
  private ClienteRepositorio clienteRepositorio;

  @Data
  private static class CriarTelefoneRequest {
    Telefone telefone;
    long clienteId;
  }

  @GetMapping("/telefones")
  public List<Telefone> obterTelefones() {
    return telefoneRepositorio.findAll();
  }

  @GetMapping("/telefone/{id}")
  public Telefone obterTelefone(@PathVariable long id) {
    return telefoneRepositorio.findById(id).get();
  }

  @PostMapping("/cadastro")
  public void cadastrarTelefone(@RequestBody CriarTelefoneRequest request) {
    var cliente = clienteRepositorio.findById(request.getClienteId()).get();
    var telefone = request.getTelefone();
    telefone.setCliente(cliente);
    telefone = telefoneRepositorio.save(telefone);
    cliente.getTelefones().add(telefone);
    clienteRepositorio.save(cliente);
  }
  @PutMapping("/atualizar")
  public void atualizarTelefone(@RequestBody Telefone telefoneAtualizado) {
    var telefone = telefoneRepositorio.findById(telefoneAtualizado.getId()).get();
    telefoneAtualizador.atualizar(telefone, telefoneAtualizado);
    telefoneRepositorio.save(telefone);
  }
  @DeleteMapping("/deletar")
  public void deletarTelefone(@RequestBody Telefone telefone) {
    var telefoneDeletado = telefoneRepositorio.findById(telefone.getId()).get();
    telefoneRepositorio.delete(telefoneDeletado);
  }
}
