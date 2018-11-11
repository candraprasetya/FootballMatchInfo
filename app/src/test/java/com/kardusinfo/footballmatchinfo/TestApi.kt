package com.kardusinfo.footballmatchinfo

import com.kardusinfo.footballmatchinfo.api.ApiRepository
import org.junit.Test
import org.mockito.Mockito

class TestApi{

@Test
fun doRequest() {
    val apiRequest = Mockito.mock(ApiRepository::class.java)
    val url = "https://www.thesportsdb.com/api/v1/json/1/search_all_teams.php?l=English%20Premier%20League\""
    apiRequest.doRequest(url)
    Mockito.verify(apiRequest).doRequest(url)
}
}