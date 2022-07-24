package com.ryosuke_mita.anatadare


import android.app.role.RoleManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import com.ryosuke_mita.anatadare.ui.theme.AnatadareTheme
import java.security.Permission
import java.util.jar.Manifest
const val REQUEST_CODE = 0x1234;
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // request call screening rol
        val roleManager: RoleManager = getSystemService(ROLE_SERVICE) as RoleManager
        val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
        startActivityForResult(intent, REQUEST_CODE)

        setContent {
            AnatadareTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == android.app.Activity.RESULT_OK) {
                // OK
            } else {
                Toast.makeText(this, "権限を与えてあげてください.", Toast.LENGTH_LONG).show()
            }
        }
    }
}



@Composable
fun Greeting() {
    Text(text = "このアプリをデフォルトの迷惑電話フィルタに設定すると、着信がある場合に相手の電話番号をGoogle検索するための通知を送信します。")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AnatadareTheme {
        Greeting()
    }
}