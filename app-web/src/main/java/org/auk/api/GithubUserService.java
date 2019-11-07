package org.auk.api;

import com.google.gson.JsonArray;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubUserService {

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: TechUp-Web-App"
    })
    @GET("users/{username}")
    Call<User> getByUsername(@Path("username") String username);

    @GET("user/repos")
    Call<JsonArray> getMyRepos(@Query("type") String type);

    @GET("users/{username}/repos")
    Call<JsonArray> getUserRepos(@Path("username") String username);

}
