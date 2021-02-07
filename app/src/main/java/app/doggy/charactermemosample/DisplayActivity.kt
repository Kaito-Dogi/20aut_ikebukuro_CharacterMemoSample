package app.doggy.charactermemosample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_display.*
import kotlinx.android.synthetic.main.activity_memo.*

class DisplayActivity : AppCompatActivity() {
    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        //MemoActivityから値を受け取る。
        val intId = intent.getIntExtra("intId", -1)

        //登録されている情報を表示。
        display(intId)

        when(intId) {
            0 -> imageView.setImageResource(R.drawable.zero)
            1 -> imageView.setImageResource(R.drawable.one)
            2 -> imageView.setImageResource(R.drawable.two)
            3 -> imageView.setImageResource(R.drawable.three)
            4 -> imageView.setImageResource(R.drawable.four)
        }

        backButton.setOnClickListener {
            val backToMain = Intent(applicationContext, MainActivity::class.java)
            startActivity(backToMain)
        }
    }

    fun display(intId: Int) {
        realm.executeTransaction {
            val character = realm.where(CharacterData::class.java).equalTo("intId", intId).findFirst()
                ?: return@executeTransaction

            nameDisplayText.text = character.name
            genderDisplayText.text = character.gender
            nicknameDisplayText.text = character.nickname
        }
    }
}