# Spark
Social network back-end in scala/kafka

In c4/, you'll find a tool analysing the data stored in
> c4/data/posts/part-\*
This tool ask you to type in a brand name and a duration (from 0 days to x months),
and it returns the number of times the brand appeared during this duration (from
now) in users' posts.

In spark/, you'll find an api of a social network with a little sequential
example showing how you can use this api.

Make sure zookeeper and some servers are started, listening to the port 9092.
Make also sure the topics "posts", "users", "messages", "sinkPosts", "sinkUsers"
and "sinkMessages" are created.

## Options

No bonus have been done

## Dependencies

We used Kafka Streaming + Ktables, sending messages in csv, using the kafka
String serializer. We also tried to use spark-streaming to convert the sink
topics into hdfs, but we had a dependency problem that we didn't manage to solve
(jackson dependency version), thus this feature isn't working.

For the analytical tool, we used spark's RDD.
