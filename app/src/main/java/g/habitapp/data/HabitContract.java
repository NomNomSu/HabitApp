package g.habitapp.data;

import android.provider.BaseColumns;

public class HabitContract {
    //Necessary empty constructor
    private HabitContract() {}

    public static class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_NAME ="name";
        public final static String COLUMN_HABIT_SO_FAR = "so_far";
        public final static String COLUMN_HABIT_TARGET = "target";

    }
}
