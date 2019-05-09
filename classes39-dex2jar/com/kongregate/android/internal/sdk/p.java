// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import com.kongregate.o.e.a;
import com.kongregate.o.e.b;
import com.kongregate.android.internal.util.j;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase$CursorFactory;
import android.content.Context;
import com.kongregate.o.e.c;

public class p extends c
{
    public p(final Context context, final String s) {
        super(context.getApplicationContext(), s + "." + "kongregate_api.db", null, 4);
    }
    
    @Override
    protected void a(final SQLiteDatabase sqLiteDatabase) {
        j.b("Building DB Version 1");
        com.kongregate.o.e.b.a(sqLiteDatabase, "CREATE TABLE IF NOT EXISTS users(  _id INTEGER PRIMARY KEY NOT NULL,  username TEXT COLLATE NOCASE NOT NULL,  game_auth_token TEXT COLLATE NOCASE);CREATE TABLE IF NOT EXISTS active_user(  _id INTEGER PRIMARY KEY NOT NULL,  user_id INTEGER NOT NULL);INSERT OR IGNORE INTO active_user (_id, user_id) VALUES (1, 0);CREATE TABLE IF NOT EXISTS statistics(  _id INTEGER PRIMARY KEY NOT NULL,  game_id INTEGER NOT NULL,  name TEXT NOT NULL COLLATE NOCASE NOT NULL,  display_name TEXT COLLATE NOCASE DEFAULT NULL,  stat_type TEXT COLLATE NOCASE DEFAULT \"max\" NOT NULL,  description TEXT COLLATE NOCASE DEFAULT NULL,  display_in_table INTEGER NOT NULL);CREATE UNIQUE INDEX IF NOT EXISTS idx_statistics_on_name ON statistics(name);CREATE TABLE IF NOT EXISTS statistic_records(  _id INTEGER PRIMARY KEY AUTOINCREMENT,  statistic_id INTEGER REFERENCES statistics(_id) ON DELETE CASCADE,  statistic_name TEXT COLLATE NOCASE,  user_id INTEGER NOT NULL,  value INTEGER,  server_value INTEGER,  pending_value INTEGER,  add_value INTEGER,  max_value INTEGER,  min_value INTEGER,  replace_value INTEGER,  sent_at INTEGER DEFAULT 0 NOT NULL);CREATE UNIQUE INDEX IF NOT EXISTS idx_sr_on_user_id_and_statistic_id ON statistic_records(user_id, statistic_id, statistic_name);CREATE INDEX IF NOT EXISTS idx_sr_on_user_id_and_values ON statistic_records(user_id, value, server_value);".split(";"));
    }
    
    @Override
    protected void a(final SQLiteDatabase sqLiteDatabase, final int n, final int n2) {
        if (n < 2 && !com.kongregate.o.e.a.a(sqLiteDatabase, "badges", "_id")) {
            com.kongregate.o.e.b.a(sqLiteDatabase, "CREATE TABLE IF NOT EXISTS badges(  _id INTEGER PRIMARY KEY,  game_id INTEGER NOT NULL,  description TEXT COLLATE NOCASE DEFAULT \"\",  level TEXT COLLATE NOCASE NOT NULL,  name TEXT COLLATE NOCASE NOT NULL,  users_count INTEGER NOT NULL DEFAULT 0,  updated_at INTEGER NOT NULL,  url TEXT COLLATE NOCASE NOT NULL);CREATE INDEX IF NOT EXISTS idx_badges_on_game_id ON badges(game_id);CREATE TABLE IF NOT EXISTS accomplishment_tasks(  _id INTEGER PRIMARY KEY,  badge_id INTEGER NOT NULL REFERENCES badges(_id) ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED,  description TEXT COLLATE NOCASE DEFAULT \"\",  name TEXT COLLATE NOCASE NOT NULL,  quota INTEGER NOT NULL,  statistic_id INTEGER NOT NULL REFERENCES statistics(_id) ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED);CREATE INDEX IF NOT EXISTS idx_accomplishment_tasks_on_badge_id ON accomplishment_tasks(badge_id);CREATE INDEX IF NOT EXISTS idx_accomplishment_tasks_on_statistic_id ON accomplishment_tasks(statistic_id);CREATE TABLE IF NOT EXISTS accomplishment_task_progress_trackers(  _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  accomplishment_task_id INTEGER NOT NULL REFERENCES accomplishment_tasks(_id) ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED,  user_id INTEGER NOT NULL,  value INTEGER NOT NULL);CREATE UNIQUE INDEX IF NOT EXISTS idx_task_trackers_on_task_id_and_user_id ON accomplishment_task_progress_trackers(accomplishment_task_id, user_id);CREATE TABLE IF NOT EXISTS user_badges(  _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  badge_id INTEGER NOT NULL REFERENCES badges(_id) ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED,  user_id INTEGER NOT NULL);CREATE UNIQUE INDEX IF NOT EXISTS idx_user_badges_on_badge_and_user_id ON user_badges(badge_id, user_id);CREATE VIEW IF NOT EXISTS user_badges_view AS  SELECT badges.*, user_badges.user_id, CASE WHEN user_badges.user_id IS NULL THEN 0 ELSE 1 END AS completed  FROM badges LEFT OUTER JOIN user_badges ON badges._id=user_badges.badge_id AND (user_id IS NULL OR user_id=COALESCE((SELECT user_id FROM active_user), 0));CREATE VIEW IF NOT EXISTS user_accomplishment_tasks_view AS  SELECT accomplishment_tasks.*, accomplishment_task_progress_trackers.user_id, accomplishment_task_progress_trackers.value, statistics.stat_type,  COALESCE(CASE WHEN stat_type='min' THEN value <= quota ELSE value >= quota END, 0) AS completed  FROM accomplishment_tasks  LEFT OUTER JOIN accomplishment_task_progress_trackers  ON accomplishment_task_progress_trackers.accomplishment_task_id=accomplishment_tasks._id AND (user_id IS NULL OR user_id=COALESCE((SELECT user_id FROM active_user), 0))  LEFT JOIN statistics ON statistics._id=accomplishment_tasks.statistic_id;".split(";"));
        }
        if (n < 3 && !com.kongregate.o.e.a.a(sqLiteDatabase, "purchase_records", "_id")) {
            com.kongregate.o.e.b.a(sqLiteDatabase, "CREATE TABLE IF NOT EXISTS purchase_records(  _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  purchased_at INTEGER NOT NULL,  sku TEXT COLLATE NOCASE NOT NULL,  user_id INTEGER NOT NULL,  error_count INTEGER DEFAULT 0 NOT NULL);CREATE INDEX IF NOT EXISTS idx_purchase_records_on_user_id ON purchase_records(user_id);".split(";"));
        }
        if (n < 4 && com.kongregate.o.e.a.a(sqLiteDatabase, "purchase_records", "_id")) {
            com.kongregate.o.e.b.a(sqLiteDatabase, "DROP TABLE IF EXISTS purchase_records;".split(";"));
        }
    }
}
