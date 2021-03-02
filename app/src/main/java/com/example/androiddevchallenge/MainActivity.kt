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

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.androiddevchallenge.data.Cat
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp {
                    lookDetails(it)
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(itemClick: (id: Int) -> Unit = {}) {
    Surface(
        color = Color.LightGray,
        modifier = Modifier.fillMaxWidth()
    ) {

        val cats = getCats()
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            items(cats) {
                ItemLayout(it, itemClick)
            }
        }
    }
}

@Composable
fun ItemLayout(cat: Cat, itemClick: (id: Int) -> Unit) {
    ConstraintLayout(
        Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White)
            .clickable { itemClick(cat.id) }
            .padding(5.dp)
    ) {
        val (image, nameText, sexImage, ageText) = createRefs()

        val imagePainter = painterResource(getCatImageRes(cat.id))
        Image(
            imagePainter, "",
            Modifier
                .width(250.dp)
                .height(250.dp)
//                .wrapContentHeight()
//                .padding(10.dp)
                .clip(RoundedCornerShape(4.dp))
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 10.dp)
                }
        )

        Text(
            text = cat.name,
            modifier = Modifier.constrainAs(nameText) {
                top.linkTo(image.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        val sexImagePainter = painterResource(if (cat.sex == "M") R.drawable.male else R.drawable.female)
        Image(
            sexImagePainter, "",
            Modifier
                .width(24.dp)
                .constrainAs(sexImage) {
                    start.linkTo(nameText.end, margin = 5.dp)
                    top.linkTo(nameText.top)
                    bottom.linkTo(nameText.bottom)
                }
        )

        Text(
            "${cat.age} days",
            Modifier
                .constrainAs(ageText) {
                    top.linkTo(nameText.bottom, margin = 2.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

fun getCatImageRes(id: Int): Int {
    return when (id) {
        1 -> R.mipmap.cat_1
        2 -> R.mipmap.cat_2
        3 -> R.mipmap.cat_3
        4 -> R.mipmap.cat_4
        5 -> R.mipmap.cat_5
        6 -> R.mipmap.cat_6
        else -> R.mipmap.cat_6
    }
}

private fun getCats(): List<Cat> {
    return listOf(
        Cat(1, "cat1", 35, "M"),
        Cat(2, "cat2", 45, "F"),
        Cat(3, "cat3", 55, "M"),
        Cat(4, "cat4", 23, "M"),
        Cat(5, "cat5", 28, "F"),
        Cat(6, "cat6", 39, "F"),
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
