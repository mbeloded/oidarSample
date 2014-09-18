package com.oidar.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;

import com.oidar.model.Radio;
import com.oidar.util.MyLog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * A class for handling queries against the SQL database
 */
@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class SqlHandler {

    public static final String DATABASE_NAME = "WendlerizedDb";
    private static final int DATABASE_VERSION = 6;

    /**
     * Stats table *
     */
    private static final String DATABASE_TABLE_WENDLER_STATS = "wendler_stats";
    private static final String KEY_ROW_ID = "_id";
    private static final String KEY_TRAINING_PERCENTAGE = "working_percentage";
    private static final String KEY_1RM = "one_rm";
    private static final String KEY_ORDER = "order_in_week";
    private static final String KEY_INCREMENT = "increment";
    private static final String KEY_WEEK = "week";
    private static final String KEY_CYCLE = "cycle";
    private static final String KEY_CYCLE_NAME = "cycle_name";
    private static final String KEY_NAME = "name";
    private static final String KEY_SHOULD_DELOAD = "should_deload";

    /**
     * Percentage table *
     */
    private static final String DATABASE_TABLE_PERCENT = "wendler_percentages";
    private static final String KEY_WEEK_ONE = "percent_week_1";
    private static final String KEY_WEEK_TWO = "percent_week_2";
    private static final String KEY_WEEK_THREE = "percent_week_3";
    private static final String KEY_WEEK_FOUR = "percent_week_4";

    /**
     * Workout table *
     */
    private static final String DATABASE_TABLE_WENDLER_WORKOUT = "wendler_workout";
    private static final String KEY_WORKOUT_ID = "workout_id";
    private static final String KEY_INSERT_TIME = "insert_time";
    private static final String KEY_WORKOUT_YEAR = "workout_year";
    private static final String KEY_WORKOUT_MONTH = "workout_month";
    private static final String KEY_WORKOUT_DAY = "workout_day";
    private static final String KEY_WORKOUT_EXERCISE = "exercise";
    private static final String KEY_WORKOUT_REPS = "reps";
    private static final String KEY_WORKOUT_ONE_RM = "one_rm";
    private static final String KEY_WORKOUT_LAST_SET = "last_set_weight";
    private static final String KEY_WORKOUT_WEEK = "week";
    private static final String KEY_WORKOUT_CYCLE = "cycle";
    private static final String KEY_WORKOUT_NOTES = "notes";
    private static final String KEY_WORKOUT_WON = "workout_won";
    private static final String KEY_WORKOUT_COMPLETED = "workout_completed";

    /**
     * Extra workout table *
     */
    private static final String DATABASE_TABLE_WENDLER_EXTRA = "workout_extra";
    private static final String KEY_EXERCISE_NAME = "exercise_name";
    private static final String KEY_NUMBER_OF_SETS_OR_REPS_TO_BE_DONE = "nbr_of_sets";
    private static final String KEY_EXTRA_WEIGHT = "extra_weight";
    private static final String KEY_EXTRA_REPS_OR_SETS_COMPLETED = "extra_reps";
    private static final String KEY_EXTRA_EXERCISE_ID = "extra_exercise_id";

    /**
     * List of extra exercises, commented out ones are used but simply here for simplicity
     */
    private static final String DATABASE_TABLE_WENDLER_EXTRA_LIST = "workout_extra_list";
    //    private static final String KEY_EXERCISE_NAME = "exercise_name";
//    private static final String KEY_NUMBER_OF_SETS_OR_REPS_TO_BE_DONE = "nbr_of_sets";
//    private static final String KEY_EXTRA_WEIGHT = "extra_weight";
//    private static final String KEY_EXTRA_REPS_OR_SETS_COMPLETED = "extra_reps";
    private static final String KEY_EXTRA_PERCENTAGE_OF_MAIN_EXERCISE
            = "extra_percentage_of_main_exercise";
    private static final String KEY_EXTRA_ORDER_IN_LIST = "extra_order_in_list";
    private static final String KEY_EXTRA_MAIN_EXERCISE_NAME =
            "extra_main_exercise_name";
    private static final String KEY_IS_BBB = "is_bbb";

    private final Context mContext;
    private DbHelper mDbHelper;
    private SQLiteDatabase mDatabase;
    private boolean mIsOpen;

    /**
     * Constructor
     */
    public SqlHandler(Context context) {
        mContext = context;
    }

    /**
     * Open the connection to the SQLite database.
     */
    public void open() throws SQLException {
        if (mIsOpen && mDbHelper != null && mDatabase != null) {
            return;
        }
        mDbHelper = new DbHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
        mIsOpen = true;
    }

    /**
     * Close the connection to the database.
     */
    public void close() {
        if (mIsOpen && mDbHelper != null) {
            mDbHelper.close();
            mIsOpen = false;
        }
    }

    /**
     * Insert one rm for all exercises as well as the workout percentage.
     */
    public void insertOneRmAndWorkoutPercentage(double pressOneRm,
                                                double deadliftOneRm,
                                                double benchOneRm,
                                                double squatOneRm,
                                                int workoutPercentage) {
//        ContentValues cv = new ContentValues();
//        cv.put(KEY_1RM, pressOneRm);
//        cv.put(KEY_CYCLE, 1);
//        cv.put(KEY_CYCLE_NAME, 1);
//        cv.put(KEY_WEEK, 1);
//        cv.put(KEY_TRAINING_PERCENTAGE, workoutPercentage);
//        cv.put(KEY_NAME, Constants.EXERCISES[0]);
//        mDatabase.insert(DATABASE_TABLE_WENDLER_STATS, null, cv);
//
//        cv.clear();
//        cv.put(KEY_1RM, deadliftOneRm);
//        cv.put(KEY_CYCLE, 1);
//        cv.put(KEY_CYCLE_NAME, 1);
//        cv.put(KEY_WEEK, 1);
//        cv.put(KEY_TRAINING_PERCENTAGE, workoutPercentage);
//        cv.put(KEY_NAME, Constants.EXERCISES[1]);
//        mDatabase.insert(DATABASE_TABLE_WENDLER_STATS, null, cv);
//
//        cv.clear();
//        cv.put(KEY_1RM, benchOneRm);
//        cv.put(KEY_CYCLE, 1);
//        cv.put(KEY_CYCLE_NAME, 1);
//        cv.put(KEY_WEEK, 1);
//        cv.put(KEY_TRAINING_PERCENTAGE, workoutPercentage);
//        cv.put(KEY_NAME, Constants.EXERCISES[2]);
//        mDatabase.insert(DATABASE_TABLE_WENDLER_STATS, null, cv);
//
//        cv.clear();
//        cv.put(KEY_1RM, squatOneRm);
//        cv.put(KEY_CYCLE, 1);
//        cv.put(KEY_CYCLE_NAME, 1);
//        cv.put(KEY_WEEK, 1);
//        cv.put(KEY_TRAINING_PERCENTAGE, workoutPercentage);
//        cv.put(KEY_NAME, Constants.EXERCISES[3]);
//        mDatabase.insert(DATABASE_TABLE_WENDLER_STATS, null, cv);
    }

    /**
     * Insert the order of workouts.
     */
    public void insertExerciseOrder(int pressDay, int deadliftDay, int benchDay, int squatDay) {

        ContentValues cv = new ContentValues();

//        cv.put(KEY_ORDER, pressDay);
//        mDatabase.update(DATABASE_TABLE_WENDLER_STATS, cv, KEY_NAME + "=?",
//                new String[]{Constants.EXERCISES[0]});
//
//        cv.clear();
//        cv.put(KEY_ORDER, deadliftDay);
//        mDatabase.update(DATABASE_TABLE_WENDLER_STATS, cv, KEY_NAME + "=?",
//                new String[]{Constants.EXERCISES[1]});
//
//        cv.clear();
//        cv.put(KEY_ORDER, benchDay);
//        mDatabase.update(DATABASE_TABLE_WENDLER_STATS, cv, KEY_NAME + "=?",
//                new String[]{Constants.EXERCISES[2]});
//
//        cv.clear();
//        cv.put(KEY_ORDER, squatDay);
//        mDatabase.update(DATABASE_TABLE_WENDLER_STATS, cv, KEY_NAME + "=?",
//                new String[]{Constants.EXERCISES[3]});

    }

    /**
     * Insert the percentages for our workouts.
     */
    public void insertWeekPercentages(
            int[] weekOne, int[] weekTwo, int[] weekThree, int[] weekFour) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_WEEK_ONE, weekOne[0]);
        cv.put(KEY_WEEK_TWO, weekTwo[0]);
        cv.put(KEY_WEEK_THREE, weekThree[0]);
        cv.put(KEY_WEEK_FOUR, weekFour[0]);

        mDatabase.insert(DATABASE_TABLE_PERCENT, null, cv);

        cv.clear();
        cv.put(KEY_WEEK_ONE, weekOne[1]);
        cv.put(KEY_WEEK_TWO, weekTwo[1]);
        cv.put(KEY_WEEK_THREE, weekThree[1]);
        cv.put(KEY_WEEK_FOUR, weekFour[1]);

        mDatabase.insert(DATABASE_TABLE_PERCENT, null, cv);

        cv.clear();
        cv.put(KEY_WEEK_ONE, weekOne[2]);
        cv.put(KEY_WEEK_TWO, weekTwo[2]);
        cv.put(KEY_WEEK_THREE, weekThree[2]);
        cv.put(KEY_WEEK_FOUR, weekFour[2]);

        mDatabase.insert(DATABASE_TABLE_PERCENT, null, cv);
    }

    /**
     * Update the percentages of our workouts.
     */
    public void updatePercentages(int[] weekOne, int[] weekTwo, int[] weekThree, int[] weekFour) {
        ContentValues cv = new ContentValues();

        cv.put(KEY_WEEK_ONE, weekOne[0]);
        cv.put(KEY_WEEK_TWO, weekTwo[0]);
        cv.put(KEY_WEEK_THREE, weekThree[0]);
        cv.put(KEY_WEEK_FOUR, weekFour[0]);

        mDatabase.update(DATABASE_TABLE_PERCENT, cv, KEY_ROW_ID + "=?", new String[]{"1"});

        cv.clear();
        cv.put(KEY_WEEK_ONE, weekOne[1]);
        cv.put(KEY_WEEK_TWO, weekTwo[1]);
        cv.put(KEY_WEEK_THREE, weekThree[1]);
        cv.put(KEY_WEEK_FOUR, weekFour[1]);

        mDatabase.update(DATABASE_TABLE_PERCENT, cv, KEY_ROW_ID + "=?", new String[]{"2"});

        cv.clear();
        cv.put(KEY_WEEK_ONE, weekOne[2]);
        cv.put(KEY_WEEK_TWO, weekTwo[2]);
        cv.put(KEY_WEEK_THREE, weekThree[2]);
        cv.put(KEY_WEEK_FOUR, weekFour[2]);

        mDatabase.update(DATABASE_TABLE_PERCENT, cv, KEY_ROW_ID + "=?", new String[]{"3"});
    }

    /**
     * Insert the initial increments for workouts.
     */
    public void insertIncrements(double pressIncrement,
                                 double deadliftIncrement,
                                 double benchIncrement,
                                 double squatIncrement) {

        ContentValues cv = new ContentValues();
        cv.put(KEY_INCREMENT, pressIncrement);
//        mDatabase.update(DATABASE_TABLE_WENDLER_STATS, cv, KEY_NAME + "=?",
//                new String[]{Constants.EXERCISES[0]});
//
//        cv.clear();
//        cv.put(KEY_INCREMENT, deadliftIncrement);
//        mDatabase.update(DATABASE_TABLE_WENDLER_STATS, cv, KEY_NAME + "=?",
//                new String[]{Constants.EXERCISES[1]});
//
//        cv.clear();
//        cv.put(KEY_INCREMENT, benchIncrement);
//        mDatabase.update(DATABASE_TABLE_WENDLER_STATS, cv, KEY_NAME + "=?",
//                new String[]{Constants.EXERCISES[2]});
//
//        cv.clear();
//        cv.put(KEY_INCREMENT, squatIncrement);
//        mDatabase.update(DATABASE_TABLE_WENDLER_STATS, cv, KEY_NAME + "=?",
//                new String[]{Constants.EXERCISES[3]});
    }

    /**
     * Return if the data base is initialized.
     */
    public boolean isInitialized() {
        Cursor c = null;
        try {
            c = mDatabase.query(DATABASE_TABLE_WENDLER_STATS, new String[]{KEY_ROW_ID},
                    null, null, null, null, null);
            return c != null && c.moveToFirst();
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    /**
     * Return a list of workouts for a given week.
     */
    public ArrayList<Radio> getRadiosForList(int week) {
        ArrayList<Radio> list = new ArrayList<Radio>();

//        String[] names = getExerciseNamesInOrder();
//
//        for (String name : names) {
//            int cycle = getCurrentCycle(name);
//            int cycleName = getCurrentCycleName(name);
//            int id = exerciseHasBeenDone(name, week, cycle);
//
//            if (id != -1) {
//                boolean isWorkoutComplete = isWorkoutComplete(id);
//                list.add(new Radio(name, StringHelper.getTranslatableName(mContext, name),
//                        isWorkoutComplete, exerciseWon(id),
//                        week, cycle, cycleName, id, getMainExerciseForWorkout(isWorkoutComplete, id)
//                        , getExtraExerciseForWorkout(name, id, isWorkoutComplete),
//                        getInsertTimeForId(id), getTimeForWorkout(id), getNotesForWorkout(id)));
//            } else {
//                list.add(new Radio(name, StringHelper.getTranslatableName(mContext, name),
//                        week, cycle, cycleName, -1));
//            }
//        }
        return list;
    }

    /**
     * Return the names of our exercises in order converted to references a string item
     */
    public String[] getExerciseNamesInOrder() {
        String[] arr = new String[4];

        Cursor cursor = null;
        try {
            cursor = mDatabase.query(DATABASE_TABLE_WENDLER_STATS, new String[]{KEY_NAME},
                    null, null, null, null, KEY_ORDER + " ASC ");
            int i = 0;
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    arr[i] = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                    i++;
                } while (cursor.moveToNext() && i < arr.length);
            }
            return arr;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * Delete an old workout.
     */
    public void deleteRadio(Radio radio) {
//        if (isWorkoutLatest(radio)) {
//
//            int week = workout.getWeek();
//            int cycle = workout.getCycle();
//            int cycleName = workout.getCycleDisplayName();
//            double weight = workout.getMainExercise().getWeight();
//
//            if (workout.isWon() && workout.getWeek() == 4) {
//                weight = getOneRmForExercise(workout.getName());
//                weight -= getIncrement(workout.getName());
//            }
//            updateWithStats(workout.getName(), cycle, week, weight, cycleName);
//            setWorkoutShouldDeload(workout.getName(), getWorkoutShouldDeload(workout));
//        }
//
//        mDatabase.delete(DATABASE_TABLE_WENDLER_WORKOUT, KEY_WORKOUT_ID + "=?",
//                new String[]{String.valueOf(workout.getWorkoutId())});
//        mDatabase.delete(DATABASE_TABLE_WENDLER_EXTRA, KEY_WORKOUT_ID + "=?",
//                new String[]{String.valueOf(workout.getWorkoutId())});
    }

    /**
     * Store a workout.
     */
    public void storeRadio(Radio radio) {
//        storeMainExercise(workout, workout.isComplete());
//        storeAdditionalExercise(workout.getWorkoutId(), workout.getAdditionalExercises());
//
//        if (isWorkoutLatest(workout)) {
//            updateWorkoutStats(workout, true);
//        }
    }

    /**
     * Return if the workout should deload.
     */
    public boolean doDeload(Radio radio) {

//        if (workout.getWeek() < 4) {
//            return false;
//        }
//        Cursor cursor = null;
//        try {
//            cursor = mDatabase.query(DATABASE_TABLE_WENDLER_STATS, null,
//                    KEY_NAME + "=? AND " + KEY_SHOULD_DELOAD + "=?",
//                    new String[]{workout.getName(), "1"}, null, null, null);
//            boolean bool = cursor != null && cursor.getCount() == 1;
//
//            if (bool) {
//                setWorkoutShouldDeload(workout.getName(), false);
//            }
//
//            return bool;
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }

        return false;

    }

    /**
     * Restore the database from a save file.
     */
    public void restoreDbFromFile(File file) {
        mDbHelper.restoreDatabase(file);
    }

    /**
     * Return if a given workout is complete.
     */
    private boolean isWorkoutComplete(int id) {
        Cursor c = null;
        try {
            c = mDatabase.query(DATABASE_TABLE_WENDLER_WORKOUT, null,
                    KEY_WORKOUT_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
            return c != null
                    && c.moveToFirst()
                    && c.getInt(c.getColumnIndex(KEY_WORKOUT_COMPLETED)) == 1;
        } finally {
            if (c != null) {
                c.close();
            }
        }

    }


    /**
     * Return if a given workout was won.
     */
    private boolean exerciseWon(int id) {
        Cursor c = null;
        try {
            c = mDatabase.query(DATABASE_TABLE_WENDLER_WORKOUT, null,
                    KEY_WORKOUT_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
            return c != null
                    && c.moveToFirst()
                    && c.getInt(c.getColumnIndex(KEY_WORKOUT_WON)) == 1;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }

    /**
     * Return if a given workout for a specific cycle and week has been done.
     */
    private int exerciseHasBeenDone(String sqlExerciseName, int week, int cycle) {
        Cursor c = null;
        try {
            c = mDatabase.query(DATABASE_TABLE_WENDLER_WORKOUT, new String[]{KEY_WORKOUT_ID},
                    KEY_WORKOUT_EXERCISE + "=? AND " + KEY_CYCLE + "=? AND " + KEY_WEEK + "=?",
                    new String[]{sqlExerciseName, String.valueOf(cycle), String.valueOf(week)},
                    null, null, null);
            if (c != null && c.moveToFirst()) {
                return c.getInt(c.getColumnIndex(KEY_WORKOUT_ID));
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return -1;
    }

    /**
     * Return the current cycle for a given exercise.
     */
    private int getCurrentCycle(String sqlExerciseName) {
        Cursor c = null;
        try {
            c = mDatabase.query(DATABASE_TABLE_WENDLER_STATS, new String[]{KEY_CYCLE},
                    KEY_NAME + "=?", new String[]{sqlExerciseName}, null, null, null);
            if (c != null && c.moveToFirst()) {
                return c.getInt(c.getColumnIndex(KEY_CYCLE));
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return 1;
    }

    /**
     * Return the current cycle name for a given exercise.
     */
    private int getCurrentCycleName(String sqlExerciseName) {
        Cursor c = null;
        try {
            c = mDatabase.query(DATABASE_TABLE_WENDLER_STATS, new String[]{KEY_CYCLE_NAME},
                    KEY_NAME + "=?", new String[]{sqlExerciseName}, null, null, null);
            if (c != null && c.moveToFirst()) {
                return c.getInt(c.getColumnIndex(KEY_CYCLE_NAME));
            }
        } finally {
            if (c != null) {
                c.close();
            }
        }
        return 1;
    }


    /**
     * Return the insert time for a given workout id.
     */
    private long getInsertTimeForId(int workoutId) {
        Cursor cursor = null;
        try {
            cursor = mDatabase.query(DATABASE_TABLE_WENDLER_WORKOUT,
                    new String[]{KEY_INSERT_TIME}, KEY_WORKOUT_ID + "=?",
                    new String[]{String.valueOf(workoutId)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                return Long.parseLong(cursor.getString(cursor.getColumnIndex(KEY_INSERT_TIME)));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return System.currentTimeMillis();
    }

    /**
     * Return the time the workout was completed for a given workout id.
     */
    private Time getTimeForWorkout(int workoutId) {
        Cursor cursor = null;
        try {
            cursor = mDatabase.query(DATABASE_TABLE_WENDLER_WORKOUT,
                    new String[]{KEY_WORKOUT_YEAR, KEY_WORKOUT_MONTH, KEY_WORKOUT_DAY},
                    KEY_WORKOUT_ID + "=?", new String[]{String.valueOf(workoutId)}, null, null,
                    null);

            Time time = new Time();
            time.setToNow();
            if (cursor != null && cursor.moveToFirst()) {
                time.year =
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_WORKOUT_YEAR)));
                time.month = Integer.parseInt(
                        cursor.getString(cursor.getColumnIndex(KEY_WORKOUT_MONTH)));
                time.monthDay =
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_WORKOUT_DAY)));
            }
            return time;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * Return the notes for a given workout id.
     */
    private String getNotesForWorkout(int workoutId) {
        Cursor cursor = null;
        try {
            cursor = mDatabase.query(DATABASE_TABLE_WENDLER_WORKOUT,
                    new String[]{KEY_WORKOUT_NOTES}, KEY_WORKOUT_ID + "=?",
                    new String[]{String.valueOf(workoutId)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndex(KEY_WORKOUT_NOTES));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return "";
    }

    /**
     * Set a given workout to deload when next possible.
     */
    private void setWorkoutShouldDeload(String workoutName, boolean value) {

        ContentValues cv = new ContentValues();

        cv.put(KEY_SHOULD_DELOAD, value ? 1 : 0);
        mDatabase.update(DATABASE_TABLE_WENDLER_STATS, cv, KEY_NAME + "=?",
                new String[]{workoutName});
    }

    /**
     * Update a exercise with given stats.
     */
    private void updateWithStats(
            String name, int cycle, int week, double weight, int cycleName) {
        if (cycle < 1) {
            cycle = 1;
            cycleName = 1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_WORKOUT_ONE_RM, weight);
        contentValues.put(KEY_WEEK, String.valueOf(week));
        contentValues.put(KEY_CYCLE, String.valueOf(cycle));
        contentValues.put(KEY_CYCLE_NAME, String.valueOf(cycleName));
        mDatabase.update(
                DATABASE_TABLE_WENDLER_STATS, contentValues, KEY_NAME + "=?", new String[]{name});
    }

    /**
     * Inner class for handling the connection to the SQLiteDatabase.
     */
    private static class DbHelper extends SQLiteOpenHelper {

        private final Context mContext;

        /**
         * Constructor.
         */
        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mContext = context;
        }

        /**
         * Called when the helper is created.
         */
        @Override
        public void onCreate(SQLiteDatabase db) {

            /**
             * Table for managing all the percentages for the 4 weeks.
             */
            db.execSQL("CREATE TABLE " + DATABASE_TABLE_PERCENT + " ("
                    + KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_WEEK_ONE + " INTEGER NOT NULL, "
                    + KEY_WEEK_TWO + " INTEGER NOT NULL, "
                    + KEY_WEEK_THREE + " INTEGER NOT NULL, "
                    + KEY_WEEK_FOUR + " INTEGER NOT NULL);");

            /**
             * Table for managing the stats for each workout.
             */
            db.execSQL("CREATE TABLE " + DATABASE_TABLE_WENDLER_STATS + " (" +
                    KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_TRAINING_PERCENTAGE + " INTEGER NOT NULL, " +
                    KEY_1RM + " INTEGER NOT NULL, " +
                    KEY_INCREMENT + " TEXT, " +
                    KEY_WEEK + " TEXT, " +
                    KEY_CYCLE + " TEXT, " +
                    KEY_CYCLE_NAME + " TEXT, " +
                    KEY_ORDER + " INTEGER, " +
                    KEY_SHOULD_DELOAD + " INTEGER, " +
                    KEY_NAME + " TEXT);");

            /**
             * Table for saving the main exercise of a workout.
             */
            db.execSQL("CREATE TABLE " + DATABASE_TABLE_WENDLER_WORKOUT + " (" +
                    KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_INSERT_TIME + " TEXT NOT NULL, " +
                    KEY_WORKOUT_YEAR + " INTEGER NOT NULL, " +
                    KEY_WORKOUT_MONTH + " INTEGER NOT NULL, " +
                    KEY_WORKOUT_DAY + " INTEGER NOT NULL, " +
                    KEY_WORKOUT_EXERCISE + " TEXT NOT NULL, " +
                    KEY_WORKOUT_REPS + " TEXT NOT NULL, " +
                    KEY_WORKOUT_ONE_RM + " TEXT NOT NULL, " +
                    KEY_WORKOUT_LAST_SET + " TEXT NOT NULL, " +
                    KEY_WORKOUT_WEEK + " TEXT NOT NULL, " +
                    KEY_WORKOUT_CYCLE + " TEXT NOT NULL, " +
                    KEY_CYCLE_NAME + " TEXT NOT NULL, " +
                    KEY_WORKOUT_ID + " INTEGER NOT NULL, " +
                    KEY_WORKOUT_NOTES + " TEXT NOT NULL, " +
                    KEY_WORKOUT_COMPLETED + " INTEGER NOT NULL, " +
                    KEY_TRAINING_PERCENTAGE + " INTEGER NOT NULL, " +
                    KEY_WORKOUT_WON + " INTEGER);");

            /**
             * Table for managing extra exercises for a specific workout.
             */
            db.execSQL("CREATE TABLE " + DATABASE_TABLE_WENDLER_EXTRA + " (" +
                    KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_WORKOUT_ID + " INTEGER NOT NULL, " +
                    KEY_EXERCISE_NAME + " TEXT NOT NULL, " +
                    KEY_EXTRA_EXERCISE_ID + " INTEGER NOT NULL, " +
                    KEY_NUMBER_OF_SETS_OR_REPS_TO_BE_DONE + " TEXT NOT NULL, " +
                    KEY_EXTRA_WEIGHT + " TEXT NOT NULL, " +
                    KEY_EXTRA_PERCENTAGE_OF_MAIN_EXERCISE + " TEXT, " +
                    KEY_EXTRA_MAIN_EXERCISE_NAME + " TEXT, " +
                    KEY_EXTRA_REPS_OR_SETS_COMPLETED + " TEXT NOT NULL);");

            /**
             * Table for storing all extra exercises used so they can be reused.
             */
            db.execSQL("CREATE TABLE " + DATABASE_TABLE_WENDLER_EXTRA_LIST + " (" +
                    KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KEY_EXERCISE_NAME + " TEXT NOT NULL, " +
                    KEY_EXTRA_EXERCISE_ID + " TEXT NOT NULL, " +
                    KEY_WORKOUT_EXERCISE + " TEXT NOT NULL, " +
                    KEY_NUMBER_OF_SETS_OR_REPS_TO_BE_DONE + " TEXT NOT NULL, " +
                    KEY_EXTRA_WEIGHT + " TEXT, " +
                    KEY_EXTRA_PERCENTAGE_OF_MAIN_EXERCISE + " TEXT, " +
                    KEY_EXTRA_MAIN_EXERCISE_NAME + " TEXT, " +
                    KEY_INCREMENT + "TEXT, " +
                    KEY_IS_BBB + "INTEGER, " +
                    KEY_EXTRA_ORDER_IN_LIST + " TEXT NOT NULL);");
        }

        /**
         * Called when the database is upgraded.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL("ALTER TABLE " + DATABASE_TABLE_WENDLER_EXTRA_LIST + " ADD COLUMN " +
                        KEY_IS_BBB + " INTEGER DEFAULT 0");
            } catch (Exception e) {
                MyLog.v("Failed to add column " + KEY_IS_BBB + " in " +
                        DATABASE_TABLE_WENDLER_EXTRA_LIST);
            }

            try {
                db.execSQL("ALTER TABLE " + DATABASE_TABLE_WENDLER_STATS + " ADD COLUMN "
                        + KEY_SHOULD_DELOAD + " INTEGER DEFAULT 0");
            } catch (Exception e) {
                MyLog.v("Failed to add column " + KEY_SHOULD_DELOAD + " in " +
                        DATABASE_TABLE_WENDLER_STATS);
            }

            try {
                db.execSQL("ALTER TABLE " + DATABASE_TABLE_WENDLER_STATS + " ADD COLUMN "
                        + KEY_CYCLE_NAME + " INTEGER DEFAULT 0");
            } catch (Exception e) {
                MyLog.v("Failed to add column " + KEY_CYCLE_NAME + " in " +
                        DATABASE_TABLE_WENDLER_STATS);
            }

            try {
                db.execSQL("ALTER TABLE " + DATABASE_TABLE_WENDLER_WORKOUT + " ADD COLUMN "
                        + KEY_CYCLE_NAME + " INTEGER DEFAULT 0");
            } catch (Exception e) {
                MyLog.v("Failed to add column " + KEY_CYCLE_NAME + " in " +
                        DATABASE_TABLE_WENDLER_WORKOUT);
            }
        }

        /**
         * Restore the database from a file.
         */
        @SuppressWarnings("ConstantConditions")
        public void restoreDatabase(File file) {
            close();
            File oldDb = new File(String.valueOf(mContext.getDatabasePath(getDatabaseName())));
            if (file.exists()) {
                try {
                    copyFile(new FileInputStream(file), new FileOutputStream(oldDb));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                getWritableDatabase().close();
            }
        }

        /**
         * Copy a given file.
         */
        private static void copyFile(FileInputStream fromFile, FileOutputStream toFile)
                throws IOException {
            FileChannel fromChannel = null;
            FileChannel toChannel = null;
            try {
                fromChannel = fromFile.getChannel();
                toChannel = toFile.getChannel();
                fromChannel.transferTo(0, fromChannel.size(), toChannel);
            } finally {
                try {
                    if (fromChannel != null) {
                        fromChannel.close();
                    }
                } finally {
                    if (toChannel != null) {
                        toChannel.close();
                    }
                }
            }
        }
    }
}
