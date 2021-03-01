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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

const val PET_KEY = "pet_key"
const val PET_RESULT_KEY = "pet_result_key"


class DetailyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val parcelableExtra = intent.getParcelableExtra<Pet>(PET_KEY)
        setContent {
            MyTheme {
                MyContent(pet = parcelableExtra)
            }
        }
    }


    @Composable
    fun MyContent(pet: Pet?) {
        if (pet == null) {
            return
        }

        Column() {
            val image = painterResource(id = R.drawable.dog1)
            Image(
                painter = image,
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Text(
                text = """Two before narrow not relied how except moment myself. Dejection assurance mrs led certainly. So gate at no only none open. Betrayed at properly it of graceful on. Dinner abroad am depart ye turned hearts as me wished. Therefore allowance too perfectly gentleman supposing man his now. Families goodness all eat out bed steepest servants. Explained the incommode sir improving northward immediate eat. Man denoting received you sex possible you. Shew park own loud son door less yet. """,
                style = typography.body1,
                modifier = Modifier.padding(12.dp)
            )

            Button(onClick = {
                setResult(RESULT_OK, intent.apply {
                    putExtra(PET_RESULT_KEY, pet.id)
                })
                finish()
            }, modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Adopt",
                    modifier = Modifier.padding(12.dp),
                    style = typography.button
                )
            }
        }
    }

    @Preview
    @Composable
    fun MyPreview() {
        MyContent(
            pet = Pet(
                "Dog1",
                "",
                3,
                "Yellow"
            )
        )
    }
}
