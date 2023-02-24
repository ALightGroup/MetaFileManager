package com.alg.metafile.file.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.alg.matefile.lib.BaseActivity

class FileActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Text("asd")
    }
  }

}