package com.example.permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.florent37.runtimepermission.kotlin.askPermission

class MainActivity : AppCompatActivity() {

    private lateinit var tv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById(R.id.tv_permission)

//        checkPermission()

//tv.setOnClickListener {
//    requestPermission()
//}

myPermission()

    }


//    fun checkPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
//            tv.text
//            Toast.makeText(this, "ruxsat berilgan", Toast.LENGTH_SHORT).show()
//        }else{
//            tv.text
//            Toast.makeText(this, "ruxsat berilmagan", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    fun requestPermission() {
//        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS),1)
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 1){
//            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                Toast.makeText(this, "Ruxsat berildi", Toast.LENGTH_SHORT).show()
//        }else{
//            Toast.makeText(this, "Ruxsat berilmadi", Toast.LENGTH_SHORT).show()
//        }
//    }

    fun myPermission(){

        askPermission(Manifest.permission.READ_CONTACTS){
            //all permissions already granted or just granted
tv.text = "Ruxsat berilgan"
        }.onDeclined { e ->
            if (e.hasDenied()) {

                AlertDialog.Builder(this)
                    .setMessage("Please accept our permissions")
                    .setPositiveButton("yes") { dialog, which ->
                        e.askAgain();
                    } //ask again
                    .setNegativeButton("no") { dialog, which ->
                        dialog.dismiss();
                    }
                    .show();
            }

            if(e.hasForeverDenied()) {
                //the list of forever denied permissions, user has check 'never ask again'

                // you need to open setting manually if you really need it
                e.goToSettings();
            }
        }

    }
}