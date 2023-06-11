package br.com.API.Produtos.controle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.API.Produtos.modelo.ProdutoModelo;
import br.com.API.Produtos.modelo.RespostaModelo;
import br.com.API.Produtos.servico.ProdutoServico;

@RestController// define esse aquivo como responsavel por criar rotas
//@RequestMapping("/api/produtos") // Prefixo da URL para todas as rotas deste controlador
@CrossOrigin(origins = "*" )// Libera a API para qualquer porta acessar
public class ProdutoControle {

    @Autowired
    private ProdutoServico ps;

    @DeleteMapping("/remover/{codigo}")// remove o produto correspondente ao id codigo
    public ResponseEntity<RespostaModelo> remover(@PathVariable long codigo){
        return ps.remover(codigo);
    }

    @PutMapping("/alterar")// alterar informações do produto ou erro
    public ResponseEntity<?> alterar(@RequestBody ProdutoModelo pm){
        return ps.cadastrarAlterar(pm,"alterar");
    }

    @PostMapping("/cadastrar")// Cadastra informações do produto ou erro
    public ResponseEntity<?> cadastrar(@RequestBody ProdutoModelo pm){
        return ps.cadastrarAlterar(pm,"cadastrar");
    }

    @GetMapping("listar")
public Iterable<ProdutoModelo> listar(){
    return ps.listar();

}

    @GetMapping("/")//Define o tipo de requisição como GET
    public String rota(){
        return "API de produtos funcionando!";
    }
    
}
 