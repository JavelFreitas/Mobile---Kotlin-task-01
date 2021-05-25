package com.example.jogodavelha

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jogodavelha.databinding.ActivityJogoBinding

class MainActivity : AppCompatActivity() {
    private var player: Int = 1
    private var oneScore: Int = 0
    private var twoScore: Int = 0
    private lateinit var binding: ActivityJogoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJogoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val allButtons = listOf(binding.button1, binding.button2, binding.button3, binding.button4, binding.button5, binding.button6, binding.button7, binding.button8, binding.button9)
        for (button in allButtons){
            button.setOnClickListener {
                val marcou = markButton(button)
                if(marcou){
                    condicaoVitoria(allButtons)
                    changePlayer(binding.playerTurnNow)
                }
            }
        }
    }

    private fun markButton(button: Button): Boolean {
        if (button.text != "-") {
            Toast.makeText(this, "Já escolhido", Toast.LENGTH_SHORT).show()
            return false
        }
        button.text = if (this.player == 1) "X" else "O"
        return true
    }

    private fun changePlayer(playerTurnNow: TextView) {
        this.player = if(this.player == 1) 2 else 1
        playerTurnNow.text = "P" + this.player
    }
    //1, 2, 3 - 4, 5, 6 - 7, 8, 9 - 1, 5, 9 - 3, 5, 7 - 0, 3, 6 - 1, 4, 7 - 2, 5, 8
    private fun condicaoVitoria(allButtons: List<Button>) {
        if ((allButtons[0].text != "-" && allButtons[0].text == allButtons[1].text && allButtons[1].text == allButtons[2].text) ||
            (allButtons[3].text != "-" && allButtons[3].text == allButtons[4].text && allButtons[4].text == allButtons[5].text) ||
            (allButtons[6].text != "-" && allButtons[6].text == allButtons[7].text && allButtons[7].text == allButtons[8].text) ||
            (allButtons[0].text != "-" && allButtons[0].text == allButtons[4].text && allButtons[4].text == allButtons[8].text) ||
            (allButtons[2].text != "-" && allButtons[2].text == allButtons[4].text && allButtons[4].text == allButtons[6].text) ||
            (allButtons[0].text != "-" && allButtons[0].text == allButtons[3].text && allButtons[3].text == allButtons[6].text) ||
            (allButtons[1].text != "-" && allButtons[1].text == allButtons[4].text && allButtons[4].text == allButtons[7].text) ||
            (allButtons[2].text != "-" && allButtons[2].text == allButtons[5].text && allButtons[5].text == allButtons[8].text)){
            Toast.makeText(this, "Player $player ganhou", Toast.LENGTH_SHORT).show()
            changePlayerScore(this.player)
            resetGame(allButtons)
        }else{
            var counter = 0
            for (button in allButtons){
                if (button.text != "-"){
                    counter++
                }
            }
            if (counter == 9){
                Toast.makeText(this, "Empate", Toast.LENGTH_SHORT).show()
                resetGame(allButtons)
            }
        }
    }

    private fun changePlayerScore(player: Int) {
        if (player == 1){
            oneScore++
            binding.pOneScore.text = oneScore.toString()
        }else {
            twoScore++
            binding.pTwoScore.text = twoScore.toString()
        }
    }
    private fun resetGame(allButtons: List<Button>) {
        for (button in allButtons){
            button.text = "-"
        }
    }
}
