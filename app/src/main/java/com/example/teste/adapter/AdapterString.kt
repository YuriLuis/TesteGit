package com.example.teste.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.teste.R
import com.example.teste.model.Materia
import kotlinx.android.synthetic.main.alunos_layout.view.*

class AdapterString(private  val materias: MutableList<Materia>) : RecyclerView.Adapter<AdapterString.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.alunos_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() =  materias.size


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val materia : Materia = materias[position]
        holder.nomeAluno.text = materia.alunos.toString()
        /*
        holder.nomeAluno.text = "Nome = ${aluno.nomeAluno}"
        holder.prova1.text = "Prova 01 = ${aluno.provas[0]}"
        holder.prova2.text = "Prova 02 = ${aluno.provas[1]}"
        holder.prova3.text = "Prova 03 = ${aluno.provas[2]}"
        holder.nota.text = "Nota \n ${String.format("%.2f",aluno.mediaNotas().toString().toDouble())}"

         */

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nomeAluno : TextView = itemView.textViewNomeAluno
        var prova1 : TextView = itemView.textViewProva1
        var prova2 : TextView = itemView.textViewProva2
        var prova3 : TextView = itemView.textViewProva3
        var nota : TextView = itemView.textViewNota
    }
}