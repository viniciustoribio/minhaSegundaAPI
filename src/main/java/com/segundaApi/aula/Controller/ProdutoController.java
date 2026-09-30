package com.segundaApi.aula.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.segundaApi.aula.Service.ProdutoService;
import com.segundaApi.aula.Model.ProdutoModel;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // READ - Listar todos os produtos
    @GetMapping
    public List<ProdutoModel> getAllProdutos() {
        return produtoService.findAll();
    }

    // READ - Buscar um produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> getProdutoById(@PathVariable Long id) {
        Optional<ProdutoModel> produto = produtoService.findById(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE - Criar um novo produto
    @PostMapping
    public ProdutoModel createProduto(@RequestBody ProdutoModel produto) {
        return produtoService.save(produto);
    }

    // UPDATE - Atualizar um produto por ID
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModel> updateProduto(@PathVariable Long id, @RequestBody ProdutoModel produtoAtualizado) {
        Optional<ProdutoModel> produto = produtoService.update(id, produtoAtualizado);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE - Deletar um produto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        if (produtoService.deleteById(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}