package evan.chen.tutorial.tdd.mocksharedpreferencesample

import android.content.Context
import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class RepositoryTest {

    @Test
    fun saveUserId() {
        val sharedPrefs = mockk<SharedPreferences>(relaxed = true)
        val sharedPrefsEditor = mockk<SharedPreferences.Editor>(relaxed = true)
        val context = mockk<Context>(relaxed = true)

        //Arrange
        every{context.getSharedPreferences(any(), any())}.returns(sharedPrefs)
        every{sharedPrefs.edit()}.returns(sharedPrefsEditor)
        every{sharedPrefsEditor.putString(any(), any())}.returns(sharedPrefsEditor)

        val userId = "A1234567"
        val preKey = "USER_ID"

        //Act 呼叫repository.saveUserId()
        val repository = Repository(context)
        repository.saveUserId(userId)

        //Assert
        //檢查是否有putString，及傳入的key、value是否正確
        verify{sharedPrefsEditor.putString(eq(preKey), eq(userId)) }
        //檢查SharedPreference是否有呼叫commit
        verify{sharedPrefsEditor.commit()}
    }
}