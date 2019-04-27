package it.starksoftware.ssform.attach;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

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

                        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                        builder.setTitle(R.string.app_name)
                                .setMessage("Vuoi Eliminare l'allegato ?")
                                .setCancelable(false)
                                .setPositiveButton("Elimina", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        attachFiles.remove(position);
                                        setupAdapter();
                                    }
                                })
                                .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();

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
