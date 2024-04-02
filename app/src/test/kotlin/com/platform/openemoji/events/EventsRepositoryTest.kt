package com.platform.openemoji.events

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class EventsRepositoryTest {
    private lateinit var repository: EventsMockDataRepository

    @Before
    fun setup() {
        val testEvents =
            listOf(
                Event(
                    "name1",
                    "date1",
                    "image1",
                    "url1",
                ),
                Event(
                    "name2",
                    "date2",
                    "image2",
                    "url2",
                ),
                Event(
                    "name3",
                    "date3",
                    "image3",
                    "url3",
                ),
            )
        repository = EventsMockDataRepository(null, testEvents)
    }

    @Test
    fun testGetEvents() =
        runBlocking {
            val events = repository.getEvents()
            assertEquals(3, events.size)
        }

    @Test
    fun testGetLimitedEvents() =
        runBlocking {
            val events = repository.getEvents(2)
            assertEquals(2, events.size)
        }
}
