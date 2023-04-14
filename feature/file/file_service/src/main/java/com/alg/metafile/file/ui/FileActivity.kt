package com.alg.metafile.file.ui

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.alg.matefile.lib.framework.ui.BaseActivity
import com.alg.metafile.file.state.viewmodel.FileViewModel
import com.alg.metafile.file.ui.compose.FileUI
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.PermissionUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FileActivity : BaseActivity() {

  private val fileViewModel: FileViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      FileUI(fileViewModel)
    }
    fileViewModel.getFileList()
    onBackPressedDispatcher.addCallback(this) {
      if(!fileViewModel.onBack()){
        finish()
      }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()) {
      startActivity(Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION))
    } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R && !PermissionUtils.isGranted(
        PermissionConstants.STORAGE
      )
    ) {
      PermissionUtils.permission(PermissionConstants.STORAGE)
        .callback { isAllGranted, granted, deniedForever, denied ->
          if (!isAllGranted) {
            AlertDialog.Builder(this)
              .setMessage("请同意存储权限以正常使用功能")
              .setNegativeButton("取消") { _: DialogInterface, _: Int ->
                AppUtils.exitApp()
              }
              .setPositiveButton("确定") { _: DialogInterface, _: Int ->
                try {
                  val intent = Intent()
                  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                  intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                  intent.data = (Uri.fromParts("package", packageName, null))
                  startActivity(intent)
                } catch (_: Exception) {
                }
              }
              .create().show()
          }
        }.request()
    }
  }
}