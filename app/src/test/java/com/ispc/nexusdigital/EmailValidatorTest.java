package com.ispc.nexusdigital;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test

/**
 * Pruebas unitarias con JUnit para la validación de correos electrónicos.
 * Test Case: Validador - email válido
 */
public class EmailValidatorTest {

    // --- Métodos principales para la matriz de pruebas ---

    @Test
    public void testEmailValido_retornaVerdadero() {
        assertTrue("Un email estándar debe ser válido",
                EmailValidator.esValido("usuario@dominio.com"));
        assertTrue("Un email con subdominio debe ser válido",
                EmailValidator.esValido("alumno@ispc.edu.ar"));
    }

    @Test
    public void testEmailMalFormado_retornaFalso() {
        assertFalse("Un email sin '@' debe ser inválido",
                EmailValidator.esValido("usuariodominio.com"));
        assertFalse("Un email sin dominio debe ser inválido",
                EmailValidator.esValido("usuario@"));
        assertFalse("Un valor null debe ser inválido",
                EmailValidator.esValido(null));
        assertFalse("Un texto vacío debe ser inválido",
                EmailValidator.esValido(""));
    }

    // --- Casos de prueba detallados (válidos) ---

    @Test
    public void emailValidoEstandar_retornaVerdadero() {
        assertTrue("Un email estándar como 'usuario@dominio.com' debe ser válido",
                EmailValidator.esValido("usuario@dominio.com"));
    }

    @Test
    public void emailConPuntosYGuiones_retornaVerdadero() {
        assertTrue("Un email con puntos y guiones en el usuario debe ser válido",
                EmailValidator.esValido("juan.alonso-dev@dominio.com"));
    }

    @Test
    public void emailConSubdominioYDominioPais_retornaVerdadero() {
        assertTrue("Un email con subdominios como '.edu.ar' debe ser válido",
                EmailValidator.esValido("alumno@ispc.edu.ar"));
        assertTrue("Un email con '.com.ar' debe ser válido",
                EmailValidator.esValido("contacto@nexusdigital.com.ar"));
    }

    @Test
    public void emailConSignoMas_retornaVerdadero() {
        assertTrue("Un email con alias '+' debe ser válido",
                EmailValidator.esValido("usuario+notificaciones@nexus.com"));
    }

    @Test
    public void emailConEspaciosAlInicioOFin_retornaVerdadero() {
        assertTrue("Un email válido con espacios en los extremos debe aceptarse tras aplicar trim",
                EmailValidator.esValido("  usuario@nexus.com  "));
    }

    // --- Casos de prueba inválidos ---

    @Test
    public void emailNulo_retornaFalso() {
        assertFalse("Un valor null debe ser inválido",
                EmailValidator.esValido(null));
    }

    @Test
    public void emailVacio_retornaFalso() {
        assertFalse("Un texto vacío debe ser inválido",
                EmailValidator.esValido(""));
    }

    @Test
    public void emailSoloEspacios_retornaFalso() {
        assertFalse("Un texto con solo espacios debe ser inválido",
                EmailValidator.esValido("    "));
    }

    @Test
    public void emailSinArroba_retornaFalso() {
        assertFalse("Un email sin '@' debe ser inválido",
                EmailValidator.esValido("usuariodominio.com"));
    }

    @Test
    public void emailSinUsuario_retornaFalso() {
        assertFalse("Un email sin parte local antes de la '@' debe ser inválido",
                EmailValidator.esValido("@dominio.com"));
    }

    @Test
    public void emailSinDominio_retornaFalso() {
        assertFalse("Un email sin dominio después de la '@' debe ser inválido",
                EmailValidator.esValido("usuario@"));
    }

    @Test
    public void emailSinExtensionOConTldIncompleto_retornaFalso() {
        assertFalse("Un email sin extensión de dominio (TLD) debe ser inválido",
                EmailValidator.esValido("usuario@dominio"));
        assertFalse("Un email con extensión de un solo carácter debe ser inválido",
                EmailValidator.esValido("usuario@dominio.c"));
    }

    @Test
    public void emailConMultiplesArrobas_retornaFalso() {
        assertFalse("Un email con múltiples '@' debe ser inválido",
                EmailValidator.esValido("usuario@@dominio.com"));
        assertFalse("Un email con '@' duplicada en partes distintas debe ser inválido",
                EmailValidator.esValido("usuario@nexus@dominio.com"));
    }

    @Test
    public void emailConEspaciosIntermedios_retornaFalso() {
        assertFalse("Un email con espacios intermedios debe ser inválido",
                EmailValidator.esValido("usuario @dominio.com"));
        assertFalse("Un email con espacios en el dominio debe ser inválido",
                EmailValidator.esValido("usuario@domin io.com"));
    }
}
