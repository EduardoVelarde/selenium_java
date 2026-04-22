# Selenium POM Framework (Java)

Framework de automatizacion web creado desde cero usando:

- Java 17
- Selenium WebDriver
- TestNG
- WebDriverManager
- POM (Page Object Model)
- Reporte HTML con ExtentReports (Spark)

## Requisitos previos

Para ejecutar este framework de forma estable:

- **JDK:** Java 11 o superior (recomendado 17 si tu equipo ya lo usa)
- **Maven:** **3.9.0 o superior** (recomendado 3.9.6+)

Puedes validar tu version instalada con:

```bash
mvn -version
```

> Nota: en este proyecto se uso Maven 3.9.10 en el entorno de desarrollo.

## Error comun: `invalid target release: 17`

Si al ejecutar `mvn clean test` ves este error, significa que Maven esta usando un JDK menor al requerido por el `pom.xml`.

Ejemplo real:

- Maven 3.9.15
- Java version 11.0.12
- Error: `invalid target release: 17`

Con esta actualizacion el proyecto queda compatible con **Java 11+**, por lo que ese error desaparece usando JDK 11 o superior.

Si quieres verificar rapidamente:

```bash
mvn -version
```

Revisa que `Java version` sea 11 o mayor y que `JAVA_HOME` apunte a ese JDK.

## Error comun: `package org.testng does not exist`

Si aparece durante `compile` y el error apunta a `src/main/java/.../listeners/TestListener.java`, el problema es de **scope de dependencias**:

- `TestListener` usa interfaces de TestNG (`ITestListener`, `ITestResult`, etc.)
- Si `testng` esta con `scope test`, Maven no la expone al classpath de `src/main/java`.

En este proyecto ya se corrigio dejando `testng` sin `scope test` en el `pom.xml`.

## Sitio de entrenamiento

Se utiliza la pagina de entrenamiento con login:

- https://the-internet.herokuapp.com/login

Credenciales validas usadas en los tests:

- username: `tomsmith`
- password: `SuperSecretPassword!`

## Estructura

```text
src/main/java/com/example/framework
  ├── core/DriverFactory.java
  ├── listeners/TestListener.java
  ├── pages/BasePage.java
  ├── pages/LoginPage.java
  └── utils/{ConfigReader,ReportManager}.java

src/test/java/com/example/framework/tests
  ├── BaseTest.java
  └── LoginTests.java
```

## Ejecutar pruebas

```bash
mvn clean test
```

Opciones:

```bash
mvn clean test -Dbrowser=firefox -Dheadless=false
```

## Reporte HTML

Al finalizar la ejecucion, el reporte se genera en:

- `reports/extent-report.html`
