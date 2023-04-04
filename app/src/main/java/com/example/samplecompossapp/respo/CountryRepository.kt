package com.example.samplecompossapp.respo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpHeader
import com.example.rocketreserver.GetCarrierDetailsQuery
import com.example.rocketreserver.GetMemberDetailsQuery
import javax.inject.Inject

class CountryRepository @Inject constructor(private val apolloClient: ApolloClient) {
    /* suspend fun getCountries() =
         apolloClient.query(GetAllCountryQuery()).execute()*/

    /* suspend fun getCountryDetails(continentCode: String) = apolloClient.query(
         GetCountryDetailsQuery(continentCode)).execute() */

    suspend fun getCarrierDetails(id: String) = apolloClient
        .query(GetCarrierDetailsQuery(id))
        .execute()



    suspend fun getMemberDetails() = apolloClient
        .query(GetMemberDetailsQuery())
        .addHttpHeader(
            "Authorization",
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaE1lbWJlcklkIjoiTkgyMDIzMTM0MDY4MDUiLCJtZW1iZXJJZCI6ImJldHdpbjc1NTY1NSIsImNhcnJpZXJJZCI6NDMyLCJwbGFuSWQiOjYxNzUsImNhcmROdW1iZXIiOjE2MTExNTY2NTU5NTN9.qFkxozfJDiCNjbDCRSSMh8cwleQpSqLvUX0loVMo7kc")
        .execute()
}