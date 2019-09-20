package com.jedev.osorteador.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jedev.osorteador.R
import com.jedev.osorteador.utils.General.alert
import com.jedev.osorteador.utils.RandomUtils
import com.jedev.osorteador.viewmodel.RandomViewModel
import kotlinx.android.synthetic.main.fragment_random.*

class RandomFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(RandomViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_random, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRandom.setOnClickListener {
            executeRandom()
        }

        repeat.setOnCheckedChangeListener { _, _ ->
            clearHistoric()
        }
    }

    private fun clearHistoric() {
        viewModel.clearHistoric()
    }

    private fun executeRandom() {
        val startNum = edtStart.text.toString().toInt()
        val endNum = edtEnd.text.toString().toInt()
        val diff = ((startNum - endNum) * -1)

        if (diff == 0) {
            return alert(view!!,"Defina um intervalo entre os números").show()
        }

        var number = RandomUtils.randomNumbers(startNum, endNum)

        if (!repeat.isChecked) {
            if (viewModel.historicNumbers.size - 1 >= diff) {
                return alert(view!!, "Todos os números ja foram sorteados")
                    .setAction("Reiniciar") { clearHistoric() }
                    .show()

            } else {
                while (viewModel.historicNumbers.indexOf(number) > -1) {
                    number = RandomUtils.randomNumbers(startNum, endNum)
                }
            }
        }

        num.text = number.toString()
        viewModel.historicNumbers.add(number)
    }
}
