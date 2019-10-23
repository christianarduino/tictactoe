package com.christianarduino.tictactoe

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableRow
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var activePlayer: Int = 0
    private var winner: Int? = null
    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onButtonClick(view: View) {
        val button: Button = view as Button
        var cellId = 0
        when (button.id) {
            R.id.button1 -> cellId = 1
            R.id.button2 -> cellId = 2
            R.id.button3 -> cellId = 3
            R.id.button4 -> cellId = 4
            R.id.button5 -> cellId = 5
            R.id.button6 -> cellId = 6
            R.id.button7 -> cellId = 7
            R.id.button8 -> cellId = 8
            R.id.button9 -> cellId = 9
        }
        playGame(cellId, button)
    }

    private fun playGame(cellId: Int, button: Button) {
        if (activePlayer == 0) {
            button.isEnabled = false
            button.text = "X"
            player1.add(cellId)
            button.setTextColor(Color.parseColor("#006db3"))
            activePlayer = 1
        } else {
            button.isEnabled = false
            button.text = "O"
            button.setTextColor(Color.parseColor("#b34600"))
            player2.add(cellId)
            activePlayer = 0
        }
        checkWinner()
        if (winner != null) {
            winnerText.text = "Congratulation! \n The winner is Player $winner"
            stopGame()
        }
    }

    private fun checkWinner() {

        //Row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
            return
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
            return
        }

        //Row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
            return
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
            return
        }

        //Row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
            return
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
            return
        }

        //Col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
            return
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
            return
        }

        //Col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
            return
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
            return
        }

        //Col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
            return
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
            return
        }


        //Diag 1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
            return
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
            return
        }

        //Diag 2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
            return
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
            return
        }

    }

    private fun stopGame() {
        for (i in 0 until tableLayout.childCount) {
            val view: TableRow = tableLayout.getChildAt(i) as TableRow

            for (j in 0 until view.childCount) {
                val button = view.getChildAt(j)
                button.isEnabled = false
            }
        }
        restartGame.isEnabled = true
    }

    fun onRestartGame(restartButton : View) {
        for (i in 0 until tableLayout.childCount) {
            val view: TableRow = tableLayout.getChildAt(i) as TableRow

            for (j in 0 until view.childCount) {
                val button = view.getChildAt(j) as Button

                button.text = ""
                button.isEnabled = true
            }
        }

        player1.clear()
        player2.clear()
        winner = null
        activePlayer = 0
        winnerText.text = "Who will win?"
    }
}
