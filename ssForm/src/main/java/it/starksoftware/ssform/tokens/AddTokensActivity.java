package it.starksoftware.ssform.tokens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.TokensAdapter;
import it.starksoftware.ssform.adapter.TokensChipAdapter;
import it.starksoftware.ssform.adapter.TokensSearchAdapter;
import it.starksoftware.ssform.model.FormTokenObject;
import it.starksoftware.ssform.model.TokesTags;

public class AddTokensActivity extends AppCompatActivity {

    private ActionBar actionBar;
    public ArrayList<FormTokenObject> datas;
    //private EditText etSearch;
    private ListView lvList;
    private TokensAdapter adapter;
    private TokensSearchAdapter simpleSearchAdapter;

    public ArrayList<TokesTags> tokensView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tokens);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        // etSearch = this.findViewById(R.id.etSearch);
        lvList = this.findViewById(R.id.lvList);

        //DATA_SOURCE
        Bundle extras = getIntent().getExtras();

        if (savedInstanceState == null) {
            if (extras != null) {
                datas = (ArrayList<FormTokenObject>) extras.get("DATA_SOURCE");
            }
        }

        if (actionBar != null) {
            actionBar.setTitle("Tokens");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            actionBar.setDisplayShowTitleEnabled(true);
        }

        setAdapter();

    }

    public void setAdapter() {
        tokensView = new ArrayList<>();
        adapter = new TokensChipAdapter(datas);
        simpleSearchAdapter = new TokensSearchAdapter(this, adapter);
        lvList.setAdapter(simpleSearchAdapter);
    }

    public void notifyDataSetChanged() {
        refreshFlexbox();
        //simpleSearchAdapter.notifyDataSetChanged();
    }

    private void refreshFlexbox() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tokens_picker_menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            finishPickAttach();
            return true;
        } else if (id == R.id.menu_check) {
            finishPickAttach();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void finishPickAttach() {



        Intent data = new Intent();
        data.putExtra("TOKENS", ((TokensChipAdapter) adapter).getTokens());
        setResult(7715, data);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

}
