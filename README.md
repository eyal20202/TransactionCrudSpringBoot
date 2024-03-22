# TransactionCrudSpringBoot

run on docker:

docker build -t transaction .

docker run -p 9090:8080 transaction

## 1.	קבלת כל התנועות
      http://localhost:9090/
## 2.	קבלת כל התנועות מסוג
###      a.	 זכות
      http://localhost:9090/getPositiveAmount
###      b.	חובה
      http://localhost:9090/getNegativeAmount


## 3.	קבלת תנועות מתאריך עד תאריך
      http://localhost:9090/searchDate?fromDate=28.2.2022&untilDate=29.3.2024

## 4.	קבלת תנועות מסכום מסוים
      http://localhost:9090/getFromAmount?number=3001.5

## 5.	סיכום כל התנועות – קבלת מצב חשבון (בהנחה שמצב חשבון מתחיל ב0)
      http://localhost:8080/sumAmounts
