#!/bin/sh

echo "⏳ Waiting for MySQL at mysql:3306..."

while ! nc -z mysql 3306; do
  echo "❌ MySQL not ready. Retrying in 2s..."
  sleep 2
done

echo "✅ MySQL is up — starting Spring Boot application!"
exec java -jar app.jar
