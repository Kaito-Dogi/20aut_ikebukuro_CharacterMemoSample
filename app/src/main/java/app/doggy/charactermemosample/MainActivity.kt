package app.doggy.charactermemosample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    open val data: List<CharacterData> = listOf(
        CharacterData(R.drawable.ic_launcher_background, "item1"),
        CharacterData(R.drawable.ic_launcher_background, "item2"),
        CharacterData(R.drawable.ic_launcher_background, "item3"),
        CharacterData(R.drawable.ic_launcher_background, "item4"),
        CharacterData(R.drawable.ic_launcher_background, "item5"),
        CharacterData(R.drawable.ic_launcher_background, "item6"),
        CharacterData(R.drawable.ic_launcher_background, "item7"),
        CharacterData(R.drawable.ic_launcher_background, "item8"),
        CharacterData(R.drawable.ic_launcher_background, "item9")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //adapterをインスタンス化する。
        val adapter = RecyclerViewAdapter(this)

        //RecyclerViewのレイアウトを決める。

        recyclerView.layoutManager = LinearLayoutManager(this) //縦横表示
        //recyclerView.layoutManager = GridLayoutManager(baseContext,2) //グリッド表示

        //RecyclerViewにadapterを渡す。
        recyclerView.adapter = adapter

        //RecyclerViewにデータを表示する。
        adapter.addAll(data)
    }
}