package com.alg.metafile.file.state.holder

import androidx.annotation.IntDef
import com.alg.metafile.file.state.holder.SortType.Companion.DEFAULT
import com.alg.metafile.file.state.holder.SortType.Companion.SPACE
import com.alg.metafile.file.state.holder.SortType.Companion.TIME
import java.io.File

class FileListHolder(
  private val fileList: List<File> = arrayListOf(),
  val parentPath: String? = null
) {

  private var isRewind: Boolean = false

  @SortType var sortType: Int = DEFAULT
    set(value) {
      isRewind = field == value
      field = value
    }

  fun getFileList(): List<File> {
    return when (sortType) {
      TIME -> {
        fileList.sortedWith { f1, f2 ->
          var result = if (f1.lastModified() < f2.lastModified())
            -1
          else if (f1.lastModified() > f2.lastModified())
            1
          else
            0
          if (isRewind) {
            result = -result
          }
          result
        }
      }
      SPACE -> {
        fileList.sortedWith { f1, f2 ->
          var result = if (f1.length() < f2.length())
            -1
          else if (f1.length() > f2.length())
            1
          else
            0
          if (isRewind) {
            result = -result
          }
          result
        }
      }
      else -> fileList.sortedWith { f1, f2 ->
        var result = f1.name.compareTo(f2.name)
        if (isRewind) {
          result = -result
        }
        result
      }
    }
  }
}

data class FileData(val file: File)

@IntDef(DEFAULT, TIME, SPACE)
annotation class SortType {

  companion object {

    const val DEFAULT = 1

    const val TIME = 2

    const val SPACE = 3
  }
}