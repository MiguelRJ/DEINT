package com.example.calllogsprovider;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity implements CallLogsContract.View {

    private CallLogsContract.Presenter presenter;
    private CallLogAdapter adapter;
    private ListView lw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new CallLogAdapter(this);
        presenter = new CallLogPresenter(this);
        lw = findViewById(android.R.id.list);
        lw.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getCallLogs();
    }

    @Override
    public void swapCursor(Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
