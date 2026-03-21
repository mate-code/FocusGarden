package com.matecode.focusgarden;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {
    private final MutableLiveData<String> selectedCategory = new MutableLiveData<String>();

    public CategoryViewModel(){
        selectedCategory.setValue("Work");
    }

    public MutableLiveData<String> getSelectedCategory() {
        return selectedCategory;
    }
}
