package g.habitapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import g.habitapp.data.HabitDbHelper;
import g.habitapp.data.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mHabitHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHabitHelper = new HabitDbHelper(this);
        insertHabit();
        readHabit();

    }

    private void insertHabit() {
        //insert some dummy data
        SQLiteDatabase db = mHabitHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME,"Do not eat sweets");
        values.put(HabitEntry.COLUMN_HABIT_SO_FAR,0 );
        values.put(HabitEntry.COLUMN_HABIT_TARGET,25 );
        db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    private void readHabit() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mHabitHelper.getReadableDatabase();
        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_SO_FAR,
                HabitEntry.COLUMN_HABIT_TARGET};

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        try {
            Log.d("Database writer: ", (HabitEntry._ID + " - " +
                    HabitEntry.COLUMN_HABIT_NAME + " - " +
                    HabitEntry.COLUMN_HABIT_SO_FAR + " - " +
                    HabitEntry.COLUMN_HABIT_TARGET + "\n"));

            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int soFarColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_SO_FAR);
            int targetColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_TARGET);
            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentSoFar = cursor.getInt(soFarColumnIndex);
                int currentTarget = cursor.getInt(targetColumnIndex);

                Log.d("Appender: ", ("\n" + currentID + " - " +
                        currentName + " - " +
                        currentSoFar + " - " +
                        currentTarget ));
            }
        } finally {
            cursor.close();
        }
    }
}
