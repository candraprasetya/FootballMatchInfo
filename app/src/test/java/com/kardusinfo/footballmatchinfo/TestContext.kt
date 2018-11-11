package com.kardusinfo.footballmatchinfo

import com.kardusinfo.footballmatchinfo.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContext : CoroutineContextProvider(){
    override val main: CoroutineContext = Unconfined
}