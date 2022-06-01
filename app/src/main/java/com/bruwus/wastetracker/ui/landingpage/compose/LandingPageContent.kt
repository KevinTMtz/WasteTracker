package com.bruwus.wastetracker.ui.landingpage.compose

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.bruwus.wastetracker.R
import com.bruwus.wastetracker.ui.AuthActivity

@Preview
@Composable
fun LandingPageContent() {
    val context = LocalContext.current

    val landImage = painterResource(id = R.drawable.land)
    val landImageRatio = landImage.intrinsicSize.width / landImage.intrinsicSize.height

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(ContextCompat.getColor(context, R.color.blue))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = landImage,
                contentDescription = stringResource(R.string.landing_page_image_content_description),
                modifier = Modifier
                    .aspectRatio(landImageRatio)
                    .fillMaxWidth(),
            )
        }

        Column (
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 32.dp),
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
                    .wrapContentWidth(Alignment.Start),
                fontSize = 60.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(ContextCompat.getColor(context, R.color.white))
            )

            Text(
                text = stringResource(R.string.landing_page_message),
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start),
                fontSize = 20.sp,
                color = Color(ContextCompat.getColor(context, R.color.grey_light))
            )

            Row(
                modifier = Modifier
                    .weight(1f, true)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier.padding(0.dp, 64.dp),
                    onClick = {
                        val intent = Intent(context, AuthActivity::class.java)
                        context.startActivity(intent)
                    },
                ) {
                    Text(stringResource(R.string.landing_page_button_login).uppercase())
                }
            }
        }
    }
}