package com.nm.desafio_android_nalmir_hugo.ui.comicBookDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.bumptech.glide.Glide
import com.nm.desafio_android_nalmir_hugo.R
import com.nm.desafio_android_nalmir_hugo.ui.charactersList.CharactersListActivity
import com.nm.domain.entity.Comic
import com.nm.domain.entity.MCharacter
import com.nm.infrastructure.base.BaseActivity
import com.nm.infrastructure.base.BaseViewModel
import com.nm.infrastructure.util.extensions.activity.setupToolbarWithTitle
import com.nm.infrastructure.util.extensions.format.toCurrency
import com.nm.infrastructure.util.extensions.livecycle.bind
import kotlinx.android.synthetic.main.characters_detail_activity.*
import kotlinx.android.synthetic.main.characters_detail_content.*
import kotlinx.android.synthetic.main.comicbook_detail_content.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicBookDetailActivity : BaseActivity() {

    override val baseViewModel: BaseViewModel get() = viewModel
    private val viewModel: ComicBookDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comicbook_detail_activity)

        initVars()
        initActions()
        subscribeUi()
    }

    private fun initVars() {
        setupToolbarWithTitle(
            toolbar, getString(R.string.comicbook_detail_activity_title), false
        )

        viewModel.onComicDetail(
            intent.getLongExtra(CHARACTER_ID, -1L)
        )
    }

    private fun initActions() {

    }

    private fun subscribeUi() {
        bind(viewModel.loading, ::showHideLoading)
        bind(viewModel.comic, ::showComic)
    }

    private fun showHideLoading(loading: Boolean) {

    }

    private fun showComic(comic: Comic) {

        Glide.with(this)
            .load(comic.thumbnail)
            .placeholder(R.drawable.ic_person_black_24dp)
            .into(comic_image)

        comic_title.text = comic.title
        comic_description.text = comic.title
        comic_price.text =  "Price  ${comic.price.toCurrency()}"
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
        const val CHARACTER_ID = "character_id"

        fun newIntent(context: Context, characterId: Long) : Intent {
            return Intent(context, ComicBookDetailActivity::class.java).putExtra(CHARACTER_ID, characterId)
        }
    }

}
