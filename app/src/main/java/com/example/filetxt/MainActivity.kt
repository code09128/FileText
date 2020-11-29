package com.example.filetxt

import android.Manifest
import android.R.attr
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val path = this.getExternalFilesDir(null)
//        val letDirectory = File(path, "LET")
//        letDirectory.mkdirs()
//
//        val file = File(letDirectory, "Records.txt")
//
//        FileOutputStream(file).use {
////            it.write("record goes here")
//            file.appendText("record goes here")
//        }
//
//        val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }

        val version = "V1.0"
        val version2 = "V2.0"

        if (Build.VERSION.SDK_INT >= 23) {
            val REQUEST_CODE_CONTACT = 101

            val permissions = arrayOf<String>(Manifest.permission.WRITE_EXTERNAL_STORAGE)

            //驗證是否有取可權
            for (str in permissions) {
                if (checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申請權限
                    requestPermissions(permissions, REQUEST_CODE_CONTACT)
                    return
                } else {
                    val path = Environment.getExternalStorageDirectory().absolutePath + "/Download/MyTest"
                    Log.e("------path", path)
                    val files = File(path)

                    if (!files.exists()) {
                        files.mkdirs()
                    }
                    try {
                        val inputStream: InputStream = File(path + File.separator + "log.txt").inputStream()
                        val inputString = inputStream.bufferedReader().use {
                            it.readText()
                        }

                        Log.e("readText", "讀取成功" + inputString)

                        if(inputString != version2){
                            val fw = FileWriter(path + File.separator + "log.txt")
                            fw.write(version2)
                            fw.close()
                            Log.e("writeTEXT", "文件寫入成功")
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }
}