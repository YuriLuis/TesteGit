package com.example.teste.model

class Turma(val nomeTurma: String) {

    private val materias: MutableList<Materia> = mutableListOf()

    fun addMateria(materia: Materia) = materias.add(materia)

    fun melhorAlunoDaTurma(): MutableList<Aluno> {
        var mediaAluno = 0.0
        var melhorAluno = Aluno("", arrayOf(0.0))
        var listAluno = mutableListOf<Aluno>()
        materias.forEach { materia ->
            materia.alunos.forEach { aluno ->
                if (mediaAluno < aluno.mediaNotas()) {
                    mediaAluno = aluno.mediaNotas()
                    melhorAluno = aluno
                }
            }
        }
        listAluno.add(melhorAluno)
        return listAluno
    }

    fun alunosReprovados(): MutableList<Aluno> {
        var alunosReprovados = mutableListOf<Aluno>()
        materias.forEach { materia ->
            materia.alunos.forEach { aluno ->
                if (aluno.mediaNotas() < 7.0) {
                    alunosReprovados.add(aluno)
                }
            }
        }
        return alunosReprovados
    }

    fun alunosAprovados(): MutableList<Aluno> {
        var alunosAprovados = mutableListOf<Aluno>()
        materias.forEach { materia ->
            materia.alunos.forEach { aluno ->
                if (aluno.mediaNotas() >= 7.0) {
                    alunosAprovados.add(aluno)
                }
            }
        }
        return alunosAprovados
    }

    fun quantidadeProvas(): Int {
        var quantidadeProvas = 0
        materias.forEach { materia ->
            materia.alunos.forEach { aluno ->
                if (quantidadeProvas < aluno.provas.size) {
                    quantidadeProvas = aluno.provas.size
                }
            }
        }
        return quantidadeProvas
    }

    fun nomeMelhorAlunoEmCadaProva(): MutableList<String>{
        var nomesAlunos = mutableListOf<String>()
        var quantidadeProvas = quantidadeProvas() -1

        nomesAlunos.add("Nome do melhor aluno em cada prova:")

        for (i in 0..quantidadeProvas){
            var nota = 0.0
            var alunoMelhorNaProva = Aluno("", arrayOf(0.0))
            materias.forEach { materia ->
                materia.alunos.forEach { aluno ->
                    if (nota < aluno.provas[i]){
                        nota = aluno.provas[i]
                        alunoMelhorNaProva = aluno
                    }
                }
                var indiceProva = i + 1
                nomesAlunos.add(" Prova $indiceProva: Nome = ${alunoMelhorNaProva.nomeAluno}" +
                        "\n Nota = ${String.format("%.2f", nota)} \n ")
            }
        }
        return nomesAlunos
    }

    fun mediaTurmaPorProva(): MutableList<String> {
        var provas = mutableListOf<String>()
        var quantidadeProvas = quantidadeProvas() - 1

        provas.add("Média da turma $nomeTurma ")

        for (i in 0..quantidadeProvas) {
            var notasProva = mutableListOf<Double>()
            materias.forEach { materia ->
                materia.alunos.forEach { aluno ->
                    notasProva.add(aluno.provas[i])
                }
            }
            var mediaProva = String.format("%.2f", notasProva.average())
            var indiceDaProva = i + 1
            provas.add("Prova $indiceDaProva: $mediaProva")
        }
        return provas
    }
}