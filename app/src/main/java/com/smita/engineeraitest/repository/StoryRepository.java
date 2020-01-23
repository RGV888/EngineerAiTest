package com.smita.engineeraitest.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.smita.engineeraitest.Models.Hits;
import com.smita.engineeraitest.Models.StoryResponse;
import com.smita.engineeraitest.services.RetrofitApis;
import com.smita.engineeraitest.services.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryRepository {
    public RetrofitApis retrofitApis;
    public static StoryRepository storyRepository;
    private int maxLimit=0;
    private MutableLiveData<List<Hits>> liveData=new MutableLiveData<>();
    public StoryRepository(){
        retrofitApis= RetrofitClient.getRetrofitClient().create(RetrofitApis.class);
    }

    public static StoryRepository getInstance(){

        if(storyRepository==null){
            storyRepository=new StoryRepository();
        }
        return storyRepository;

    }


    public LiveData<List<Hits>> getLiveData(){
        if(liveData==null){
            liveData=new MutableLiveData<>();
        }
        return liveData;
    }



    public void CallForTopic(int pageNo){

        if(pageNo!=1 && pageNo>maxLimit ){
            return;
        }

        retrofitApis.getTopicList("story",pageNo).enqueue(new Callback<StoryResponse>() {
            @Override
            public void onResponse(Call<StoryResponse> call, Response<StoryResponse> response) {
                if(response.isSuccessful()){

                    maxLimit=response.body().getNbPages();
                    liveData.postValue(response.body().getHits());

                }
            }

            @Override
            public void onFailure(Call<StoryResponse> call, Throwable t) {
                liveData.postValue(null);
            }
        });

    }


}
