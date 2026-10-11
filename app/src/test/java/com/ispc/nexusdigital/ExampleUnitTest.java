package com.ispc.nexusdigital;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    /**
     * Validador de campos obligatorios para formularios.
     * Test Case ID: AUT-UNIT-02
     */
    public boolean esCampoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    @Test
    public void testCampoObligatorioVacio_retornaFalso() {
        assertFalse("Un texto vacío debe ser inválido", esCampoValido(""));
        assertFalse("Un texto con solo espacios debe ser inválido", esCampoValido("   "));
        assertFalse("Un valor null debe ser inválido", esCampoValido(null));
    }

    @Test
    public void testCampoConContenido_retornaVerdadero() {
        assertTrue("Un texto con caracteres debe ser válido", esCampoValido("Juan"));
    }
}