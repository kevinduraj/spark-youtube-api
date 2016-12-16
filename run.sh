#!/bin/bash
#-----------------------------------------------------------------------------------#
  if [ "$1" == "0" ] 2>/dev/null; then
    echo "sbt clean && sbt package"
    sbt clean && sbt package
    #mvn clean install
    #mvn clean compile assembly:single

#-----------------------------------------------------------------------------------#
elif [ "$1" == "1" ] 2>/dev/null; then

spark-submit                                        \
  --class "YoutubeVideos"                           \
  --master local[16]                                \
  --driver-memory   32G                             \
  --executor-memory 16G                             \
  target/scala-2.11/spark-cassandra_2.11-1.0.jar    \
  "count_not_null"

#-----------------------------------------------------------------------------------#
elif [ "$1" == "2" ] 2>/dev/null; then

spark-submit                                        \
  --class "YoutubeVideos"                           \
  --master local[16]                                \
  --driver-memory   32G                             \
  --executor-memory 16G                             \
  target/scala-2.11/spark-cassandra_2.11-1.0.jar    \
  "count_null"

#-----------------------------------------------------------------------------------#
elif [ "$1" == "3" ] 2>/dev/null; then

spark-submit                                        \
  --class "YoutubeVideos"                           \
  --master local[16]                                \
  --driver-memory   32G                             \
  --executor-memory 16G                             \
  target/scala-2.11/spark-cassandra_2.11-1.0.jar    \
  "count_all"

#-----------------------------------------------------------------------------------#
elif [ "$1" == "4" ] 2>/dev/null; then

spark-submit                                        \
  --class "YoutubeVideos"                           \
  --master local[16]                                \
  --driver-memory   32G                             \
  --executor-memory 16G                             \
  target/scala-2.11/spark-cassandra_2.11-1.0.jar    \
  "export_null"

#-----------------------------------------------------------------------------------#
else

  echo "+--------------------------------------------------+"
  echo "| ./run.sh 0 -- mvn clean install                  |"
  echo "| ./run.sh 1 -- count_not_null                     |"
  echo "| ./run.sh 2 -- count_null                         |"
  echo "| ./run.sh 3 -- count_all                          |"
  echo "| ./run.sh 4 -- export_null                        |"
  echo "+--------------------------------------------------+"

fi
#-----------------------------------------------------------------------------------#

