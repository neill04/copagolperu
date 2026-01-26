# CopaGol Peru - Backend API

[![Deploy on Railway](https://railway.app/button.svg)](https://api.copagolperu.com)
![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-316192?logo=postgresql&logoColor=white)
![Security](https://img.shields.io/badge/Security-JWT_OAuth2-red)

> **API RESTful escalable para la gestión digital de academias deportivas y próximamente para la organización de torneos.**
> **Base URL:** [https://api.copagolperu.com](https://api.copagolperu.com)

## Descripción del Proyecto

**CopaGol Peru** es una solución Backend diseñada para modernizar la gestión administrativa deportiva. El sistema centraliza la información de academias, automatiza la generación de planillas y expone servicios seguros para clientes Web y Móviles.

## Características Principales

* **Seguridad Robusta (IAM):** Autenticación vía JWT (JSON Web Tokens), protección contra ataques comunes y gestión de sesiones Stateless.
* **Reportes Automatizados:** Motor de generación de documentos Excel para planillas de juego oficiales.
* **Gestión de Staff:** Administración jerárquica de Entrenadores, Delegados y Administradores.
* **Arquitectura Modular:** Diseño desacoplado que facilita el mantenimiento y la escalabilidad de nuevos módulos (Fixture, Tabla de Posiciones, etc).
* **Cloud Native:** Contenerizado con Docker y orquestado para despliegue continuo.

## Stack Tecnológico

| Categoría | Tecnología | Uso |
| :--- | :--- | :--- |
| **Core** | Java 17, Spring Boot 3 | Lógica de negocio y API REST. |
| **Persistencia** | PostgreSQL, Hibernate/JPA | Gestión de datos relacionales. |
| **Seguridad** | Spring Security 6, JWT | Autenticación y Autorización. |
| **Docs** | OpenAPI 3 (Swagger) | Documentación viva de la API. |
| **DevOps** | Docker, Jenkins, Railway | CI/CD e Infraestructura. |
| **QA & Testing** | JUnit 5, Mockito, JMeter | Pruebas unitarias y de carga. |

---

## Módulos del Sistema

| Módulo | Descripción | Endpoint Base |
| :--- | :--- | :--- |
| **Auth & Usuarios** | Login, Registro y validación de tokens. | `/api/auth` |
| **Academias** | Gestión de instituciones (CRUD). | `/api/academias` |
| **Equipos** | Administración de plantillas y categorías. | `/api/academias/{id}/equipos` |
| **Jugadores** | Fichas técnicas, fotos y validación DNI. | `/api/.../jugadores` |
| **Ubicación** | Datos maestros (Dep/Prov/Dist). | `/api/ubicacion` |

---

## Ingeniería de Software & CI/CD

Este proyecto implementa un pipeline de **Integración y Entrega Continua (CI/CD)** avanzado, garantizando que solo código de calidad llegue a producción.

![Build Status](https://img.shields.io/badge/Jenkins-Build%20Passing-brightgreen?logo=jenkins) ![Quality Gate](https://img.shields.io/badge/SonarQube-Quality_Gate_Passed-4E9BCD?logo=sonarqube) ![Coverage](https://img.shields.io/badge/Coverage-High-success)

### 1. Calidad de Código (Static Analysis)
Integración con **SonarQube** para análisis estático.
* **Zero Vulnerabilities:** Escaneo de seguridad (SAST).
* **Code Smells:** Mantenimiento estricto de deuda técnica.
* **Quality Gate:** Configurado para detener el pipeline si la calidad desciende.

### 2. Seguridad Ofensiva (DAST)
Escaneo dinámico con **OWASP ZAP** (Zed Attack Proxy).
* Se validó la robustez de los endpoints contra inyecciones y accesos no autorizados.
* *Resultado:* Los endpoints protegidos rechazan correctamente peticiones maliciosas (HTTP 401/403).

### 3. Pruebas de Rendimiento
Pruebas de estrés realizadas con **Apache JMeter**.
* **Throughput:** ~10.6 req/sec bajo carga simulada.
* **Latencia Promedio:** 48ms.
* **Tasa de Error:** 0.00% (Estabilidad garantizada).

---

## Infraestructura y Despliegue

La aplicación opera bajo una arquitectura de contenedores, gestionada automáticamente por **Railway**.

* **Contenerización:** Imagen Docker basada en `openjdk:17-jdk-alpine` para minimizar la superficie de ataque y el tamaño.
* **Base de Datos:** PostgreSQL gestionada en la nube con conexión privada.
* **Despliegue Continuo:** Cada `push` a la rama `main` dispara la construcción y despliegue de una nueva versión sin tiempo de inactividad (Zero Downtime Deployment).

## Licencia y Créditos

Desarrollado por **NEOC Soft**.
* **Contacto:** olazabalneill2004@gmail.com
* **LinkedIn:** www.linkedin.com/in/neill-olazabal-chavez-689455305

---
© 2026 CopaGol Peru. Todos los derechos reservados.