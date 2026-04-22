# Selenium POM Framework (Java)

Framework de automatizacion web creado desde cero usando:

- Java 17
- Selenium WebDriver
- TestNG
- WebDriverManager
- POM (Page Object Model)
- Reporte HTML con ExtentReports (Spark)

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
