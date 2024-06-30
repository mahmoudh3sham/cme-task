package com.cme.domain.usecase

import com.cme.domain.fake_repo.FakeAlbumsRepo
import com.cme.domain.model.Album
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetAlbumsUseCaseTest {

    private lateinit var getAlbumsUseCase: GetAlbumsUseCase
    private lateinit var fakeAlbumsRepo: FakeAlbumsRepo

    @Before
    fun setup(){
        fakeAlbumsRepo = FakeAlbumsRepo()
        getAlbumsUseCase = GetAlbumsUseCase(fakeAlbumsRepo)
    }

    @Test
    fun `getAlbums returns list of albums`() = runBlocking {

        val result = getAlbumsUseCase.getAlbums().first() //first returns the first emit of the flow which is MutableList<Album>

        val expected: MutableList<Album> = mutableListOf(
            Album("1", "artist","artistUrl","name","date","albumUrl","imgUrl", listOf(),"Copy Right"),
            Album("2", "artist","artistUrl","name","date","albumUrl","imgUrl", listOf(),"Copy Right"),
        )

        assertEquals(expected, result)
    }
}