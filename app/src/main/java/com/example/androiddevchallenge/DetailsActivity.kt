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
package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.CatDetails
import com.example.androiddevchallenge.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity() {

    val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra("id", 0)
        Log.e("DetailsActivity", "id=" + id)
        detailsViewModel.catDetails.observe(this) {
            setContent {
                activityLayout(it)
            }
        }
        detailsViewModel.getDetails(id)
    }
}

fun Context.lookDetails(id: Int) {
    startActivity(
        Intent(this, DetailsActivity::class.java)
            .apply {
                putExtra("id", id)
            }
    )
}

@Composable
fun activityLayout(catDetails: CatDetails) {
    val buttonEnable = remember { mutableStateOf(true) }

    Column(
        Modifier
            .padding(10.dp)
    ) {

        Button(
            onClick = {
                buttonEnable.value = !buttonEnable.value
            },
            enabled = buttonEnable.value,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (buttonEnable.value) MaterialTheme.colors.primaryVariant else Color.Gray
            )
        ) {
            Text(if (buttonEnable.value) "Adpot it" else "Thank you!")
        }

        detailsLayout(catDetails)
    }
}

@Composable
fun detailsLayout(catDetails: CatDetails) {
    val state = rememberScrollState()

    Column(
        Modifier
            .padding(10.dp)
            .verticalScroll(state),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Log.e("DetailsActivity", "id=" + catDetails.id)
        val imagePainter = painterResource(getCatImageRes(catDetails.id))
        Image(
            imagePainter, "",
            Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(4.dp))
        )

        Text(
            catDetails.name,
            style = MaterialTheme.typography.h6
        )

        val sexImagePainter =
            painterResource(if (catDetails.sex == "M") R.drawable.male else R.drawable.female)
        Image(sexImagePainter, "1")

        Text(
            "${catDetails.age} days",
            Modifier
                .padding(top = 2.dp)
        )

        Text(
            catDetails.introduction,
            Modifier
                .padding(top = 2.dp),
            style = MaterialTheme.typography.body1
        )
    }
}
