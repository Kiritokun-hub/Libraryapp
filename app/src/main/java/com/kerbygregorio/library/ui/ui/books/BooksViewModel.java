package com.kerbygregorio.library.ui.ui.books;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BooksViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public BooksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}