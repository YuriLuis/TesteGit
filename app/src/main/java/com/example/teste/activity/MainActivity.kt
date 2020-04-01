package com.example.teste.activity

import android.os.Bundle
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teste.R
import com.example.teste.adapter.AdapterAlunos
import com.example.teste.model.Aluno
import com.example.teste.model.Materia
import com.example.teste.model.Turma
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAlunos: RecyclerView
    private lateinit var radioGroupOpcoes: RadioGroup
    private lateinit var materia: Materia
    private lateinit var turma: Turma

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        instanciaLayoutComXml()
        adicionaMateriaAndTurma()
        mostraResultados()
    }

    fun instanciaLayoutComXml() {

        recyclerViewAlunos = findViewById(R.id.recyclerViewAlunos)
        radioGroupOpcoes = findViewById(R.id.radioGroupOpcoes)
    }

    private fun configuraRecyclerViewAlunos(adapterAlunos: AdapterAlunos) {

        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerViewAlunos.layoutManager = layoutManager
        recyclerViewAlunos.adapter = adapterAlunos
        recyclerViewAlunos.setHasFixedSize(true)
    }

    private fun adapterAlunos(alunos: MutableList<Aluno>): AdapterAlunos {
        return AdapterAlunos(alunos)
    }

    fun adicionaMateriaAndTurma() {
        materia = Materia("Lógica de programação I")
        materia.addAlunoMateria(Aluno("Yuri", arrayOf(10.0, 5.4, 7.9)))
        materia.addAlunoMateria(Aluno("Joaquin", arrayOf(7.0, 6.5, 7.8)))
        materia.addAlunoMateria(Aluno("Fabiano", arrayOf(5.0, 4.6, 9.8)))
        materia.addAlunoMateria(Aluno("Ronaldo Gordo", arrayOf(9.0, 6.2, 8.6)))
        materia.addAlunoMateria(Aluno("Ronaldinho Gaucho", arrayOf(7.2, 6.2, 4.6)))
        materia.addAlunoMateria(Aluno("Romario Baixinho", arrayOf(5.6, 7.9, 3.0)))
        materia.addAlunoMateria(Aluno("Juca", arrayOf(9.9, 1.5, 3.0)))
        materia.addAlunoMateria(Aluno("Adriano Imperador", arrayOf(6.6, 7.7, 5.0)))
        materia.addAlunoMateria(Aluno("Messi", arrayOf(9.6, 6.7, 8.0)))
        materia.addAlunoMateria(Aluno("CR7", arrayOf(5.0, 6.7, 7.0)))

        turma = Turma("ADS 5")
        turma.addMateria(materia)
    }

    fun mostraResultados() {

        radioGroupOpcoes.setOnCheckedChangeListener { group, checkedId ->

            if (radioGroupOpcoes.checkedRadioButtonId == R.id.radioButtonNomeAlunosAprovados) {
                limpaLista()
                configuraRecyclerViewAlunos(adapterAlunos(turma.alunosAprovados()))
            }

            if (radioGroupOpcoes.checkedRadioButtonId == R.id.radioButtonNumeroDeAlunosAprovadosReprovados) {
                val aprovados = turma.alunosAprovados().size.toString()
                val reprovados = turma.alunosReprovados().size.toString()
                val resultado = "Alunos Aprovados = $aprovados \n Alunos reprovados = $reprovados"
                limpaLista()
                criaAlertDialog(resultado)
            }

            if (radioGroupOpcoes.checkedRadioButtonId == R.id.radioButtonNomeMelhorAlunoEmCadaProva) {
                limpaLista()
                var resultado = ""
                turma.nomeMelhorAlunoEmCadaProva().forEach { aluno ->
                    resultado += "$aluno \n"
                }
                criaAlertDialog(resultado)
            }

            if (radioGroupOpcoes.checkedRadioButtonId == R.id.radioButtonNomeDoMelhorAlunoTurma) {
                limpaLista()
                configuraRecyclerViewAlunos(adapterAlunos(turma.melhorAlunoDaTurma()))
            }

            if (radioGroupOpcoes.checkedRadioButtonId == R.id.radioButtonMediaTurmaEmCadaProva) {
                limpaLista()
                var resultado = ""
                turma.mediaTurmaPorProva().forEach { prova ->
                    resultado += "$prova \n"
                }
                criaAlertDialog(resultado)
            }
        }
    }

    fun criaAlertDialog(message: String): AlertDialog {
      return   AlertDialog.Builder(this)
            .setTitle("Avaliação Alunos")
            .setMessage(message)
            .setPositiveButton("Ok") { dialog, which ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }

    fun  limpaLista(){
        var alunos = mutableListOf<Aluno>()
        configuraRecyclerViewAlunos(adapterAlunos(alunos))
    }
}


