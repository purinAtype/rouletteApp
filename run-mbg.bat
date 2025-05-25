@echo off
set MBG_JAR=C:\dev\mbg\mybatis-generator-core-1.4.1.jar
set PG_JAR=C:\dev\mbg\postgresql-42.7.5.jar
set CONFIG=C:\Users\ka2mi\OneDrive\develop\source\Java\workspace\rouletteApp\src\main\resources\generatorConfig.xml

java -cp "%MBG_JAR%;%PG_JAR%" org.mybatis.generator.api.ShellRunner -configfile %CONFIG% -overwrite
pause
