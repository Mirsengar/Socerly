package contagiouscode.mirsengar.socerly.data

import contagiouscode.mirsengar.socerly.data.models.MatchesResponse
import contagiouscode.mirsengar.socerly.util.API_KEY
import contagiouscode.mirsengar.socerly.util.GET_MATCH_ENDPOINT
import contagiouscode.mirsengar.socerly.util.SEASON_ID
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
     @GET(GET_MATCH_ENDPOINT)
     suspend fun getMatches(
               @Query("apikey") apiKey : String = API_KEY ,
               @Query("season_id") seasonId : Int = SEASON_ID ,
     ) : MatchesResponse
     
     @GET(GET_MATCH_ENDPOINT)
     suspend fun getLiveMatches(
               @Query("apikey") apiKey : String = API_KEY ,
               @Query("live") seasonId : Int = SEASON_ID ,
     ) : MatchesResponse
}