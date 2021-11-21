package br.com.rd.ProjetoIntegrador.Controller;

import br.com.rd.ProjetoIntegrador.model.dto.Card.CardProdutoDTO;
import br.com.rd.ProjetoIntegrador.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Card")
public class CardController {
    @Autowired
    ProdutoService produtoService;


    @GetMapping("{id}")
    public CardProdutoDTO findCardProdutoById_produto(@PathVariable("id") Long id){
        return this.produtoService.findCardProdutoById_produto(id);
    }
    @GetMapping("/multi")
    public List<CardProdutoDTO> findCardsProfutoById_produto(@RequestBody List<Long> list){
        return this.produtoService.findCardsProfutoById_produto(list);
    }

    @GetMapping("/novidades")
    public List<CardProdutoDTO> findCardsProfutoByNovidade(){
        return this.produtoService.findCardsProfutoByNovidade();
    }

    @GetMapping("/destaques")
    public List<CardProdutoDTO> findCardsProdutoByDestaques(){
        return this.produtoService.findCardsProdutoByDestaques();
    }

    @GetMapping("/busca/{busca}")
    public List<CardProdutoDTO> findCardsProdutoByBusca(@PathVariable("busca") String busca){
        return this.produtoService.findCardsProdutoByBusca(busca);
    }


}
