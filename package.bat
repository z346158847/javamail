REM ����ű�
REM 2019-05-09
REM zhouzhec[zz.mark06@gmail.com]

REM ���� call ���� mvn ���������޷�ִ��
call mvn clean package "-DskipTests"
move target\javamail-0.0.1-SNAPSHOT.jar  mail.jar
pause