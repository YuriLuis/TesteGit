package com.example.teste.model

class Materia(val nome: String) {

    val alunos: MutableList<Aluno> = mutableListOf()

    fun addAlunoMateria(aluno: Aluno) = alunos.add(aluno)
}