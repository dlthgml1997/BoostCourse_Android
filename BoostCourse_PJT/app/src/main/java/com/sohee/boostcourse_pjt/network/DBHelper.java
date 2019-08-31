package com.sohee.boostcourse_pjt.network;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sohee.boostcourse_pjt.ui.movie.item.MovieDetailItem;
import com.sohee.boostcourse_pjt.ui.movie.item.MovieItem;
import com.sohee.boostcourse_pjt.ui.review.item.ReviewItem;

import java.util.ArrayList;

import static com.sohee.boostcourse_pjt.network.Sql.creatTableReviewSql;
import static com.sohee.boostcourse_pjt.network.Sql.createTableInlineSql;
import static com.sohee.boostcourse_pjt.network.Sql.createTableOutlineSql;
import static com.sohee.boostcourse_pjt.network.Sql.insertInlineSql;
import static com.sohee.boostcourse_pjt.network.Sql.insertOutlineSql;
import static com.sohee.boostcourse_pjt.network.Sql.insertReviewSql;
import static com.sohee.boostcourse_pjt.network.Sql.selectInlineSql;
import static com.sohee.boostcourse_pjt.network.Sql.selectOutlineSql;
import static com.sohee.boostcourse_pjt.network.Sql.selectReviewSql;

public class DBHelper {

    public static Cursor cursor;
    public static String sql;

    private static final String TAG = "DBHelper";

    private static SQLiteDatabase database;

