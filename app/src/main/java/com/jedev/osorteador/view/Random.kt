package com.jedev.osorteador.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.snackbar.Snackbar
import com.jedev.osorteador.R
import com.jedev.osorteador.utils.RandomUtils
import kotlinx.android.synthetic.main.activity_random.*

class Random : AppCompatActivity() {

    private val historicNumbers = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        btnRandom.setOnClickListener {
            executeRandom()
        }

        repeat.setOnCheckedChangeListener { _, _ ->
            clearHistoric()
        }

        edtEnd.doAfterTextChanged {
            clearHistoric()
        }

        edtStart.doAfterTextChanged {
            clearHistoric()
        }
    }

    private fun alert(msg: String): Snackbar {
        return Snackbar.make(rootLayout, msg, Snackbar.LENGTH_SHORT)
    }

    private fun clearHistoric() {
        historicNumbers.clear()
    }

    private fun executeRandom() {
        val startNum = edtStart.text.toString().toInt()
        val endNum = edtEnd.text.toString().toInt()
        val diff = ((startNum - endNum) * -1)

        if (diff == 0) {
            return alert("Defina um intervalo entre os numeros").show()
        }

        var number = RandomUtils.randomNumbers(startNum, endNum)

        if (!repeat.isChecked) {
            if (historicNumbers.size - 1 >= diff) {
                return alert("Todos os numeros ja foram sorteados")
                    .setAction("Reiniciar") { clearHistoric() }
                    .show()

            } else {
                while (historicNumbers.indexOf(number) > -1) {
                    number = RandomUtils.randomNumbers(startNum, endNum)
                }
            }
        }

        num.text = number.toString()
        historicNumbers.add(number)
    }
}
