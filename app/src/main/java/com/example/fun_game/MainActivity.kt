package com.example.fun_game



import com.example.fun_game.R;

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.TextView
import android.widget.ImageView
import android.graphics.Color
import android.os.Handler
import java.util.Arrays
import java.util.Collections
import android.view.View
import android.app.AlertDialog

import android.content.DialogInterface


class MainActivity : ComponentActivity() {
    private lateinit var tv_p1: TextView
    private lateinit var tv_p2: TextView
    private lateinit var aries: ImageView
    private lateinit var aries2: ImageView
    private lateinit var gemini: ImageView
    private lateinit var gemini2: ImageView
    private lateinit var leo: ImageView
    private lateinit var leo2: ImageView
    private lateinit var scorpio: ImageView
    private lateinit var scorpio2: ImageView
    private lateinit var virgo: ImageView
    private lateinit var virgo2: ImageView
    private lateinit var taurus: ImageView
    private lateinit var taurus2: ImageView

    // Array for images
    private val cardArray = intArrayOf(
        R.drawable.aries, R.drawable.aries2, R.drawable.gemini,
        R.drawable.gemini2, R.drawable.leo, R.drawable.leo2,
        R.drawable.scorpio, R.drawable.scorpio2, R.drawable.virgo,
        R.drawable.virgo2, R.drawable.taurus, R.drawable.taurus2
    )



    private var firstCard: Int = 0
    private var secondCard: Int = 0
    private var clickedFirst: Int = 0
    private var clickedSecond: Int = 0
    private var cardNumber: Int = 1
    private var turn: Int = 1
    private var playerPoints: Int = 0
    private var cpuPoints: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize TextViews
        tv_p1 = findViewById(R.id.tv_p1)
        tv_p2 = findViewById(R.id.tv_p2)

        // Initialize ImageViews
        aries = findViewById(R.id.Aries)
        aries2 = findViewById(R.id.Aries2)
        gemini = findViewById(R.id.Gemini)
        gemini2 = findViewById(R.id.Gemini2)
        leo = findViewById(R.id.Leo)
        leo2 = findViewById(R.id.Leo2)
        scorpio = findViewById(R.id.Scorpio)
        scorpio2 = findViewById(R.id.Scorpio2)
        virgo = findViewById(R.id.Virgo)
        virgo2 = findViewById(R.id.Virgo2)
        taurus = findViewById(R.id.Taurus)
        taurus2 = findViewById(R.id.Taurus2)

        aries.setTag("0")
        aries2.setTag("1")
        gemini.setTag("2")
        gemini2.setTag("3")
        leo.setTag("4")
        leo2.setTag("5")
        scorpio.setTag("6")
        scorpio2.setTag("7")
        virgo.setTag("8")
        virgo2.setTag("9")
        taurus.setTag("10")
        taurus2.setTag("11")

        // Load the card images and shuffle
        frontOfCardResources()
        Collections.shuffle(Arrays.asList(cardArray))

        // Changes the color of the second player
        tv_p2.setTextColor(Color.YELLOW)

        aries.setOnClickListener { view ->
            val theCard = view.tag as? Int
            doStuff(aries, theCard)
        }

