## Project scope

A simple demo project to reproduce https://issues.apache.org/jira/browse/TOMEE-2143.

## Working Version with TomEE 7.0.5

Run `mvn clean test -Ptomee-7.0.5`

All works fine.

## Broken Version with TomEE 7.1.0

Run `mvn clean test -Ptomee-7.1.0`

Tests will fail with:

```
Caused by: org.apache.johnzon.mapper.MapperException: Text '20180910121456+0200' could not be parsed at index 0
        at org.apache.johnzon.mapper.MappingParserImpl.toValue(MappingParserImpl.java:716)
        at org.apache.johnzon.mapper.MappingParserImpl.buildObject(MappingParserImpl.java:347)
        at org.apache.johnzon.mapper.MappingParserImpl.readObject(MappingParserImpl.java:150)
        at org.apache.johnzon.mapper.MappingParserImpl.readObject(MappingParserImpl.java:142)
        at org.apache.johnzon.mapper.MappingParserImpl.readObject(MappingParserImpl.java:129)
        at org.apache.johnzon.mapper.Mapper.mapObject(Mapper.java:310)
        at org.apache.johnzon.mapper.Mapper.readObject(Mapper.java:228)
        at org.apache.johnzon.jsonb.JohnzonJsonb.fromJson(JohnzonJsonb.java:194)
        ... 60 more
Caused by: java.time.format.DateTimeParseException: Text '20180910121456+0200' could not be parsed at index 0
        at java.time.format.DateTimeFormatter.parseResolved0(DateTimeFormatter.java:1949)
        at java.time.format.DateTimeFormatter.parse(DateTimeFormatter.java:1851)
        at java.time.LocalDateTime.parse(LocalDateTime.java:492)
        at java.time.LocalDateTime.parse(LocalDateTime.java:477)
        at org.apache.johnzon.jsonb.JohnzonBuilder$7.fromString(JohnzonBuilder.java:487)
        at org.apache.johnzon.jsonb.JohnzonBuilder$7.fromString(JohnzonBuilder.java:479)
        at org.apache.johnzon.mapper.internal.ConverterAdapter.to(ConverterAdapter.java:37)
        at org.apache.johnzon.mapper.internal.ConverterAdapter.to(ConverterAdapter.java:24)
        at org.apache.johnzon.mapper.MappingParserImpl.toValue(MappingParserImpl.java:710)
        ... 67 more

```