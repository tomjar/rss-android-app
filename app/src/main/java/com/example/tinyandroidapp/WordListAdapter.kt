package com.example.tinyandroidapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.wordlist_item.view.*
import org.json.JSONObject


class WordListAdapter(context: Context, wordList: MutableList<JSONObject>) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    var mWordList = wordList
    var mInflator: LayoutInflater = LayoutInflater.from(context)
    var mContext : Context = context

    class WordViewHolder(itemView: View, adapter: WordListAdapter, context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var mAdapter = adapter
        var mItemView = itemView.setOnClickListener(this)
        var mContext : Context = context

        override fun onClick(p0: View?) {
            var mPosition : Int = layoutPosition
            val rssFeedObj = RssFeedObj(mAdapter.mWordList[mPosition])


            val intent = Intent(mContext, RssDetailsActivity::class.java)

            intent.putExtra("albumurl", rssFeedObj.albumImageUrl)
            intent.putExtra("albumname", rssFeedObj.albumName)
            intent.putExtra("artistname", rssFeedObj.artistName)

            startActivity(mContext, intent, null)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordViewHolder {
        var mItemView : View = mInflator.inflate(R.layout.wordlist_item, parent, false)
        return WordViewHolder(mItemView, this, mContext)
    }

    override fun getItemCount(): Int {
        return mWordList.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        var mCurrent = mWordList[position].getString("artistName")
        holder.itemView.word.text = mCurrent
    }


}