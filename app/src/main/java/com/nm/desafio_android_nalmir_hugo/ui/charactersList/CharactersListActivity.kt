package com.nm.desafio_android_nalmir_hugo.ui.charactersList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nm.desafio_android_nalmir_hugo.R
import com.nm.desafio_android_nalmir_hugo.ui.characterDetail.CharactersDetailActivity
import com.nm.domain.entity.MCharacter
import com.nm.infrastructure.base.BaseActivity
import com.nm.infrastructure.base.BaseViewModel
import com.nm.infrastructure.util.extensions.activity.setupToolbarWithTitle
import com.nm.infrastructure.util.extensions.livecycle.bind
import kotlinx.android.synthetic.main.characters_list_activity.*
import kotlinx.android.synthetic.main.characters_list_content.*
import kotlinx.android.synthetic.main.view_empty_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.security.AccessController.getContext

class CharactersListActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel
    private val viewModel: CharactersListViewModel by viewModel()

    private lateinit var charactersListAdapter: CharactersListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.characters_list_activity)

        initVars()
        initActions()
        subscribeUi()
    }

    private fun initVars() {
        setupToolbarWithTitle(
            toolbar, getString(R.string.character_list_activity_title), false
        )

        val decorator = DividerItemDecoration(rv_characters.context, DividerItemDecoration.VERTICAL)
        decorator.setDrawable(resources.getDrawable(R.drawable.divider_line))

        rv_characters.layoutManager = LinearLayoutManager(this)
        rv_characters.addItemDecoration(decorator)
    }

    private fun initActions() {

        characters_refresh.setOnRefreshListener {
            viewModel.onCharactersList()
        }

        tv_without_alert.setOnClickListener {
            characters_refresh.isRefreshing = true
            emptyLayout.visibility = View.GONE
            viewModel.onCharactersList()
        }
    }

    private fun subscribeUi() {
        bind(viewModel.error, ::showError)
        bind(viewModel.loading, ::showHideLoading)
        bind(viewModel.characters, ::showCharacterList)
    }

    private fun showError(error: Boolean) {
        if (error) {
            if (rv_characters.adapter == null || rv_characters.adapter!!.itemCount <=0 ) {
                emptyLayout.visibility = View.VISIBLE
            } else {
                emptyLayout.visibility = View.GONE
            }
        }
    }

    private fun showHideLoading(loading: Boolean) {
        characters_refresh.isRefreshing = loading
    }


    private fun showCharacterList(characters: List<MCharacter>) {
        charactersListAdapter =
            CharactersListAdapter(this, R.layout.characters_list_cell, characters, Glide.with(this)) {
                callCharacterDetail(it)
            }

        rv_characters.adapter = charactersListAdapter
    }

    private fun callCharacterDetail(character: MCharacter) {
        val intent = CharactersDetailActivity.newIntent(this, character)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CharactersListActivity::class.java)
        }
    }


}
