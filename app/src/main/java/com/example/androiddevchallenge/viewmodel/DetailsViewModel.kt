/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.data.CatDetails

class DetailsViewModel : ViewModel() {

    val catDetails = MutableLiveData<CatDetails>()

    fun getDetails(id: Int) {
        val detailsList = listOf(
            CatDetails(0, "Cat1", 35, "M", "The Cat1 is is very naughty. she likes to play with me. she often runs here and there and likes running after something. mimi’s favourite game is playing with balls, ropes and stones. i love it very much. sometimes, mimi is very gentle. she likes to wash her face and doesn’t play with me. after lunch, mimi often lies on the sofa to sleep. when i go home, mimi often jumps onto my knees. i like to give a bath to mimi."),
            CatDetails(1, "Cat2", 45, "F", "The Cat2 It is special. Its fur is black not yellow"),
            CatDetails(2, "Cat3", 55, "M", "The Cat3 is is very naughty. she likes to play with me. she often runs here and there and likes running after something. mimi’s favourite game is playing with balls, ropes and stones. i love it very much. sometimes, mimi is very gentle. she likes to wash her face and doesn’t play with me. after lunch, mimi often lies on the sofa to sleep. when i go home, mimi often jumps onto my knees. i like to give a bath to mimi. It is active, active, alert, intelligent, likes to go out and adaptable."),
            CatDetails(3, "Cat4", 23, "M", "The Cat4 It is special. Its fur is black not yellow"),
            CatDetails(4, "Cat5", 28, "F", "The Cat5 is is very naughty. she likes to play with me. she often runs here and there and likes running after something. mimi’s favourite game is playing with balls, ropes and stones. i love it very much. sometimes, mimi is very gentle. she likes to wash her face and doesn’t play with me. after lunch, mimi often lies on the sofa to sleep. when i go home, mimi often jumps onto my knees"),
            CatDetails(5, "Cat6", 39, "F", "The Cat5 is is very naughty. she likes to play with me. she often runs here and there and likes running after something. mimi’s favourite game is playing with balls, ropes and stones. i love it very much. sometimes, mimi is very gentle. she likes to wash her face and doesn’t play with me. after lunch, mimi often lies on the sofa to sleep. when i go home, mimi often jumps onto my knees"),
        )

        detailsList.forEach {
            if (it.id == id) {
                catDetails.value = it
            }
        }
    }
}
