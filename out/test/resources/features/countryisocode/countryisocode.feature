Feature: Codigo ISO de paises
  Como usuario de la plataforma
  necesito validar que la funcionalidad de encontar codigo ISO de un pais este trabajando correctamente
  para asegurar que todos los paises puedan ser encontrados

  Background:
    Given que el usuario de la plataforma consulte el codigo ISO

  Scenario: Nombre del codigo ISO de un Pais de forma correcta
    When que el usuario ingrese el nombre correctamente del pais "Colombia"
    Then el usuario debera ver como resultado el codigo ISO solicitado "CO"

  Scenario: Nombre del codigo ISO de un Pais de forma incorrecta
    When que el usuario ingrese el nombre incorrectamente del pais "Colobia"
    Then el usuario debera ver como resultado "No country found by that name"


