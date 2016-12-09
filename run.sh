#!/bin/bash
#--------------------------------------------------------------------------------------------------------#                                                                                                           
  if [ "$1" == "0" ] 2>/dev/null; then
    echo "sbt clean && sbt package"
    sbt clean && sbt package
    #mvn clean install
    #mvn clean compile assembly:single

elif [ "$1" == "1" ] 2>/dev/null; then
    # mvn exec:java -Dexec.mainClass=kduraj.App
    # java -cp target/youtube-s3-analytics-1.0.0-jar-with-dependencies.jar fresno.youtube.analytics.App 

spark-submit    \
  --class "YoutubeVideos"           \
  --master local[8]                 \
  --driver-memory   32G             \
  --executor-memory 16G             \
  target/scala-2.11/spark-cassandra_2.11-1.0.jar 


#--------------------------------------------------------------------------------------------------------#
else

  echo "+--------------------------------------------------+"
  echo "| ./run.sh 0 -- mvn clean install                  |"
  echo "| ./run.sh 1 -- App                                |"
  echo "| ./run.sh 9 -- s3cmd ls s3://fresno-youtube-json  |"
  echo "+--------------------------------------------------+"

fi
#--------------------------------------------------------------------------------------------------------#



