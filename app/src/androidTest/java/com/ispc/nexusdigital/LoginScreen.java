
package com.ispc.nexusdigital;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

public class LoginScreen {

    private final int campoEmail = R.id.etEmail;
    private final int campoClave = R.id.etClave;
    private final int botonIngresar = R.id.btnLogin;
    private final int mensajeError = R.id.tvError;

    public LoginScreen escribirEmail(String email) {
        onView(withId(campoEmail))
                .perform(clearText(), typeText(email), closeSoftKeyboard());
        return this;
    }

    public LoginScreen escribirClave(String clave) {
        onView(withId(campoClave))
                .perform(clearText(), typeText(clave), closeSoftKeyboard());
        return this;
    }

    public void ingresar() {
        onView(withId(botonIngresar)).perform(click());
    }

    public void verificarErrorVisible(String mensaje) {
        onView(withId(mensajeError))
                .check(matches(isDisplayed()))
                .check(matches(withText(mensaje)));
    }
}

