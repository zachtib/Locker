package com.zachtib.locker

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.zachtib.locker.data.LockerDatabase
import com.zachtib.locker.models.Note
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTests {
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    val db = LockerDatabase.build(appContext)
    val dao = db.notesDao()

    @Before
    fun clearAllTables() {
        db.clearAllTables()
    }

    @Test
    fun testMultpleInsertions() {
        dao.insert(Note(title = "Hello", content = "World"))
        dao.insert(Note(title = "Foo", content = "Bar"))
    }

    @Test
    fun testDeferredSelect() {
        dao.insert(Note(title = "Hello", content = "World"))
        dao.insert(Note(title = "Foo", content = "Bar"))
        val result: List<Note> = runBlocking {
            dao.getAll()
        }
        assertEquals(2, result.size)
    }
}
