package com.example.PostsApp.UI;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.PostsApp.Data.PostsClint;
import com.example.PostsApp.Pojo.PostModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostViewModel extends ViewModel{
    private static final String TAG = "PostViewModel";
        MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();
        MutableLiveData<String> posts = new MutableLiveData<>();

        public void getPosts() {
           Single observable = PostsClint.PostsClient.getINSTANCE()
                   .getPosts()
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread());
           observable.subscribe(o-> postsMutableLiveData.setValue((List<PostModel>) o)
                   ,e-> Log.d(TAG,"getPosts: "+e));
        }
    }

