@file:OptIn(ExperimentalPermissionsApi::class)

package com.ad_coding.notificationscourse

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import com.ad_coding.notificationscourse.ui.theme.NotificationsCourseTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotificationsCourseTheme {
                val postNotificationPermission =
                    rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

                val waterNotificationService = WaterNotificationService(this)

                LaunchedEffect(key1 = true) {
                    if (!postNotificationPermission.status.isGranted) {
                        postNotificationPermission.launchPermissionRequest()
                    }
                }

                Column {
                    Button(
                        onClick = {
                            waterNotificationService.showBasicNotification()
                        }
                    ) {
                        Text(text = "Show basic notification")
                    }

                    Button(
                        onClick = {
                            waterNotificationService.showExpandableNotification()
                        }
                    ) {
                        Text(text = "Show expandable with image notification")
                    }

                    Button(
                        onClick = {
                            waterNotificationService.showExpandableLongText()
                        }
                    ) {
                        Text(text = "Show expandable with text notification")
                    }

                    Button(
                        onClick = {
                            waterNotificationService.showInboxStyleNotification()
                        }
                    ) {
                        Text(text = "Show inbox-style notification")
                    }

                    Button(
                        onClick = {
                            waterNotificationService.showNotificationGroup()
                        }
                    ) {
                        Text(text = "Show inbox-style notification group")
                    }
                }
            }
        }
    }
}