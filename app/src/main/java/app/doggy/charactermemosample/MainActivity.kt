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

    private val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    //サンプルデータを設定。
//    val data: List<CharacterData> = listOf(
//        CharacterData(R.drawable.ic_launcher_background, "item1"),
//        CharacterData(R.drawable.ic_launcher_background, "item2"),
//        CharacterData(R.drawable.ic_launcher_background, "item3"),
//        CharacterData(R.drawable.ic_launcher_background, "item4"),
//        CharacterData(R.drawable.ic_launcher_background, "item5"),
//        CharacterData(R.drawable.ic_launcher_background, "item6"),
//        CharacterData(R.drawable.ic_launcher_background, "item7"),
//        CharacterData(R.drawable.ic_launcher_background, "item8"),
//        CharacterData(R.drawable.ic_launcher_background, "item9")
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characterDataList = readAll()

        // タスクリストが空だったときにダミーデータを生成する
        if (characterDataList.isEmpty()) {
            createDummyData()
        }

        //adapterをインスタンス化する。
        val adapter = RecyclerViewAdapter(this, characterDataList, true)

        //RecyclerViewのレイアウトを決める。
        recyclerView.layoutManager = LinearLayoutManager(this) //縦横表示
        //recyclerView.layoutManager = GridLayoutManager(baseContext,2) //グリッド表示

        //RecyclerViewにadapterを渡す。
        recyclerView.adapter = adapter

        //RecyclerViewにデータを表示する。
        //adapter.addAll(data)
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun createDummyData() {
        for (i in 0..10) {
            create(R.drawable.ic_launcher_background, "Character $i")
        }
    }

    fun create(imageId: Int, name: String) {
        realm.executeTransaction {
            val characterData = it.createObject(CharacterData::class.java, UUID.randomUUID().toString())
            characterData.characterImageResource = imageId
            characterData.name = name
        }
    }

    fun readAll(): RealmResults<CharacterData> {
        return realm.where(CharacterData::class.java).findAll()
    }
}