package com.example.bankingapp.dbHelper
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.bankingapp.model.Customer
import com.example.bankingapp.model.Transaction


class DBhelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null , DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME ($PHONENUMBER_COl INTEGER PRIMARY KEY, $NAME_COl TEXT,$BALANCE_COL DECIMAL,$ACCOUNT_NO_COL VARCHAR)")
        db.execSQL("CREATE TABLE $TABLE_NAME1 ($TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT, $DATE TEXT,$FROMNAME TEXT,$TONAME TEXT,$AMOUNT DECIMAL,$STATUS TEXT)")
        db.execSQL("insert into $TABLE_NAME values(0908777632,'Kavya sita raman',1000900,'XXXXXXXXXXXX1231')")
        db.execSQL("insert into $TABLE_NAME values(8456789012,'Ram',100000,'XXXXXXXXXXXX3417')")
        db.execSQL("insert into $TABLE_NAME values(8749984873,'Khushal',150000,'XXXXXXXXXXXX4124')")
        db.execSQL("insert into $TABLE_NAME values(2678901234,'Samarth Shah',54708,'XXXXXXXXXXXX2342')")
        db.execSQL("insert into $TABLE_NAME values(3890123456,'Akshay ',70002,'XXXXXXXXXXXX4525')")
        db.execSQL("insert into $TABLE_NAME values(0999987654,'Rajat',780072,'XXXXXXXXXXXX5232')")
        db.execSQL("insert into $TABLE_NAME values(5012345678,'Suresh Sharma',302100,'XXXXXXXXXXXX3458')")
        db.execSQL("insert into $TABLE_NAME values(5647837648,'Kiran Singh',200000,'XXXXXXXXXXXX4560')")
        db.execSQL("insert into $TABLE_NAME1 values(2324,'8/09/22','Kavya sita raman','Akshay',10000.00,'Success')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {


    }
    fun insertTransferData(
        date: String?,
        from_name: String?,
        to_name: String?,
        amount: String?,
        status: String?
    ){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(DATE, date)
        values.put(FROMNAME, from_name)
        values.put(TONAME, to_name)
        values.put(AMOUNT, amount)
        values.put(STATUS, status)
        db.insert(TABLE_NAME1, null, values)
        db.close()
    }
    fun updateAmount(phoneNumber: String, amount: String) {
        val db = this.writableDatabase
        db.execSQL("update Customers set $BALANCE_COL = $amount where $PHONENUMBER_COl = $phoneNumber")
    }
    fun getCustomers(context: Context): ArrayList<Customer> {
        val qry = "SELECT * From $TABLE_NAME"
        val db = this.readableDatabase
        val cursor:Cursor = db.rawQuery(qry,null)
        val customers = ArrayList<Customer>()
        while(cursor.moveToNext()){
            val customer = Customer(cursor.getString(cursor.getColumnIndexOrThrow(NAME_COl)),
                cursor.getLong(cursor.getColumnIndexOrThrow(PHONENUMBER_COl)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(BALANCE_COL)),
                cursor.getString(cursor.getColumnIndexOrThrow(EMAIL_COL)),
                cursor.getString(cursor.getColumnIndexOrThrow(ACCOUNT_NO_COL)),
                cursor.getString(cursor.getColumnIndexOrThrow(IFSC_CODE_COL)))
            customers.add(customer)
        }
        cursor.close()
        db.close()
        return customers
    }
    fun getTransaction(context: Context): ArrayList<Transaction> {
        val qry = "SELECT * From $TABLE_NAME1"
        val db = this.readableDatabase
        val cursor:Cursor = db.rawQuery(qry,null)
        val transactions = ArrayList<Transaction>()
        while(cursor.moveToNext()){
            val transaction = Transaction(cursor.getString(cursor.getColumnIndexOrThrow(DATE)),
                cursor.getString(cursor.getColumnIndexOrThrow(FROMNAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(TONAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(AMOUNT)),
                cursor.getString(cursor.getColumnIndexOrThrow(STATUS)))
            transactions.add(transaction)
        }
        cursor.close()
        db.close()
        return transactions
    }
    fun readParticularData(phoneNumber: String): Cursor? {
        val qry = "SELECT $BALANCE_COL from $TABLE_NAME where $PHONENUMBER_COl = $phoneNumber"
        val db = this.readableDatabase
        return db.rawQuery(qry, null)
    }

    fun readselectuserdata(phoneNumber: String): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery(
            "select * from user_table except select * from user_table where phoneNumber = $phoneNumber",
            null
        )
    }
    companion object{
        private const val DATABASE_NAME = "SparkBank"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "Customers"
        private const val TABLE_NAME1 = "Transactions"
        val PHONENUMBER_COl = "id"
        val NAME_COl = "name"
        val BALANCE_COL = "balance"
        val EMAIL_COL = "email"
        val ACCOUNT_NO_COL = "accountNo"
        val IFSC_CODE_COL = "ABC09876541"
        val STATUS = "status"
        val AMOUNT = "amount"
        val TONAME = "toName"
        val FROMNAME = "fromName"
        val DATE = "date"
        val TRANSACTIONID = "transID"
    }
}