package eng3.barbara;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.Test;

public class TesteCalculaDataDevolucao {

    // teste com 3 livros
    @Test
    public void testEmprestimoComTresLivros() {
        Emprestimo emprestimo = new Emprestimo();

        Livro livro1 = new Livro(1); // Prazo  dias
        Livro livro2 = new Livro(2); 
        Livro livro3 = new Livro(3); 

        emprestimo.itens.add(new Item(livro1));
        emprestimo.itens.add(new Item(livro2));
        emprestimo.itens.add(new Item(livro3));

        Date dataEmprestimo = new Date();
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(dataEmprestimo);

       
        calendario.add(Calendar.DATE, 6);
        Date dataEsperada = calendario.getTime();

        assertEquals(dataEsperada.toString(), emprestimo.calculaDataDevolucao().toString());
    }

    
    @Test(expected = IllegalArgumentException.class)
    public void testExcecaoParaMaisDeCincoLivros() {
        Emprestimo emprestimo = new Emprestimo();

        // Add 6 livros
        for (int i = 0; i < 6; i++) {
            emprestimo.itens.add(new Item(new Livro(i + 1)));
        }

        emprestimo.calculaDataDevolucao(); 
    }

    // teste 1 livro
    @Test
    public void testEmprestimoComUmLivro() {
        Emprestimo emprestimo = new Emprestimo();

        Livro livro1 = new Livro(1); // Prazo 2 dias

        emprestimo.itens.add(new Item(livro1));

        Date dataEmprestimo = new Date();
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(dataEmprestimo);
        calendario.add(Calendar.DATE, 2); // 2 dias

        Date dataEsperada = calendario.getTime();
        assertEquals(dataEsperada.toString(), emprestimo.calculaDataDevolucao().toString());
    }

    // Teste data nula
    @Test
    public void testItemComDataNula() {
        Item item = new Item(new Livro(1));
        item.calculaDataDevolucao(null);
        assertNull(item.getDataDevolucao());
    }

    // Teste setDataDevolucao
    @Test
    public void testSetDataDevolucao() {
        Item item = new Item(new Livro(1));
        Date data = new Date();
        item.setDataDevolucao(data);
        assertEquals(data, item.getDataDevolucao());
    }
}
}
