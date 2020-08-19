package com.example.math

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_loser.view.*
import kotlinx.android.synthetic.main.fragment_winner.view.*


class winnerFragment : Fragment() {
    val args:winnerFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_winner, container, false)
        val score = args.score;
        view.winnerText.text = view.winnerText.text.toString() + "$score"

        view.replayWinner.setOnClickListener {
            findNavController().navigate(winnerFragmentDirections.actionWinnerFragmentToQuastionsFragment2())
        }



        return view
    }


}