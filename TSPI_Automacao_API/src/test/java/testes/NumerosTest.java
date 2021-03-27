package testes;

import numeros.Numeros;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NumerosTest {

    private Numeros numeros;

    @Before
    public void setUp(){
        numeros = new Numeros();
    }
    @Test
    public void testValidarSeEUmaUnidade(){
        // Vou utilizar o método eUmaUnidade passando o valor 9, que trata=se de uma unidade
        boolean eUnidade = numeros.eUmaUnidade(9);

        // Vou validar que a resposta é verdadeira
        Assert.assertTrue(eUnidade);
    }

    @Test
    public void testValidarSeUmNumeroNaoEUmaUnidade(){
        // Vou utilizar o método eUmaUnidade passando o valor 10, que não trata=se de uma unidade
        boolean eUnidade = numeros.eUmaUnidade(10);

        // Vou validar que a resposta é verdadeira
        Assert.assertFalse(eUnidade);
    }

}
