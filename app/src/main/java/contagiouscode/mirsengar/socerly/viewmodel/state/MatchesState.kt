package contagiouscode.mirsengar.socerly.viewmodel.state

sealed class MatchesState {
     object Empty : MatchesState()
     object Loading : MatchesState()
     class Success(val data : List<contagiouscode.mirsengar.socerly.data.models.Data>) : MatchesState()
     class Error(val message : String) : MatchesState()
}
