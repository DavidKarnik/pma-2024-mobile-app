package com.example.hraj.handlers;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hraj.R;

public class SearchHandler {
    private Context context;

    public SearchHandler(Context context) {
        this.context = context;
    }

    public void showSearchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.search_title));

        final EditText input = new EditText(context);
        builder.setView(input);

        builder.setPositiveButton(context.getString(R.string.search), (dialog, which) -> {
            String query = input.getText().toString();
            searchFor(query);
        });
        builder.setNegativeButton(context.getString(R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void searchFor(String query) {
        Toast.makeText(context, context.getString(R.string.searching) + ": " + query, Toast.LENGTH_SHORT).show();
    }
}
