package br.com.rd.ProjetoIntegrador.Controller.Card;

import br.com.rd.ProjetoIntegrador.Controller.BaseControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

public class CardControllerTest extends BaseControllerTest {

    @Test
    public void testGetNovidadesCards() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Card/novidades")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetFindCardProdutoById_produto() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Card/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testPostFindCardsProfutoById_produto() throws Exception {
        List<Long> list = List.of(1L, 2L, 3L, 4L, 5L);
        mockMvc.perform(MockMvcRequestBuilders.post("/Card/multi")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(list.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Adicione mais métodos de teste para outras funcionalidades da controller
}