    /**
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


    /**
     * 테이블을 생성합니다.
     */
    public static void createTable(String tableName) {
        println("createTable 호출됨 : " + tableName);
//        database.execSQL("drop table if exists "+tableName);
        if (database != null) {
            switch (tableName) {
                default:
                case "outline":
                    database.execSQL(createTableOutlineSql);
                    println("outline 테이블 생성 요청됨.");
                    break;

                case "inline":
                    database.execSQL(createTableInlineSql);
                    println("inline 테이블 생성 요청됨.");
                    break;

                case "review":
                    database.execSQL(creatTableReviewSql);
                    println("review 테이블 생성 요청됨.");
                    break;

            }
        } else {
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }

    /**
     * 오프라인시 데이터를 디비에서 불러옵니다.
     */
    public static ArrayList<?> selectTable(String tableName) {
        println("selectData() 호출됨.");

        if (database != null) {
            switch (tableName) {
                default:
                case "outline":
                    ArrayList<MovieItem> items = outlineSelect(tableName);
                    return items;

                case "inline":
                    ArrayList<MovieDetailItem> DetailItems = inlineSelect(tableName);
                    return DetailItems;

                case "review":
                    ArrayList<ReviewItem> reviewItems = reviewSelect(tableName);
                    return reviewItems;
            }
        }
        return null;
    }

    /*
     * 오프라인 시에 데이터를 디비에서 불러옵니다.
     * 리뷰 목록
     */
    private static ArrayList<ReviewItem> reviewSelect(String tableName) {
        int i = 0;

        sql = selectReviewSql + tableName;

        cursor = database.rawQuery(sql, null);
        println("review 조회된 데이터 개수 : " + cursor.getCount());

        ArrayList<ReviewItem> reviewItems = new ArrayList<ReviewItem>();

        while (cursor.moveToNext()) {
            reviewItems.add(getReivewDataFromCursor(cursor, i));
            i++;
        }

        println("#" + i + " ->" + reviewItems.toString());
        cursor.close();
        return reviewItems;
    }

    private static ReviewItem getReivewDataFromCursor(Cursor cursor, int i) {
        String movie_title = cursor.getString(0);
        int total = cursor.getInt(1);
        String writer = cursor.getString(2);
        int review_id = cursor.getInt(3);
        String writer_image = cursor.getString(4);
        String time = cursor.getString(5);
        String time_stamp = cursor.getString(6);
        Float rating = cursor.getFloat(7);
        String contents = cursor.getString(8);
        int recommend = cursor.getInt(9);

        ReviewItem reviewItem = new ReviewItem(i, movie_title, total, writer, review_id, writer_image, time, time_stamp, rating, contents, recommend);

        return reviewItem;
    }

    /*
     * 오프라인 시에 데이터를 디비에서 불러옵니다.
     * 영화 목록
     */
    private static ArrayList<MovieItem> outlineSelect(String tableName) {
        sql = selectOutlineSql + tableName;

        cursor = database.rawQuery(sql, null);

        println("outline 조회된 데이터 개수 : " + cursor.getCount());

        ArrayList<MovieItem> movieItems = new ArrayList<MovieItem>();

        int i = 0;
        while (cursor.moveToNext()) {
            movieItems.add(getMovieItemFromCursor(cursor, i));
            i++;
        }

        println("#" + i + " ->" + movieItems.toString());
        cursor.close();
        return movieItems;
    }

    private static MovieItem getMovieItemFromCursor(Cursor cursor, int i) {
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

        return movieItem;
    }

    /*
     * 오프라인 시에 데이터를 디비에서 불러옵니다.
     * 영화 상세
     */
    private static ArrayList<MovieDetailItem> inlineSelect(String tableName) {
        int i = 0;

        sql = selectInlineSql + tableName;

        cursor = database.rawQuery(sql, null);
        println("inline 조회된 데이터 개수 : " + cursor.getCount());

        ArrayList<MovieDetailItem> DetailItems = new ArrayList<MovieDetailItem>();

        while (cursor.moveToNext()) {
            DetailItems.add(getMovieDetailItemFromCursor(cursor));
            i++;
        }

        println("#" + i + " ->" + DetailItems.toString());
        cursor.close();

        return DetailItems;
    }

    private static MovieDetailItem getMovieDetailItemFromCursor(Cursor cursor) {
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

        MovieDetailItem movieDetailItem = new MovieDetailItem(id, title, date, user_rating, audience_rating, reviewer_rating, reservation_rate, reservation_grade, grade, thumb, image, photos, videos, outlinks, genre, duration, audience, synopsis, director, actor, like, dislike);

        return movieDetailItem;
    }

    /**
     * 서버 통신 시에 데이터를 디비에 넣습니다.
     * 영화 목록
     */
    public static void insertOutlineData(ArrayList<MovieItem> items) {
        for (int i = 0; i < items.size(); i++) {
            String sql = insertOutlineSql;
            Object[] params = {items.get(i).getId(), items.get(i).getTitle(), items.get(i).getTitle_eng(), items.get(i).getDate(), items.get(i).getUser_rating(), items.get(i).getAudienceRating(), items.get(i).getReviewer_rating(), items.get(i).getReservation_rate(), items.get(i).getReservation_grade(), items.get(i).getGrade(), items.get(i).getThumb(), items.get(i).getImage()};

            if (database != null) {
                database.execSQL(sql, params);
            }
        }
        Log.d(TAG, "outline 데이터를 넣었습니다.");
    }

    /**
     * 서버 통신 시에 데이터를 디비에 넣습니다.
     * 영화 상세
     */
    public static void insertInlineData(MovieDetailItem item) {
        try {
            String sql = insertInlineSql;
            Object[] params = {item.getId(), item.getTitle(), item.getDate(), item.getUser_rating(), item.getAudience_rating(), item.getReviewer_rating(), item.getReservation_rate(), item.getReservation_grade(), item.getGrade(), item.getThumb(), item.getImage(), item.getPhotos(), item.getVideos(), item.getOutlinks(), item.getGenre(), item.getDuration(), item.getAudience(), item.getSynopsis(), item.getDirector(), item.getActor(), item.getLike(), item.getDislike()};


            if (database != null) {
                database.execSQL(sql, params);
            }
            Log.d(TAG, "inline 데이터를 넣었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 서버 통신 시에 데이터를 디비에 넣습니다.
     * 리뷰 조회
     */
    public static void insertReviewData(ArrayList<ReviewItem> items) {
        try {
            for (int i = 0; i < items.size(); i++) {
                String sql = insertReviewSql;
                Object[] params = {items.get(i).getMovieTitle(), items.get(i).getTotal(), items.get(i).getWriter(), items.get(i).getReview_id(), items.get(i).getWriter_image(), items.get(i).getTime(), items.get(i).getTime_stamp(), items.get(i).getRating(), items.get(i).getContents(), items.get(i).getRecommend()};

                if (database != null) {
                    database.execSQL(sql, params);
                }
            }
            ;
            Log.d(TAG, "review 데이터를 넣었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void println(String data) {
        Log.d(TAG, data);
    }

}
