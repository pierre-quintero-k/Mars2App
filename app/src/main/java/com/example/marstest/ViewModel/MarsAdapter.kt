package com.example.marstest.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marstest.Model.Remoto.TerrenoDeMArte
import com.example.marstest.databinding.MarsItemBinding

class MarsAdapter: RecyclerView.Adapter<MarsAdapter.MarsViewHolder>() {

    private var listadeMars= listOf<TerrenoDeMArte>()

    val terrenoSeleccionado= MutableLiveData<TerrenoDeMArte>()


    inner class MarsViewHolder(private val binding: MarsItemBinding):
            RecyclerView.ViewHolder(binding.root),

        View.OnClickListener{
            fun bind(mars: TerrenoDeMArte){
                Glide.with(binding.imageView).load(mars.img_src).centerCrop().into(binding.imageView)
                itemView.setOnClickListener(this)
            }

        override fun onClick(v: View?) {
            terrenoSeleccionado.value=listadeMars[adapterPosition]

        }

    }

    //funcion para seleccionar
    fun elementoSeleccionado(): LiveData<TerrenoDeMArte> = terrenoSeleccionado

    //funcion para actualizar
    fun updateData(list: List<TerrenoDeMArte>){
        listadeMars=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsViewHolder {
        val binding= MarsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MarsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listadeMars.size
    }

    override fun onBindViewHolder(holder: MarsViewHolder, position: Int) {
        val terrenoSeleccionado=listadeMars[position]
        holder.bind(terrenoSeleccionado)
    }
}