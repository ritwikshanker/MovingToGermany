package com.moving.germany.translate

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moving.germany.base.RequestResult
import kotlinx.coroutines.launch

class TranslateViewModel : ViewModel() {

    var translatedData: MutableLiveData<RequestResult<Any>> = MutableLiveData()
    var repo = TranslateRepo()


    fun getTranslatedText() {
        viewModelScope.launch {
            translatedData.value = RequestResult.Loading("")
            try {
                val result = repo.getTranslatedText()
                translatedData.value = RequestResult.Success(result)
            } catch (e: Exception) {
                translatedData.value = RequestResult.Error(e)
            }
        }
    }
}