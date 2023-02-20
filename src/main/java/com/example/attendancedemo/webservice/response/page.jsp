<html>
<head>
    <meta charset="utf-8">
    <title>时间</title>

    <script>
        getDates1();
        function getDates1(){
            //设置当前时间
            var date = new Date();
            var year = date.getFullYear();//月份从0~11，所以加一
            var dateArr = [date.getMonth() + 1,date.getDate(),date.getHours(),date.getMinutes(),date.getSeconds()];
            for(var i=0;i<dateArr.length;i++){
                if (dateArr[i] >= 1 && dateArr[i] <= 9) {
                    dateArr[i] = "0" + dateArr[i];
                }
            }
            var strDate = year+'-'+dateArr[0]+'-'+dateArr[1]+' '+dateArr[2]+':'+dateArr[3]+':'+dateArr[4];
            document.getElementById("dates1").innerHTML = strDate;

        }
    </script>
    <script>
        getDates2();
        function getDates2(){
            //设置当前时间
            var date = new Date();
            var year = date.getFullYear();//月份从0~11，所以加一
            var dateArr = [date.getMonth() + 1,date.getDate(),date.getHours(),date.getMinutes(),date.getSeconds()];
            for(var i=0;i<dateArr.length;i++){
                if (dateArr[i] >= 1 && dateArr[i] <= 9) {
                    dateArr[i] = "0" + dateArr[i];
                }
            }
            var strDate = year+'-'+dateArr[0]+'-'+dateArr[1]+' '+dateArr[2]+':'+dateArr[3]+':'+dateArr[4];
            document.getElementById("dates2").innerHTML = strDate;
        }
    </script>

</head>
<body>

<form id="MarkTime" method="get" action="http://localhost:8080/marktime" name="time" value="dates1">
    <h1>上班打卡时间</h1>
    <p id="dates1" ></p>
    <button type="button" onclick="getDates1()" type="submit" >出勤打卡</button>
</form>


</body>
</html>