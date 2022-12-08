package contagiouscode.mirsengar.socerly.repository

import contagiouscode.mirsengar.socerly.data.ApiService
import contagiouscode.mirsengar.socerly.data.models.Data
import java.util.Date
import javax.inject.Inject

class MatchesRepository @Inject constructor(private val apiService : ApiService) {
     suspend fun getMatches() : List<Data> = apiService.getMatches().data
     suspend fun getLiveMatches() : List<Data> = apiService.getLiveMatches().data
}