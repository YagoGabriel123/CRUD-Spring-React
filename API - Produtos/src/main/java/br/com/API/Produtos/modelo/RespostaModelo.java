package br.com.API.Produtos.modelo;


import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component //permite a injeção de dependencia
@Getter
@Setter
public class RespostaModelo {
    
    private String mensagen;
}
