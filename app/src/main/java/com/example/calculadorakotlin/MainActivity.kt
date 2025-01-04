package com.example.calculadorakotlin

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.mariuszgromada.math.mxparser.*;


class MainActivity : AppCompatActivity() {

    private lateinit var expressao: TextView
    private lateinit var resultado: TextView
    private var expressaoAtual = ""
    private var resultadoAtual = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        resultado = findViewById(R.id.resultado)
        expressao = findViewById(R.id.expressao)


        setNumberClickListener(R.id.num_zero, "0")
        setNumberClickListener(R.id.num_um, "1")
        setNumberClickListener(R.id.num_dois, "2")
        setNumberClickListener(R.id.num_tres, "3")
        setNumberClickListener(R.id.num_quatro, "4")
        setNumberClickListener(R.id.num_cinco, "5")
        setNumberClickListener(R.id.num_seis, "6")
        setNumberClickListener(R.id.num_sete, "7")
        setNumberClickListener(R.id.num_oito, "8")
        setNumberClickListener(R.id.num_nove, "9")
        setNumberClickListener(R.id.num_ponto, ".")

        setOperatorClickListener(R.id.num_soma, "+")
        setOperatorClickListener(R.id.num_sub, "-")
        setOperatorClickListener(R.id.num_multi, "*")
        setOperatorClickListener(R.id.div, "/")

        findViewById<View>(R.id.igual).setOnClickListener {
            calcularResultado()
        }

        findViewById<View>(R.id.limpar).setOnClickListener {
            limparExpressao()
        }

        findViewById<View>(R.id.apagar).setOnClickListener {
            apagarUltimoCaractere()
        }
    }

    fun apagarUltimoCaractere() {
        if (expressaoAtual.isNotEmpty()) {
            expressaoAtual = expressaoAtual.substring(0, expressaoAtual.length - 1)
            expressao.text = expressaoAtual
        }
    }
     fun setNumberClickListener(id: Int, value: String) {
        findViewById<View>(id).setOnClickListener {
            atualizarExpressao(value)
        }
    }

   fun setOperatorClickListener(id: Int, value: String) {
        findViewById<View>(id).setOnClickListener {
            atualizarExpressao(" $value ")
        }
    }

     fun atualizarExpressao(valor: String) {
        expressaoAtual += valor
        expressao.text = expressaoAtual
    }

     fun calcularResultado() {
        val e = Expression(expressaoAtual)
        val resultadoCalculado = e.calculate()
        resultadoAtual = if (resultadoCalculado % 1 == 0.0) {
            resultadoCalculado.toInt().toString()
        } else {
            resultadoCalculado.toString()
        }
        resultado.text = resultadoAtual
    }

    fun limparExpressao() {
        expressaoAtual = ""
        resultadoAtual = ""
        expressao.text = expressaoAtual
        resultado.text = resultadoAtual
    }
}


