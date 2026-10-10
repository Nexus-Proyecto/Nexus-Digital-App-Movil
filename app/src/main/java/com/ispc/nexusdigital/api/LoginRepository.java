package com.ispc.nexusdigital.api;

import retrofit2.Call;

public class LoginRepository {

    private final ApiService apiService;

    public LoginRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Call<LoginResponse> login(LoginRequest request) {
        return apiService.login(request);
    }
}