
import { useInsertionEffect, useState } from 'react';
import './App.css';
import Formulario from './Formulario';
import Tabela from './Tabela';
//import { type } from '@testing-library/user-event/dist/type';

function App() {
//Objeto produto
const produto = {
  codigo : 0,
  nome : '',
  marca: ''
}


//UserState
const[btnCadastrar,setBtnCadastrar] = useState(true);
const [produtos, setProdutos] = useState([]);
const[objProduto,setObjProduto] = useState(produto);


//UserEffect
useInsertionEffect(()=>{
fetch("http://localhost:8080/listar")
.then(retorno => retorno.json())
.then(retorno_convertido => setProdutos(retorno_convertido));
},[]);

//Obter os dados do formulario
const aoDigitar = (e) => {
 setObjProduto({...objProduto,[e.target.name]:e.target.value});
} 
  //Cadastrar produto
  const cadastrar = () =>{
    fetch("http://localhost:8080/cadastrar",{
      method: 'post', //determina o tipo de parametro como POST
      body:JSON.stringify(objProduto),
      headers:{
        'Content-type':'application/json',
        'Accept':'application/json'
      }
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido =>{
      
      if(retorno_convertido.mensagem !== undefined){
        alert(retorno_convertido.mensagem);
      }else{
        setProdutos([...produtos, retorno_convertido]);//
        alert('Produto cadastrado com sucesso');
        limparFormulario();
      }

    })
  }

   //Alterar produto
   const alterar = () =>{
    fetch("http://localhost:8080/alterar",{
      method: 'put', //determina o tipo de parametro como PUT
      body:JSON.stringify(objProduto),
      headers:{
        'Content-type':'application/json',
        'Accept':'application/json'
      }
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido =>{
      
      if(retorno_convertido.mensagem !== undefined){
        alert(retorno_convertido.mensagem);
      }else{
        alert('Produto alterado com sucesso');
    //Copia do vertor de produtos
    let vetorTemp = [...produtos];

    //indice
    let indice = vetorTemp.findIndex((p) =>{ // percore o vetor e retorna o valor da verificação
      return p.codigo === objProduto.codigo;//
    });

    //Alterar produto do vetorTemp
    vetorTemp[indice] = objProduto;
    //atualizar o vetor de produtos
    setProdutos(vetorTemp);

        limparFormulario();
      }

    })
  }

//Remover produto
const remover = () =>{
  fetch("http://localhost:8080/remover" +objProduto.codigo,{
    method: 'delete', //determina o tipo de parametro como DELETE
    headers:{
      'Content-type':'application/json',
      'Accept':'application/json'
    }
  })
  .then(retorno => retorno.json())
  .then(retorno_convertido =>{
    
    //Mensagem
    alert(retorno_convertido.mensagem);

    //Copia do vertor de produtos
    let vetorTemp = [...produtos];

    //indice
    let indice = vetorTemp.findIndex((p) =>{ // percore o vetor e retorna o valor da verificação
      return p.codigo === objProduto.codigo;//
    });

    //Remover produto do vetorTemp
    vetorTemp.splice(indice,1);
    //atualizar o vetor de produtos
    setProdutos(vetorTemp);
    limparFormulario();
  })
}


  //Limpar formulario

  const limparFormulario = () =>{
    setObjProduto(produto);
    setBtnCadastrar(true);

  }

  
  //Selecionar Produto
  const selecionarProduto = (indice) =>{
setObjProduto(produtos[indice]);
setBtnCadastrar(false);

  }
  
  //Retorno          
  return (
    <div >
      <Formulario botao={btnCadastrar} eventoTeclado={aoDigitar}cadastrar={cadastrar} 
      obj ={objProduto}cancelar={limparFormulario} remover={remover} alterar={alterar}/>
      <Tabela vetor={produtos} selecionar ={selecionarProduto} />
    </div>
  );
}

export default App;
