package com.example.math

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_loser.view.*


class loserFragment : Fragment() {
    val args:loserFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_loser, container, false)
        val score = args.score;
        view.loserText.text = view.loserText.text.toString() + "$score";

        view.replayLoser.setOnClickListener {
            findNavController().navigate(loserFragmentDirections.actionLoserFragmentToQuastionsFragment2())
        }
        return view
    }
}

