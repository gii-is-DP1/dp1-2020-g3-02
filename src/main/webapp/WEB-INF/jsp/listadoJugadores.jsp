<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">
    <h2>Jugadores</h2>

    <table id="jugadoresTable" class="table table-striped">
        <thead>
        <tr>	
		
            <th style="width: 150px;">Nombre</th>
            <th style="width: 150px;">dni</th>
            <th style="width: 200px;">direccion</th>
            <th>email</th>
            <th style="width: 120px">localidad</th>
            <th>altura</th>
            <th>peso</th>
            <th>fecha_nacimiento</th>
            <th>posicion_principal</th>
            <th>posicion_secundaria</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jugadores}" var="jugador">
            <tr>
                <td>
                    <spring:url value="/jugadores/{" var="jugadorUrl">
                        <spring:param name="jugadorId" value="${jugador.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(ownerUrl)}"><c:out value="${jugador.firstName} ${jugador.lastName}"/></a>
                </td>
                <td>
                    <c:out value="${jugador.dni}"/>
                </td>
                <td>
                    <c:out value="${jugador.direccion}"/>
                </td>
                  <td>
                    <c:out value="${jugador.email}"/>
                </td>
                <td>
                    <c:out value="${jugador.localidad}"/>
                </td>
                <td>
                    <c:out value="${jugador.altura}"/>
                </td>
                <td>
                    <c:out value="${jugador.peso}"/>
                </td>
                  <td>
                    <c:out value="${jugador.fechaNacimiento}"/>
                </td>
                <td>
                    <c:out value="${jugador.posicionPrincipal}"/>
                </td>
                <td>
                    <c:out value="${jugador.posicionSecundaria}"/>
                </td>
                
                
               
 
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
