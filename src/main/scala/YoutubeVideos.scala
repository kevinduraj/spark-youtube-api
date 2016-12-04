/**
 * Written by: Kevin Duraj 
 * Spark 2.0 with Cassandra 3.7 
 */

import org.apache.spark.{SparkContext, SparkConf}
import com.datastax.spark.connector._
import org.apache.spark.sql._
import org.apache.spark.sql.cassandra._

object YoutubeVideos {

    val locale = new java.util.Locale("us", "US")
    val formatter = java.text.NumberFormat.getIntegerInstance(locale)

    def main(args: Array[String]) {

        val conf = new SparkConf(true).setAppName("YoutubeVideos")
        val sc = new SparkContext(conf)
        //val conf = new SparkConf(true).set("spark.cassandra.connection.host", "69.13.39.46")
        //val sc = new SparkContext("spark://69.13.39.46:7077", "cloud1", conf)

        //val rdd = sc.cassandraTable("youtube", "video2")
        //println("Total Videos: " + rdd.count)
        //println(rdd.first)

        val sqlContext = new org.apache.spark.sql.SQLContext(sc)
        import sqlContext.implicits._

        val df1 = sqlContext.read.format("org.apache.spark.sql.cassandra").options(Map( "table" -> "video2", "keyspace" -> "youtube" )).load()
        df1.printSchema()
        df1.registerTempTable("video")
        
        val df2 = sqlContext.sql("SELECT video_id, video_title, ts_data_update FROM video WHERE ts_data_update <= '2016-12-04 00:00:00+0000'")
        df2.show(100, false)
        val df3 = df2.coalesce(1)
        df3.write.format("com.databricks.spark.csv").mode(SaveMode.Overwrite).save("/home/fresno/video.csv")
        
        
        sc.stop();

    }

}
