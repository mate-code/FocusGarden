package com.matecode.focusgarden.Category;

import androidx.lifecycle.MutableLiveData;
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
