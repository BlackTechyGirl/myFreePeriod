<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menstrual Cycle Tracker</title>
    <!-- Add any necessary CSS or JavaScript files -->
</head>
<body>
<h1>Menstrual Cycle Tracker</h1>
<form action="/cycle" method="post" modelAttribute="cycle">
    <label for="cycleLength">Cycle Length:</label>
    <input type="number" id="cycleLength" name="cycleLength" required><br><br>

    <label for="lastPeriodDate">Last Period Date:</label>
    <input type="date" id="lastPeriodDate" name="lastPeriodDate" required><br><br>

    <label for="flowLength">Flow Length:</label>
    <input type="number" id="flowLength" name="flowLength" required><br><br>

    <input type="submit" value="Submit">
</form>
</body>
</html>
