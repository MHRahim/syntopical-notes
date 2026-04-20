r:
	./gradlew bootRun

b:
	./gradlew build

s:
	./gradlew seed --args="UserSeeder AuthorSeeder BookSeeder"

rs:
	./gradlew dbDropAll
	./gradlew dbUpdate
	make s
