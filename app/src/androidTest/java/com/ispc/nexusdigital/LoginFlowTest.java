package com.ispc.nexusdigital;

import androidx.test.espresso.accessibility.AccessibilityChecks;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginFlowTest {

    // Chequeo de accesibilidad desactivado temporalmente
    // @BeforeClass
    // public static void enableAccessibilityChecks() {
    //     AccessibilityChecks.enable();
    // }

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void mostrarErrorCuandoLosCamposEstanVacios() {
        // Arrange
        LoginScreen login = new LoginScreen();

        // Act
        login.ingresar();

        // Assert
        login.verificarErrorVisible(
                "Completá email y contraseña"
        );
    }
}