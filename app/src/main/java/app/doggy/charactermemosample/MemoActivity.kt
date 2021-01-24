package app.doggy.charactermemosample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_memo.*
import kotlinx.android.synthetic.main.item_character_data_cell.*

class MemoActivity : AppCompatActivity() {
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        //MainActivityから値を受け取る。
        val name = intent.getStringExtra("NAME")
        editNameText.setText(name)

        display(name.toString())

        saveButton.setOnClickListener {
            update(name.toString())
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    fun display(name: String) {
        realm.executeTransaction {
            val character = realm.where(CharacterData::class.java).equalTo("name", name).findFirst()
                ?: return@executeTransaction

            editNameText.setText(character.name)
            editGenderText.setText(character.gender)
            editNicknameText.setText(character.nickname)
        }
    }

    fun update(name: String) {
        realm.executeTransaction {
            val character = realm.where(CharacterData::class.java).equalTo("name", name).findFirst()
                ?: return@executeTransaction

            character.name = editNameText.text.toString()
            character.nickname = editNicknameText.text.toString()
            character.gender = editGenderText.text.toString()
        }
    }
}