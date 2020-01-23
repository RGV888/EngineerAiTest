package com.smita.engineeraitest.services;

import com.smita.engineeraitest.Models.StoryResponse;
import com.smita.engineeraitest.utils.AppApis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApis {

    @GET(AppApis.SEARCH_TOPICS_URL)
    Call<StoryResponse> getTopicList(@Query("tags") String tags,@Query("page") int pageNo);

}
