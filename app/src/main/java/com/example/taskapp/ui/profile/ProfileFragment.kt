package com.example.taskapp.ui.profile

import android.app.ActionBar
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.taskapp.data.local.Pref
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var pref: Pref





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        saveNickName()
        hideActionBar()
        binding.profileImage.setOnClickListener {
            pickImageFromGallery()
        }
        exitAlertDialog()

    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent,1)

    }

    private fun exitAlertDialog() {
        binding.ivExit.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.exit_text))
                .setMessage(getString(R.string.exit_text_end))
                .setPositiveButton(getString(R.string.delete_accept)) { _, _ ->
                    findNavController().navigate(R.id.phoneFragment)
                }
                .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }

    private fun hideActionBar() {
        val actionBar: androidx.appcompat.app.ActionBar? = (requireActivity() as? AppCompatActivity)?.supportActionBar
        actionBar?.hide()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            pref.setPicture(imageUri.toString())
            binding.profileImage.setImageURI(imageUri)
        }
    }

    private fun saveNickName() {
        binding.etName.setText(pref.getNickName())
        binding.btnSave.addTextChangedListener {
            pref.setNickName(binding.etName.text.toString())
        }
    }


}
