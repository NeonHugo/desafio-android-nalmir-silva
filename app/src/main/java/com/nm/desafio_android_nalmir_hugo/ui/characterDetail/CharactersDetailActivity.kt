package com.nm.desafio_android_nalmir_hugo.ui.characterDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.bumptech.glide.Glide
import com.nm.desafio_android_nalmir_hugo.R
import com.nm.desafio_android_nalmir_hugo.ui.charactersList.CharactersListActivity
import com.nm.desafio_android_nalmir_hugo.ui.comicBookDetail.ComicBookDetailActivity
import com.nm.domain.entity.MCharacter
import com.nm.infrastructure.base.BaseActivity
import com.nm.infrastructure.base.BaseViewModel
import com.nm.infrastructure.util.extensions.activity.setupToolbarWithTitle
import com.nm.infrastructure.util.extensions.livecycle.bind
import kotlinx.android.synthetic.main.characters_detail_activity.*
import kotlinx.android.synthetic.main.characters_detail_content.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersDetailActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel
    private val viewModel: CharactersDetailViewModel by viewModel()

    private var heroID = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.characters_detail_activity)

        initVars()
        initActions()
        subscribeUi()
    }

    private fun initVars() {
        setupToolbarWithTitle(
            toolbar, getString(R.string.character_detail_activity_title), false
        )

        viewModel.onCharacterDetail(
            intent.getParcelableExtra(CHARACTER_KEY)!!
        )
    }

    private fun initActions() {
        btn_comics.setOnClickListener {
            callComicBookDetail()
        }

    }

    private fun callComicBookDetail() {
        val intent = ComicBookDetailActivity.newIntent(
            this,
            heroID
        )

        startActivity(intent)

        finish()
    }

    private fun subscribeUi() {
        bind(viewModel.loading, ::showHideLoading)
        bind(viewModel.character, ::showCharacter)
    }

    private fun showHideLoading(loading: Boolean) {

    }

    private fun showCharacter(mCharacter: MCharacter) {
        heroID = mCharacter.id

        Glide.with(this)
            .load(mCharacter.thumbnail)
            .placeholder(R.drawable.ic_person_black_24dp)
            .into(hero_image)

        hero_name.text = mCharacter.name
        hero_description.text = mCharacter.description
    }

    private fun callCharacterList() {
        val intent = CharactersListActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onBackPressed() {
        callCharacterList()
    }

    companion object {
        const val CHARACTER_KEY = "character_key"

        fun newIntent(context: Context, mCharacter: MCharacter) : Intent {
            return Intent(context, CharactersDetailActivity::class.java).putExtra(CHARACTER_KEY, mCharacter)
        }
    }

}
