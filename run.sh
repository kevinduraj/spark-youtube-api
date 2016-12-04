#!/bin/bash

# sbt clean
# sbt package

spark-submit    \
  --class "YoutubeVideos"           \
  --master local[8]                 \
  --driver-memory   32G             \
  --executor-memory 16G             \
  target/scala-2.11/spark-cassandra_2.11-1.0.jar 

