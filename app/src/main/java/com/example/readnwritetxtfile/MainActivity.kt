package com.example.readnwritetxtfile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        save_btn.setOnClickListener{
            val file = filename.text.toString()
            val text = text_msg.text.toString()

            val fileOutputStream : FileOutputStream

            try{

            fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
            fileOutputStream.write(text.toByteArray())
        }
            catch(e: FileNotFoundException){
                e.printStackTrace()
            }
            catch(e:Exception){
                e.printStackTrace()
            }

            showToast("Saved to file")
        }
        show_btn.setOnClickListener{
            val filename = filename.text.toString()

            if(filename.toString()!=null && filename.trim()!="") {
                var fileInputStream: FileInputStream? = null
                fileInputStream = openFileInput(filename)
                var InputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader: BufferedReader = BufferedReader(InputStreamReader)

                val stringBuilder: StringBuilder = StringBuilder()
                var text: String? = null

                while ({ text = bufferedReader.readLine(); text }() != null) {
                    stringBuilder.append(text)
                }
                text_msg.setText(stringBuilder.toString().toString())
            }
            else
                showToast("name of the file cannot be empty")

        }

    }

    fun Context.showToast(text:CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this,text, duration).show()
    }
}