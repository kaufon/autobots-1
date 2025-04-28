package com.autobots.automanager.controles;

import java.util.List;

import javax.print.Doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

import lombok.Data;

@RestController
@RequestMapping("/documento")
public class DocumentoControler {

  @Autowired
  private DocumentoRepositorio documentoRepositorio;

  @Autowired
  private ClienteRepositorio clienteRepositorio;

  @Autowired
  private DocumentoAtualizador documentoAtualizador;

  @Data
  private static class CriarDocumentoRequest{
    Documento documento;
    long clienteId;
  }


  @GetMapping("/documentos")
  public List<Documento> obterDocumentos() {
    List<Documento> documentos = documentoRepositorio.findAll();
    return documentos;
  }
  @GetMapping("/documento/{id}")
  public Documento obterDocumento(@PathVariable long id) {
    var documento = documentoRepositorio.findById(id);
    return documento.get();
  }
  @PostMapping("/cadastro")
  public void cadastrarDocumento(@RequestBody CriarDocumentoRequest request) {
    var cliente = clienteRepositorio.findById(request.getClienteId()).get();
    var document = request.getDocumento();
    document.setCliente(cliente);
    document = documentoRepositorio.save(document);
    cliente.getDocumentos().add(document);
    clienteRepositorio.save(cliente);
  }
  @PutMapping("/atualizar")
  public void atualizarDocument(@RequestBody Documento documento){
    var doc = documentoRepositorio.findById(documento.getId()).get();
    documentoAtualizador.atualizar(doc, documento);
     documentoRepositorio.save(doc);
  }
  @DeleteMapping("/deletar")
  public void deletarDocumento(@RequestBody Documento documento){
    var doc = documentoRepositorio.findById(documento.getId()).get();
    documentoRepositorio.delete(doc);
  }
}
