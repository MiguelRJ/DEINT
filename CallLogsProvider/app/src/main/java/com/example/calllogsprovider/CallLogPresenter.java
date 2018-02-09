package com.example.calllogsprovider;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;

/**
 * Created by usuario on 9/02/18.
 */

public class CallLogPresenter implements LoaderManager.LoaderCallbacks<Cursor>, CallLogsContract.Presenter {

    private CallLogsContract.View view;
    private final static int CALLLOG=0;

    public CallLogPresenter(CallLogsContract.View view){
        this.view = view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {

        CursorLoader cursorLoader = null;
        switch (id){
            case CALLLOG:
                String strOrder = CallLog.Calls.DATE+" DESC";
                String[] projection = {CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION, CallLog.Calls.TYPE,CallLog.Calls._ID}; //numero, fecha, duracion, tipo llamada
                cursorLoader = new CursorLoader(
                        view.getContext(),
                        Uri.parse(CallLog.CONTENT_URI+"/calls"),
                        projection,
                        null,
                        null,
                        strOrder
                        );
                break;
        }

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        view.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.swapCursor(null);
    }

    public void getCallLogs() {
        ((Activity)view.getContext()).getLoaderManager().restartLoader(CALLLOG,null,this);
    }
}
