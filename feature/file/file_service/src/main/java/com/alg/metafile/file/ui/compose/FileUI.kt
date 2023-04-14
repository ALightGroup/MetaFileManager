package com.alg.metafile.file.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alg.matefile.lib.ROOT_PATH
import com.alg.matefile.lib.common.ui.compose.widget.VectorImage
import com.alg.metafile.file.R
import com.alg.metafile.file.state.holder.FileListHolder
import com.alg.metafile.file.state.holder.SortType.Companion.DEFAULT
import com.alg.metafile.file.state.holder.SortType.Companion.SPACE
import com.alg.metafile.file.state.holder.SortType.Companion.TIME
import com.alg.metafile.file.state.viewmodel.FileViewModel
import com.blankj.utilcode.util.FileUtils
import java.text.SimpleDateFormat
import java.util.Date

@Composable fun FileUI(fileViewModel: FileViewModel) {
  val list by fileViewModel.files.observeAsState(FileListHolder())
  val sortType = remember { mutableStateOf(DEFAULT) }
  list.sortType = sortType.value
  FileBody(list, sortType) { path ->
    path?.let { fileViewModel.getFileList(it) }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun FileBody(
  holder: FileListHolder,
  sortType: MutableState<Int>,
  getFileList: (String?) -> Unit
) {
  var expanded by remember { mutableStateOf(false) }
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
      },
      actions = {
        IconButton(onClick = {}) {
          Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            tint = Color.Gray
          )
        }
        IconButton(onClick = {
          expanded = true
        }) {
          Image(
            painter = painterResource(id = R.drawable.file_ic_sort),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.Gray)
          )
        }
        DropdownMenu(expanded = expanded,
          onDismissRequest = { expanded = false }) {
          DropdownMenuItem(
            text = { Text(stringResource(id = R.string.file_sort_by_default)) },
            onClick = { sortType.value = DEFAULT },
            leadingIcon = {
              Icon(
                Icons.Filled.List,
                contentDescription = null,
                tint = Color.Gray
              )
            })
          DropdownMenuItem(
            text = { Text(stringResource(id = R.string.file_sort_by_time)) },
            onClick = { sortType.value = TIME },
            leadingIcon = {
              Image(
                painterResource(id = R.drawable.file_ic_time),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray)
              )
            })
          DropdownMenuItem(
            text = { Text(stringResource(id = R.string.file_sort_by_size)) },
            onClick = { sortType.value = SPACE },
            leadingIcon = {
              Image(
                painterResource(id = R.drawable.file_ic_space),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Gray)
              )
            })
        }
      }
    )
    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(16.dp),
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Gray)
        .absolutePadding(left = 16.dp, right = 16.dp)
    ) {
      item {
        Spacer(
          modifier = Modifier
            .fillMaxWidth()
            .height(0.dp)
        )
      }
      holder.getFileList().forEach {
        item {
          Column {
            Row(modifier = Modifier
              .fillMaxWidth()
              .background(color = Color.White, shape = RoundedCornerShape(16.dp))
              .clickable {
                getFileList(it.absolutePath)
              }
              .absolutePadding(left = 16.dp, top = 8.dp, right = 16.dp, bottom = 8.dp)) {
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
            Text(text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(it.lastModified())))
            Text(text = FileUtils.getSize(it))
          }
        }
      }
      item {
        Spacer(
          modifier = Modifier
            .fillMaxWidth()
            .height(0.dp)
        )
      }
    }
  }
}
