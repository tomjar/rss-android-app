package com.example.tinyandroidapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.wordlist_item.view.*


class WordListAdapter(context: Context, wordList: MutableList<String>) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    var mWordList = wordList
    var mInflator: LayoutInflater = LayoutInflater.from(context)

    class WordViewHolder(itemView: View, adapter: WordListAdapter) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WordViewHolder {
        var mItemView : View = mInflator.inflate(R.layout.wordlist_item, parent, false)
        return WordViewHolder(mItemView, this)
    }

    override fun getItemCount(): Int {
        return mWordList.size
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        var mCurrent = mWordList[position]
        holder.itemView.word.text = mCurrent
    }


}