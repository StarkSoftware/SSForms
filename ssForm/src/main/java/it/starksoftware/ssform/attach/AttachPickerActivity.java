package it.starksoftware.ssform.attach;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import it.starksoftware.ssform.R;
import it.starksoftware.ssform.adapter.FormAttachAdapter;
import it.starksoftware.ssform.file_explorer.FileExplorerActivity;
import it.starksoftware.ssform.helper.RecyclerItemClickListener;
import it.starksoftware.ssform.helper.SimpleDividerItemDecoration;

public class AttachPickerActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private final int RESULT_CODE_RELOAD = 1;
    public RecyclerView recyclerView;
    public FormAttachAdapter mAdapter;
    private ArrayList<String> attachFiles;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attach);
        Intent intent = getIntent();
        if (intent == null || intent.getExtras() == null) {
            finish();
            return;
        }
        ctx = AttachPickerActivity.this;
        attachFiles = new ArrayList<String>();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Allegati");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            actionBar.setDisplayShowTitleEnabled(true);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            attachFiles = new ArrayList<String>();
            attachFiles = (ArrayList<String>)extras.getSerializable("ArrayCurrentAttach");
            if (attachFiles.size() > 0)
                setupAdapter();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.attach_picker_menu_main, menu);
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
            return true;
        } else if (id == R.id.menu_attach) {
            captureFileWithPermission();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void captureFileWithPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (rc == PackageManager.PERMISSION_GRANTED) {
                captureFiles();
            } else {
                captureFileWithPermission();
            }
        } else {
            captureFiles();
        }
    }

    public void captureFiles()
    {
        Intent intent = new Intent(ctx, FileExplorerActivity.class);
        startActivityForResult(intent, RESULT_CODE_RELOAD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {

            if (data != null) {
                ArrayList<String> list = (ArrayList<String>) data.getExtras().getSerializable("ArrayList");
                for (int p = 0; p < list.size(); p++)
                    attachFiles.add(list.get(p));
                setupAdapter();
            }
        }
    }

    public void finishPickAttach() {
        Intent data = new Intent();
        data.putExtra("ArrayList", attachFiles);
        setResult(RESULT_OK, data);
        finish();
    }
    public void setupAdapter()
    {
        mAdapter = new FormAttachAdapter(attachFiles, AttachPickerActivity.this);
        if (attachFiles != null)
            mAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(ctx));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(ctx, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {

                        new SweetAlertDialog(ctx, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("Attach")
                                .setContentText("Vuoi Eliminare l'allegato ?")
                                .setConfirmText("Elimina")
                                .setCancelText("Annulla")
                                .showCancelButton(true)
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.cancel();
                                    }
                                })
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {

                                        attachFiles.remove(position);
                                        setupAdapter();
                                        sDialog.cancel();
                                    }
                                })
                                .show();

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }


    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finishPickAttach();
        super.onBackPressed();
    }


}
