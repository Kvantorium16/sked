
package com.example.sked

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        var db = DataBaseHandler(context)
    btn_save.setOnClickListener {
        if (subject.text.toString().isNotEmpty() &&
            date.text.toString().isNotEmpty() &&
            time.text.toString().isNotEmpty() &&
            teacher.text.toString().isNotEmpty()
        ) {

            var subject = Subject(
                subject.text.toString(), date.text.toString(),
                time.text.toString(), teacher.text.toString()
            )

            db.insertData(subject)

        } else {
            Toast.makeText(context,"Пожалуйства заполните всю информацию", Toast.LENGTH_SHORT).show()
        }
    }

        btn_read.setOnClickListener {
            var data = db.readData()
            result.text = ""
            for (i in 0 until data.size-1) {
                result.append(
                    "\n№" + data[i].id.toString() + ". Subject:" + data[i].subject + " Date:" + data[i].date + " Time:"
                            + data[i].time + " Teacher:" + data[i].teacher
                )
            }
        }

        btn_delete.setOnClickListener {
            db.deleteData()
            btn_read.performClick()
        }

    }
}