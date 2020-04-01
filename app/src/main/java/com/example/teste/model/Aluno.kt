package com.example.teste.model

import com.example.teste.activity.MainActivity

class Aluno(val nomeAluno: String, val provas: Array<Double>) {

    fun mediaNotas() = provas.average()

    fun menorNota() = provas.min()

    fun maiorNota() = provas.max()
}