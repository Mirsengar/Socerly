package contagiouscode.mirsengar.socerly.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.HttpException
import contagiouscode.mirsengar.socerly.repository.MatchesRepository
import contagiouscode.mirsengar.socerly.viewmodel.state.MatchesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(private val matchesRepository : MatchesRepository) : ViewModel() {
     private var _inPlayMatchesState = MutableStateFlow<MatchesState>(MatchesState.Empty)
     val inPlayMatchesState : StateFlow<MatchesState> = _inPlayMatchesState.stateIn(
               initialValue = MatchesState.Empty ,
               scope = viewModelScope ,
               started = SharingStarted.WhileSubscribed(5000)
     )
     
     private var _upcomingMatchesState = MutableStateFlow<MatchesState>(MatchesState.Empty)
     val upcomingMatchesState : StateFlow<MatchesState> = _upcomingMatchesState.stateIn(
               initialValue = MatchesState.Empty ,
               scope = viewModelScope ,
               started = SharingStarted.WhileSubscribed(5000)
     )
     
     init {
          getAllInPlayMatches()
          getUpcomingMatches()
     }
     
     fun getAllInPlayMatches() {
          _inPlayMatchesState.value = MatchesState.Loading
          viewModelScope.launch(Dispatchers.IO) {
               try {
                    val inplayMatchesResponse = matchesRepository.getLiveMatches()
                    _inPlayMatchesState.value = MatchesState.Success(inplayMatchesResponse)
               }
               catch (exception : HttpException) {
                    _inPlayMatchesState.value = MatchesState.Error("Something went wrong")
               }
               catch (exception : IOException) {
                    _inPlayMatchesState.value = MatchesState.Error("No internet connection")
               }
          }
     }
     
     fun getUpcomingMatches() {
          _upcomingMatchesState.value = MatchesState.Loading
          viewModelScope.launch(Dispatchers.IO) {
               try {
                    val inplayMatchesResponse = matchesRepository.getMatches()
                    _upcomingMatchesState.value = MatchesState.Success(inplayMatchesResponse)
               }
               catch (exception : HttpException) {
                    _upcomingMatchesState.value = MatchesState.Error("Something went wrong")
               }
               catch (exception : IOException) {
                    _upcomingMatchesState.value = MatchesState.Error("No internet connection")
               }
          }
     }
}