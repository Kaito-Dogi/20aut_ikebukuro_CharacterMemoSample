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

        //登録されている情報を表示。
        display(name.toString())

        //Intentをインスタンス化。
        val intent = Intent(applicationContext, DisplayActivity::class.java)

        //Intentにidを渡す。
        prepareForIntent(name as String, intent)

        //保存ボタンを押した時の処理。
        saveButton.setOnClickListener {
            update(name.toString())
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
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

    fun prepareForIntent(name: String, intent: Intent) {
        realm.executeTransaction {
            val character = realm.where(CharacterData::class.java).equalTo("name", name).findFirst()
                ?: return@executeTransaction

            intent.putExtra("intId", character.intId)
        }
    }
}