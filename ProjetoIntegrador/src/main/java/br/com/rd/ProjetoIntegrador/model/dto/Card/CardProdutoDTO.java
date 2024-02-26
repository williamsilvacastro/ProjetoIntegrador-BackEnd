package br.com.rd.ProjetoIntegrador.model.dto.Card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;
import java.math.BigInteger;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CardProdutoDTO {

    private BigInteger id_produto;
    private String foto;
    private String nome_produto;
    private String descricao;
    private Double valor_preco;
    private Date data_de_criacao;
    private Boolean destaque;

    public CardProdutoDTO(Object[] elem) {
        if(elem[0] != null && elem[0] instanceof Number ){
            Number number = (Number) elem[0];
            if (number instanceof Integer || number instanceof Long || number instanceof Short || number instanceof Byte || number instanceof BigInteger) {
                this.id_produto = BigInteger.valueOf(number.longValue());
            } else if (number instanceof Float || number instanceof Double) {
                this.id_produto = new BigInteger(String.valueOf(number));
            } else {
                throw new IllegalArgumentException("Tipo de número não suportado");
            }
        }
        this.foto = (String) elem[1];
        this.nome_produto = (String) elem[2];
        this.descricao = (String) elem[3];
        this.valor_preco = (Double) elem[4];
        this.data_de_criacao = (Date) elem[5];
        this.destaque = (Boolean) elem[6];
    }
}
