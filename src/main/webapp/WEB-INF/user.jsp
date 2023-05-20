<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ru">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Здоровье++</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
  
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="assets/vendor/animate.css/animate.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

  <!-- ========================================================  -->
</head>

<body>
		    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>


	<!-- ======= Header ======= -->
	<header id="header" class="fixed-top">
		<div class="container d-flex align-items-center">

			<h1 class="logo me-auto"><a href="/">Здоровье++</a></h1>

			<nav id="navbar" class="navbar order-last order-lg-0">
				<ul>
					<li><a class="nav-link scrollto active" href="/">Главная</a></li>
					<li><a class="nav-link scrollto" href="/userAppo">Записи</a></li>
					<c:choose>
						<c:when test="${sessionScope.user_id != null}">
							<li><a class="nav-link scrollto" href="/logout">Выйти</a></li>

						</c:when>
						<c:otherwise>
							<li><a class="nav-link scrollto" href="/index">Войти</a></li>
						</c:otherwise>
					</c:choose>

					<li></li>
				</ul>
				<i class="bi bi-list mobile-nav-toggle"></i>
			</nav><!-- .navbar -->

			<a href="/index" class="appointment-btn scrollto"><span class="d-none d-md-inline">Записаться</span></a>

		</div>

	</header><!-- End Header -->
  
  
  <!-- content -->
	<div class="container" style="margin:100px;">
	<h1>
		<c:out value="${userid.username}" />
	</h1>
	<table class="table table-striped table-inverse table-responsive">
    <thead class="thead-inverse">
			<tr>
				<th>ID</th>
				<th>Дата записи</th>
				<th>Имя врача</th>
				<th>Пациент</th>
				<th>Действия</th>
			</tr>
			 </thead>
       		 <tbody>
			<c:forEach items="${all}" var="v">
			<c:choose>
				<c:when test="${v.user.getId() == userid.id}">

			<tr>
				<td scope="row"><c:out value="${v.id}" /></td>
				<td scope="row"><c:out value="${v.date}" /></td>
				<td scope="row"><c:out value="${v.doctor.name}" /></td>
				<td scope="row"><c:out value="${v.user.username}" /></td>
				<td scope="row"><a href="/delete/${v.id}">Удалить</a></td>
			</tr>
					
				</c:when>
				<c:otherwise>
					
				</c:otherwise>
			</c:choose>
			</c:forEach>
			</tbody>
			</table>
</div>

</body>
</html>