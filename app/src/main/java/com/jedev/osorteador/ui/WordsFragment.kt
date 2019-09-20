package com.jedev.osorteador.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jedev.osorteador.R
import com.jedev.osorteador.adapter.ItemWordAdapter
import com.jedev.osorteador.utils.ComponentsUtils.setupRecyclerView
import com.jedev.osorteador.utils.FragmentHelper.snackBar
import com.jedev.osorteador.utils.FragmentHelper.toast
import com.jedev.osorteador.utils.RandomUtils
import com.jedev.osorteador.utils.ViewUtils.customAlertDialog
import com.jedev.osorteador.viewmodel.WordsViewModel
import kotlinx.android.synthetic.main.dialog_add_word.view.*
import kotlinx.android.synthetic.main.fragment_words.*

class WordsFragment : Fragment() {

    enum class MODE(val id: Int) {
        Historic(0),
        Words(1)
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(WordsViewModel::class.java)
    }

    private var actualMode = MODE.Words

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_words, container, false)
    }

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set Recycle Layout
        setupRecyclerView(context!!, recyclerView)
        recyclerView.adapter = ItemWordAdapter(mutableListOf(), ::onDeleteWorld)

        changeListMode(MODE.Words)

        btn_add_word.setOnClickListener {
            showDialogAddWord()
        }

        btn_random.setOnClickListener {
            onRandomWord()
        }

        btn_change_list_word.setOnClickListener {
            actualMode = if (actualMode == MODE.Words) MODE.Historic else MODE.Words

            changeListMode(actualMode)
        }
    }

    private fun changeListMode(modeList: MODE) {
        actualMode = modeList

        val data = when (modeList) {
            MODE.Historic -> viewModel.historicWords.value
            MODE.Words -> viewModel.words.value
        }

        (recyclerView.adapter as ItemWordAdapter).setItems(data!!)
    }

    private fun onRandomWord() {
        viewModel.words.value?.let {
            if (it.isNotEmpty()) {
                val word = RandomUtils.randomWords(it)

                viewModel.addInHistoric(word)
                changeListMode(MODE.Historic)

                snackBar(word)
            } else {
                toast("Lista de palavras vazia")
            }
        }
    }

    @SuppressLint("InflateParams")
    private fun showDialogAddWord() {
        val mDialogView = LayoutInflater.from(this.context!!).inflate(R.layout.dialog_add_word, null)
        val mAlertDialog = customAlertDialog(context!!, mDialogView,"Adicionar palavra").show()

        mDialogView.edt_word.requestFocus()

        mDialogView.btn_save.setOnClickListener {
            val word = mDialogView.edt_word.text.toString().trim()

            if (word.isNotEmpty()) {
                viewModel.addWord(word)
                changeListMode(MODE.Words)

                mAlertDialog.dismiss()
            }
        }

        mDialogView.btn_cancel.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

    private fun onDeleteWorld(word: String) {
        viewModel.removeWord(word)
    }
}
