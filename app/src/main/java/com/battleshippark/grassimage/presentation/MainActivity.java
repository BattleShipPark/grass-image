package com.battleshippark.grassimage.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.battleshippark.grassimage.BuildConfig;
import com.battleshippark.grassimage.R;
import com.battleshippark.grassimage.data.DaumSearchRepository;
import com.battleshippark.grassimage.data.NaverSearchRepository;
import com.battleshippark.grassimage.domain.DaumMapper;
import com.battleshippark.grassimage.domain.GetDaumResult;
import com.battleshippark.grassimage.domain.GetNaverResult;
import com.battleshippark.grassimage.domain.NaverMapper;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements UiListener {
    @BindView(R.id.engine_spinner)
    Spinner engineSpinner;
    @BindView(R.id.query_edit)
    EditText queryEdit;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_layout)
    View progressLayout;

    private MainPresenter presenter;

    private MainPresenter.Mode mode = MainPresenter.Mode.NAVER;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        initUI();
    }

    @Override
    protected void onDestroy() {
        presenter.release();
        super.onDestroy();
    }

    @Override
    public void update(SearchResult result) {
        adapter.setItems(result.items);
    }

    @Override
    public void showProgress() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public void showEmptyPage() {

    }

    @Override
    public void hideEmptyPage() {

    }

    @Override
    public void load() {
        presenter.load(mode, queryEdit.getText().toString());
    }

    @Override
    public void changeMode(MainPresenter.Mode mode) {
        this.mode = mode;
        load();
    }

    private void initData() {
        presenter = new MainPresenter(this,
                new GetNaverResult(new NaverSearchRepository(),
                        new GetNaverResult.Param(BuildConfig.NAVER_CLIENT_ID, BuildConfig.NAVER_CLIENT_SECRET),
                        Schedulers.io(), AndroidSchedulers.mainThread(), new NaverMapper()
                ),
                new GetDaumResult(new DaumSearchRepository(),
                        new GetDaumResult.Param(BuildConfig.DAUM_API_KEY),
                        Schedulers.io(), AndroidSchedulers.mainThread(), new DaumMapper()
                ),
                new Mapper());
        presenter.init();

        adapter = new MainAdapter(this);
    }

    private void initUI() {
        engineSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.changeMode(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        queryEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onQueryChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.span_count)));
        recyclerView.setAdapter(adapter);
    }
}
