package com.sohee.boostcourse_pjt.network;

public class Sql {

    /*
     * outline
     */
    public static String createTableOutlineSql
            = "create table if not exists outline"
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

    public static String insertOutlineSql
            = "insert into outline(id, title, title_eng, date_value, user_rating, audience_rating, reviewer_rating, reservation_rate, reservation_grade, grade, thumb, image) " +
            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String selectOutlineSql
            = "select id, title, title_eng, date_value, user_rating, audience_rating, reviewer_rating, reservation_rate, reservation_grade, grade, thumb, image from ";

    /*
     * inline
     */
    public static String createTableInlineSql
            = "create table if not exists inline"
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

    public static String insertInlineSql
            = "insert into inline(id, title, date_value, user_rating, audience_rating, reviewer_rating, reservation_rate, reservation_grade, grade, thumb, image, photos, videos, outlinks, genre, duration, audience, synopsis, director, actor,like_value, dislike) " +
            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

    public static String selectInlineSql
            = "select id, title, date_value, user_rating, audience_rating, reviewer_rating, reservation_rate, reservation_grade, grade, thumb, image, photos, videos, outlinks, genre, duration, audience, synopsis, director, actor, like_value, dislike from ";

    /*
     * review
     */
    public static String creatTableReviewSql
            = "create table if not exists review" +
            "(" +
            "    movie_title text, " +
            "    total integer, " +
            "    writer text, " +
            "    review_id int, " +
            "    writer_image text, " +
            "    time text, " +
            "    time_stamp text, " +
            "    rating float, " +
            "    contents text, " +
            "    recommend integer" +
            ")";

    public static String insertReviewSql
            = "insert into review(movie_title, total, writer, review_id, writer_image, time, time_stamp, rating, contents, recommend) " +
            "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String selectReviewSql
            = "select movie_title, total, writer, review_id, writer_image, time, time_stamp, rating, contents, recommend from ";

}
