package com.alg.metafile.file.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alg.matefile.lib.ROOT_PATH
import com.alg.matefile.lib.common.ui.compose.widget.VectorImage
import com.alg.metafile.file.R
import com.alg.metafile.file.state.holder.FileListHolder
import com.alg.metafile.file.state.viewmodel.FileViewModel

@Composable fun FileUI(fileViewModel: FileViewModel) {
  val listState = fileViewModel.files.observeAsState(FileListHolder())
  Column(modifier = Modifier.fillMaxSize()) {
    FileBody(listState.value) { path ->
      path?.let { fileViewModel.getFileList(it) }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun FileBody(holder: FileListHolder, getFileList: (String?) -> Unit) {
  Column(modifier = Modifier.fillMaxSize()) {
    TopAppBar(
      title = {
        val title = if (holder.parentPath == ROOT_PATH) {
          stringResource(id = com.alg.metafile.resources.R.string.app_name)
        } else {
          holder.parentPath?.split("/")?.lastOrNull()
            ?: stringResource(id = com.alg.metafile.resources.R.string.app_name)
        }
        Text(text = title)
      }
    )
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)
        .absolutePadding(left = 16.dp, right = 16.dp)
        .statusBarsPadding()
    ) {
      holder.fileList.forEach {
        item {
          Spacer(
            modifier = Modifier
              .height(16.dp)
              .fillMaxWidth()
          )
        }
        item {
          Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .clickable {
              getFileList(it.absolutePath)
            }
            .absolutePadding(left = 16.dp, top = 16.dp, right = 16.dp, bottom = 16.dp)) {
            VectorImage(
              resId = R.drawable.file_ic_folder, modifier = Modifier.size(32.dp)
            )
            Text(
              text = it.name,
              fontSize = 16.sp,
              fontWeight = FontWeight.W500,
              modifier = Modifier
                .absolutePadding(left = 10.dp)
                .align(Alignment.CenterVertically)
            )
          }
        }
        item {
          Spacer(
            modifier = Modifier
              .height(16.dp)
              .fillMaxWidth()
          )
        }
      }
    }
  }
}
