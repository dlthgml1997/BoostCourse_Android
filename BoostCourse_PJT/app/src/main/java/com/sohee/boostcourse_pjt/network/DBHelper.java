package com.sohee.boostcourse_pjt.network;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.sohee.boostcourse_pjt.ui.movie.item.MovieDetailItem;
import com.sohee.boostcourse_pjt.ui.movie.item.MovieItem;

import java.util.ArrayList;

public class DBHelper {
    private static final String TAG = "DBHelper";

    private static SQLiteDatabase database;

    private static String createTableOutlineSql = "create table if not exists outline"
            + "(" +
            "    _id integer PRIMARY KEY autoincrement, " +
            "    id integer, " +
            "    title text, " +
            "    title_eng text, " +
            "    date_value text, " +
            "    user_rating float, " +
            "    audience_rating float, " +
            "    reviewer_rating float, " +
            "    reservation_rate float, " +
            "    reservation_grade integer, " +
            "    grade integer, " +
            "    thumb text, " +
            "    image text" +
            ")";

    private static String createTableInlineSql = "create table if not exists inline"
            + "(" +
            "    _id integer PRIMARY KEY autoincrement, " +
            "    id integer, " +
            "    title text UNIQUE, " +
            "    date_value text, " +
            "    user_rating float, " +
            "    audience_rating float, " +
            "    reviewer_rating float, " +
            "    reservation_rate float, " +
            "    reservation_grade integer, " +
            "    grade integer, " +
            "    thumb text, " +
            "    image text, " +
            "    photos text, " +
            "    videos text, " +
            "    outlinks text, " +
            "    genre text, " +
            "    duration integer, " +
            "    audience integer, " +
            "    synopsis text, " +
            "    director text, " +
            "    actor text, " +
            "    like_value integer, " +
            "    dislike integer" +
            ")";

    /*
     * 디비를 엽니다.
     */

