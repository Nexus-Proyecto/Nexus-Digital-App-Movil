
package com.ispc.nexusdigital.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private final LoginRepository loginRepository;

    private final MutableLiveData<LoginResponse> loginExitoso =
            new MutableLiveData<>();

    private final MutableLiveData<String> errorLogin =
            new MutableLiveData<>();

    public LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public LiveData<LoginResponse> getLoginExitoso() {
        return loginExitoso;
    }

    public LiveData<String> getErrorLogin() {
        return errorLogin;
    }

    public void iniciarSesion(LoginRequest request) {

        loginRepository.login(request).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(
                    Call<LoginResponse> call,
                    Response<LoginResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    loginExitoso.setValue(response.body());
                } else {
                    errorLogin.setValue(
                            "No se pudo iniciar sesión. Verificá tus datos."
                    );
                }
            }

            @Override
            public void onFailure(
                    Call<LoginResponse> call,
                    Throwable t) {

                errorLogin.setValue(
                        "No se pudo conectar con el servidor."
                );
            }
        });
    }
}