        aries2.setOnClickListener { view ->
            val theCard = view.tag as? Int
            doStuff(aries2, theCard)
        }
        gemini.setOnClickListener { view ->
            val theCard = view.tag as? Int
            doStuff(gemini, theCard)
        }
        gemini2.setOnClickListener { view ->
            val theCard = view.tag as? Int
            doStuff(gemini2, theCard)
        }
        leo.setOnClickListener { view ->
            val theCard = view.tag as? Int
            doStuff(leo, theCard)
        }
        leo2.setOnClickListener { view ->
            val theCard = view.tag as? Int
            doStuff(leo2, theCard)
        }
        scorpio.setOnClickListener { view ->
            val theCard = view.tag as? Int
            doStuff(scorpio, theCard)
        }
        scorpio2.setOnClickListener { view ->
            val theCard = view.tag as? Int
            doStuff(scorpio2, theCard)
        }
        virgo.setOnClickListener { view ->
            val theCard = view.tag as? Int
            doStuff(virgo, theCard)
        }
        virgo2.setOnClickListener { view ->
            val theCard = view.tag as? Int
            doStuff(virgo2, theCard)
        }
        taurus.setOnClickListener { view ->
            val theCard = view.tag as? Int
            doStuff(taurus, theCard)
        }
        taurus2.setOnClickListener { view ->
            val theCard = view.tag as? Int
            doStuff(taurus2, theCard)
        }
    }

    private fun doStuff(imageView: ImageView, card: Int?) {
        when (card) {
            R.id.Aries -> imageView.setImageResource(R.drawable.aries)
            R.id.Aries2 -> imageView.setImageResource(R.drawable.aries2)
            R.id.Gemini -> imageView.setImageResource(R.drawable.gemini)
            R.id.Gemini2 -> imageView.setImageResource(R.drawable.gemini2)
            R.id.Leo -> imageView.setImageResource(R.drawable.leo)
            R.id.Leo2 -> imageView.setImageResource(R.drawable.leo2)
            R.id.Scorpio -> imageView.setImageResource(R.drawable.scorpio)
            R.id.Scorpio2 -> imageView.setImageResource(R.drawable.scorpio2)
            R.id.Virgo -> imageView.setImageResource(R.drawable.virgo)
            R.id.Virgo2 -> imageView.setImageResource(R.drawable.virgo2)
            R.id.Taurus -> imageView.setImageResource(R.drawable.taurus)
            R.id.Taurus2 -> imageView.setImageResource(R.drawable.taurus2)
        }

        if (cardNumber == 1) {
            firstCard = card ?: -1
            if (firstCard > 200) {
                firstCard -= 100
            }
            cardNumber = 2
            clickedFirst = card ?: -1

            imageView.isEnabled = false
        } else if (cardNumber == 2) {
            secondCard = card ?: -1
            if (secondCard > 200) {
                secondCard -= 100
            }
            cardNumber = 1
            clickedSecond = card ?: -1

            // Enable ImageViews after a delay
            val handler = Handler()
            handler.postDelayed({
                enableAllImageViews()
                calculate() // Uncomment if calculate function is defined
            }, 1000)
        }
    }

    private fun calculate() {
        if (firstCard == secondCard) {
            if (clickedFirst == 0) {
                aries.visibility = View.INVISIBLE
            } else if (clickedFirst == 1) {
                aries2.visibility = View.INVISIBLE
            } else if (clickedFirst == 2) {
                gemini.visibility = View.INVISIBLE
            } else if (clickedFirst == 3) {
                gemini2.visibility = View.INVISIBLE
            } else if (clickedFirst == 4) {
                leo.visibility = View.INVISIBLE
            } else if (clickedFirst == 5) {
                leo2.visibility = View.INVISIBLE
            } else if (clickedFirst == 6) {
                scorpio.visibility = View.INVISIBLE
            } else if (clickedFirst == 7) {
                scorpio2.visibility = View.INVISIBLE
            } else if (clickedFirst == 8) {
                taurus.visibility = View.INVISIBLE
            } else if (clickedFirst == 9) {
                taurus2.visibility = View.INVISIBLE
            } else if (clickedFirst == 10) {
                virgo.visibility = View.INVISIBLE
            } else if (clickedFirst == 11) {
                virgo2.visibility = View.INVISIBLE
            }

            if (clickedSecond == 0) {
                aries.visibility = View.INVISIBLE
            } else if (clickedSecond == 1) {
                aries2.visibility = View.INVISIBLE
            } else if (clickedSecond == 2) {
                gemini.visibility = View.INVISIBLE
            } else if (clickedSecond == 3) {
                gemini2.visibility = View.INVISIBLE
            } else if (clickedSecond == 4) {
                leo.visibility = View.INVISIBLE
            } else if (clickedSecond == 5) {
                leo2.visibility = View.INVISIBLE
            } else if (clickedSecond == 6) {
                scorpio.visibility = View.INVISIBLE
            } else if (clickedSecond == 7) {
                scorpio2.visibility = View.INVISIBLE
            } else if (clickedSecond == 8) {
                taurus.visibility = View.INVISIBLE
            } else if (clickedSecond == 9) {
                taurus2.visibility = View.INVISIBLE
            } else if (clickedSecond == 10) {
                virgo.visibility = View.INVISIBLE
            } else if (clickedSecond == 11) {
                virgo2.visibility = View.INVISIBLE
            }
            //add pointers to correct player
            if (turn == 1) {
                playerPoints++
                tv_p1.text = "P1: $playerPoints"
            } else if (turn == 2) {
                cpuPoints++
                tv_p2.text = "P2: $cpuPoints"
            }

        } else {
            aries.setImageResource(R.drawable.quest);
            aries2.setImageResource(R.drawable.quest);
            gemini.setImageResource(R.drawable.quest);
            gemini2.setImageResource(R.drawable.quest);
            leo.setImageResource(R.drawable.quest);
            leo2.setImageResource(R.drawable.quest);
            scorpio.setImageResource(R.drawable.quest);
            scorpio2.setImageResource(R.drawable.quest);
            taurus.setImageResource(R.drawable.quest);
            taurus2.setImageResource(R.drawable.quest);
            virgo.setImageResource(R.drawable.quest);
            virgo2.setImageResource(R.drawable.quest);

            if (turn == 1) {
                turn = 2;
                tv_p1.setTextColor(Color.GRAY);
                tv_p2.setTextColor(Color.BLACK);
            } else if (turn == 2) {
                turn = 1;
                tv_p2.setTextColor(Color.GRAY);
                tv_p1.setTextColor(Color.BLACK);
            }
        }
        aries.setEnabled(true);
        aries2.setEnabled(true);
        gemini.setEnabled(true);
        gemini2.setEnabled(true);
        leo.setEnabled(true);
        leo2.setEnabled(true);
        scorpio.setEnabled(true);
        scorpio2.setEnabled(true);
        taurus.setEnabled(true);
        taurus2.setEnabled(true);
        virgo.setEnabled(true);
        virgo2.setEnabled(true);

        //check if the game over
        checkEnd();
    }

    private fun checkEnd() {
        if (aries.visibility == View.INVISIBLE &&
            aries2.visibility == View.INVISIBLE &&
            gemini.visibility == View.INVISIBLE &&
            gemini2.visibility == View.INVISIBLE &&
            leo.visibility == View.INVISIBLE &&
            leo2.visibility == View.INVISIBLE &&
            scorpio.visibility == View.INVISIBLE &&
            scorpio2.visibility == View.INVISIBLE &&
            taurus.visibility == View.INVISIBLE &&
            taurus2.visibility == View.INVISIBLE &&
            virgo.visibility == View.INVISIBLE &&
            virgo2.visibility == View.INVISIBLE
        ) {

            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder
                .setMessage("GAME OVER!\nP1: $playerPoints\nP2: $cpuPoints")
                .setCancelable(false)
                .setPositiveButton("NEW") { dialogInterface, _ ->
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("EXIT") { dialogInterface, _ ->
                    finish()
                }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }

    private fun frontOfCardResources() {
        aries.setImageResource(R.drawable.aries)
        aries2.setImageResource(R.drawable.aries2)
        gemini.setImageResource(R.drawable.gemini)
        gemini2.setImageResource(R.drawable.gemini2)
        leo.setImageResource(R.drawable.leo)
        leo2.setImageResource(R.drawable.leo2)
        scorpio.setImageResource(R.drawable.scorpio)
        scorpio2.setImageResource(R.drawable.scorpio2)
        virgo.setImageResource(R.drawable.virgo)
        virgo2.setImageResource(R.drawable.virgo2)
        taurus.setImageResource(R.drawable.taurus)
        taurus2.setImageResource(R.drawable.taurus2)
    }

    private fun enableAllImageViews() {
        aries.isEnabled = true
        aries2.isEnabled = true
        gemini.isEnabled = true
        gemini2.isEnabled = true
        leo.isEnabled = true
        leo2.isEnabled = true
        scorpio.isEnabled = true
        scorpio2.isEnabled = true
        virgo.isEnabled = true
        virgo2.isEnabled = true
        taurus.isEnabled = true
        taurus2.isEnabled = true
    }
}
