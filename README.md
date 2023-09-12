# ELEA APx Backend
 
### Reglas de Calidad de código

#### Test unitarios 
##### Estructura

    void test_<metodo_a_probar>_<escenario>() { //ejemplo test_authenticate_ok
        //Preparar información
        
        //Configurar Mocks
        
        //Ejecutar logica de negocio
        
        //Validar resultados
        
        //Validar mocks 
    }

### Configuración de ambiente
#### Configuración de aplicación en Azure Active Directory

1. Ir al recurso de Azure Active Directory que se va a utilizar
2. En la página _"App registrations"_, seleccionar "New registration"
3. Cuando la página _"Register an application"_ aparezca, ingresar la información de registro de la aplicación:
   * En la sección _"Name"_, ingresar un nombre significativo a la aplicación, por ejemplo elea-active-directory-apx
   * En _"Supported account"_, elegir _"Accounts in any organizational directory"_.
   * Hacer click en _"Register"_ para crear la apliación.

4. En la página _"Overview"_, se encuentra el valor _Application (client) ID_, guardarlo ya que ser necesario luego para configurar la aplicación
5. En la lista de páginas de la aplicación, elegir _"Manifest":
   * En el editor del manifiesto, setear el valor de la property _allowPublicClient_ en true

   * Click en Save en la barra debajo del editor.

   
### Configuración de para Jasypt

#### Introducción:
Ayuda a cifrar propiedades de la aplicación,

Al descargar el código se deben crear las siguientes variables de entorne en tu sistema:

APX_AD_TENANTID = ENC(+GgcYCM8o1FH0qQF5rEDYIPVRXh5RkwP8GWnrmCeicBZdyix2ADqEvYn6cPpEtja)

APX_AZURE_APPLICATION_INSIGHTS_INSTRUMENTATION_KEY = ENC(ABMhf1JphzPQfQCgHS69NgBgXZJ9eJHQ6V+RuHOVXyvQ349Mx5b/6KgINrvc25GK)

APX_GEOCODE_APIS_DIRECCIONES_ENDPOINT = ENC(WIwuXEer5pEjBUpQ15raDX9dgsStJb4Q3cl2SYinkdoWzY4HrY4v94cGFOwEPL7ANawUm3XeAB77XYugA4vayWzOovIexl55TibRcm4zYLUcYml0Z8p5ec/0fT0EwR/SiMjWfbk0FhmNDNcwzgv9RUUWLH7DU0v8Kfm/I4UZAQs=)

APX_GEODATA_REPORTS_STORAGE_CONNECTIONSTRING = ENC(6tNnnfCsZWYxKdTcBS5D5UycnvekplXjq2WBfYupn7cyT/LupXfXSRB/gzGLfQaNG6Zb6/tizy9Rx6pTx7oweiQEfOmD5KadBC02A0s6298NEUKkVowXFGHOV4jcuIEWapDJgOhz0ajmemGVFgedJt+A9KCzhoyHeeX27gNRIWuhsIh2H+QXD1QBhVEp+GecindbRZkfh5kan4hW11325kyudIe9PSPEaCoOK/W+YaLxriTGJ6GphUGhcueui16R)

APX_GEODATA_REPORTS_STORAGE_CONTAINERNAME = ENC(I1RMqh+U/LZFROlTdDJNyA==)

APX_MAIL_HOST = ENC(RXu6sDulHl7JYmKhygmhbZuhHgtSTraqvwxrnO1XhO4=)

APX_MAIL_PASSWORD = ENC(3bfVSP7M2ViOguJmUtV3gVZd8FnBHizf)

APX_MAIL_PORT = ENC(O8Yhrp77sfp5HJYevZPOAw==)

APX_MAIL_USERNAME = ENC(rTLV7rUl6T2/SmnNptfwK8MyuB+7LlV6sWafegzQOhM=)

