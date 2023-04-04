package com.example.samplecompossapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.example.rocketreserver.GetCarrierDetailsQuery
import com.example.rocketreserver.GetMemberDetailsQuery
import com.example.samplecompossapp.respo.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) :
    ViewModel() {

    var errorMessage: String by mutableStateOf("")
    private val _uiState =
        MutableStateFlow<CountryViewModel.CountryUiState>(CountryViewModel.CountryUiState.Empty)
    val uiState: StateFlow<CountryViewModel.CountryUiState> = _uiState

    private val _memberUiState =
        MutableStateFlow<CountryViewModel.MemberUiState>(CountryViewModel.MemberUiState.Empty)
    val memberuiState: StateFlow<CountryViewModel.MemberUiState> = _memberUiState

    fun getCarriers() {
        _uiState.value = CountryUiState.Loading
        viewModelScope.launch {
            try {
                val countryResponse = countryRepository.getCarrierDetails(300.toString())
                val memberResponse = countryRepository.getMemberDetails()

                Log.d("SVS", "Member Response  $memberResponse")
                _uiState.value = CountryUiState.Loaded(countryResponse)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                _uiState.value = CountryUiState.Error(errorMessage)
            }
        }
    }

    fun getMember() {
        viewModelScope.launch {
            try {
                val memberResponse = countryRepository.getMemberDetails()
                Log.d("SVS", "Member Response  $memberResponse")
                _memberUiState.value = MemberUiState.Loaded(memberResponse)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }


    sealed class CountryUiState {
        object Empty : CountryUiState()
        object Loading : CountryUiState()
        class Loaded(val data: ApolloResponse<GetCarrierDetailsQuery.Data>) : CountryUiState()
        class Error(val message: String) : CountryUiState()
    }

    sealed class MemberUiState {
        object Empty : MemberUiState()
      //  object Loading : MemberUiState()
        class Loaded(val data: ApolloResponse<GetMemberDetailsQuery.Data>) : MemberUiState()
     //   class Error(val message: String) : MemberUiState()
    }

}