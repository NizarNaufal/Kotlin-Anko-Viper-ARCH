package id.nizardev.viperarchitecture.view.adapter

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TextView.*
import androidx.recyclerview.widget.RecyclerView
import id.nizardev.viperarchitecture.R
import id.nizardev.viperarchitecture.view.model.Joke

class JokesListAdapter(private var listener: (Joke?) -> Unit, private var dataList: List<Joke>?) : RecyclerView.Adapter<JokesListAdapter.ViewHolder>() {

    // Creating card_view_custom_layout ViewHolder to speed up the performance
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView? = itemView.findViewById(R.id.tv_id_card_view_custom_layout)
        val tvJoke: TextView? = itemView.findViewById(R.id.tv_joke_card_view_custom_layout)
    }

    override fun getItemCount() = dataList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val viewRow = LayoutInflater.from(parent.context).inflate(R.layout.card_view_custom_layout, parent, false)
        return ViewHolder(viewRow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvId?.text = dataList?.let { it[position].id.toString() }
        holder.tvJoke?.text = dataList?.let { Html.fromHtml(it[position].text) }
        holder.itemView?.setOnClickListener { listener(dataList?.get(position)) }
    }

    fun updateData(list: List<Joke>) {
        this.dataList = list
        this.notifyDataSetChanged()
    }
}
