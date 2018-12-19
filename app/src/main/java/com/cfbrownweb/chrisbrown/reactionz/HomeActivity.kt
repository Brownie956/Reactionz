package com.cfbrownweb.chrisbrown.reactionz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        small_game_btn.setOnClickListener {
            val intent = Intent(this, SmallGameActivity::class.java)
            startActivity(intent)
        }

        medium_game_btn.setOnClickListener {
            val intent = Intent(this, MediumGameActivity::class.java)
            startActivity(intent)
        }

        large_game_btn.setOnClickListener {
            val intent = Intent(this, LargeGameActivity::class.java)
            startActivity(intent)
        }
    }
}
