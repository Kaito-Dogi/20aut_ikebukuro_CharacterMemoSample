package app.doggy.charactermemosample

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    //RecyclerViewに表示するリストの宣言。
    val items: MutableList<CharacterData> = mutableListOf()

    //ViewHolderをインスタンス化する。
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_character_data_cell, parent, false)

        //リスナを設定。
        view.setOnClickListener(ItemClickListener())

        return ViewHolder(view)
    }

    //リストの要素数を返す。
    override fun getItemCount(): Int {
        return items.size
    }

    //itemsのposition番目の要素をViewに表示する。
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.characterImageView.setImageResource(item.characterImageResource)
        holder.characterNameTextView.text = item.name
    }

    //RecyclerViewにデータを表示する。
    fun addAll(items: List<CharacterData>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    //まずViewHolderクラスを作成する。
    //ViewHolderクラスは、Viewを保持するクラスであり、Adapterを使うときに必要となる。
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val characterImageView: ImageView = view.findViewById(R.id.characterImageView)
        val characterNameTextView: TextView = view.findViewById(R.id.characterNameTextView)
    }

    //リスナクラスを作成。
    inner class ItemClickListener: View.OnClickListener {
        override fun onClick(view: View) {
            //characterNameTextViewのidを取得。
            val characterNameTextView = view.findViewById<TextView>(R.id.characterNameTextView)

            //characterNameTextViewのtextを取得。
            val name = characterNameTextView.text

            //characterNameTextViewのtextをトースト。
            Toast.makeText(context, name, Toast.LENGTH_LONG).show()

            //画面処理の設定
            //現在のActivityを取得する。
            val context = view.context
            //intentのインスタンスを生成。
            val intent = Intent(context, MemoActivity::class.java)
            //（暫定的に）name: CharacterDataを渡す。
            intent.putExtra("NAME", characterNameTextView.text)
            //intentを実行。
            context.startActivity(intent)
        }
    }

}