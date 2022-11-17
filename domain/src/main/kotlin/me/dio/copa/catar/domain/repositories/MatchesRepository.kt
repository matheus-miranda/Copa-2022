package me.dio.copa.catar.domain.repositories

import kotlinx.coroutines.flow.Flow
import me.dio.copa.catar.domain.model.Match

interface MatchesRepository {
    fun getMatches(): Flow<List<Match>>
    suspend fun enableNotificationFor(id: String)
    suspend fun disableNotificationFor(id: String)
}
