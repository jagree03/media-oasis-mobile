package com.example.jagree03.mediaoasis.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

/* Database Config*/
private val DataBaseName = "MediaOasisDB.db"
private val ver : Int = 1

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context,DataBaseName,null, ver) {

    /* User Table */
    private val TableName = "User"

    private val Column_ID = "userID"
    private val Column_FirstName = "userFirstName"
    private val Column_LastName = "userLastName"
    private val Column_Email = "userEmail"
    private val Column_PhoneNo = "userPhoneNo"
    private val Column_UserName = "userUserName"
    private val Column_Password = "userPassWord"
    private val Column_IsAdmin = "userIsAdmin"
    /*************************/

    /**
     * This method is called the first time a database is accessed.
     * It creates a new database in the system.
     */
    override fun onCreate(db: SQLiteDatabase?) {
       try {
           val sqlCreateStatement: String = "CREATE TABLE " + TableName + " ( " +
                   Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   Column_FirstName + " TEXT NOT NULL, " +
                   Column_LastName + " TEXT NOT NULL, " +
                   Column_Email + " TEXT NOT NULL DEFAULT 0, " +
                   Column_PhoneNo + " TEXT NOT NULL, " +
                   Column_UserName + " TEXT NOT NULL UNIQUE, " +
                   Column_Password + " TEXT NOT NULL, " +
                   Column_IsAdmin + "INTEGER NOT NULL" + ")"

           db?.execSQL(sqlCreateStatement)
       }
       catch (e: SQLiteException) {}
    }

    /**
     * This method is called if the database ver. is changed
     */
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    /**
     * This method accepts a User object, checks if the user already exists, if not
     * then places the values of the individual attributes of the User object and inserts them into a ContentValues set
     * which gets inserted into the respective table via the insert method upon the database.
     * return  1 : the new use has been add to the database successfully
     * return -1 : Error, adding new user
     * return -2 : can not Open/Create database
     * return -3 : user name is already exist
     * @param user Accepts a user object
     * @return Int Integer value
     */
    fun addUser(user: User) : Int {

        val db: SQLiteDatabase
        val isUserNameAlreadyExists = checkUserName(user) // check if the username is already exist in the database
        if(isUserNameAlreadyExists < 0)
           return isUserNameAlreadyExists

        try {
            db = this.writableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val cv: ContentValues = ContentValues()

        cv.put(Column_FirstName, user.userFirstName)
        cv.put(Column_LastName,user.userLastName)
        cv.put(Column_Email, user.userEmail)
        cv.put(Column_PhoneNo, user.userPhoneNo)
        cv.put(Column_UserName, user.userUserName.lowercase())
        cv.put(Column_Password, user.userPassWord)

        val success  =  db.insert(TableName, null, cv)

        db.close()
        if (success.toInt() == -1) return success.toInt() //Error, adding new user
        else return success.toInt() //1

    }

    /**
     * This method checks if the user already exists within the database
     * @param user Accepts a user object
     * @return Int Integer value that represents whether the user has been found or not
     */
    private fun checkUserName(user: User): Int {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val userName = user.userUserName.lowercase()

        val sqlStatement = "SELECT * FROM $TableName WHERE $Column_UserName = ?"
        val param = arrayOf(userName)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)

        if(cursor.moveToFirst()){
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return -3 // error, the user name is already exist
        }

        cursor.close()
        db.close()
        return 0 //User not found

    }

    /**
     * This method gets a user from the database
     * @param user A user object
     * @return Int Integer value that represents whether the user has been found or not
     */
    fun getUser(user: User) : Int {

        val db: SQLiteDatabase
        try {
            db = this.readableDatabase
        }
        catch(e: SQLiteException) {
            return -2
        }

        val userName = user.userUserName.lowercase()
        val userPassword = user.userPassWord
        //val sqlStatement = "SELECT * FROM $TableName WHERE $Column_UserName = $userName AND $Column_Password = $userPassword"

        val sqlStatement = "SELECT * FROM $TableName WHERE $Column_UserName = ? AND $Column_Password = ?"
        val param = arrayOf(userName,userPassword)
        val cursor: Cursor =  db.rawQuery(sqlStatement,param)
        if(cursor.moveToFirst()){
            // The user is found
            val n = cursor.getInt(0)
            cursor.close()
            db.close()
            return n
        }

        cursor.close()
        db.close()
        return -1 //User not found

    }

}