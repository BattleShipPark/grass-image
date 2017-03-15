package com.battleshippark.grassimage.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        initUI();
    }

    @Override
    public void update(SearchResult result) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

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
    }

    private void initUI() {
        queryEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                presenter.onTextChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