APX_SECURITY_AD_AUTHORITY = ENC(TVOfqTzyu8fnbRWCjcBdP8xuYbKOLZGOIazaaF25pJ0Jj1JCC74ROiI/6J7GGNJ6NXkfgsYo55el95evWOdHVizrosEiSkzE3lLA1Wz9Q8Q=)

APX_SECURITY_AD_CLIENTID = ENC(tdRiVqn+iH8du8SMuQhgJGXQs/Ym2i4+AvBTjwBdFv6HjteTwEtvq5Dic2MiRImQ)

APX_SECURITY_AD_CLIENTSECRET = ENC(evsU6NU+DMQBQm+GOhsKbJM/Lu2c0q1UmeZJv69E5r4T8rtpt3DcCEgTTC/ilXo3)

APX_SECURITY_AD_USERNAMESUFFIX = ENC(FObN1UhmjbKtNZfZIleaap10OHci+fAx)

APX_SECURITY_AD_SCOPE = ENC(dNRltQbREp/1zZaXnOkH/GZhrclZyLjGzk7pjRYYv08UXEP8xG9RoW3cv2hLunCU)

APX_SECURITY_SUPPORT_EMAIL = ENC(/TPpKD12+1NgopvYANN+6wJ6TbpyQtiicOvcxFT6TV208bRygZrbnQ==)

APX_SPRING_DATASOURCE_PASSWORD = ENC(2N53IAcLfVrdpETejUQmfxGxuSTMu59B)

APX_SPRING_DATASOURCE_URL = ENC(MsmarO0tfFnqkukpm3iYSxEn75+7D0u8QXxMDYWQH4BwcvoKBfsuTUYp+GrmDInxGEzCsPGuZiU4XK/PC7B8oY+QZPhab7MLVacDBLrCS1y/TeqOqC23+SeJfsC7ah8+)

APX_SPRING_DATASOURCE_USERNAME = ENC(sTKr6XaQaHyBtW0tAQYibFWXdbX3WRzE)

APX_AZURE_STORAGE_ACCOUNT_NAME = ENC(HSTugSro3Y+igIK8AJbiXQy22s5xAsGT)

APX_AZURE_STORAGE_ACCOUNT_KEY = ENC(jyzxm2bfMzg0ALHA5UB0dOehrmVdi4LugE3EsC7VUwg38sgqz3nryEHF5cCZtgX7u0txgQGSHVAkZ866Hj4I7ofuS0GRBVorsOVZTL6RV68uFDmlqS60TprhxvBRLN2RkVJh5dFA6cc=)

APX_REDIS_HOST = ENC(br03W8w/+4gewbyoBhicm90dWD3AGoz6Hjw72Qla2Sl69XU6uQl5/eMOK8LVlCe7)

APX_REDIS_PORT = ENC(bZWhK3mVyAya+dsuSmshUA==)

APX_REDIS_PASSWORD = ENC(0lFyGm4hsGoMzjoehXS1PfvTIiLcm+IgSeeUZPaHmp1eLKdrsaqw1QA9ksEZmr8aXbzBR7aQJj8=)

APX_REDIS_SSL = ENC(kV1oMYDac4pdHM+RcxxNqw==)

### Encryptor password
APX_JASYPT_ENCRYPTOR_PASSWORD = Se tiene en el canal de slack del pod

Si tienes windows deberían quedar de esta manera:

![Alt text](variables-entorno.png?raw=true "Variables de entorno en windows")

### Notes

1. Si deseas encriptar alguna variable recuerda siempre agregarla en este Readme.
   1. Recuerda que el tipo de cifrado es bidireccional (Two Way Encryption) debido a que hacemos uso de una clave secreta.
2. Si deseas desencriptar alguna variable usa el valor dentro del patrón ENC() y utiliza la clave secreta.
3. Puedes referirte a la página https://www.javainuse.com/jasypt para hacer los procesos de Encrypt/Decrypt.
