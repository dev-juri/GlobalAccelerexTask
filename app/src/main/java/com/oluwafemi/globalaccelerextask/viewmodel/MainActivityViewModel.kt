package com.oluwafemi.globalaccelerextask.viewmodel


import androidx.lifecycle.*
import com.oluwafemi.globalaccelerextask.NetworkResult
import com.oluwafemi.globalaccelerextask.domain.CardDetails
import com.oluwafemi.globalaccelerextask.repository.RepositoryImpl
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: RepositoryImpl,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<NetworkResult<CardDetails>> = MutableLiveData()
    val dataState: LiveData<NetworkResult<CardDetails>> get() = _dataState

    fun getDetails(cardNumber: Long) {
        viewModelScope.launch {
            _dataState.value = repository.fetchCardDetails(cardNumber)
        }
    }
}

