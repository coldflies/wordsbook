package com.example.wordsbook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;



public class myContentProvider extends ContentProvider
{
    SQLiteDatabase database;
    private final int DIR = 0;
    private final int ITEM = 1;
    private final String AUTHORITY = "com.example.WordsBook.provider";
    private static UriMatcher uriMatcher;

    public myContentProvider(){

    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        int deletedRows = 0;
        deletedRows = database.delete("words", selection, selectionArgs);
        return deletedRows;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        long newId = database.insert("words", null, values);
        Uri returnUri = Uri.parse("content://" + AUTHORITY + "Database/" + newId);
        return returnUri;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder)
    {
        Cursor cursor = null;
        cursor = database.query("words", projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        int updateRows = 0;
        updateRows = database.update("words", values, selection, selectionArgs);

        return updateRows;
    }
}
