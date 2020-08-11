#elesawm
#314825191

compile: bin
	javac -cp biuoop-1.4.jar:src -d bin src/*/*.java src/Ass6Game.java
run:	
	java -cp biuoop-1.4.jar:bin Ass6Game 1 2 3 4

jar:
	jar cvfm ass6game.jar MANIFEST.MF -C bin .

bin:
	mkdir bin	