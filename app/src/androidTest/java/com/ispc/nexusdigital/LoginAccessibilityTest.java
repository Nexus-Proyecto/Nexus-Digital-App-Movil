package com.ispc.nexusdigital;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.accessibility.AccessibilityChecks;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(AndroidJUnit4.class)
public class LoginAccessibilityTest {

    private final ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);


    @Rule
    public RuleChain reglas = RuleChain
            .outerRule(new ExternalResource() {
                @Override
                protected void before() {
                    cerrarSesionGuardada();
                }
            })
            .around(activityRule);

    @BeforeClass
    public static void habilitarChequeosDeAccesibilidad() {
        AccessibilityChecks.enable()
                .setRunChecksFromRootView(true); // audita TODA la pantalla en cada interacción
    }

    @Test
    public void camposDelLogin_cumplenChequeosDeAccesibilidad() {
        // Arrange
        LoginScreen login = new LoginScreen();

        // Act: cada interacción dispara la auditoría de accesibilidad
        login.escribirEmail("admin@nexus.com")
                .escribirClave("1234");

        // Assert: si un chequeo falla, Espresso lanza AccessibilityViewCheckException
    }

    @Test
    public void mensajeDeError_cumpleChequeosDeAccesibilidad() {
        // Arrange
        LoginScreen login = new LoginScreen();

        // Act: con los campos vacíos aparece el mensaje de error (validación local, sin red)
        login.ingresar();

        // Assert: se audita la pantalla con el mensaje de error visible
        login.verificarErrorVisible("Completá email y contraseña");
    }

    private static void cerrarSesionGuardada() {
        Context ctx = ApplicationProvider.getApplicationContext();
        File dir = new File(ctx.getApplicationInfo().dataDir, "shared_prefs");
        File[] archivos = dir.listFiles();
        if (archivos == null) return;
        for (File f : archivos) {
            String nombre = f.getName().replace(".xml", "");
            ctx.getSharedPreferences(nombre, Context.MODE_PRIVATE).edit().clear().commit();
        }
    }
}
