package br.com.API.Produtos.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.API.Produtos.modelo.ProdutoModelo;

@Repository //define esse arquivo como repositorio
public interface ProdutoReporitorio extends CrudRepository<ProdutoModelo,Long> {//<aquivos com os atributo, tipo do ID> 
    
}
