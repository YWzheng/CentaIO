package com.example.centaio.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface PageDao {

    @Query("SELECT * FROM " + Constants.TABLE_NAME_PAGE)
    List<Page> getPages();

    /*
     * Insert the object in database
     * @param note, object to be inserted
     */
    @Insert
    void insertPage(Page page);

    /*
     * update the object in database
     * @param note, object to be updated
     */
    @Update
    void updateNote(Page page);


    @Query("DELETE FROM " + Constants.TABLE_NAME_PAGE)
    void deleteAll();

    /*
     * delete the object from database
     * @param note, object to be deleted
     */
    @Delete
    void deleteNote(Page page);

    // Note... is varargs, here note is an array
    /*
     * delete list of objects from database
     * @param note, array of oject to be deleted
     */
    @Delete
    void deleteNotes(Page... pages);

}
