## Starter para proyecto en Django

## Como crear y actualizar modelos

- Cambia tus modelos en models.py

- Ejecuta `python manage.py makemigrations` para crear migraciones para esos cambios

- Ejecuta `python manage.py migrate` para aplicar esos cambios a la base de datos

## Comandos utiles

#### Relacionados con el proyecto

Crear un nuevo proyecto
`django-admin startproject name folder`

Para crear una nueva app se ejecuta
`python manage.py startapp appnombre`

Para ejecutar el servidor de desarrollo se corre:
`python manage.py runserver`

#### Obtener el sql que genera las tablas

`python manage.py sqlmigrate polls 0001`

> 0001 se debe cambiar por el numero de version que se quiera obtener eso aparece en app/migrations/0001_initial.py
