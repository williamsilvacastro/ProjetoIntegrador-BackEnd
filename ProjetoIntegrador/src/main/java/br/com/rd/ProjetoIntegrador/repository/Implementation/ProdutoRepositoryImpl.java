package br.com.rd.ProjetoIntegrador.repository.Implementation;

import br.com.rd.ProjetoIntegrador.model.dto.Card.CardProdutoDTO;
import br.com.rd.ProjetoIntegrador.model.entity.Produto;
import br.com.rd.ProjetoIntegrador.repository.ProdutoRepositoryCustom;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityResult;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

//    @Override
//    public List<Produto> buscaAvancada(String cat, String marc, String fam, String prato) {
//        Query sql = entityManager.createNativeQuery("SELECT * FROM produto where id_categoria "+cat+"   AND id_marca "+marc+" AND id_familia "+fam+" AND id_prato "+prato+"", Produto.class);
//        List<Produto> list = sql.getResultList();
//        return list;
//    }

    @Override
    public CardProdutoDTO findCardProdutoById_produto(Long id) {
        Query q =entityManager.createNativeQuery("select pv.id_produto, p.foto, p.nome_produto, p.descricao, pv.valor_preco, p.data_de_criacao, p.destaque from produto p inner join preco_venda pv on (p.id_produto =pv.id_produto) where pv.id_produto = :id order by pv.data_vigencia desc limit 1");
        q.setParameter("id",id);
        List<Object[]> resultList = q.getResultList();
        List<CardProdutoDTO> listCard = resultList.stream().map(elem -> new CardProdutoDTO(elem)).collect(Collectors.toList());
        for (CardProdutoDTO c : listCard){
            return c;
        }
        return null;
    }

    @Override
    public List<CardProdutoDTO> findCardsProdutoById_produto(List<Long> list) {
        String ids="pv.id_produto<0";
        int cont =0;
        for(Long id:list){
            ids+=" or pv.id_produto = "+id+" ";
            cont++;
        }
        Query q =entityManager.createNativeQuery("select pv.id_produto, p.foto, p.nome_produto, p.descricao, pv.valor_preco, p.data_de_criacao, p.destaque from produto p inner join preco_venda pv on (p.id_produto =pv.id_produto) where "+ids+ " order by pv.data_vigencia desc, pv.id_produto limit "+cont);
        List<Object[]> resultList = q.getResultList();
        List<CardProdutoDTO> listCard = resultList.stream().map(elem -> new CardProdutoDTO(elem)).collect(Collectors.toList());
        return listCard;
    }

    @Override
    public List<CardProdutoDTO> findCardsProdutoByNovidade() {


        Query q =entityManager.createNativeQuery("select pv.id_produto, p.foto, p.nome_produto, p.descricao, pv.valor_preco, p.data_de_criacao, p.destaque from produto p inner join preco_venda pv on (p.id_produto =pv.id_produto) order by pv.data_vigencia desc, p.data_de_criacao asc, pv.id_produto limit 5");
        List<Object[]> resultList = q.getResultList();
        List<CardProdutoDTO> listCard = resultList.stream().map(elem -> new CardProdutoDTO(elem)).collect(Collectors.toList());
        return listCard;
    }

    @Override
    public List<CardProdutoDTO> findCardsProdutoByDestaques() {


        Query q =entityManager.createNativeQuery("select pv.id_produto, p.foto, p.nome_produto, p.descricao, pv.valor_preco, p.data_de_criacao, p.destaque from produto p inner join preco_venda pv on (p.id_produto =pv.id_produto) where p.destaque = true order by pv.data_vigencia desc, pv.id_produto asc limit 10");
        List<Object[]> resultList = q.getResultList();
        List<CardProdutoDTO> listCard = resultList.stream().map(elem -> new CardProdutoDTO(elem)).collect(Collectors.toList());
        return listCard;
    }
    @Override
    public List<CardProdutoDTO> findCardsProdutoByDestaquesAll() {


//        Query q =entityManager.createNativeQuery("select pv.id_produto, p.foto, p.nome_produto, p.descricao, pv.valor_preco, p.data_de_criacao, p.destaque from produto p inner join ( select pv2.valor_preco, pv2.id_produto, pv2.data_vigencia from preco_venda as pv2 join ( select valor_preco, id_produto, max(data_vigencia) as data_vigencia from preco_venda group by id_produto, data_vigencia) as pv3 on pv3.id_produto = pv2.id_produto where pv3.data_vigencia = pv2.data_vigencia) pv on (p.id_produto = pv.id_produto) where p.destaque = true");
//        List<CardProdutoDTO> listCard = (List<CardProdutoDTO>) q.getResultList().stream().map(elem -> new CardProdutoDTO((EntityResult) elem)).collect(Collectors.toList());
        String queryString = "select pv.id_produto, p.foto, p.nome_produto, p.descricao, pv.valor_preco, p.data_de_criacao, p.destaque from produto p " +
                "inner join ( " +
                "select pv2.valor_preco, pv2.id_produto, pv2.data_vigencia from preco_venda as pv2 " +
                "join ( " +
                "select valor_preco, id_produto, max(data_vigencia) as data_vigencia from preco_venda group by id_produto, data_vigencia" +
                ") as pv3 on pv3.id_produto = pv2.id_produto where pv3.data_vigencia = pv2.data_vigencia" +
                ") pv on (p.id_produto = pv.id_produto) where p.destaque = true";
        Query q = entityManager.createNativeQuery(queryString);
        List<Object[]> resultList = q.getResultList();
        List<CardProdutoDTO> listCard = resultList.stream().map(elem -> new CardProdutoDTO(elem)).collect(Collectors.toList());
        return listCard;
    }

    @Override
    public List<CardProdutoDTO> findCardsProdutoByIdMarca(Long id) {
        Query q =entityManager.createNativeQuery("select pv.id_produto, p.foto, p.nome_produto, p.descricao, pv.valor_preco, p.data_de_criacao, p.destaque from produto p inner join preco_venda pv on (p.id_produto =pv.id_produto) where p.id_marca = "+id+" order by pv.data_vigencia desc, pv.id_produto asc limit 10");
        List<Object[]> resultList = q.getResultList();
        List<CardProdutoDTO> listCard = resultList.stream().map(elem -> new CardProdutoDTO(elem)).collect(Collectors.toList());
        return listCard;
    }

    @Override
    public List<CardProdutoDTO> findCardsProdutoByBusca(String busca) {


        Query q =entityManager.createNativeQuery("select pv.id_produto, p.foto, p.nome_produto, p.descricao, pv.valor_preco, p.data_de_criacao, p.destaque from produto p inner join (select pv2.valor_preco, pv2.id_produto, pv2.data_vigencia from preco_venda as pv2 join (select valor_preco, id_produto, max(data_vigencia) as data_vigencia from preco_venda group by id_produto, data_vigencia) as pv3 on pv3.id_produto = pv2.id_produto where pv3.data_vigencia = pv2.data_vigencia) pv on (p.id_produto =pv.id_produto) WHERE p.NOME_PRODUTO LIKE '%"+busca+"%' order by p.nome_produto");
        List<CardProdutoDTO> listCard = q.getResultList();
        return listCard;
    }
    @Override
    public List<CardProdutoDTO> buscaAvancada(String cat, String marc, String fam, String prato) {
        Query q= entityManager.createNativeQuery("SELECT pv.id_produto, p.foto, p.nome_produto, p.descricao, pv.valor_preco,p.data_de_criacao, p.destaque FROM produto p inner join (select pv2.valor_preco, pv2.id_produto, pv2.data_vigencia from preco_venda as pv2 join (select valor_preco, id_produto, max(data_vigencia) as data_vigencia from preco_venda group by id_produto, data_vigencia) as pv3 on pv3.id_produto = pv2.id_produto where pv3.data_vigencia = pv2.data_vigencia) pv on (p.id_produto =pv.id_produto) where id_categoria "+cat+"   AND id_marca "+marc+" AND id_familia "+fam+" AND id_prato "+prato+"");
        List resultList = q.getResultList();
        List<CardProdutoDTO> listCard = (List<CardProdutoDTO>) resultList.stream().map(elem -> new CardProdutoDTO((Object[]) elem)).collect(Collectors.toList());
        return listCard;
    }

    @Override
    public List<CardProdutoDTO> findCardsProdutoAll() {


        Query q =entityManager.createNativeQuery("select pv.id_produto, p.foto, p.nome_produto, p.descricao, pv.valor_preco, p.data_de_criacao, p.destaque from produto p inner join (select pv2.valor_preco, pv2.id_produto, pv2.data_vigencia from preco_venda as pv2 join (select valor_preco, id_produto, max(data_vigencia) as data_vigencia from preco_venda group by id_produto, data_vigencia) as pv3 on pv3.id_produto = pv2.id_produto where pv3.data_vigencia = pv2.data_vigencia) pv on (p.id_produto =pv.id_produto) ORDER BY RAND ()");
        List<Object[]> resultList = q.getResultList();
        List<CardProdutoDTO> listCard = resultList.stream().map(elem -> new CardProdutoDTO(elem)).collect(Collectors.toList());
        return listCard;
    }

}