    public static void openDatabase(Context context, String databaseName) {
        println("openDatabase() 호출 됨.");

        try {
            database = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);

            if (database != null) {
                println("데이터베이스 " + databaseName + " 오픈됨.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * 테이블을 생성합니다.
     */

    public static void createTable(String tableName) {
        println("createTable 호출됨 : " + tableName);

        if (database != null) {
            //필요시 테이블 삭제
//            database.execSQL("drop table if exists " + tableName);
            switch (tableName) {
                default:
                case "outline":
                    database.execSQL(createTableOutlineSql);
                    println("outline 테이블 생성 요청됨.");
                    break;

                case "inline":
                    database.execSQL(createTableInlineSql);
                    println("inline 테이블 생성 요청됨.");

            }
        } else {
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }

    /*
     * 오프라인시 데이터를 디비에서 불러옵니다.
     */
    public static ArrayList<?> selectTable(String tableName) {
        println("selectData() 호출됨.");
        Cursor cursor;
        String sql;
        int i = 0;

        if (database != null) {
            switch (tableName) {
                default:
                case "outline":
                    sql =
                            "select id, title, title_eng, date_value, user_rating, audience_rating, reviewer_rating, reservation_rate, reservation_grade, grade, thumb, image from "
                                    + tableName;

                    cursor = database.rawQuery(sql, null);

                    println("outline 조회된 데이터 개수 : " + cursor.getCount());

                    ArrayList<MovieItem> items = new ArrayList<MovieItem>();

                    i = 0;
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(cursor.getInt(0));
                        String title = cursor.getString(1);
                        String title_eng = cursor.getString(2);
                        String date = cursor.getString(3);
                        Float user_rating = cursor.getFloat(4);
                        Float audience_rating = cursor.getFloat(5);
                        Float reviewer_rating = cursor.getFloat(6);
                        Float reservation_rate = cursor.getFloat(7);
                        int reservation_grade = cursor.getInt(8);
                        int grade = cursor.getInt(9);
                        String thumb = cursor.getString(10);
                        String image = cursor.getString(11);

                        MovieItem movieItem = new MovieItem(i, id, image, title, title_eng, audience_rating, date, user_rating, reviewer_rating, reservation_rate, reservation_grade, grade, thumb);

                        items.add(movieItem);
                        i++;
                    }

                    println("#" + i + " ->" + items.toString());
                    cursor.close();
                    return items;

                case "inline":
                    sql = "select id, title, date_value, user_rating, audience_rating, reviewer_rating, reservation_rate, reservation_grade, grade, thumb, image, photos, videos, outlinks, genre, duration, audience, synopsis, director, actor, like_value, dislike from "
                            + tableName;


                    cursor = database.rawQuery(sql, null);

                    println("inline 조회된 데이터 개수 : " + cursor.getCount());

                    ArrayList<MovieDetailItem> DetailItems = new ArrayList<MovieDetailItem>();

                    i = 0;
                    while (cursor.moveToNext()) {
                        int id = cursor.getInt(cursor.getInt(0));
                        String title = cursor.getString(1);
                        String date = cursor.getString(2);
                        Float user_rating = cursor.getFloat(3);
                        Float audience_rating = cursor.getFloat(4);
                        Float reviewer_rating = cursor.getFloat(5);
                        Float reservation_rate = cursor.getFloat(6);
                        int reservation_grade = cursor.getInt(7);
                        int grade = cursor.getInt(8);
                        String thumb = cursor.getString(9);
                        String image = cursor.getString(10);
                        String photos = cursor.getString(11);
                        String videos = cursor.getString(12);
                        String outlinks = cursor.getString(13);
                        String genre = cursor.getString(14);
                        int duration = cursor.getInt(15);
                        int audience = cursor.getInt(16);
                        String synopsis = cursor.getString(17);
                        String director = cursor.getString(18);
                        String actor = cursor.getString(19);
                        int like = cursor.getInt(20);
                        int dislike = cursor.getInt(21);

                        MovieDetailItem movieDetailItem = new MovieDetailItem(i, id, title, date, user_rating, audience_rating, reviewer_rating, reservation_rate, reservation_grade, grade, thumb, image, photos, videos, outlinks, genre, duration, audience, synopsis, director, actor, like, dislike);

                        DetailItems.add(movieDetailItem);
                        i++;
                    }

                    println("#" + i + " ->" + DetailItems.toString());
                    cursor.close();
                    return DetailItems;
            }


        }
        return null;
    }


    /*
     * 서버 통신 시에 데이터를 디비에 넣습니다.
     * 영화 목록
     */
    public static void insertOutlineData(ArrayList<MovieItem> items) {
        for (int i = 0; i < items.size(); i++) {
            String sql = "insert into outline(id, title, title_eng, date_value, user_rating, audience_rating, reviewer_rating, reservation_rate, reservation_grade, grade, thumb, image) " +
                    "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Object[] params = {items.get(i).getId(), items.get(i).getTitle(), items.get(i).getTitle_eng(), items.get(i).getDate(), items.get(i).getUser_rating(), items.get(i).getAudienceRating(), items.get(i).getReviewer_rating(), items.get(i).getReservation_rate(), items.get(i).getReservation_grade(), items.get(i).getGrade(), items.get(i).getThumb(), items.get(i).getImage()};

            if (database != null) {
                database.execSQL(sql, params);
            }
        }
        Log.d(TAG, "outline 데이터를 넣었습니다.");
    }

    /*
     * 서버 통신 시에 데이터를 디비에 넣습니다.
     * 영화 상세
     */
    public static void insertInlineData(MovieDetailItem item) {
        try {
//        for (int i = 0; i < items.size(); i++) {
            String sql = "insert into inline(id, title, date_value, user_rating, audience_rating, reviewer_rating, reservation_rate, reservation_grade, grade, thumb, image, photos, videos, outlinks, genre, duration, audience, synopsis, director, actor,like_value, dislike) " +
                    "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
            Object[] params = {item.getId(), item.getTitle(), item.getDate(), item.getUser_rating(), item.getAudience_rating(), item.getReviewer_rating(), item.getReservation_rate(), item.getReservation_grade(), item.getGrade(), item.getThumb(), item.getImage(), item.getPhotos(), item.getVideos(), item.getOutlinks(), item.getGenre(), item.getDuration(), item.getAudience(), item.getSynopsis(), item.getDirector(), item.getActor(), item.getLike(), item.getDislike()};


            if (database != null) {
                database.execSQL(sql, params);
            }
//        }
            Log.d(TAG, "inline 데이터를 넣었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void println(String data) {
        Log.d(TAG, data);
    }

}
