name := "spark"

version := "1.0"

scalaVersion := "2.11.8"
/*
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-core" % "2.9.1"
dependencyOverrides += "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.1"
dependencyOverrides += "com.fasterxml.jackson.module" %% "jackson-module-scala_2.11" % "2.9.1"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-repl" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming-flume" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-network-shuffle" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming-flume-assembly" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-mesos" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-graphx" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-catalyst" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-launcher" % "2.3.1"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.1"
libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.3.1"
libraryDependencies += "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.3.1" */

libraryDependencies += "org.apache.kafka" % "kafka_2.11" % "1.0.1"
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "1.0.1"
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "1.0.1"
