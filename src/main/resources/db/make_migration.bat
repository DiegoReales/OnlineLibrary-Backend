@echo off
setlocal enabledelayedexpansion

set "timestamp=%date:~-4%%date:~-7,2%%date:~-10,2%%time:~0,2%%time:~3,2%%time:~6,2%"

set /p migration_name=Introduce una descripcion significativa:

rem Reemplazar espacios por guiones bajos (_)
set "migration_name=!migration_name: =_!"

set "migration_directory=migrations"
if not exist "!migration_directory!" mkdir "!migration_directory!"

set "migration_file=!migration_directory!\!timestamp!__!migration_name!.sql"

echo -- DESCRIPTION:    !migration_name! > "!migration_file!"
echo -- CREATED:        %DATE% %TIME:~,-3% >> "!migration_file!"
echo -- AUTHOR:         %USERNAME% >> "!migration_file!"

echo Archivo de migracion creado: !migration_file!
