SRC = $(wildcard *.java)

all: build

build: ${SRC}
	javac -Xlint:deprecation ${SRC}
	jar cvf ./DataGenerator.jar ./*.class
clean:
	rm -f *.class *.jar
