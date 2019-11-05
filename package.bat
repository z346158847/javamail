REM 打包脚本
REM 2019-05-09
REM zhouzhec[zz.mark06@gmail.com]

REM 不加 call 会让 mvn 后面的语句无法执行
call mvn clean package "-DskipTests"
move target\javamail-0.0.1-SNAPSHOT.jar  mail.jar
pause