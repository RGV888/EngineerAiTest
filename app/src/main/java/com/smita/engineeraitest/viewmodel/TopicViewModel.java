package com.smita.engineeraitest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.smita.engineeraitest.Models.Hits;
import com.smita.engineeraitest.repository.StoryRepository;

import java.util.List;

public class TopicViewModel extends AndroidViewModel {

    private StoryRepository storyRepository;
    public TopicViewModel(@NonNull Application application) {
        super(application);
        storyRepository=StoryRepository.getInstance();
    }

    public LiveData<List<Hits>> getTopicList(){
        return storyRepository.getLiveData();
    }

    public void CallForTopicList(int pageNo){
        storyRepository.CallForTopic(pageNo);
    }
}
