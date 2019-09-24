package com.jedev.osorteador.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jedev.osorteador.R
import com.jedev.osorteador.adapter.ItemWordAdapter
import com.jedev.osorteador.adapter.ItemWordRaffledAdapter
import com.jedev.osorteador.utils.ComponentsUtils.setupRecyclerView
import com.jedev.osorteador.utils.FragmentHelper.snackBar
import com.jedev.osorteador.utils.FragmentHelper.toast
import com.jedev.osorteador.utils.RandomUtils
import com.jedev.osorteador.utils.ViewUtils.customAlertDialog
import kotlinx.android.synthetic.main.dialog_add_word.view.*
import kotlinx.android.synthetic.main.fragment_words.*
import org.jetbrains.anko.support.v4.find

class WordsFragment : Fragment() {

    enum class MODE(val id: Int) {
        Historic(0),
        Words(1)
    }

    private val words = mutableListOf<String>()
    private val historicWords = mutableListOf<String>()

    private var actualMode = MODE.Words


    private val recyclerView by lazy { find<RecyclerView>(R.id.recyclerView) }

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

        btn_clear_history.setOnClickListener {
            onClearHistory()
        }

        btn_change_list_word.setOnClickListener {
            actualMode = if (actualMode == MODE.Words) MODE.Historic else MODE.Words

            changeListMode(actualMode)
        }
    }

    private fun onClearHistory() {
        historicWords.clear()
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    private fun changeListMode(modeList: MODE) {
        actualMode = modeList

        when (modeList) {
            MODE.Historic -> {
                btn_clear_history.visibility = View.VISIBLE
                recyclerView.adapter = ItemWordRaffledAdapter(historicWords)
            }
            MODE.Words -> {
                btn_clear_history.visibility = View.GONE

                recyclerView.adapter = ItemWordAdapter(words, ::onDeleteWorld)
            }
        }
    }

    private fun onRandomWord() {
        if (words.size > 1)  {
            val word = RandomUtils.randomWords(words)

            addInHistoric(word)
            changeListMode(MODE.Historic)

            snackBar(word)
        } else {
            toast("Adicione palavras na lista!")
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
                addWord(word)
                changeListMode(MODE.Words)

                mAlertDialog.dismiss()
            }
        }

        mDialogView.btn_cancel.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

    private fun onDeleteWorld(word: String) {
        removeWord(word)
    }


    private fun addWord(item: String) {
        this.words.add(item)
    }

    private fun removeWord(item: String) {
        this.words.remove(item)
    }

    private fun addInHistoric(item: String) {
        this.historicWords.add(item)
    }
}
