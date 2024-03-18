package com.example.blocktofocus.ui.home

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.blocktofocus.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val listView: ListView = binding.listViewApps
        val appsList = getAllAppsFromDevice(requireActivity().packageManager)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, appsList)
        listView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getAllAppsFromDevice(packageManager: PackageManager): List<String> {
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val resolveInfoList: List<ResolveInfo> = packageManager.queryIntentActivities(intent, 0)
        val appsList = mutableListOf<String>()
        for (resolveInfo in resolveInfoList) {
            val appLabel: CharSequence = resolveInfo.loadLabel(packageManager)
            appsList.add(appLabel.toString())
        }
        return appsList
    }
}
