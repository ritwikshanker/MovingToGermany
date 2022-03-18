package com.moving.germany.about

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.moving.germany.MainActivity
import com.moving.germany.R
import com.moving.germany.base.BaseFragment
import com.moving.germany.databinding.AboutFragmentBinding
import com.moving.germany.databinding.TranslateToGermanFragmentBinding

class AboutFragment : BaseFragment<AboutFragmentBinding>() {

    private val contributors = listOf(
        Contributor("Ritwik Shanker", "🇮🇳", true),
    )

    private fun setupNavBar() {
        (activity as? MainActivity)?.apply {
            title = getString(R.string.about_us)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        }
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.about_us)?.isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    private fun renderVersion() {
        try {
            val pInfo = requireContext().packageManager.getPackageInfo(
                requireContext().packageName, 0
            )
            val version = pInfo?.versionName
            binding.textVersion.text = version
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun renderContributors() {
        binding.containerContributors?.apply {
            removeAllViews()
            contributors.mapIndexed { index, item ->
                addView(
                    createContributorTextView(index, item),
                    LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                )
            }
        }
    }

    private fun createContributorTextView(index: Int, contributor: Contributor): TextView {
        val contributorText = "${contributor.flag} ${contributor.name}" +
                if (contributor.owner) " (Owner)" else ""
        return TextView(requireContext()).apply {
            text = contributorText
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            setPadding(0, if (index == 0) 0 else 4, 0, 0)
        }
    }

    data class Contributor(val name: String, val flag: String, val owner: Boolean = false)

    companion object {
        fun newInstance() = AboutFragment()
    }

    override fun layoutResourceId(): Int = R.layout.about_fragment

    override fun initViewCreated() {
        setupNavBar()
        renderVersion()
        renderContributors()
    }
}