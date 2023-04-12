package com.example.samplecompossapp.ui.viewmodel

import android.text.Editable
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.example.rocketreserver.GetCarrierDetailsQuery
import com.example.rocketreserver.GetMemberDetailsQuery
import com.example.samplecompossapp.respo.CountryRepository
import dagger.Module
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class CountryViewModel @AssistedInject constructor(private val countryRepository: CountryRepository,
                                                   @Assisted private val carrierID : String) :
    ViewModel() {

    var errorMessage: String by mutableStateOf("")
    private val _uiState =
        MutableStateFlow<CountryViewModel.CountryUiState>(CountryViewModel.CountryUiState.Empty)
    val uiState: StateFlow<CountryViewModel.CountryUiState> = _uiState

    private val _memberUiState =
        MutableStateFlow<CountryViewModel.MemberUiState>(CountryViewModel.MemberUiState.Empty)
    val memberuiState: StateFlow<CountryViewModel.MemberUiState> = _memberUiState


    @AssistedFactory
    interface CarrierIDFactory {
        fun create(carrierID: String): CountryViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun providesFactory(
            assistedFactory: CarrierIDFactory,
            carrierID: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                // using our ArticlesFeedViewModelFactory's create function
                // to actually create our viewmodel instance
                return assistedFactory.create(carrierID) as T
            }
        }
    }

    @Module
    @InstallIn(ActivityRetainedComponent::class)
    interface AssistedInjectModule

    fun getCarriers() {
        _uiState.value = CountryUiState.Loading
        viewModelScope.launch {
            try {
                val countryResponse = countryRepository.getCarrierDetails(carrierID)
                val memberResponse = countryRepository.getMemberDetails()

                Log.d("SVS", "Member Response  $carrierID  $memberResponse")
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