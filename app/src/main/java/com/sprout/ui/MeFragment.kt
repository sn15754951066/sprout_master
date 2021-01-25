package com.sprout.ui

import androidx.fragment.app.Fragment

class MeFragment : Fragment() {
    companion object {
        val instance: MeFragment by lazy { MeFragment() }
    }
}