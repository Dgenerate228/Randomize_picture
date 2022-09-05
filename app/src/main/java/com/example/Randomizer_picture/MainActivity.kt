package com.example.Randomizer_picture

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.RadioButton
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.lesson_kotlin_part_3.R
import com.example.lesson_kotlin_part_3.databinding.ActivityMainBinding
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var useKeyword: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getRandomImageButton.setOnClickListener {
            onGetRandomImagePressed()

        }
        binding.getRandomImageButton.setOnLongClickListener {
            showToastWithRandomNumber()
    }
        binding.getRandomImageButton2.setOnClickListener {
            onGetRandomImagePressedTwo()
        }
        binding.keywordEditText.setOnEditorActionListener() { _, actionID, _ ->
            if (actionID == EditorInfo.IME_ACTION_SEARCH) {
                return@setOnEditorActionListener onGetRandomImagePressedTwo()
            }
            return@setOnEditorActionListener false
        }

        binding.buttonRadio.setOnClickListener {
            onGetRadioButtonRandomImage()
        }

        binding.useKeywordChekBox.setOnClickListener {
            this.useKeyword = binding.useKeywordChekBox.isChecked
            updateUI()
        }
        updateUI()
}
    private fun onGetRandomImagePressed() {
        binding.progressBar.visibility = View.VISIBLE
        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.cat_load)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?,
                                          target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(binding.testImageView)


    }

    private fun showToastWithRandomNumber(): Boolean {
        val message = getString(R.string.random_number)
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
        return true
    }

        private fun onGetRandomImagePressedTwo(): Boolean {
        val keyword = binding.keywordEditText.text.toString()
        if (useKeyword &&  keyword.isBlank()) {
                binding.keywordEditText.error = "Keyword is empty"
                return true
            }
        val encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8.name())
            binding.progressBar.visibility = View.VISIBLE
            Glide.with(this)
                .load("https://source.unsplash.com/random/800x600?${if (useKeyword) "?$encodedKeyword" else ""}")
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.cat_load)

                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?,
                                              target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }
                })
                .into(binding.testImageView)
            return false

        }
    private fun updateUI() = with(binding) {
        useKeywordChekBox.isChecked = useKeyword
                if(useKeyword) {
                    keywordEditText.visibility = View.VISIBLE

                } else {
                    keywordEditText.visibility = View.GONE
                }
        if(useKeyword) {
            getRandomImageButton2.visibility = View.VISIBLE

        } else {
            getRandomImageButton2.visibility = View.GONE

        //Ниже под вопросом

        }
        if (useKeyword) {
            previewText.visibility = View.GONE

        } else {
            previewText.visibility = View.VISIBLE
        }
        if (useKeyword) {
            buttonRadio.visibility = View.GONE

        } else {
            buttonRadio.visibility = View.VISIBLE
        }

        if (useKeyword) {
            keywordRadioGroup.visibility = View.GONE

        } else {
            keywordRadioGroup.visibility = View.VISIBLE
        }

    }
    private fun onGetRadioButtonRandomImage(): Boolean {
        val checkedId = binding.keywordRadioGroup.checkedRadioButtonId
        val keyword = binding.keywordRadioGroup.findViewById<RadioButton>(checkedId).text.toString()
        val encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8.name())
        binding.progressBar.visibility = View.VISIBLE
        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600?$encodedKeyword")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.cat_load)

            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?,
                             target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(binding.testImageView)
        return false
    }


}