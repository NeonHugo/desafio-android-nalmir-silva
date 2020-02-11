package com.nm.desafio_android_nalmir_hugo.ui.charactersList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.nm.desafio_android_nalmir_hugo.R
import com.nm.domain.entity.MCharacter
import de.hdodenhof.circleimageview.CircleImageView


class CharactersListAdapter(
    private val context: Context,
    private val resource: Int,
    private val data: List<MCharacter>,
    private val mGlide: RequestManager,
    private val itemListener: (MCharacter) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewModel: View

        viewModel = inflater.inflate(resource, parent, false)
        return DefaultVH(viewModel)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        processDefault(holder, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private inner class DefaultVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photo: CircleImageView = itemView.findViewById(R.id.hero_image)
        var title: TextView = itemView.findViewById(R.id.title)

        init {
            itemView.rootView.setOnClickListener {
                val position = adapterPosition
                val item = data!![position]
                itemListener.invoke(item)
            }
        }
    }

    private fun processDefault(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        val defaultVH = holder as DefaultVH

        mGlide.load(item.thumbnail)
            .placeholder(R.drawable.ic_person_black_24dp)
            .into(defaultVH.photo)

        defaultVH.title.text = item.name
    }
}