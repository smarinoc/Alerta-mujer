package com.udea.alerta.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.udea.alerta.R
import com.udea.alerta.ui.MainActivity


class Calculator : AppCompatActivity() {
    private var num1 = 0.0
    private var num2 = 0.0
    private var operacion = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        val resultado = findViewById<TextView>(R.id.resultadoText)

        resultado.text = "0"
        operacion = SIN_OPERACION

        var uno: Button? = null
        var dos: Button? = null
        var tres: Button? = null
        var cuatro: Button? = null
        var cinco: Button? = null
        var seis: Button? = null
        var siete: Button? = null
        var ocho: Button? = null
        var nueve: Button? = null
        var suma: Button? = null
        var resta: Button? = null
        var multiplicar: Button? = null
        var dividir: Button? = null
        var cero: Button? = null
        var punto: Button? = null
        var clear: Button? = null
        var igual: Button? = null

        uno = findViewById(R.id.unoBtn)
        dos = findViewById(R.id.dosBtn)
        tres = findViewById(R.id.tresBtn)
        cuatro = findViewById(R.id.cuatroBtn)
        cinco = findViewById(R.id.cincoBtn)
        seis = findViewById(R.id.seisBtn)
        siete = findViewById(R.id.sieteBtn)
        ocho = findViewById(R.id.ochoBtn)
        nueve = findViewById(R.id.nueveBtn)
        cero = findViewById(R.id.ceroBtn)
        suma = findViewById(R.id.sumaBtn)
        resta = findViewById(R.id.restaBtn)
        multiplicar = findViewById(R.id.multiplicarBtn)
        dividir = findViewById(R.id.divisionBtn)
        punto = findViewById(R.id.puntoBtn)
        clear = findViewById(R.id.clearBtn)
        igual = findViewById(R.id.igualBtn)

        uno.setOnClickListener { numberPressed("1") }
        dos.setOnClickListener { numberPressed("2") }
        tres.setOnClickListener { numberPressed("3") }
        cuatro.setOnClickListener { numberPressed("4") }
        cinco.setOnClickListener { numberPressed("5") }
        seis.setOnClickListener { numberPressed("6") }
        siete.setOnClickListener { numberPressed("7") }
        ocho.setOnClickListener { numberPressed("8") }
        nueve.setOnClickListener { numberPressed("9") }
        cero.setOnClickListener {
            val resultado = findViewById<TextView>(R.id.resultadoText)
            resultado.text="malo"
            val intent: Intent = Intent(this, MainActivity()::class.java)
            startActivity(intent)
        }
        punto.setOnClickListener {
            numberPressed(".")

        }

        clear.setOnClickListener { resetAll() }

        suma.setOnClickListener { operationPressed(SUMA) }
        resta.setOnClickListener { operationPressed(RESTA) }
        multiplicar.setOnClickListener { operationPressed(MULTIPLICACION) }
        dividir.setOnClickListener { operationPressed(DIVISION) }

        igual.setOnClickListener { resolvePressed() }
    }

    private fun numberPressed(num: String) {
        val resultado = findViewById<TextView>(R.id.resultadoText)

        if (resultado.text == "0" && num != ".") {
            resultado.text = "$num"
        } else {
            resultado.text = "${resultado.text}$num"
        }

        if (operacion == SIN_OPERACION) {
            num1 = resultado.text.toString().toDouble()
        } else {
            num2 = resultado.text.toString().toDouble()
        }
    }

    private fun operationPressed(operacion: Int) {
        val resultado = findViewById<TextView>(R.id.resultadoText)

        this.operacion = operacion
        num1 = resultado.text.toString().toDouble()

        resultado.text = "0"
    }

    private fun resolvePressed() {
        val resultado = findViewById<TextView>(R.id.resultadoText)

        val result = when (operacion) {
            SUMA -> num1 + num2
            RESTA -> num1 - num2
            MULTIPLICACION -> num1 * num2
            DIVISION -> num1 / num2
            else -> 0
        }

        num1 = result as Double

        resultado.text = if ("$result".endsWith(".0")) {
            "$result".replace(".0", "")
        } else {
            "%.2f".format(result)
        }
    }

    private fun resetAll() {
        val resultado = findViewById<TextView>(R.id.resultadoText)

        resultado.text = "0"
        num1 = 0.0
        num2 = 0.0
    }

    companion object {
        const val SUMA = 1
        const val RESTA = 2
        const val MULTIPLICACION = 3
        const val DIVISION = 4
        const val SIN_OPERACION = 0
    }
}