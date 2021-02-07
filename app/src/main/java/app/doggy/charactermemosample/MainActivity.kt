package app.doggy.charactermemosample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.realm.Realm
import io.realm.RealmResults
import io.realm.Sort
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val imageIds: Array<Int> = arrayOf(
        R.drawable.zero,
        R.drawable.one,
        R.drawable.two,
        R.drawable.three,
        R.drawable.four
    )

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //データを読み込む。
        val characterDataList = readAll()

        //データが空の時、データを生成する。
        if (characterDataList.isEmpty()) {
            createData()
        }

        //adapterをインスタンス化する。
        val adapter = RecyclerViewAdapter(this, characterDataList, true)

        //RecyclerViewのレイアウトを決める。
        recyclerView.layoutManager = LinearLayoutManager(this) //縦横表示
        //recyclerView.layoutManager = GridLayoutManager(baseContext,2) //グリッド表示

        //RecyclerViewにadapterを渡す。
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun createData() {
        for (i in 0..4) {
            create(i, imageIds[i], "Character $i")
        }
    }

    fun create(intId: Int, imageId: Int, name: String) {
        realm.executeTransaction {
            val characterData = it.createObject(CharacterData::class.java, UUID.randomUUID().toString())
            characterData.characterImageResource = imageId
            characterData.name = name

            characterData.intId = intId
        }
    }

    fun readAll(): RealmResults<CharacterData> {
        return realm.where(CharacterData::class.java).findAll()
    }
}