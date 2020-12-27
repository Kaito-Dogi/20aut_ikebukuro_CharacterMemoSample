package app.doggy.charactermemosample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_memo.*
import kotlinx.android.synthetic.main.item_character_data_cell.*

class MemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        //MainActivityから値を受け取る。
        val name = intent.getStringExtra("NAME")
        editNameText.setText(name)

        saveButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}