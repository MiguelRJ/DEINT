package com.example.calllogsprovider;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by usuario on 9/02/18.
 */

class CallLogAdapter extends CursorAdapter {

    public SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");

    public CallLogAdapter(Context context) {
        super(context, null,CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER); // Flag para notificar cambios
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_call,viewGroup,false);
        CallLogHolder holder = new CallLogHolder();
        holder.txvNumber = view.findViewById(R.id.txvNumber);
        holder.txvDate = view.findViewById(R.id.txvDate);
        holder.txvType = view.findViewById(R.id.txvType);
        holder.txvDuration = view.findViewById(R.id.txvDuration);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        CallLogHolder callLogHolder = (CallLogHolder) view.getTag();
        callLogHolder.txvNumber.setText(cursor.getString(0));
        callLogHolder.txvDate.setText(
                sdf.format(new Date(Long.parseLong(cursor.getString(1))))
        );
        callLogHolder.txvDuration.setText(cursor.getString(2));

        String strType = null;
        int type = Integer.parseInt(cursor.getString(3));
        switch (type) {
            case CallLog.Calls.INCOMING_TYPE:
                strType = "incoming";
                break;
            case CallLog.Calls.OUTGOING_TYPE:
                strType = "outgoing";
                break;
            case CallLog.Calls.MISSED_TYPE:
                strType = "missed";
                break;
            case CallLog.Calls.VOICEMAIL_TYPE:
                strType = "voicemail";
                break;
            case CallLog.Calls.REJECTED_TYPE:
                strType = "rejected";
                break;
            case CallLog.Calls.BLOCKED_TYPE:
                strType = "blocked";
                break;
        }

        callLogHolder.txvType.setText(strType);
    }

    private class CallLogHolder {
        TextView txvNumber;
        TextView txvDate;
        TextView txvType;
        TextView txvDuration;
    }

}
