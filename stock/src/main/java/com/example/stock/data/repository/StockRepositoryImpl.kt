package com.example.stock.data.repository

import com.example.stock.data.csv.CSVParser
import com.example.stock.data.local.StockDatabase
import com.example.stock.data.mapper.toCompanyListing
import com.example.stock.data.mapper.toCompanyListingEntity
import com.example.stock.data.remote.StockApi
import com.example.stock.domain.model.CompanyListing
import com.example.stock.domain.repository.StockRepository
import com.example.stock.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @date: 2022/6/13
 * @author: chenyongda3
 * Description:
 */
@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase,
    val companyListingsParser: CSVParser<CompanyListing>
) : StockRepository {

    val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()

            val shouldJustLoadCache = !fetchFromRemote && !isDbEmpty

            if (shouldJustLoadCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {
                val response = api.getListings()
                companyListingsParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map {
                        it.toCompanyListingEntity()
                    }
                )
                emit(
                    Resource.Success(dao.searchCompanyListing("")
                        .map { it.toCompanyListing() })
                )
                emit(Resource.Loading(false))
            }
        }
    }
}