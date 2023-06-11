package br.com.API.Produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.API.Produtos.modelo.ProdutoModelo;
import br.com.API.Produtos.modelo.RespostaModelo;
import br.com.API.Produtos.repositorio.ProdutoReporitorio;

@Service
public class ProdutoServico {
@Autowired
private ProdutoReporitorio pr;

@Autowired
private RespostaModelo rm;//feedback para falta de infomação no cadastro de produto

//Método para listar todos os produtos
public Iterable<ProdutoModelo> listar(){
    return pr.findAll();
}
    //Método para cadatrar ou alterar produtos 
    public ResponseEntity<?> cadastrarAlterar(ProdutoModelo pm, String acao){

        //Verifica se o Nome do produto foi inserido
        if(pm.getNome().equals("")){
            rm.setMensagen("O nome do produto é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        } 
        
        //Verifica se a Marca do produto foi inserido
        else if(pm.getMarca().equals("")){
            rm.setMensagen("O nome da Marca é obrigatório");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }
        //Se o nome e marca do produto estiverem OK - salva o produto!
        else {
            if(acao.equals("cadastrar")){
                return new ResponseEntity<ProdutoModelo>(pr.save(pm),HttpStatus.CREATED);
            }else{
                return new ResponseEntity<ProdutoModelo>(pr.save(pm),HttpStatus.OK);

            }
        }
        }
        //Método para remover produtos
        public ResponseEntity<RespostaModelo> remover(long codigo){
            pr.deleteById(codigo);

            rm.setMensagen("O produto "+codigo+ " foi removido com sucesso");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
        }

    }
    

