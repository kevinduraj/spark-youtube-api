/**
 * Written by: Kevin Duraj 
 * Spark 2.0 with Cassandra 3.7 
 * https://github.com/apache/spark/blob/master/examples/src/main/scala/org/apache/spark/examples/sql/SparkSQLExample.scala
 */

import com.datastax.spark.connector._
//import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql._
import org.apache.spark.sql.cassandra._
import org.apache.spark.sql.Encoder
import org.apache.spark.sql.Row
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._

object YoutubeVideos {

    val locale = new java.util.Locale("us", "US")
    val formatter = java.text.NumberFormat.getIntegerInstance(locale)

    def main(args: Array[String]) {

        //count_all();
        //count_null();
        export_null();

    }

    def count_null() {

        val spark = SparkSession.builder().appName("YoutubeVideos").config("spark.some.config.option", "some-value").getOrCreate()
        import spark.implicits._

        val df1 = spark.read.format("org.apache.spark.sql.cassandra").options(Map( "table" -> "video2", "keyspace" -> "youtube" )).load()
        df1.printSchema()
        df1.createOrReplaceTempView("video")

        //val df2 = spark.sql("SELECT video_id, video_title, ts_data_update FROM video WHERE ts_data_update <= '2016-12-04 00:00:00+0000'")
        val df2 = spark.sql("SELECT count(video_id) ts_stats_update FROM video WHERE ts_stats_update IS NULL")
        df2.show()

    }

    def count_all() {

        val spark = SparkSession.builder().appName("YoutubeVideos").config("spark.some.config.option", "some-value").getOrCreate()
        import spark.implicits._

        val df1 = spark.read.format("org.apache.spark.sql.cassandra").options(Map( "table" -> "video2", "keyspace" -> "youtube" )).load()
        df1.printSchema()
        df1.createOrReplaceTempView("video")

        //val df2 = spark.sql("SELECT video_id, video_title, ts_data_update FROM video WHERE ts_data_update <= '2016-12-04 00:00:00+0000'")
        val df2 = spark.sql("SELECT count(video_id) FROM video")
        df2.show()

    }

    def export_null() {

        val spark = SparkSession.builder().appName("YoutubeVideos").config("spark.some.config.option", "some-value").getOrCreate()
        import spark.implicits._

        val df1 = spark.read.format("org.apache.spark.sql.cassandra").options(Map( "table" -> "video2", "keyspace" -> "youtube" )).load()
        df1.printSchema()
        df1.createOrReplaceTempView("video")

        //val df2 = spark.sql("SELECT video_id, video_title, ts_data_update FROM video WHERE ts_data_update <= '2016-12-04 00:00:00+0000'")
        val df2 = spark.sql("SELECT video_id FROM video WHERE video_title IS NULL")
        println("NULL = " + df2.count())
        df2.show(25, false)

        //df2.write.format("org.apache.spark.sql.cassandra").options(Map("keyspace" -> "youtube", "table" -> "video1")).mode("append").save()

        val df3 = df2.coalesce(1)
        df3.write.format("com.databricks.spark.csv").mode(SaveMode.Overwrite).save("/home/fresno/video_null")

    }

}
