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

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    val array = Array(100) {
        Pet(
            "Dog$it",
            "",
            3,
            "Yellow"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }

    private val startActivityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                it.data?.getStringExtra(PET_RESULT_KEY).let { id ->
                    array.find { it.id == id }
                }?.isAdopt?.value = true
            }
        }

    // Start building your app here!
    @Composable
    fun MyApp() {
        LazyColumn {
            items(items = array) {
                PetItemContent(pet = it)
            }
        }
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

    @Composable
    fun PetItemContent(pet: Pet) {
        var isAdopt = remember { mutableStateOf(pet.isAdopt.value) }
        pet.isAdopt = isAdopt
        Card(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .alpha(if (isAdopt.value) 0.3f else 1f)
        ) {
            Surface(modifier = Modifier.padding(16.dp)) {
                Row {
                    val imageModifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                        .fillMaxWidth()

                    Image(
                        painterResource(id = R.drawable.dog1), "",
                        modifier = imageModifier,
                        contentScale = ContentScale.Crop
                    )

                    Spacer(Modifier.width(16.dp))

                    Column(modifier = Modifier.width(150.dp)) {
                        Text(text = "Name: ${pet.name}")
                        Divider(color = Color.Transparent, thickness = 5.dp)
                        Text(text = "Age: ${pet.age}")
                    }
                    if (!isAdopt.value) {
                        Button(
                            onClick = {
                                startActivityLauncher.launch(
                                    Intent(
                                        this@MainActivity,
                                        DetailyActivity::class.java
                                    ).apply {
                                        putExtra(PET_KEY, pet)
                                    }
                                )
                            }
                        ) {
                            Text(text = "Go Adopt")
                        }
                    }
                }
            }
        }
    }
}
