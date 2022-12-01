package com.sae.tukangin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiManager {
        private static RetrofitInterface service;
        private static ApiManager apiManager;
        private ApiManager() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://127.0.0.1:8000/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(RetrofitInterface.class);
        }
        public static ApiManager getInstance() {
            if (apiManager == null) {
                apiManager = new ApiManager();
            }
            return apiManager;
        }
        public void createUser(User user) {
            Call<User> userCall = service.registerUser(user);
        }

}
