package com.ispc.nexusdigital;

import java.util.regex.Pattern;

/**
 * Validador para verificación de formato de correo electrónico.
 */
public class EmailValidator {

    // Expresión regular estándar para validar correos electrónicos
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Valida si una cadena de texto tiene un formato de email válido.
     *
     * @param email Dirección de correo electrónico a validar.
     * @return true si el email es válido; false si es nulo, vacío o tiene formato inválido.
     */
    public static boolean esValido(String email) {
        if (email == null) {
            return false;
        }
        String emailTrimmed = email.trim();
        if (emailTrimmed.isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(emailTrimmed).matches();
    }
}
