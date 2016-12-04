/**
 * Written by: Kevin Duraj 
 * Spark 2.0 with Cassandra 3.7 
 */

import org.apache.spark.{SparkContext, SparkConf}
import com.datastax.spark.connector._
import org.apache.spark.sql._

object YoutubeVideos {

    val locale = new java.util.Locale("us", "US")
    val formatter = java.text.NumberFormat.getIntegerInstance(locale)

    def main(args: Array[String]) {

        val conf = new SparkConf(true).setAppName("YoutubeVideos")
        val sc = new SparkContext(conf)
        //val conf = new SparkConf(true).set("spark.cassandra.connection.host", "69.13.39.46")
        //val sc = new SparkContext("spark://69.13.39.46:7077", "cloud1", conf)

        val rdd = sc.cassandraTable("youtube", "video2")
        println(rdd.count)
        println(rdd.first)
        //println(rdd.map(_.getInt("url")).sum)

        sc.stop();

    }

}
