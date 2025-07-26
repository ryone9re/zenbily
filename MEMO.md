# zenbily

- Kafkaトピック作成

```sh
/opt/kafka/bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --topic user-location --partitions 1 --replication-factor 1 --if-not-exists
```

TODO:
decaton導入、疎通確認
